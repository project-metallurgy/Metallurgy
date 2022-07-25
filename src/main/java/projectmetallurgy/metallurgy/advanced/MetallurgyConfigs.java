package projectmetallurgy.metallurgy.advanced;

import net.minecraftforge.common.ForgeConfigSpec;
import projectmetallurgy.metallurgy.block.copper.BlockChalcopyrite;
import projectmetallurgy.metallurgy.block.copper.BlockMalachite;
import projectmetallurgy.metallurgy.block.iron.BlockHematite;

public class MetallurgyConfigs {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec CONFIG_SPEC;

    static {
        BUILDER.push("This is the configs for Metallurgy.");
        //

        BlockChalcopyrite.n0  = BUILDER.comment("").define("chalcopyrite_n0",0.8);
        BlockMalachite.n0 = BUILDER.comment("").define("malachite_n0",0.75);
        BlockHematite.n0 = BUILDER.comment("").define("hematite_n0",0.9);

        //
        BUILDER.pop();
        CONFIG_SPEC=BUILDER.build();
    }
}
