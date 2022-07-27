package projectmetallurgy.metallurgy.block.blockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;


public class BarrelBlockEntity extends BlockEntity {

    public BarrelBlockEntity( BlockPos pWorldPosition, BlockState pBlockState) {
        super(BlockEntityRegistry.BARREL_BLOCK_ENTITY.get(), pWorldPosition, pBlockState);
    }
}
