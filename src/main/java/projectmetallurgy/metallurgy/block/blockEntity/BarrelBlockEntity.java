package projectmetallurgy.metallurgy.block.blockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.Nullable;


public class BarrelBlockEntity extends BlockEntity{

    public FluidStack fluid = FluidStack.EMPTY;
    public ItemStack ore = new ItemStack(Items.AIR);
    public ItemStack tailing = new ItemStack(Items.AIR);
    public ItemStack bowl = new ItemStack(Items.AIR);

    public BarrelBlockEntity( BlockPos pWorldPosition, BlockState pBlockState) {
        super(BlockEntityRegistry.BARREL_BLOCK_ENTITY.get(), pWorldPosition, pBlockState);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("ore", ore.serializeNBT());
        pTag.put("tailing", tailing.serializeNBT());
        pTag.put("bowl",bowl.serializeNBT());
        pTag.put("fluid",fluid.writeToNBT(new CompoundTag()));
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        this.ore = ItemStack.of(pTag.getCompound("ore"));
        this.tailing = ItemStack.of(pTag.getCompound("tailing"));
        this.bowl = ItemStack.of(pTag.getCompound("bowl"));
        this.fluid = FluidStack.loadFluidStackFromNBT(pTag.getCompound("fluid"));
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return serializeNBT();
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        super.handleUpdateTag(tag);
        load(tag);
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = super.serializeNBT();
        tag.put("ore",ore.serializeNBT());
        tag.put("tailing",tailing.serializeNBT());
        tag.put("bowl",bowl.serializeNBT());
        tag.put("fluid",fluid.writeToNBT(new CompoundTag()));
        return tag;
    }
    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        super.onDataPacket(net, pkt);
        load(pkt.getTag());
    }
}
