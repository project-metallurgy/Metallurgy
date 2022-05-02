package projectmetallurgy.metallurgy;

import com.mojang.logging.LogUtils;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;



@Mod("metallurgy")
public class Metallurgy {
    public static final String MOD_ID = "metallurgy";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Metallurgy(){

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);
        RegHandler.ITEM_DEFERRED_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
    public void setup(FMLCommonSetupEvent setupEvent){

    }
}
