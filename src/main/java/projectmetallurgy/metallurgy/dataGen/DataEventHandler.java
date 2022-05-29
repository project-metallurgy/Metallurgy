package projectmetallurgy.metallurgy.dataGen;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import projectmetallurgy.metallurgy.Metallurgy;
import projectmetallurgy.metallurgy.block.LootDataProvider;

@Mod.EventBusSubscriber
public class DataEventHandler {
@SubscribeEvent
    public static void onData (GatherDataEvent event){
    event.getGenerator().addProvider(new LootDataProvider(event.getGenerator(), Metallurgy.MOD_ID));
}
}
