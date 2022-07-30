package projectmetallurgy.metallurgy.advanced.actions;

import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import projectmetallurgy.metallurgy.block.blockEntity.StoneAnvilBlockEntity;
import projectmetallurgy.metallurgy.item.ItemRegistry;
import projectmetallurgy.metallurgy.item.raw.ItemRawOre;

@Mod.EventBusSubscriber
public class StoneAnvilAction {
    @SubscribeEvent
    public static void onPlaceOreOnTheAnvil(PlayerInteractEvent.RightClickBlock event){
        if (event.getPlayer().getMainHandItem().getItem() instanceof ItemRawOre&&event.getWorld().getBlockEntity(event.getPos())instanceof StoneAnvilBlockEntity){
            System.out.println("Interacted!");
        }
    }
}
