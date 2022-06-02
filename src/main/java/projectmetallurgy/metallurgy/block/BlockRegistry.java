package projectmetallurgy.metallurgy.block;

import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import projectmetallurgy.metallurgy.Metallurgy;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Metallurgy.MOD_ID);
    public static final RegistryObject<Block> copperConcentrate = BLOCKS.register("copper_concentrate", BlockCopperConcentrate::new);
    public static final RegistryObject<Block> copperCalcine = BLOCKS.register("copper_calcine", BlockCopperConcentrate::new);
    public static final RegistryObject<Block> copperOre = BLOCKS.register("copper_ore", BlockCopperOre::new);


}
