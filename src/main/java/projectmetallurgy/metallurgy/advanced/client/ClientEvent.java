package projectmetallurgy.metallurgy.advanced.client;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import projectmetallurgy.metallurgy.Metallurgy;
import projectmetallurgy.metallurgy.advanced.renderer.BarrelRenderer;
import projectmetallurgy.metallurgy.advanced.renderer.MortarRenderer;
import projectmetallurgy.metallurgy.advanced.renderer.StoneAnvilRenderer;
import projectmetallurgy.metallurgy.block.BlockRegistry;
import projectmetallurgy.metallurgy.block.blockEntity.BlockEntityRegistry;
import projectmetallurgy.metallurgy.item.ItemRegistry;

@Mod.EventBusSubscriber(value = Dist.CLIENT,modid = Metallurgy.MOD_ID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEvent {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event){
        event.enqueueWork(()->{
            regGranularityOverride(ItemRegistry.rawChalcopyriteItem.get());
            regGranularityOverride(ItemRegistry.rawHematiteItem.get());
            regGranularityOverride(ItemRegistry.rawMalachiteItem.get());
        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.mortarBlock.get(), RenderType.cutout());
        });
    }
    @SubscribeEvent
    public static void onRegRenderer(EntityRenderersEvent.RegisterRenderers event){
        event.registerBlockEntityRenderer(BlockEntityRegistry.STONE_ANVIL_BLOCK_ENTITY.get(), StoneAnvilRenderer::new);
        event.registerBlockEntityRenderer(BlockEntityRegistry.MORTAR_BLOCK_ENTITY.get(), MortarRenderer::new);
        event.registerBlockEntityRenderer(BlockEntityRegistry.BARREL_BLOCK_ENTITY.get(), BarrelRenderer::new);
    }

    public static int getGranularity(ItemStack pStack, ClientLevel pLevel,LivingEntity pEntity,int pSeed){
        if (pStack.getTag()!=null) {
            return pStack.getTag().getInt("granularity");
        }
        else {
            return 10000;
        }
    }

    public static void regGranularityOverride(Item item){
        ItemProperties.register(item,
                new ResourceLocation(Metallurgy.MOD_ID,"granularity"),ClientEvent::getGranularity);
    }

}



