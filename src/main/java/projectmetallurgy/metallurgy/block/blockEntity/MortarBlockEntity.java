package projectmetallurgy.metallurgy.block.blockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class MortarBlockEntity extends BlockEntity {
    public MortarBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.MORTAR_BLOCK_ENTITY.get(), pos, state);
    }
}
