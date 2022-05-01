package projectmetallurgy.metallurgy;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber()
public class RegHandler {
    public static final DeferredRegister<Item> ITEM_DEFERRED_REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS,Metallurgy.MOD_ID);

    @SubscribeEvent
    public static void regItem(RegistryEvent.Register<Item> event){
        ITEM_DEFERRED_REGISTER.register("test_item",()->{
            return new Item(new Item.Properties());
        });
    }
}
