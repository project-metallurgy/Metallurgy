package projectmetallurgy.metallurgy.advanced.caps;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class StoneAnvilCapProvider extends CapabilityProvider<StoneAnvilCapProvider> implements INBTSerializable<CompoundTag> {

    public static final Capability<ItemStackHandler> CAPABILITY = CapabilityManager.get(new CapabilityToken<>(){});

    private final LazyOptional<ItemStackHandler> capability;

    protected StoneAnvilCapProvider() {
        super(StoneAnvilCapProvider.class);
        capability = LazyOptional.of(ItemStackHandler::new);
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return CAPABILITY.orEmpty(cap, capability);
    }

    @Override
    public CompoundTag serializeNBT() {
        return capability.resolve().isPresent() ? capability.resolve().get().serializeNBT() : new CompoundTag();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        capability.ifPresent(cap -> cap.deserializeNBT(nbt));
    }
}
