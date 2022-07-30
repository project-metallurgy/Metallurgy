package projectmetallurgy.metallurgy.fire;

import net.minecraft.client.Minecraft;
import net.minecraft.client.server.IntegratedServer;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.players.PlayerList;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.server.ServerLifecycleHooks;

import java.util.*;

@Mod.EventBusSubscriber
public class Ticker {

    public static boolean ENABLED = false;

    public static final Map<UUID, Integer> fired_count = new HashMap<>();
    public static final Map<UUID, Integer> fired_value_count = new HashMap<>();
    public static final Random random = new Random();
    public static int delay = 0;
    private static int delayMax = 6000;
    private static boolean state = false;

    public static int fire_value=0;

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (ENABLED) {
            try {
                var uuid = event.player.getUUID();
                if (!fired_count.containsKey(uuid)) {
                    fired_count.put(uuid, 0);
                }
                if (!fired_value_count.containsKey(uuid)) {
                    fired_value_count.put(uuid, 0);
                }
                if (event.player.isOnFire() && fired_count.get(uuid) < 60) {

                    event.player.giveExperiencePoints(-50);
                    fired_count.put(uuid, fired_count.get(uuid) + 1);

                } else if (event.player.isOnFire() && fired_count.get(uuid) == 60) {
                    event.player.giveExperiencePoints(-50);
                    fired_count.put(uuid, 0);
                    fired_value_count.put(uuid, fired_value_count.get(uuid) + 50);
                    FireValue.set(FireValue.get() + 50);
                } else if (!event.player.isOnFire()) {
                    fired_value_count.put(uuid, 0);
                    fired_count.put(uuid, 0);
                }
                isPlayerTicked = true;
            } catch (Exception e) {
                if (event.player.getName().getContents().equals("Draming")) {
                    event.player.sendMessage(new TextComponent(e.getMessage()), UUID.randomUUID());
                }
                if (event.player.getName().getContents().equals("Nullpinter")) {
                    event.player.sendMessage(new TextComponent(e.getMessage()), UUID.randomUUID());
                }
            }
        }
    }

    public static boolean isWorldTicked= false;
    public static boolean isPlayerTicked = false;

        @SubscribeEvent
    public static void onWorldTick(TickEvent.WorldTickEvent event) {
        if (ENABLED) {
            try {
                var fire_value = FireValue.get();
                var players = event.world.players();
                var time = event.world.getDayTime() % 24000L;
                if (!event.world.getGameRules().getRule(GameRules.RULE_SHOWDEATHMESSAGES).get()) {
                    event.world.getGameRules().getRule(GameRules.RULE_SHOWDEATHMESSAGES).set(true, event.world.getServer());
                }
                if (players.isEmpty()) return;
                if (time > 18000 && time < 22000 && fire_value != 0) {
                    FireValue.set(FireValue.get() - 1);
                    if (!event.world.getGameRules().getRule(GameRules.RULE_DAYLIGHT).get()) {
                        event.world.getGameRules().getRule(GameRules.RULE_DAYLIGHT).set(true, event.world.getServer());
                        if (!state) state = true;
                    }
                } else if (time > 18000 && time < 22000) {
                    if (!state) state = true;
                    if (delay % delayMax == 0) {
                        delay = 1;
                        players.forEach((p) -> {
                            p.sendMessage(new TextComponent("火已渐熄，然位不见王影"), p.getUUID());
                        });
                        if (delayMax < 400) {
                            delayMax += 100;
                        }
                    }
                    delay += 1;
                    if (!event.world.getGameRules().getRule(GameRules.RULE_DAYLIGHT).get()) return;
                    event.world.getGameRules().getRule(GameRules.RULE_DAYLIGHT).set(false, event.world.getServer());

                } else if (time == 22000) {
                    //总不能每天重新来
                    //FireValue.set(0);

                    if (state) {
                        if (fired_value_count.size() > 0) {
                            int max = 0;
                            UUID maxid = null;
                            for (Map.Entry<UUID, Integer> e : fired_value_count.entrySet()) {
                                if (max <= e.getValue()) {
                                    max = e.getValue();
                                    maxid = e.getKey();
                                }
                            }
                            if (maxid != null) {
                                UUID finalMaxid = maxid;
                                players.forEach((p) -> {
                                    //p.sendMessage(new TextComponent("得益于薪王" + event.world.getServer().getPlayerList().getPlayer(finalMaxid).getName().getContents() + "，"), p.getUUID());
                                    p.sendMessage(new TextComponent("得益于薪王" + Objects.requireNonNull(Objects.requireNonNull(event.world.getServer()).getPlayerList().getPlayer(finalMaxid)).getName().getContents() + "，"), p.getUUID());
                                });
                            }
                        }
                        players.forEach((p) -> {
                            p.sendMessage(new TextComponent("火之时代被延续了……"), p.getUUID());

                        });
                        state = false;
                    }
                    if (event.world.getGameRules().getRule(GameRules.RULE_DAYLIGHT).get()) return;
                    event.world.getGameRules().getRule(GameRules.RULE_DAYLIGHT).set(true, event.world.getServer());
                }

                isWorldTicked = true;
            } catch (Exception e) {
                event.world.getServer().getPlayerList().getPlayers().forEach(player -> {
                    if (player.getName().getContents().equals("Draming")) {
                        player.sendMessage(new TextComponent(e.getMessage()), UUID.randomUUID());
                    }
                    if (player.getName().getContents().equals("Nullpinter")) {
                        player.sendMessage(new TextComponent(e.getMessage()), UUID.randomUUID());
                    }
                });
            }
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
                exp = 0;
            }
            FireValue.set(FireValue.get() + fired_value_count.get(p.getUUID()) + exp);

            fired_value_count.put(p.getUUID(), fired_value_count.get(p.getUUID()) + exp);
            event.getEntity().level.players().forEach((pl) -> {
                pl.sendMessage(new TextComponent(event.getEntity().getName().getContents() + "投入了初始之火"), p.getUUID());
            });
            p.level.getGameRules().getRule(GameRules.RULE_SHOWDEATHMESSAGES).set(false, event.getEntity().getServer());
        }

    }

}
