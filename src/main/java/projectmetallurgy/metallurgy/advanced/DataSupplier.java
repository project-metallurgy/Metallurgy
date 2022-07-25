package projectmetallurgy.metallurgy.advanced;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import projectmetallurgy.metallurgy.Metallurgy;
import projectmetallurgy.metallurgy.block.copper.BlockChalcopyrite;
import projectmetallurgy.metallurgy.block.copper.BlockCopperOre;
import projectmetallurgy.metallurgy.block.copper.BlockMalachite;
import projectmetallurgy.metallurgy.block.iron.BlockHematite;
import projectmetallurgy.metallurgy.item.raw.ItemRawChalcopyrite;
import projectmetallurgy.metallurgy.item.raw.ItemRawHematite;
import projectmetallurgy.metallurgy.item.raw.ItemRawMalachite;
import projectmetallurgy.metallurgy.item.raw.ItemRawOre;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = Metallurgy.MOD_ID)
public class DataSupplier {

    // TODO: Reg the tag supplier there.

    public static void onSetup (){
        addTagSupplier(ItemRawChalcopyrite.class, BlockChalcopyrite::genTag);
        addTagSupplier(ItemRawMalachite.class,BlockMalachite::genTag);
        addTagSupplier(ItemRawHematite.class, BlockHematite::genTag);
    }


    public static Map<Class<? extends ItemRawOre>, Supplier<CompoundTag>> tagSuppliers = new HashMap<>();

    public static void addTagSupplier(Class<?extends ItemRawOre> rawOre, Supplier<CompoundTag> tagSupplier){
        tagSuppliers.put(rawOre,tagSupplier);
    }

    public static double doubleWithOffset(double base, double offsetBound){
        Random random = new Random();
        double offsetLength = random.nextDouble(-offsetBound,offsetBound);
        return base+offsetLength;
    }

    public static int intWithOffset(int base, int offsetBound){
        Random random = new Random();
        int offsetLength = random.nextInt(-offsetBound,offsetBound);
        return base+offsetLength;
    }

}
