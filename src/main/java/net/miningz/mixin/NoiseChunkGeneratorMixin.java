package net.miningz.mixin;

import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;
import net.minecraft.world.gen.chunk.NoiseChunkGenerator;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import net.miningz.init.RuleInit;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(NoiseChunkGenerator.class)
public class NoiseChunkGeneratorMixin {

    @Shadow
    @Mutable
    @Final
    private RegistryEntry<ChunkGeneratorSettings> settings;

    @ModifyArg(method = "buildSurface(Lnet/minecraft/world/chunk/Chunk;Lnet/minecraft/world/gen/HeightContext;Lnet/minecraft/world/gen/noise/NoiseConfig;Lnet/minecraft/world/gen/StructureAccessor;Lnet/minecraft/world/biome/source/BiomeAccess;Lnet/minecraft/registry/Registry;Lnet/minecraft/world/gen/chunk/Blender;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/surfacebuilder/SurfaceBuilder;buildSurface(Lnet/minecraft/world/gen/noise/NoiseConfig;Lnet/minecraft/world/biome/source/BiomeAccess;Lnet/minecraft/registry/Registry;ZLnet/minecraft/world/gen/HeightContext;Lnet/minecraft/world/chunk/Chunk;Lnet/minecraft/world/gen/chunk/ChunkNoiseSampler;Lnet/minecraft/world/gen/surfacebuilder/MaterialRules$MaterialRule;)V"), index = 7)
    private MaterialRules.MaterialRule buildSurfaceMixin(MaterialRules.MaterialRule materialRule) {
        if (this.settings.matchesKey(ChunkGeneratorSettings.OVERWORLD)) {
            MaterialRules.MaterialRule customRules = RuleInit.createMiningRules();
            return MaterialRules.sequence(customRules, materialRule);
        }
        return materialRule;
    }

}
