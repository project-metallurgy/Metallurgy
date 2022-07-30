package projectmetallurgy.metallurgy.block.blockEntity;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

public class StoneAnvilBlockEntity extends BlockEntity {
    public Item itemPlacedOn= Items.AIR;
    public CompoundTag tag = new CompoundTag();
    public int clickCount = 0;
    public StoneAnvilBlockEntity( BlockPos pWorldPosition, BlockState pBlockState) {
        super(BlockEntityRegistry.STONE_ANVIL_BLOCK_ENTITY.get(), pWorldPosition, pBlockState);
    }

    @Override
    public CompoundTag serializeNBT() {
        String namespace = this.itemPlacedOn.getRegistryName().getNamespace();
        String path = this.itemPlacedOn.getRegistryName().getPath();
        CompoundTag tag =new CompoundTag();
        if (namespace!=null) {
            tag.put("tag",this.tag);
            String name = namespace+":"+path;
            tag.putString("item",name);
            tag.putInt("click_count",this.clickCount);
            return tag;
        }
        else {
            return null;
        }
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.tag = (CompoundTag) nbt.get("tag");
        this.clickCount = nbt.getInt("click_count");
        this.itemPlacedOn = ForgeRegistries.ITEMS.getValue(new ResourceLocation(nbt.getString("item")));
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        if (pkt.getTag()!=null) {
            handleUpdateTag(pkt.getTag());
        }
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = super.getUpdateTag();
        String namespace = this.itemPlacedOn.getRegistryName().getNamespace();
        String path = this.itemPlacedOn.getRegistryName().getPath();
        if (namespace!=null) {
            tag.put("tag",this.tag);
            String name = namespace+":"+path;
            tag.putString("item",name);
            tag.putInt("click_count",this.clickCount);
            return tag;
        }
        else {
            return null;
        }
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        this.tag = (CompoundTag) tag.get("tag");
        this.itemPlacedOn = ForgeRegistries.ITEMS.getValue(new ResourceLocation(tag.getString("item")));
    }
}
