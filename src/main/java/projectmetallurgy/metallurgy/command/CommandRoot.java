package projectmetallurgy.metallurgy.command;

import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class CommandRoot {
@SubscribeEvent
    public static void regCommand(RegisterCommandsEvent event){
    GetNBT.register(event.getDispatcher());
}
}
