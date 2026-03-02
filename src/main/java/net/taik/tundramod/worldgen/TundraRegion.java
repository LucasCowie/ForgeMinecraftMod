package net.taik.tundramod.worldgen;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

public class TundraRegion extends Region {
    public TundraRegion(ResourceLocation name, int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        // Keep all vanilla biomes as the base
        this.addModifiedVanillaOverworldBiomes(mapper, builder -> {});

        // Add tundra at specific parameter points for flat, near-sea-level terrain
        // Continentalness: near-inland to inland (stays close to sea level, not ocean or deep mountains)
        // Erosion: high values = flat terrain with gentle rolling hills (~10-20 blocks max)
        Climate.Parameter coldTemp = Climate.Parameter.span(-1.0f, -0.45f);
        Climate.Parameter anyHumidity = Climate.Parameter.span(-1.0f, 1.0f);
        Climate.Parameter nearSeaLevel = Climate.Parameter.span(-0.11f, 0.55f);
        Climate.Parameter flatRolling = Climate.Parameter.span(0.25f, 1.0f);
        Climate.Parameter surface = Climate.Parameter.point(0.0f);
        Climate.Parameter anyWeirdness = Climate.Parameter.span(-1.0f, 1.0f);

        this.addBiome(mapper,
                new Climate.ParameterPoint(coldTemp, anyHumidity, nearSeaLevel, flatRolling, surface, anyWeirdness, 0L),
                ModBiomes.TUNDRA
        );
    }
}
