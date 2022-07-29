package projectmetallurgy.metallurgy.fire;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;
import org.jetbrains.annotations.NotNull;
import projectmetallurgy.metallurgy.advanced.MineralAreaData;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FireValue {
    private static int fireValue = 0;

    public static void set(int value) {
        FireValue.fireValue = value;
    }

    public static int get() {
        if (fireValue < 0) {
            fireValue = 0;
        }
        return fireValue;
    }
}
