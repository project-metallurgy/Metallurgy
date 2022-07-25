package projectmetallurgy.metallurgy.block.iron;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import projectmetallurgy.metallurgy.block.MetaOre;

public class BlockIronOre extends MetaOre {
    public BlockIronOre() {
        super(BlockBehaviour.Properties.of(Material.STONE).strength(1.5f, 6.0f).sound(SoundType.STONE).requiresCorrectToolForDrops());
    }
}
