package projectmetallurgy.metallurgy.advanced.client;

import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
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

@Mod.EventBusSubscriber(value = Dist.CLIENT,modid = Metallurgy.MOD_ID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEvent {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event){
        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.mortarBlock.get(), RenderType.cutout());
    }
    @SubscribeEvent
    public static void onRegRenderer(EntityRenderersEvent.RegisterRenderers event){
        event.registerBlockEntityRenderer(BlockEntityRegistry.STONE_ANVIL_BLOCK_ENTITY.get(), StoneAnvilRenderer::new);
        event.registerBlockEntityRenderer(BlockEntityRegistry.MORTAR_BLOCK_ENTITY.get(), MortarRenderer::new);
    }


}
