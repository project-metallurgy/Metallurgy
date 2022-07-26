package projectmetallurgy.metallurgy.block.copper;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.ForgeConfigSpec;
import projectmetallurgy.metallurgy.Metallurgy;
import projectmetallurgy.metallurgy.utils.ProcessingCalc;

import java.util.Random;

public class BlockChalcopyrite extends BlockCopperOre{

    public static ForgeConfigSpec.ConfigValue<Double> Eta0;
    public static ForgeConfigSpec.ConfigValue<Integer> Gsps;
    public static ForgeConfigSpec.ConfigValue<Boolean> Manual;
    public static ForgeConfigSpec.ConfigValue<Integer> Alpha;

    public static CompoundTag genTag (){
        CompoundTag tag = new CompoundTag();
        Random random = Metallurgy.RANDOM;

        int mass = random.nextInt(3000,3600);

        int cu_mass = (int) (random.nextDouble(0.035,0.086)*mass);

        int fe_mass = (int) (random.nextDouble(0.030,0.076)*mass);

        int granularity = new Random().nextInt(70000,100000);

        int s_mass = (int)((cu_mass*0.505)+(fe_mass*0.574)+(mass*random.nextDouble(0.01)));

        int as_mass;
        if (random.nextDouble(0,1)<0.6){
            as_mass = 0;
        }
        else {
            as_mass = (int)(random.nextDouble(0.004,0.006)*mass);
        }
        int veinstone_mass = mass-cu_mass-fe_mass-as_mass-s_mass;
        boolean has_au;
        if(random.nextDouble(0,1)<0.7){
            has_au = false;
        }
        else {
            has_au = true;
        }
        double eta = ProcessingCalc.calcEta(Eta0.get(),granularity,Alpha.get());

        tag.putInt("mass",mass);
        tag.putInt("granularity",granularity);
        tag.putBoolean("has_au",has_au);
        tag.putInt("cu_mass",cu_mass);
        tag.putInt("fe_mass",fe_mass);
        tag.putInt("as_mass",as_mass);
        tag.putInt("s_mass",s_mass);
        tag.putInt("veinstone_mass",veinstone_mass);
        tag.putDouble("eta",eta);
        return tag;
    }
}
