package projectmetallurgy.metallurgy.block.device;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import org.jetbrains.annotations.Nullable;
import projectmetallurgy.metallurgy.block.blockEntity.BarrelBlockEntity;
import projectmetallurgy.metallurgy.block.blockEntity.BlockEntityRegistry;

public class BlockBarrel extends Block implements EntityBlock {

    public BlockBarrel() {
        super(BlockBehaviour.Properties.of(Material.WOOD));
    }
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return BlockEntityRegistry.BARREL_BLOCK_ENTITY.get().create(pos, state);
    }
}
