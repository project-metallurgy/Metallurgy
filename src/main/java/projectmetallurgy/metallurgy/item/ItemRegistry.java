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
import projectmetallurgy.metallurgy.item.tool.ItemStoneHammer;
import projectmetallurgy.metallurgy.item.tool.ItemStonePestle;

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
    public static final RegistryObject<Item> copperConcentrate = regBlockItemInMetalTab("copper_concentrate",BlockRegistry.copperConcentrate);
    public static final RegistryObject<Item> copperCalcine = regBlockItemInMetalTab("copper_calcine",BlockRegistry.copperCalcine);
    public static final RegistryObject<Item> chalcopyriteItem = regBlockItemInMetalTab("chalcopyrite",BlockRegistry.chalcopyriteBlock);
    public static final RegistryObject<Item> hematiteItem = regBlockItemInMetalTab("hematite",BlockRegistry.hematiteBlock);
    public static final RegistryObject<Item> malachiteItem = regBlockItemInMetalTab("malachite",BlockRegistry.malachiteBlock);
    public static final RegistryObject<Item> stoneAnvilItem = regBlockItemInMetalTab("stone_anvil",BlockRegistry.stoneAnvilBlock);
    public static final RegistryObject<Item> mortarAnvilItem = regBlockItemInMetalTab("mortar",BlockRegistry.mortarBlock);
    //

     //Item
    public static final RegistryObject<Item> testItem = regPlaceHolderItemInMetalTab("test");
    public static final RegistryObject<Item> rawHematiteItem = ITEMS.register("raw_hematite_item", ItemRawHematite::new);
    public static final RegistryObject<Item> rawMalachiteItem = ITEMS.register("raw_malachite_item", ItemRawMalachite::new);
    public static final RegistryObject<Item> rawChalcopyriteItem = ITEMS.register("raw_chalcopyrite_item", ItemRawChalcopyrite::new);
    public static final RegistryObject<Item> stoneHammerItem = ITEMS.register("stone_hammer", ItemStoneHammer::new);
    public static final RegistryObject<Item> stonePestleItem = ITEMS.register("stone_pestle", ItemStonePestle::new);
    //
 }
