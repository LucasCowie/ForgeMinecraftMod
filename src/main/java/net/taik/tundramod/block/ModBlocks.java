package net.taik.tundramod.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.taik.tundramod.TundraMod;
import net.taik.tundramod.block.custom.WildBushBlock;
import net.taik.tundramod.block.custom.tundraSoil;
import net.taik.tundramod.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TundraMod.MOD_ID);
    //tundra soils
    //brown soil
    public static final RegistryObject<Block> BROWN_TUNDRA_MOSS = registerBlock("brown_tundra_moss",
            () -> new tundraSoil(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BROWN)
                    .strength(0.6f)
                    .randomTicks()
                    .sound(SoundType.GRASS)));
    public static final RegistryObject<Block> BROWN_MOSS_CARPET = registerBlock("brown_moss_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BROWN)
                    .strength(0.1F)
                    .sound(SoundType.MOSS_CARPET)
                    .pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> BROWN_MOSSY_COBBLESTONE = registerBlock("brown_mossy_cobblestone",
            () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(2.0F, 6.0F)));
//crimson soil
    public static final RegistryObject<Block> CRIMSON_TUNDRA_MOSS = registerBlock("crimson_tundra_moss",
            () -> new tundraSoil(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_RED)
                    .strength(0.6F)
                    .randomTicks()
                    .sound(SoundType.GRASS)));
    public static final RegistryObject<Block> CRIMSON_MOSS_CARPET = registerBlock("crimson_moss_carpet",
            () ->new CarpetBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_RED)
                    .strength(0.1F)
                    .sound(SoundType.MOSS_CARPET)
                    .pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> CRIMSON_MOSSY_COBBLESTONE = registerBlock("crimson_mossy_cobblestone",
            () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(2.0F, 6.0F)));
//orange soil
    public static final RegistryObject<Block> ORANGE_TUNDRA_MOSS = registerBlock("orange_tundra_moss",
            () -> new tundraSoil(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_ORANGE)
                    .strength(0.6f)
                    .randomTicks()
                    .sound(SoundType.GRAVEL)));
    public static final RegistryObject<Block> ORANGE_MOSS_CARPET = registerBlock("orange_moss_carpet",
            () ->new CarpetBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_ORANGE)
                    .strength(0.1F)
                    .sound(SoundType.MOSS_CARPET)
                    .pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> ORANGE_MOSSY_COBBLESTONE = registerBlock("orange_mossy_cobblestone",
            () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(2.0F, 6.0F)));
    //soil
    public static final RegistryObject<Block> TUNDRA_SOIL = registerBlock("tundra_soil",
            () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.DIRT)
                    .strength(0.6f)
                    .sound(SoundType.GRAVEL)));
    public static final RegistryObject<Block> TUNDRA_PODZOL = registerBlock("tundra_podzol",
            () -> new tundraSoil(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BROWN)
                    .strength(0.6f)
                    .randomTicks()
                    .sound(SoundType.GRASS)));
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
        ModItems.ITEMS.register(name, () -> new BlockItem(registeredBlock.get(), new Item.Properties()));
        return registeredBlock;
    }
}
