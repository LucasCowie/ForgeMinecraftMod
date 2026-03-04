package net.taik.tundramod;

import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.taik.tundramod.block.ModBlocks;
import net.taik.tundramod.worldgen.ModSurfaceRules;
import net.taik.tundramod.worldgen.TundraRegion;
import org.slf4j.Logger;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TundraMod.MOD_ID)
public class TundraMod {
    public static final String MOD_ID = "tundramod";
    private static final Logger LOGGER = LogUtils.getLogger();


    public TundraMod(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        ModBlocks.BLOCKS.register(modEventBus);
        ModBlocks.ITEMS.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            // Register the custom biome with a weight of 20 (not quite sure what the number should be)
            Regions.register(new TundraRegion(ResourceLocation.tryBuild(MOD_ID, "overworld"), 20));

            // Add Flavor to the biome
            SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MOD_ID, ModSurfaceRules.makeRules());
        });

        LOGGER.info("TundraMod common setup - registered tundra biome");
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            event.enqueueWork(() -> {
                ItemBlockRenderTypes.setRenderLayer(ModBlocks.WILD_BUSH.get(), RenderType.cutout());
            });
        }

        @SubscribeEvent
        public static void onRegisterBlockColors(RegisterColorHandlersEvent.Block event) {
            //grass tint color
            event.register((state, level, pos, tintIndex) -> {
                if (level == null || pos == null) {
                    return GrassColor.getDefaultColor();
                }
                Block below = level.getBlockState(pos.below()).getBlock();
                if (below == ModBlocks.TUNDRA_SOIL_ORANGE.get()) {
                    return 0xA15325; // warm amber C9672E
                } else if (below == ModBlocks.TUNDRA_SOIL_RED.get()) {
                    return 0x8F3D2F; // deep red-brown B45028
                } else if (below == ModBlocks.TUNDRA_SOIL_BROWN.get()) {
                    return 0x4D3324; // earth brown
                }
                return BiomeColors.getAverageGrassColor(level, pos);
            }, Blocks.GRASS, Blocks.FERN, Blocks.TALL_GRASS, Blocks.LARGE_FERN);
            event.register((state, level, pos, tintIndex) -> {
                if (level == null || pos == null) {
                    return GrassColor.getDefaultColor();
                }
                Block below = level.getBlockState(pos.below()).getBlock();
                if (below == ModBlocks.TUNDRA_SOIL_ORANGE.get()) {
                    return 0xC9672E;
                } else if (below == ModBlocks.TUNDRA_SOIL_RED.get()) {
                    return 0xBC4F3E; // deep red-brown B45028
                } else if (below == ModBlocks.TUNDRA_SOIL_BROWN.get()) {
                    return 0x7F533B; // earth brown
                }
                return BiomeColors.getAverageGrassColor(level, pos);
            }, ModBlocks.WILD_BUSH.get());
        }
    }
}
