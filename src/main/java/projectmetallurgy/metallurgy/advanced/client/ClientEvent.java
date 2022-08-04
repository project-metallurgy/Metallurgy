package projectmetallurgy.metallurgy.advanced.client;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.renderer.item.ItemPropertyFunction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import projectmetallurgy.metallurgy.Metallurgy;
import projectmetallurgy.metallurgy.advanced.renderer.MortarRenderer;
import projectmetallurgy.metallurgy.advanced.renderer.StoneAnvilRenderer;
import projectmetallurgy.metallurgy.block.BlockRegistry;
import projectmetallurgy.metallurgy.block.blockEntity.BlockEntityRegistry;
import projectmetallurgy.metallurgy.block.blockEntity.StoneAnvilBlockEntity;
import projectmetallurgy.metallurgy.item.ItemRegistry;

import javax.annotation.Nullable;
import java.util.logging.Level;

@Mod.EventBusSubscriber(value = Dist.CLIENT,modid = Metallurgy.MOD_ID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEvent {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event){
        event.enqueueWork(()->{
            ItemProperties.register(ItemRegistry.malachiteItem.get(),
                    new ResourceLocation(Metallurgy.MOD_ID,"granularity"),ClientEvent::getGranularity);

        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.mortarBlock.get(), RenderType.cutout());
        });
    }
    @SubscribeEvent
    public static void onRegRenderer(EntityRenderersEvent.RegisterRenderers event){
        event.registerBlockEntityRenderer(BlockEntityRegistry.STONE_ANVIL_BLOCK_ENTITY.get(), StoneAnvilRenderer::new);
        event.registerBlockEntityRenderer(BlockEntityRegistry.MORTAR_BLOCK_ENTITY.get(), MortarRenderer::new);
    }

    public static int getGranularity(ItemStack pStack, ClientLevel pLevel,LivingEntity pEntity,int pSeed){
        System.out.println("test");
        if (pStack.getTag()!=null) {
            return pStack.getTag().getInt("granularity");
        }
        else {
            return 10000;
        }
    }
}



