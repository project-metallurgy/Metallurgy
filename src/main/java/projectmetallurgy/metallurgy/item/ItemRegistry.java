package projectmetallurgy.metallurgy.item;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import projectmetallurgy.metallurgy.Metallurgy;
import projectmetallurgy.metallurgy.block.BlockRegistry;
import projectmetallurgy.metallurgy.creativeTab.MetallurgyCreativeTab;
import projectmetallurgy.metallurgy.item.raw.ItemRawChalcopyrite;
import projectmetallurgy.metallurgy.item.raw.ItemRawHematite;
import projectmetallurgy.metallurgy.item.raw.ItemRawMalachite;

public class ItemRegistry {
     public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Metallurgy.MOD_ID);

     //Utils
     public static RegistryObject<Item> regBlockItemInMetalTab(String name , RegistryObject<Block> block){
        return ITEMS.register(name,()->new BlockItem(block.get(),new Item.Properties().tab(MetallurgyCreativeTab.metal)));
     }
     public static RegistryObject<Item> regPlaceHolderItemInMetalTab(String name){
         return ITEMS.register(name,()->new Item(new Item.Properties().tab(MetallurgyCreativeTab.metal)));
     }
     //

    //BlockItem
    public static final RegistryObject<Item> copperConcentrate = ITEMS.register("copper_concentrate",()->new BlockItem(BlockRegistry.copperConcentrate.get(),new Item.Properties().tab(MetallurgyCreativeTab.metal)));
    public static final RegistryObject<Item> copperCalcine = ITEMS.register("copper_calcine",()->new BlockItem(BlockRegistry.copperCalcine.get(),new Item.Properties().tab(MetallurgyCreativeTab.metal)));
    public static final RegistryObject<Item> chalcopyriteItem = regBlockItemInMetalTab("chalcopyrite",BlockRegistry.chalcopyriteBlock);

    //

     //Item
    public static final RegistryObject<Item> testItem = regPlaceHolderItemInMetalTab("test");
    public static final RegistryObject<Item> rawHematiteItem = ITEMS.register("raw_hematite_item", ItemRawHematite::new);
    public static final RegistryObject<Item> rawMalachiteItem = ITEMS.register("raw_malachite_item", ItemRawMalachite::new);
    public static final RegistryObject<Item> rawChalcopyriteItem = ITEMS.register("raw_chalcopyrite_item", ItemRawChalcopyrite::new);

    //
 }
