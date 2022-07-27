package projectmetallurgy.metallurgy.block.device;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.client.IBlockRenderProperties;
import net.minecraftforge.client.RenderProperties;
import org.jetbrains.annotations.Nullable;
import projectmetallurgy.metallurgy.block.blockEntity.StoneAnvilBlockEntity;

public class BlockStoneAnvil extends Block implements EntityBlock {
    public BlockStoneAnvil() {
        super(BlockBehaviour.Properties.of(Material.STONE));
    }



    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new StoneAnvilBlockEntity(pPos,pState);
    }

}
