package projectmetallurgy.metallurgy.fire;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.TextComponent;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class CommandRegistry {

    @SubscribeEvent
    public static void onCommandRegister(RegisterClientCommandsEvent event){
        event.getDispatcher().register(Commands.literal("fire").then(Commands.literal("set").then(Commands.argument("value", IntegerArgumentType.integer()).executes(ctx -> {
            FireValue.set(ctx.getArgument("value", Integer.class));
            return 1;
        }))).then(Commands.literal("get").executes(ctx -> {
            ctx.getSource().sendSuccess(new TextComponent(FireValue.get()+""), false);
            return 1;
        })));
    }
}
