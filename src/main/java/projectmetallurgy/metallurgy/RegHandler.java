package projectmetallurgy.metallurgy;

import net.minecraft.world.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber()
public class RegHandler {
    @SubscribeEvent
    public static void regItem(RegistryEvent.Register<Item> event){
        
    }
}
