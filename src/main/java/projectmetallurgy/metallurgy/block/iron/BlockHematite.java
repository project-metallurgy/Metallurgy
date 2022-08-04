package projectmetallurgy.metallurgy.block.iron;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.ForgeConfigSpec;
import projectmetallurgy.metallurgy.Metallurgy;
import projectmetallurgy.metallurgy.block.device.BlockMortar;
import projectmetallurgy.metallurgy.block.device.BlockStoneAnvil;
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
    public static CompoundTag crush(CompoundTag tag){
        int minGranularity = BlockStoneAnvil.MinGranularity.get();
        double lossRatio = BlockStoneAnvil.LossRatio.get();
        double crushingRatio = BlockStoneAnvil.CrushingRatio.get();

        int granularity = tag.getInt("granularity");
        granularity = (int) Math.ceil(granularity * crushingRatio);
        if(granularity<minGranularity){
            granularity=minGranularity;
        }

        int fe_mass = tag.getInt("fe_mass");
        fe_mass = (int) (fe_mass*(1-lossRatio));

        int veinstone_mass = tag.getInt("veinstone_mass");
        veinstone_mass = (int) (veinstone_mass*(1-lossRatio));


        int mass = (int) (veinstone_mass + (fe_mass * 1.430));

        double eta = ProcessingCalc.calcEta(Eta0.get(),granularity,Alpha.get());

        tag.putInt("granularity",granularity);
        tag.putInt("fe_mass",fe_mass);
        tag.putInt("veinstone_mass",veinstone_mass);
        tag.putInt("mass",mass);
        tag.putDouble("eta",eta);
        return tag;
    }
    public BlockHematite() {
        super();
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

        int fe_mass = tag.getInt("fe_mass");

        int veinstone_mass = tag.getInt("veinstone_mass");
        veinstone_mass = veinstone_mass+veinstoneCorrection;
        int mass = (int) (veinstone_mass + fe_mass*1.43);
        double eta = ProcessingCalc.calcEta(Eta0.get(),granularity,Alpha.get());
        tag.putInt("granularity",granularity);
        tag.putInt("veinstone_mass",veinstone_mass);
        tag.putInt("mass",mass);
        tag.putDouble("eta",eta);
        return tag;
    }

}
