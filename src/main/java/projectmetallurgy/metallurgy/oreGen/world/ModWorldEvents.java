package projectmetallurgy.metallurgy.oreGen.world;



import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import projectmetallurgy.metallurgy.Metallurgy;
import projectmetallurgy.metallurgy.oreGen.world.gen.ModFlowerGeneration;
import projectmetallurgy.metallurgy.oreGen.world.gen.ModOreGeneration;
import projectmetallurgy.metallurgy.oreGen.world.gen.ModTreeGeneration;

@Mod.EventBusSubscriber(modid = Metallurgy.MOD_ID)
public class ModWorldEvents {
    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent event) {
        ModOreGeneration.generateOres(event);

        //ModTreeGeneration.generateTrees(event);
        //ModFlowerGeneration.generateFlowers(event);
    }
}
