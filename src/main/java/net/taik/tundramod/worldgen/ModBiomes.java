package net.taik.tundramod.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.taik.tundramod.TundraMod;

import java.util.Objects;

public class ModBiomes {
    public static final ResourceKey<Biome> TUNDRA = register("tundra");
    public static final ResourceKey<Biome> TUNDRA_HILLS = register("tundra_hills");


    private static ResourceKey<Biome> register(String name) {
        return ResourceKey.create(
                Registries.BIOME,
                Objects.requireNonNull(
                        ResourceLocation.tryBuild(TundraMod.MOD_ID, name)));
    }
}
