package projectmetallurgy.metallurgy.block.iron;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.ForgeConfigSpec;
import projectmetallurgy.metallurgy.Metallurgy;
import projectmetallurgy.metallurgy.utils.ProcessingCalc;

import java.util.Random;

public class BlockHematite extends BlockIronOre{

    public static ForgeConfigSpec.ConfigValue<Double> Eta0;
    public static ForgeConfigSpec.ConfigValue<Integer> Gsps;
    public static ForgeConfigSpec.ConfigValue<Boolean> Manual;
    public static ForgeConfigSpec.ConfigValue<Integer> Alpha;

    public static CompoundTag genTag(){
        CompoundTag tag = new CompoundTag();
        Random random = Metallurgy.RANDOM;

        int mass = random.nextInt(4800,5200);

        int fe_mass = (int)(random.nextDouble(0.420,0.699)*mass);
        int granularity = random.nextInt(60000,90000);
        int veinstone_mass = (int) (mass-(fe_mass*1.430));
        double eta = ProcessingCalc.calcEta(Eta0.get(),granularity,Alpha.get());
        tag.putInt("mass",mass);
        tag.putInt("granularity",granularity);
        tag.putInt("fe_mass",fe_mass);
        tag.putDouble("eta",eta);
        tag.putInt("veinstone_mass",veinstone_mass);
        return tag;
    }

    public BlockHematite() {
        super();
    }
}
