package projectmetallurgy.metallurgy.block.copper;

import net.minecraft.nbt.CompoundTag;

import java.util.Random;

import static projectmetallurgy.metallurgy.advanced.DataSupplier.doubleWithOffset;
import static projectmetallurgy.metallurgy.advanced.DataSupplier.intWithOffset;

public class BlockChalcopyrite extends BlockCopperOre{
    public static CompoundTag genTag (){
        CompoundTag tag = new CompoundTag();
        Random random = new Random();

        int mass = random.nextInt(3000,3600);

        double cu_amount = random.nextDouble(0.035,0.086);
        double fe_amount = random.nextDouble(0.030,0.076);
        int granularity = new Random().nextInt(70000,100000);
        double s_amount = cu_amount*0.505+fe_amount*0.574+random.nextDouble(0.010);



        double as_amount ;
        if (random.nextDouble(0,1)<0.6){
            as_amount = 0;
        }
        else {
            as_amount = random.nextDouble(0.004,0.006);
        }

        boolean has_au;
        if(random.nextDouble(0,1)<0.7){
            has_au = false;
        }
        else {
            has_au = true;
        }

        tag.putInt("mass",mass);
        tag.putInt("granularity",granularity);
        tag.putDouble("cu_amount",cu_amount);
        tag.putDouble("fe_amount",fe_amount);
        tag.putDouble("s_amount",s_amount);
        tag.putDouble("as_amount",as_amount);
        tag.putBoolean("has_au",has_au);
        return tag;
    }
}
