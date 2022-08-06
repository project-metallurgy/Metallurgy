package projectmetallurgy.metallurgy.block.blockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.TickingBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;


public class BarrelBlockEntity extends BlockEntity{

    public ItemStack oreOn = new ItemStack(Items.AIR);

    public BarrelBlockEntity( BlockPos pWorldPosition, BlockState pBlockState) {
        super(BlockEntityRegistry.BARREL_BLOCK_ENTITY.get(), pWorldPosition, pBlockState);
    }

}
