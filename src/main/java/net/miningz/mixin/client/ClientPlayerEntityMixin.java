package net.miningz.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.Identifier;
import net.miningz.access.ClientPlayerEntityAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Environment(EnvType.CLIENT)
@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin implements ClientPlayerEntityAccess {

    @Unique
    private List<Identifier> blockIds = new ArrayList<>();
    @Unique
    private int prospectorTick;

    @Inject(method = "tick", at = @At("TAIL"))
    public void tickMixin(CallbackInfo info) {
        if (this.prospectorTick > 0) {
            this.prospectorTick--;
            if (this.prospectorTick <= 0) {
                this.blockIds.clear();
            }
        }
    }

    @Override
    public void miningZ$setProspectorBlocks(List<Identifier> blockIds) {
        this.blockIds = blockIds;
        this.prospectorTick = 100;
    }

    @Override
    public List<Identifier> miningZ$getProspectorBlocks() {
        return this.blockIds;
    }
}
