package projectmetallurgy.metallurgy.block.copper;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.Random;

import static projectmetallurgy.metallurgy.advanced.DataSupplier.doubleWithOffset;
import static projectmetallurgy.metallurgy.advanced.DataSupplier.intWithOffset;

public class BlockMalachite extends BlockCopperOre{

    public static ForgeConfigSpec.ConfigValue<Double> n0;

    public static CompoundTag genTag(){
        CompoundTag tag = new CompoundTag();
        Random random = new Random();
        int vein_mass = random.nextInt(3200,3800);
        int granularity = random.nextInt(40000,80000);

        double cu_amount = random.nextDouble(0.287,0.402);
        int cu_mass = (int) (cu_amount*vein_mass);
        int veinstone_mass = (int) (vein_mass-cu_mass*1.74);

        tag.putInt("vein_mass",vein_mass);
        tag.putDouble("cu_amount",cu_amount);
        tag.putInt("granularity",granularity);
        tag.putInt("cu_mass",cu_mass);
        tag.putInt("veinstone_mass",veinstone_mass);
        return tag;
    }
}
