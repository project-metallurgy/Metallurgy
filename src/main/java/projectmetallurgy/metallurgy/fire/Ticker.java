package projectmetallurgy.metallurgy.fire;

import net.minecraft.client.Minecraft;
import net.minecraft.client.server.IntegratedServer;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

@Mod.EventBusSubscriber
public class Ticker {
    public static final Map<UUID, Integer> fired_count = new HashMap<>();
    public static final Map<UUID, Integer> fired_value_count = new HashMap<>();
    public static final Random random = new Random();
    public static int delay = 0;

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        var uuid = event.player.getUUID();
        if (!fired_count.containsKey(uuid)) {
            fired_count.put(uuid, 0);
        }
        if (!fired_value_count.containsKey(uuid)) {
            fired_value_count.put(uuid, 0);
        }
        if (event.player.isOnFire() && fired_count.get(uuid) < 5) {
            if (random.nextDouble(0, 1) < 0.5) {
                event.player.totalExperience = event.player.totalExperience - 10;
                fired_count.put(uuid, fired_count.get(uuid) + 1);
            }
        } else if (event.player.isOnFire() && fired_count.get(uuid) == 5) {
            fired_count.put(uuid, 0);
            fired_value_count.put(uuid, fired_value_count.get(uuid) + 50);
            FireValue.get(event.player.level).fire_value = FireValue.get(event.player.level).fire_value + 50;
        } else if (!event.player.isOnFire()) {
            fired_value_count.put(uuid, 0);
            fired_count.put(uuid, 0);
        }
    }

    @SubscribeEvent
    public static void onWorldTick(TickEvent.WorldTickEvent event) {
        var fire_value = FireValue.get(event.world).fire_value;
        if (event.world.isNight() && fire_value != 0) {
            FireValue.get(event.world).fire_value -= 1;
            if (event.world.getGameRules().getRule(GameRules.RULE_DAYLIGHT).get()) return;
            event.world.getGameRules().getRule(GameRules.RULE_DAYLIGHT).set(true, event.world.getServer());
        } else if ((event.world.isNight() && fire_value == 0)) {
            if (!event.world.getGameRules().getRule(GameRules.RULE_DAYLIGHT).get()) return;
            event.world.getGameRules().getRule(GameRules.RULE_DAYLIGHT).set(false, event.world.getServer());
            delay += 1;
            if (delay == 1000) {
                delay = 0;
                var server = event.world.getServer();
                if (server == null) return;
                server.sendMessage(new TextComponent("火已渐熄，然位不见王影，请献祭具有经验值的薪王"), UUID.randomUUID());
            }
            //new MinecraftServer().getCommands().performCommand(CommandSourceStack.ERROR_NOT_PLAYER,"./")
        } else if (event.world.isDay()) {
            FireValue.get(event.world).fire_value = 0;
            if (event.world.getGameRules().getRule(GameRules.RULE_DAYLIGHT).get()) return;
            event.world.getGameRules().getRule(GameRules.RULE_DAYLIGHT).set(true, event.world.getServer());
        }
        //new MinecraftServer().getCommands().performCommand(CommandSourceStack.ERROR_NOT_PLAYER,"./")
    }

    @SubscribeEvent
    public static void onDeath(LivingDeathEvent event) {
        if (event.getEntity() instanceof Player) {
            int fire_value = fired_value_count.get(event.getEntity().getUUID());
            FireValue.get(event.getEntity().level).fire_value += fired_value_count.get(event.getEntity().getUUID()) + ((Player) event.getEntity()).totalExperience * 25;
            fired_value_count.put(event.getEntity().getUUID(), 0);
            var server = event.getEntity().level.getServer();
            if (server == null) return;
            server.sendMessage(new TextComponent("伟大的" + event.getEntity().getName().getContents() + "传了" + fire_value + "点火"), event.getEntity().getUUID());
        }

    }

}
