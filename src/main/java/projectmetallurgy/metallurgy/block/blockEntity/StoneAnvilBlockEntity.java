package projectmetallurgy.metallurgy.block.blockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class StoneAnvilBlockEntity extends BlockEntity {
    public StoneAnvilBlockEntity( BlockPos pWorldPosition, BlockState pBlockState) {
        super(BlockEntityRegistry.STONE_ANVIL_BLOCK_ENTITY.get(), pWorldPosition, pBlockState);
    }
}
