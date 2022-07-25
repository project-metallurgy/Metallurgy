package projectmetallurgy.metallurgy.oreGen.world.feature;


import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import projectmetallurgy.metallurgy.block.BlockRegistry;

import java.util.List;

public class ModConfiguredFeatures {


    public static final List<OreConfiguration.TargetBlockState> CHALCOPYRITE_TARGET = List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, BlockRegistry.chalcopyriteBlock.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, BlockRegistry.chalcopyriteBlock.get().defaultBlockState()));

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> CHALCOPYRITE_CONFIGURED_FEATURE = FeatureUtils.register("chalcopyrite",
            Feature.ORE, new OreConfiguration(CHALCOPYRITE_TARGET, 64));


}
