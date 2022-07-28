package projectmetallurgy.metallurgy.fire;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;
import projectmetallurgy.metallurgy.advanced.MineralAreaData;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FireValue extends SavedData {

    private static final String NAME = "FIRE_VALUE";

    public int fire_value = 0;

    public static FireValue get(Level level){
        if (level instanceof ServerLevel serverLevel) {
            return serverLevel.getDataStorage().computeIfAbsent(FireValue::read,FireValue::new,NAME);
        }
        else {
            throw new RuntimeException("Cannot get mineralData from client.");
        }
    }

    public static FireValue read(CompoundTag tag){
        FireValue data = new FireValue();
        data.fire_value = tag.getInt("fire");
        return data;
    }

    @Override
    public CompoundTag save(CompoundTag pCompoundTag) {
        pCompoundTag.putInt("fire",this.fire_value);
        return pCompoundTag;
    }

}
