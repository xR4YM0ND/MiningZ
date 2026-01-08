package net.miningz.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.miningz.util.MiningHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class ItemMixin {

    @Inject(method = "postMine", at = @At("HEAD"), cancellable = true)
    private void postMineMixin(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner, CallbackInfoReturnable<Boolean> info) {
        if (!world.isClient() && !MiningHelper.hasRequiredItemLevel(stack, state)) {
            info.setReturnValue(false);
        }
    }

    @Inject(method = "isCorrectForDrops", at = @At("HEAD"), cancellable = true)
    private void isCorrectForDropsMixin(ItemStack stack, BlockState state, CallbackInfoReturnable<Boolean> info) {
        if (MiningHelper.hasRequirement(state)) {
            if (MiningHelper.hasRequiredItemLevel(stack, state)) {
                info.setReturnValue(true);
            } else {
                info.setReturnValue(false);
            }
        }
    }

    // not sure about this yet
//    @Inject(method = "getMiningSpeed", at = @At("HEAD"))
//    private void getMiningSpeedMixin(ItemStack stack, BlockState state, CallbackInfoReturnable<Float> info) {
//    }
}
