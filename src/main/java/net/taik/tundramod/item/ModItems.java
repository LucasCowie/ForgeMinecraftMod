package net.taik.tundramod.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.taik.tundramod.TundraMod;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TundraMod.MOD_ID);

    public static final RegistryObject<Item> MOSS_CLUMP = ITEMS.register("moss_clump", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CRIMSON_MOSS_CLUMP = ITEMS.register("crimson_moss_clump", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ORANGE_MOSS_CLUMP = ITEMS.register("orange_moss_clump", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BROWN_MOSS_CLUMP = ITEMS.register("brown_moss_clump", () -> new Item(new Item.Properties()));

    public static void registerItem(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}