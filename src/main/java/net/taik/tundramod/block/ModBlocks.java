package net.taik.tundramod.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.taik.tundramod.TundraMod;
import net.taik.tundramod.block.custom.WildBushBlock;
import net.taik.tundramod.block.custom.tundraSoil;

import java.util.function.Supplier;

import static net.minecraft.world.level.block.Blocks.register;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TundraMod.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TundraMod.MOD_ID);

    public static final RegistryObject<Block> TUNDRA_SOIL = registerBlock("tundra_soil",
            () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.DIRT)
                    .strength(0.6f)
                    .sound(SoundType.GRAVEL)));

    public static final RegistryObject<Block> TUNDRA_SOIL_ORANGE = registerBlock("tundra_soil_orange",
            () -> new GrassBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_ORANGE)
                    .strength(0.6f)
                    .randomTicks()
                    .sound(SoundType.GRAVEL)));

    public static final RegistryObject<Block> TUNDRA_SOIL_CRIMSON = registerBlock("tundra_soil_crimson",
            () -> new tundraSoil(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_RED)
                    .strength(0.6F)
                    .randomTicks()
                    .sound(SoundType.GRASS)));

    public static final RegistryObject<Block> TUNDRA_SOIL_BROWN = registerBlock("tundra_soil_brown",
            () -> new GrassBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BROWN)
                    .strength(0.6f)
                    .randomTicks()
                    .sound(SoundType.GRASS)));

    public static final RegistryObject<Block> WILD_BUSH = registerBlock("wild_bush",
            () -> new WildBushBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .noCollission()
                    .instabreak()
                    .sound(SoundType.GRASS)
                    .offsetType(BlockBehaviour.OffsetType.XZ)));

    private static RegistryObject<Block> registerBlock(String name, Supplier<Block> block) {
        RegistryObject<Block> registeredBlock = BLOCKS.register(name, block);
        ITEMS.register(name, () -> new BlockItem(registeredBlock.get(), new Item.Properties()));
        return registeredBlock;
    }
}
