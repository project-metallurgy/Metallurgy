package projectmetallurgy.metallurgy.block.device;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class Barrel extends Block {
    public Barrel() {
        super(BlockBehaviour.Properties.of(Material.WOOD));
    }
}
