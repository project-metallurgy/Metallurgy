package projectmetallurgy.metallurgy.oreGen.world.feature;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;


public class ModPlacedFeatures {

    public static final List<PlacementModifier> CHALCOPYRITE_MODIFIER_LIST =
            List.of(
                    RarityFilter.onAverageOnceEvery(20),
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-200), VerticalAnchor.aboveBottom(80)),
                    BiomeFilter.biome(),
                    CountPlacement.of(65),
                    //PlacementUtils.countExtra(300,1,2)//,
                    //WEIGHT_MODIFIER

                    InSquarePlacement.spread()

                    //,
                    //RandomOffsetPlacement.vertical(ConstantInt.of(20))
            );

    public static final Holder<PlacedFeature> CHALCOPYRITE_ORE_FEATURE = PlacementUtils.register("chalcopyrite",
            ModConfiguredFeatures.CHALCOPYRITE_CONFIGURED_FEATURE,
            CHALCOPYRITE_MODIFIER_LIST
    );
                    //HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)))

}
