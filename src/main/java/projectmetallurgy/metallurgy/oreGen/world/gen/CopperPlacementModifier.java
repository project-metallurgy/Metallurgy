package projectmetallurgy.metallurgy.oreGen.world.gen;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.CarvingMaskPlacement;
import net.minecraft.world.level.levelgen.placement.PlacementContext;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;

import java.util.Random;
import java.util.stream.Stream;

public class CopperPlacementModifier extends PlacementModifier {

    @Override
    public Stream<BlockPos> getPositions(PlacementContext p_191845_, Random p_191846_, BlockPos p_191847_) {

        return Stream.of(new BlockPos(p_191847_.getX()+10,p_191847_.getY()+10,p_191847_.getZ()+10));
    }

    @Override
    public PlacementModifierType<?> type() {
        return null;
    }
}
