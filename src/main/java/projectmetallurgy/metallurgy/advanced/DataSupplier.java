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
import java.util.function.Function;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = Metallurgy.MOD_ID)
public class DataSupplier {

    // TODO: Reg the tag supplier there.

    public static void onSetup (){
        addTagSupplier(ItemRawChalcopyrite.class, BlockChalcopyrite::genTag);
        addTagSupplier(ItemRawMalachite.class,BlockMalachite::genTag);
        addTagSupplier(ItemRawHematite.class, BlockHematite::genTag);

        addCrushProcessor(ItemRawMalachite.class,BlockMalachite::crush);
        addCrushProcessor(ItemRawChalcopyrite.class,BlockChalcopyrite::crush);
        addCrushProcessor(ItemRawHematite.class,BlockHematite::crush);

        addSmashProcessor(ItemRawMalachite.class,BlockMalachite::smash);
        addSmashProcessor(ItemRawChalcopyrite.class,BlockChalcopyrite::smash);
        addSmashProcessor(ItemRawHematite.class,BlockHematite::smash);
    }

    //OnDigging
    public static Map<Class<? extends ItemRawOre>, Supplier<CompoundTag>> tagSuppliers = new HashMap<>();

    public static void addTagSupplier(Class<?extends ItemRawOre> rawOre, Supplier<CompoundTag> tagSupplier){
        tagSuppliers.put(rawOre,tagSupplier);
    }
    //

    //Crush
    public static Map<Class<? extends ItemRawOre>, Function<CompoundTag,CompoundTag>> crushProcessors = new HashMap<>();
    public static void addCrushProcessor(Class<?extends ItemRawOre> rawOre, Function<CompoundTag,CompoundTag> crushProcessor){
        crushProcessors.put(rawOre,crushProcessor);
    }
    //

    //Smashing
    public static Map<Class<? extends ItemRawOre>,Function<CompoundTag,CompoundTag>> smashProcessors = new HashMap<>();
    public static void addSmashProcessor (Class<? extends ItemRawOre> rawOre , Function<CompoundTag,CompoundTag> function){
        smashProcessors.put(rawOre,function);
    }
}
