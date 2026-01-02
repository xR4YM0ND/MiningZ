package net.miningz.item;

import net.fabricmc.fabric.api.tag.convention.v1.ConventionalBlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.registry.Registries;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.miningz.access.ClientPlayerEntityAccess;
import net.miningz.init.TagInit;

import java.util.ArrayList;
import java.util.List;

public class ProspectorPick extends Item {

    private final int reach;

    public ProspectorPick(int reach, Settings settings) {
        super(settings);
        this.reach = reach;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();

        List<Identifier> blockIds = new ArrayList<>();
        Direction direction = context.getSide().getOpposite();
        for (int k = 0; k <= reach; k++) {
            for (int i = -1; i <= 1; i++) {
                for (int u = -1; u <= 1; u++) {
                    BlockPos checkPos = switch (direction) {
                        case NORTH, SOUTH -> blockPos.offset(direction, k).add(i, u, 0);
                        case EAST, WEST -> blockPos.offset(direction, k).add(0, u, i);
                        case UP, DOWN -> blockPos.offset(direction, k).add(i, 0, u);
                    };

                    if (world.getBlockState(checkPos).isIn(ConventionalBlockTags.ORES) ||
                            world.getBlockState(checkPos).isIn(TagInit.PROSPECTOR_BLOCKS)) {

                        Identifier id = Registries.BLOCK.getId(world.getBlockState(checkPos).getBlock());
                        if (!blockIds.contains(id)) {
                            blockIds.add(id);
                        }
                    }
                }
            }
        }
        if (world.isClient() && context.getPlayer() instanceof ClientPlayerEntity clientPlayerEntity) {
            ((ClientPlayerEntityAccess) clientPlayerEntity).miningZ$setProspectorBlocks(blockIds);
            BlockSoundGroup blockSoundGroup = context.getWorld().getBlockState(blockPos).getSoundGroup();

            context.getWorld().playSound(clientPlayerEntity, blockPos, blockSoundGroup.getHitSound(), SoundCategory.BLOCKS, (blockSoundGroup.getVolume() + 1.0F) / 6.0F,
                    blockSoundGroup.getPitch() * 0.5F);
        }
        if (!world.isClient()) {
            context.getStack().damage(1, context.getPlayer(), EquipmentSlot.MAINHAND);
        }
        return ActionResult.success(world.isClient());
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.damage(1, attacker, EquipmentSlot.MAINHAND);
        return true;
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        if ((double) state.getHardness(world, pos) != 0.0) {
            stack.damage(1, miner, EquipmentSlot.MAINHAND);
        }
        return true;
    }

}
