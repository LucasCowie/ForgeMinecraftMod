package net.taik.tundramod.worldgen;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.taik.tundramod.block.ModBlocks;

public class ModSurfaceRules {

    // Surface blocks
    private static final SurfaceRules.RuleSource SNOW_BLOCK = makeStateRule(Blocks.SNOW_BLOCK);
    private static final SurfaceRules.RuleSource COARSE_DIRT = makeStateRule(Blocks.COARSE_DIRT);
    private static final SurfaceRules.RuleSource PODZOL = makeStateRule(Blocks.PODZOL);
    //unused
    private static final SurfaceRules.RuleSource GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);
    private static final SurfaceRules.RuleSource DIRT = makeStateRule(Blocks.DIRT);
    private static final SurfaceRules.RuleSource MUD = makeStateRule(Blocks.MUD);

    public static SurfaceRules.RuleSource makeRules() {
        // Custom tundra soil blocks - resolved here to ensure blocks are registered
        SurfaceRules.RuleSource tundraMossOrange = SurfaceRules.state(ModBlocks.TUNDRA_MOSS_ORANGE.get().defaultBlockState());
        SurfaceRules.RuleSource tundraMossCrimson = SurfaceRules.state(ModBlocks.TUNDRA_MOSS_CRIMSON.get().defaultBlockState());
        SurfaceRules.RuleSource tundraMossBrown = SurfaceRules.state(ModBlocks.TUNDRA_MOSS_BROWN.get().defaultBlockState());
        SurfaceRules.RuleSource tundraSoil = SurfaceRules.state(ModBlocks.TUNDRA_SOIL.get().defaultBlockState());
        //block var
        SurfaceRules.RuleSource tundraPodzel = SurfaceRules.state(ModBlocks.TUNDRA_PODZOL.get().defaultBlockState());
        //water level
        SurfaceRules.ConditionSource isAtOrAboveWaterLevel = SurfaceRules.waterBlockCheck(-1, 0);

        // Noise-based patchy surface: each noise range picks a different block
        SurfaceRules.RuleSource patchySurface = SurfaceRules.sequence(
                // Snow patches (~20% of surface)
                SurfaceRules.ifTrue(
                        SurfaceRules.noiseCondition(Noises.POWDER_SNOW, 0.35, 0.6),
                        SNOW_BLOCK),
                // Orange tundra soil patches (~20%)
                SurfaceRules.ifTrue(
                        SurfaceRules.noiseCondition(Noises.SURFACE, -0.6, -0.2),
                        tundraMossOrange),
                // Red tundra soil patches (~15%)
                SurfaceRules.ifTrue(
                        SurfaceRules.noiseCondition(Noises.PACKED_ICE, 0.3, 0.6),
                        tundraMossCrimson),
                // Brown tundra soil patches (~10%)
                SurfaceRules.ifTrue(
                        SurfaceRules.noiseCondition(Noises.SURFACE, 0.4, 0.7),
                        tundraMossBrown),
                // Podzol patches - dark earthy ground (~15%)
                SurfaceRules.ifTrue(
                        SurfaceRules.noiseCondition(Noises.PACKED_ICE, -0.5, -0.2),
                        tundraPodzel),
                // Default: coarse dirt base
                COARSE_DIRT
        );

        // Structure matches TerraBlender's example: sequence of biome-checked rules
        // abovePreliminarySurface ensures rules only apply above deepslate level
        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.abovePreliminarySurface(),
                        SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.TUNDRA, ModBiomes.TUNDRA_HILLS),
                                SurfaceRules.sequence(
                                        SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                                                SurfaceRules.ifTrue(isAtOrAboveWaterLevel, patchySurface)),
                                        SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, tundraSoil)
                                )
                        )
                )
        );
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }
}
