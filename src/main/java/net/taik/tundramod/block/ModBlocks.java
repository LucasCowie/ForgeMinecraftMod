package net.taik.tundramod.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
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
    //tundra soils
    public static final RegistryObject<Block> TUNDRA_SOIL = registerBlock("tundra_soil",
            () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.DIRT)
                    .strength(0.6f)
                    .sound(SoundType.GRAVEL)));

    public static final RegistryObject<Block> TUNDRA_MOSS_ORANGE = registerBlock("tundra_moss_orange",
            () -> new tundraSoil(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_ORANGE)
                    .strength(0.6f)
                    .randomTicks()
                    .sound(SoundType.GRAVEL)));

    public static final RegistryObject<Block> TUNDRA_MOSS_CRIMSON = registerBlock("tundra_moss_crimson",
            () -> new tundraSoil(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_RED)
                    .strength(0.6F)
                    .randomTicks()
                    .sound(SoundType.GRASS)));

    public static final RegistryObject<Block> TUNDRA_MOSS_BROWN = registerBlock("tundra_moss_brown",
            () -> new tundraSoil(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BROWN)
                    .strength(0.6f)
                    .randomTicks()
                    .sound(SoundType.GRASS)));
    //tundra soil var
    public static final RegistryObject<Block> TUNDRA_PODZOL = registerBlock("tundra_podzol",
            () -> new tundraSoil(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BROWN)
                    .strength(0.6f)
                    .randomTicks()
                    .sound(SoundType.GRASS)));
    //mossy colors based on tundra soil moss
    public static final RegistryObject<Block> CRIMSON_MOSSY_COBBLESTONE = registerBlock("crimson_mossy_cobblestone",
            () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(2.0F, 6.0F)));
    public static final RegistryObject<Block> ORANGE_MOSSY_COBBLESTONE = registerBlock("orange_mossy_cobblestone",
            () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(2.0F, 6.0F)));
    public static final RegistryObject<Block> BROWN_MOSSY_COBBLESTONE = registerBlock("brown_mossy_cobblestone",
            () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(2.0F, 6.0F)));

    //bush
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
