package projectmetallurgy.metallurgy;

import com.mojang.logging.LogUtils;
import net.minecraft.core.Registry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import projectmetallurgy.metallurgy.block.BlockRegistry;
import projectmetallurgy.metallurgy.item.ItemRegistry;

@Mod(Metallurgy.MOD_ID)
public class Metallurgy {
    public static final String MOD_ID = "metallurgy";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Metallurgy(){
        var modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        BlockRegistry.BLOCKS.register(modEventBus);
        ItemRegistry.ITEMS.register(modEventBus);
    }
}
