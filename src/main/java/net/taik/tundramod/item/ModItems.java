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

    public static final RegistryObject<Item> MOSS = ITEMS.register("moss", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CRIMSON_MOSS = ITEMS.register("crimson_moss", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ORANGE_MOSS = ITEMS.register("orange_moss", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BROWN_MOSS = ITEMS.register("brown_moss", () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}