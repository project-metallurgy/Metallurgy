package projectmetallurgy.metallurgy.block.misc;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CauldronBlock;
import net.minecraft.world.level.material.Material;

public class FakeWater extends Block {
    public FakeWater() {
        super(Properties.of(Material.WATER));
    }
}
