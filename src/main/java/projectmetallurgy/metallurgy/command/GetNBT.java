package projectmetallurgy.metallurgy.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.Util;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.player.Player;

import java.util.UUID;

public class GetNBT {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher){
        dispatcher.register(Commands.literal("getNBT").executes(GetNBT::execute));
    }

    private static int execute(CommandContext<CommandSourceStack> command){
        if(command.getSource().getEntity() instanceof Player){
            Player player = (Player) command.getSource().getEntity();
            try {
                player.sendMessage(new TextComponent(player.getMainHandItem().getTag().toString()), Util.NIL_UUID);
            }catch (NullPointerException nullPointerException){
                player.sendMessage(new TextComponent("Null"), UUID.fromString("metallurgy"));
            }
        }
        return Command.SINGLE_SUCCESS;
    }
}
