package projectmetallurgy.metallurgy.block.copper;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import projectmetallurgy.metallurgy.advanced.DataSupplier;
import projectmetallurgy.metallurgy.block.MetaOre;

import java.util.Random;

import static projectmetallurgy.metallurgy.advanced.DataSupplier.doubleWithOffset;
import static projectmetallurgy.metallurgy.advanced.DataSupplier.intWithOffset;


public class BlockCopperOre extends MetaOre {
    public BlockCopperOre(){
        super(BlockBehaviour.Properties.of(Material.STONE).strength(1.5f, 6.0f).sound(SoundType.STONE).requiresCorrectToolForDrops().destroyTime(100));
    }

    Random random = new Random();

}
