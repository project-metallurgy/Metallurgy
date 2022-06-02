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


    public static final List<OreConfiguration.TargetBlockState> COPPER_ORE_TARGET = List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, BlockRegistry.copperOre.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, BlockRegistry.copperOre.get().defaultBlockState()));

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> COPPER_ORE_CONFIGURED = FeatureUtils.register("citrine_ore",
            Feature.ORE, new OreConfiguration(COPPER_ORE_TARGET, 64));


}
