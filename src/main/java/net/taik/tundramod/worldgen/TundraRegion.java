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

        //cold to hot: -1.0=freezing,0=temperate, 1.0=hot
        Climate.Parameter coldTemp = Climate.Parameter.span(-1.0f, -0.4f);
        //dry to wet: So tundra could be a dry tundra or a wet tundra
        Climate.Parameter anyHumidity = Climate.Parameter.span(-1.0f, 1.0f);
        //ocean vs inland: -1=deep ocean,-0.3=coast,0=inland,1=far inland
        Climate.Parameter nearSeaLevel = Climate.Parameter.span(-0.3f, 0.8f);
        //mountains to flatland: -1=jagged mountains,0=hills, 1=flat plains
        Climate.Parameter flat = Climate.Parameter.span(0.35f, 1.0f);
        Climate.Parameter hills = Climate.Parameter.span(-0.3f, 0.35f);
        //undground vs surface: 0=surface
        Climate.Parameter surface = Climate.Parameter.point(0.0f);
        // biome variation, Weirdness controls biome variants.
        Climate.Parameter anyWeirdness = Climate.Parameter.span(-1.0f, 1.0f);

        this.addBiome(mapper,
                new Climate.ParameterPoint(
                        coldTemp,
                        anyHumidity,
                        nearSeaLevel,
                        flat,
                        surface,
                        anyWeirdness,
                        0L),
                ModBiomes.TUNDRA
        );
        this.addBiome(mapper,
                new Climate.ParameterPoint(
                        coldTemp,
                        anyHumidity,
                        nearSeaLevel,
                        hills,
                        surface,
                        anyWeirdness,
                        0L),
                ModBiomes.TUNDRA_HILLS
        );
    }
}
