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

import java.util.*;

@Mod.EventBusSubscriber
public class Ticker {
    public static final Map<UUID, Integer> fired_count = new HashMap<>();
    public static final Map<UUID, Integer> fired_value_count = new HashMap<>();
    public static final Random random = new Random();
    public static int delay = 0;
    private static int delayMax = 6000;
    private static boolean state = false;

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
            FireValue.set(FireValue.get() + 50);
        } else if (!event.player.isOnFire()) {
            fired_value_count.put(uuid, 0);
            fired_count.put(uuid, 0);
        }
    }

    @SubscribeEvent
    public static void onWorldTick(TickEvent.WorldTickEvent event) {
        var fire_value = FireValue.get();
        var players = event.world.players();
        if (players.isEmpty()) return;
        if (event.world.getDayTime() % 24000L > 18000 && fire_value != 0) {
            FireValue.set(FireValue.get() - 1);
            if (event.world.getGameRules().getRule(GameRules.RULE_DAYLIGHT).get()) return;
            event.world.getGameRules().getRule(GameRules.RULE_DAYLIGHT).set(true, event.world.getServer());
            if (!state) state = true;
        } else if ((event.world.getDayTime() % 24000L > 18000  && fire_value == 0)) {
            if (!state) state = true;
            delay += 1;
            if (delay == delayMax) {
                delay = 0;
                players.forEach((p) -> {
                    p.sendMessage(new TextComponent("火已渐熄，然位不见王影"), p.getUUID());
                });
                if (delayMax < 400) {
                    delayMax += 100;
                }
            }
            if (!event.world.getGameRules().getRule(GameRules.RULE_DAYLIGHT).get()) return;
            event.world.getGameRules().getRule(GameRules.RULE_DAYLIGHT).set(false, event.world.getServer());
            //new MinecraftServer().getCommands().performCommand(CommandSourceStack.ERROR_NOT_PLAYER,"./")
        } else if (event.world.isDay()) {
            FireValue.set(0);
            if (state) {
                players.forEach((p) -> {
                    p.sendMessage(new TextComponent("火之时代被延长了……"), p.getUUID());
                });
                state = false;
            }
            if (event.world.getGameRules().getRule(GameRules.RULE_DAYLIGHT).get()) return;
            event.world.getGameRules().getRule(GameRules.RULE_DAYLIGHT).set(true, event.world.getServer());
        }
        //new MinecraftServer().getCommands().performCommand(CommandSourceStack.ERROR_NOT_PLAYER,"./")
    }

    @SubscribeEvent
    public static void onDeath(LivingDeathEvent event) {
        if (event.getEntity() instanceof Player p) {

            if (!event.getSource().isFire()) {
                return;
            }
            var exp = p.totalExperience * 25;
            if (exp < 0) {
                exp = 1000;
            }
            FireValue.set(FireValue.get() + fired_value_count.get(p.getUUID()) + exp);
            
            fired_value_count.put(p.getUUID(), 0);
            event.getEntity().level.players().forEach((pl) -> {
                pl.sendMessage(new TextComponent(event.getEntity().getName().getContents() + "投入了初始之火"), p.getUUID());
            });
        }

    }

}
