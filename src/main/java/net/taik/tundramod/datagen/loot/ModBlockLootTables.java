package net.taik.tundramod.datagen.loot;

import net.minecraft.world.level.block.Blocks;
import net.taik.tundramod.block.ModBlocks;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.TUNDRA_SOIL.get());
        this.dropSelf(ModBlocks.TUNDRA_PODZOL.get());

        this.add(ModBlocks.BROWN_TUNDRA_MOSS.get(), block -> createSilkTouchDrop(ModBlocks.BROWN_TUNDRA_MOSS.get(), ModBlocks.TUNDRA_SOIL.get()));
        this.dropSelf(ModBlocks.BROWN_MOSS_CARPET.get());
        this.dropSelf(ModBlocks.BROWN_MOSSY_COBBLESTONE.get());

        this.add(ModBlocks.CRIMSON_TUNDRA_MOSS.get(), block -> createSilkTouchDrop(ModBlocks.CRIMSON_TUNDRA_MOSS.get(), ModBlocks.TUNDRA_SOIL.get()));
        this.dropSelf(ModBlocks.CRIMSON_MOSS_CARPET.get());
        this.dropSelf(ModBlocks.CRIMSON_MOSSY_COBBLESTONE.get());

        this.add(ModBlocks.ORANGE_TUNDRA_MOSS.get(), block -> createSilkTouchDrop(ModBlocks.ORANGE_TUNDRA_MOSS.get(), ModBlocks.TUNDRA_SOIL.get()));
        this.dropSelf(ModBlocks.ORANGE_MOSSY_COBBLESTONE.get());
        this.dropSelf(ModBlocks.ORANGE_MOSS_CARPET.get());

        this.add(ModBlocks.WILD_BUSH.get(), block -> createShearsOnlyDrop(ModBlocks.WILD_BUSH.get()));
    }

    protected LootTable.Builder createCopperLikeOreDrops(Block pBlock, Item item) {
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F)))
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }
    protected LootTable.Builder createSilkTouchDrop(Block pBlock, Block dBlock){
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock,
                        LootItem.lootTableItem(dBlock)));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}