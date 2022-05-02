package projectmetallurgy.metallurgy.item;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import projectmetallurgy.metallurgy.Metallurgy;
import projectmetallurgy.metallurgy.block.BlockRegistry;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Metallurgy.MOD_ID);
    public static final RegistryObject<Item> copperConcentrate = ITEMS.register("copper_concentrate",()->new BlockItem(BlockRegistry.copperConcentrate.get(),new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> copperCalcine = ITEMS.register("copper_calcine",()->new BlockItem(BlockRegistry.copperCalcine.get(),new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));
}
