package projectmetallurgy.metallurgy.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class CopperCalcine extends Block {
    public CopperCalcine() {
        super(BlockBehaviour.Properties.of(Material.STONE).strength(1.5f, 6.0f).sound(SoundType.STONE).requiresCorrectToolForDrops());
    }
}
