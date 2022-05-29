package projectmetallurgy.metallurgy.dataGen;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber
public class ItemGen {
    @SubscribeEvent
    public static void dataGen (GatherDataEvent event){
        //event.getGenerator().addProvider();
    }
}
