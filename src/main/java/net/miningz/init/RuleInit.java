package net.miningz.init;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.noise.NoiseParametersKeys;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;

public class RuleInit {

    private static final MaterialRules.MaterialRule GRANITE = MaterialRules.block(Blocks.GRANITE.getDefaultState());
    private static final MaterialRules.MaterialRule ANDESITE = MaterialRules.block(Blocks.ANDESITE.getDefaultState());
    private static final MaterialRules.MaterialRule TUFF = MaterialRules.block(Blocks.TUFF.getDefaultState());
    private static final MaterialRules.MaterialRule CALCITE = MaterialRules.block(Blocks.CALCITE.getDefaultState());
    private static final MaterialRules.MaterialRule DIORITE = MaterialRules.block(Blocks.DIORITE.getDefaultState());
    private static final MaterialRules.MaterialRule BLACKSTONE = MaterialRules.block(Blocks.BLACKSTONE.getDefaultState());
    private static final MaterialRules.MaterialRule SMOOTH_BASALT = MaterialRules.block(Blocks.SMOOTH_BASALT.getDefaultState());

    private static final boolean isMeadowLoaded = FabricLoader.getInstance().isModLoaded("meadow");
    private static final boolean isNaturesSpiritLoaded = FabricLoader.getInstance().isModLoaded("natures_spirit");
    private static final boolean isAlpineWhispersLoaded = FabricLoader.getInstance().isModLoaded("alpinewhispers");
    private static final boolean isHearthAndTimberLoaded = FabricLoader.getInstance().isModLoaded("hearth_and_timber");
    private static final boolean isLarionLoaded = FabricLoader.getInstance().isModLoaded("larion");

    public static void init() {

    }

    public static MaterialRules.MaterialRule createMiningRules() {

        MaterialRules.MaterialCondition highNoise = MaterialRules.noiseThreshold(NoiseParametersKeys.CALCITE, 0.2, 0.4);

        if (isMeadowLoaded && isNaturesSpiritLoaded && isAlpineWhispersLoaded  && isHearthAndTimberLoaded && isLarionLoaded) {

            MaterialRules.MaterialCondition lowNoise = MaterialRules.noiseThreshold(NoiseParametersKeys.CALCITE, 0.15, 0.3);

            MaterialRules.MaterialRule sequence = MaterialRules.sequence(

                    MaterialRules.condition(
                            MaterialRules.aboveY(YOffset.fixed(48), 1),
                            MaterialRules.condition(
                                    MaterialRules.not(MaterialRules.aboveY(YOffset.fixed(56), 1)),
                                    ANDESITE
                            )
                    ),

                    MaterialRules.condition(
                            MaterialRules.aboveY(YOffset.fixed(39), 1),
                            MaterialRules.condition(
                                    MaterialRules.not(MaterialRules.aboveY(YOffset.fixed(48), 1)),
                                    MaterialRules.sequence(
                                            MaterialRules.condition(highNoise, CALCITE),
                                            DIORITE
                                    )
                            )
                    ),

                    MaterialRules.condition(
                            MaterialRules.aboveY(YOffset.fixed(29), 1),
                            MaterialRules.condition(
                                    MaterialRules.not(MaterialRules.aboveY(YOffset.fixed(39), 1)),
                                    MaterialRules.block(Registries.BLOCK.get(Identifier.of("meadow", "limestone")).getDefaultState())
                            )
                    ),

                    MaterialRules.condition(
                            MaterialRules.aboveY(YOffset.fixed(6), 1),
                            MaterialRules.condition(
                                    MaterialRules.not(MaterialRules.aboveY(YOffset.fixed(29), 1)),
                                    MaterialRules.sequence(
                                            MaterialRules.condition(lowNoise, MaterialRules.block(Registries.BLOCK.get(Identifier.of("natures_spirit", "travertine")).getDefaultState())),
                                            MaterialRules.block(Registries.BLOCK.get(Identifier.of("alpinewhispers", "alpine_gneiss")).getDefaultState())
                                    )
                            )
                    ),

                    MaterialRules.condition(
                            MaterialRules.aboveY(YOffset.fixed(-2), 1),
                            MaterialRules.condition(
                                    MaterialRules.not(MaterialRules.aboveY(YOffset.fixed(6), 1)),
                                    GRANITE
                            )
                    ),

                    MaterialRules.condition(
                            MaterialRules.aboveY(YOffset.fixed(-23), 1),
                            MaterialRules.condition(
                                    MaterialRules.not(MaterialRules.aboveY(YOffset.fixed(-2), 1)),
                                    TUFF
                            )
                    ),

                    MaterialRules.condition(
                            MaterialRules.aboveY(YOffset.fixed(-40), 1),
                            MaterialRules.condition(
                                    MaterialRules.not(MaterialRules.aboveY(YOffset.fixed(-23), 1)),
                                    MaterialRules.sequence(
                                        MaterialRules.condition(lowNoise, SMOOTH_BASALT),
                                        MaterialRules.block(Registries.BLOCK.get(Identifier.of("hearth_and_timber", "groutless_rubblestone")).getDefaultState())
                                    )
                            )
                    ),

                    MaterialRules.condition(
                            MaterialRules.aboveY(YOffset.fixed(-63), 1),
                            MaterialRules.condition(
                                    MaterialRules.not(MaterialRules.aboveY(YOffset.fixed(-54), 1)),
                                    BLACKSTONE
                            )
                    )
            );

            return sequence;
        } else {
            MaterialRules.MaterialRule sequence = MaterialRules.sequence(

                    MaterialRules.condition(
                            MaterialRules.aboveY(YOffset.fixed(27), 1),
                            MaterialRules.condition(
                                    MaterialRules.not(MaterialRules.aboveY(YOffset.fixed(41), 1)),
                                    ANDESITE
                            )
                    ),

                    MaterialRules.condition(
                            MaterialRules.aboveY(YOffset.fixed(18), 1),
                            MaterialRules.condition(
                                    MaterialRules.not(MaterialRules.aboveY(YOffset.fixed(27), 1)),
                                    MaterialRules.sequence(
                                            MaterialRules.condition(highNoise, CALCITE),
                                            DIORITE
                                    )
                            )
                    ),

                    MaterialRules.condition(
                            MaterialRules.aboveY(YOffset.fixed(4), 1),
                            MaterialRules.condition(
                                    MaterialRules.not(MaterialRules.aboveY(YOffset.fixed(18), 1)),
                                    GRANITE
                            )
                    ),

                    MaterialRules.condition(
                            MaterialRules.aboveY(YOffset.fixed(-14), 1),
                            MaterialRules.condition(
                                    MaterialRules.not(MaterialRules.aboveY(YOffset.fixed(4), 1)),
                                    TUFF
                            )
                    ),

                    MaterialRules.condition(
                            MaterialRules.aboveY(YOffset.fixed(-31), 1),
                            MaterialRules.condition(
                                    MaterialRules.not(MaterialRules.aboveY(YOffset.fixed(-14), 1)),
                                    SMOOTH_BASALT
                            )
                    ),

                    MaterialRules.condition(
                            MaterialRules.aboveY(YOffset.fixed(-63), 1),
                            MaterialRules.condition(
                                    MaterialRules.not(MaterialRules.aboveY(YOffset.fixed(-54), 1)),
                                    BLACKSTONE
                            )
                    )
            );
            return sequence;
        }

    }
}
