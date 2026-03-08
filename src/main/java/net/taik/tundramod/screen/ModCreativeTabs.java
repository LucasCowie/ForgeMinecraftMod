package net.taik.tundramod.screen;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.taik.tundramod.TundraMod;
import net.taik.tundramod.block.ModBlocks;
import net.taik.tundramod.item.ModItems;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TundraMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> TUNDRA_TAB = CREATIVE_MODE_TABS.register("tundra_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.MOSS_CLUMP.get()))
                    .title(Component.translatable("creativetab.tundra_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModBlocks.TUNDRA_SOIL.get());
                        pOutput.accept(ModBlocks.TUNDRA_PODZOL.get());

                        pOutput.accept(ModItems.MOSS_CLUMP.get());

                        pOutput.accept(ModItems.BROWN_MOSS_CLUMP.get());
                        pOutput.accept(ModBlocks.BROWN_TUNDRA_MOSS.get());
                        pOutput.accept(ModBlocks.BROWN_MOSS_CARPET.get());
                        pOutput.accept(ModBlocks.BROWN_MOSSY_COBBLESTONE.get());


                        pOutput.accept(ModItems.CRIMSON_MOSS_CLUMP.get());
                        pOutput.accept(ModBlocks.CRIMSON_TUNDRA_MOSS.get());
                        pOutput.accept(ModBlocks.CRIMSON_MOSS_CARPET.get());
                        pOutput.accept(ModBlocks.CRIMSON_MOSSY_COBBLESTONE.get());

                        pOutput.accept(ModItems.ORANGE_MOSS_CLUMP.get());
                        pOutput.accept(ModBlocks.ORANGE_TUNDRA_MOSS.get());
                        pOutput.accept(ModBlocks.ORANGE_MOSS_CARPET.get());
                        pOutput.accept(ModBlocks.ORANGE_MOSSY_COBBLESTONE.get());

                        pOutput.accept(ModBlocks.WILD_BUSH.get());

                    })
                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}