package projectmetallurgy.metallurgy.oreGen.world.feature;

import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModOrePlacement {
    public static List<PlacementModifier> orePlacement(PlacementModifier modifier1, PlacementModifier modifier2) {
        return List.of(modifier1, InSquarePlacement.spread(), modifier2, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(int count, PlacementModifier modifier) {
        return orePlacement(CountPlacement.of(count), modifier);
    }

    public static List<PlacementModifier> rareOrePlacement(int onAverageOnceEvery, PlacementModifier modifier) {
        return orePlacement(RarityFilter.onAverageOnceEvery(onAverageOnceEvery), modifier);

    }
}
