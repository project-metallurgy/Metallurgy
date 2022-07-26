package projectmetallurgy.metallurgy.block.copper;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.ForgeConfigSpec;
import projectmetallurgy.metallurgy.Metallurgy;
import projectmetallurgy.metallurgy.item.raw.ItemRawOre;
import projectmetallurgy.metallurgy.utils.ProcessingCalc;

import java.util.Random;

public class BlockMalachite extends BlockCopperOre{

    public static ForgeConfigSpec.ConfigValue<Double> Eta0;
    public static ForgeConfigSpec.ConfigValue<Integer> Gsps;
    public static ForgeConfigSpec.ConfigValue<Boolean> Manual;
    public static ForgeConfigSpec.ConfigValue<Integer> Alpha;

    public static CompoundTag genTag(){
        CompoundTag tag = new CompoundTag();
        Random random = Metallurgy.RANDOM;
        int mass = random.nextInt(3200,3800);
        int granularity = random.nextInt(40000,80000);
        int cu_mass = (int) (mass * random.nextDouble(0.287,0.402));
        int veinstone_mass = (int) (mass-cu_mass*1.74);
        double eta =  ProcessingCalc.calcEta(Eta0.get(),granularity,Alpha.get());
        tag.putInt("mass",mass);
        tag.putInt("granularity",granularity);
        tag.putInt("cu_mass",cu_mass);
        tag.putInt("veinstone_mass",veinstone_mass);
        tag.putDouble("eta",eta);
        return tag;
    }
}
