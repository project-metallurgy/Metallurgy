package projectmetallurgy.metallurgy.block.blockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.TickingBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.UUID;


public class BarrelBlockEntity extends BlockEntity{

    public BarrelBlockEntity( BlockPos pWorldPosition, BlockState pBlockState) {
        super(BlockEntityRegistry.BARREL_BLOCK_ENTITY.get(), pWorldPosition, pBlockState);
    }

}
