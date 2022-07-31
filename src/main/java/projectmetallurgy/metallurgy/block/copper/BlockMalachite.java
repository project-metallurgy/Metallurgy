package projectmetallurgy.metallurgy.block.copper;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.ForgeConfigSpec;
import projectmetallurgy.metallurgy.Metallurgy;
import projectmetallurgy.metallurgy.block.device.BlockStoneAnvil;
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
        cu_mass = (int) (cu_mass*(1-BlockStoneAnvil.LossRatio.get()));

        int veinstone_mass = tag.getInt("veinstone_mass");
        veinstone_mass = (int) (veinstone_mass*(1-lossRatio));


        int mass = (int) (veinstone_mass + (cu_mass * 1.740));

        double eta = ProcessingCalc.calcEta(Eta0.get(),granularity,Alpha.get());

        tag.putInt("granularity",granularity);
        tag.putInt("cu_mass",cu_mass);
        tag.putInt("veinstone_mass",veinstone_mass);
        tag.putInt("mass",mass);
        tag.putDouble("eta",eta);
        return tag;
    }

}
