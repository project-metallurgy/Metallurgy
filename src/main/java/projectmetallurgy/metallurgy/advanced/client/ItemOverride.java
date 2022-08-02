package projectmetallurgy.metallurgy.advanced.client;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.renderer.item.ItemPropertyFunction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.jetbrains.annotations.Nullable;
import projectmetallurgy.metallurgy.Metallurgy;
import projectmetallurgy.metallurgy.item.ItemRegistry;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
public class ItemOverride {
    @SubscribeEvent
    public static void onClient (FMLClientSetupEvent event){
        event.enqueueWork(()->{
            ItemProperties.register(ItemRegistry.malachiteItem.get(),new ResourceLocation(Metallurgy.MOD_ID,"granularity"), new ItemPropertyFunction(){
                @Override
                public float call(ItemStack pStack, @Nullable ClientLevel pLevel, @Nullable LivingEntity pEntity, int pSeed) {
                    return pStack.getTag().getInt("granularity");
                }
            });
        });
    }
}
