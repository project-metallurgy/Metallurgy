package projectmetallurgy.metallurgy.block;

import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import projectmetallurgy.metallurgy.Metallurgy;
import projectmetallurgy.metallurgy.block.copper.BlockChalcopyrite;
import projectmetallurgy.metallurgy.block.copper.BlockMalachite;
import projectmetallurgy.metallurgy.block.device.BlockBarrel;
import projectmetallurgy.metallurgy.block.device.BlockStoneAnvil;
import projectmetallurgy.metallurgy.block.iron.BlockHematite;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Metallurgy.MOD_ID);
    public static final RegistryObject<Block> copperConcentrate = BLOCKS.register("copper_concentrate", BlockCopperConcentrate::new);
    public static final RegistryObject<Block> copperCalcine = BLOCKS.register("copper_calcine", BlockCopperConcentrate::new);

    public static final RegistryObject<Block> chalcopyriteBlock = BLOCKS.register("chalcopyrite", BlockChalcopyrite::new);
    public static final RegistryObject<Block> hematiteBlock = BLOCKS.register("hematite", BlockHematite::new);
    public static final RegistryObject<Block> malachiteBlock = BLOCKS.register("malachite", BlockMalachite::new);

    public static final RegistryObject<Block> stoneAnvilBlock = BLOCKS.register("stone_anvil", BlockStoneAnvil::new);
    public static final RegistryObject<Block> barrelBlock = BLOCKS.register("barrel", BlockBarrel::new);
}
