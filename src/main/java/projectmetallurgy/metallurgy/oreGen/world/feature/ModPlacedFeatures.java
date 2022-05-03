package projectmetallurgy.metallurgy.oreGen.world.feature;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import projectmetallurgy.metallurgy.oreGen.world.feature.ModConfiguredFeatures;


public class ModPlacedFeatures {
    public static final Holder<PlacedFeature> EBONY_PLACED = PlacementUtils.register("ebony_placed",
            ModConfiguredFeatures.EBONY_SPAWN, VegetationPlacements.treePlacement(
                    PlacementUtils.countExtra(3, 0.1f, 2)));

    public static final Holder<PlacedFeature> PINK_ROSE_PLACED = PlacementUtils.register("pink_rose_placed",
            ModConfiguredFeatures.PINK_ROSE, RarityFilter.onAverageOnceEvery(16),
            InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

    public static final Holder<PlacedFeature> CITRINE_ORE_PLACED = PlacementUtils.register("citrine_ore_placed",
            ModConfiguredFeatures.CITRINE_ORE,
            ModOrePlacement.rareOrePlacement(5, // VeinsPerChunk
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)))
            );
                    //HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)))

}
