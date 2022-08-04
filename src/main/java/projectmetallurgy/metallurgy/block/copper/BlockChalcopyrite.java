package projectmetallurgy.metallurgy.block.copper;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.ForgeConfigSpec;
import projectmetallurgy.metallurgy.Metallurgy;
import projectmetallurgy.metallurgy.block.device.BlockMortar;
import projectmetallurgy.metallurgy.block.device.BlockStoneAnvil;
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

    public static CompoundTag crush(CompoundTag tag){
        int minGranularity = BlockStoneAnvil.MinGranularity.get();
        double lossRatio = BlockStoneAnvil.LossRatio.get();
        double crushingRatio = BlockStoneAnvil.CrushingRatio.get();

        int granularity = tag.getInt("granularity");
        granularity = (int) Math.ceil(granularity * crushingRatio);
        if(granularity<minGranularity){
            granularity=minGranularity;
        }

        int cu_mass = tag.getInt("cu_mass");
        cu_mass = (int) (cu_mass*(1-lossRatio));
        int fe_mass = tag.getInt("fe_mass");
        fe_mass = (int) (fe_mass*(1-lossRatio));
        int as_mass = tag.getInt("as_mass");
        as_mass = (int) (as_mass*(1-lossRatio));
        int s_mass = tag.getInt("s_mass");
        s_mass = (int) (s_mass*(1-lossRatio));

        int veinstone_mass = tag.getInt("veinstone_mass");
        veinstone_mass = (int) (veinstone_mass*(1-lossRatio));


        int mass = veinstone_mass +cu_mass+fe_mass+as_mass+s_mass;

        double eta = ProcessingCalc.calcEta(Eta0.get(),granularity,Alpha.get());

        tag.putInt("granularity",granularity);
        tag.putInt("cu_mass",cu_mass);
        tag.putInt("fe_mass",fe_mass);
        tag.putInt("as_mass",as_mass);
        tag.putInt("s_mass",s_mass);
        tag.putInt("veinstone_mass",veinstone_mass);
        tag.putInt("mass",mass);
        tag.putDouble("eta",eta);
        return tag;
    }

    public static CompoundTag smash(CompoundTag tag){
        int minGranularity = BlockMortar.MinGranularity.get();
        int veinstoneCorrection = BlockMortar.VeinstoneCorrection.get();
        double smashingRatio = BlockMortar.SmashingRatio.get();

        int granularity = tag.getInt("granularity");
        granularity = (int) Math.ceil(granularity * smashingRatio);
        if(granularity<minGranularity){
            granularity=minGranularity;
        }
        int cu_mass = tag.getInt("cu_mass");
        int fe_mass = tag.getInt("fe_mass");
        int as_mass = tag.getInt("as_mass");
        int s_mass = tag.getInt("s_mass");
        int veinstone_mass = tag.getInt("veinstone_mass");
        veinstone_mass = veinstone_mass+veinstoneCorrection;
        int mass = veinstone_mass +cu_mass+fe_mass+as_mass+s_mass;
        double eta = ProcessingCalc.calcEta(Eta0.get(),granularity,Alpha.get());
        tag.putInt("granularity",granularity);
        tag.putInt("veinstone_mass",veinstone_mass);
        tag.putInt("mass",mass);
        tag.putDouble("eta",eta);
        return tag;
    }

}
