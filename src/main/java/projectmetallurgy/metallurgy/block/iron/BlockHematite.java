package projectmetallurgy.metallurgy.block.iron;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.DyeableArmorItem;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.Random;

public class BlockHematite extends BlockIronOre{

    public static ForgeConfigSpec.ConfigValue<Double> n0;

    public static CompoundTag genTag(){
        CompoundTag tag = new CompoundTag();
        Random random = new Random();

        int mass = random.nextInt(4800,5200);


        double fe_amount = random.nextDouble(0.420,0.699);
        int granularity = random.nextInt(60000,90000);

        tag.putInt("mass",mass);
        tag.putInt("granularity",granularity);
        tag.putDouble("fe_amount",fe_amount);
        return tag;
    }

    public BlockHematite() {
        super();

    }
}
