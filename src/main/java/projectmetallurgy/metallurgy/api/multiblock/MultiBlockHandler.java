package projectmetallurgy.metallurgy.api.multiblock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Cancelable;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultiBlockHandler {

    static List<IMultiBlock> multiBlocks = new ArrayList<>();
    static Map<ResourceLocation, IMultiBlock> byUniqueName = new HashMap<>();

    public static synchronized void registerMultiBlock(IMultiBlock multiBlock) {
        multiBlocks.add(multiBlock);
        byUniqueName.put(multiBlock.getUniqueName(), multiBlock);
    }

    public static List<IMultiBlock> getMultiBlocks() {
        return multiBlocks;
    }

    @Nullable
    public static IMultiBlock getByUniqueName(ResourceLocation name) {
        return byUniqueName.get(name);
    }


    public interface IMultiBlock {
        /**
         * @return name of the MultiBlock. This is used for the interdiction NBT system on the hammer, so this name /must/ be unique.
         */
        ResourceLocation getUniqueName();

        /**
         * Check whether the given block can be used to trigger the structure creation of the multiBlock.<br>
         * Basically, a less resource-intensive preliminary check to avoid checking every structure.
         */
        boolean isBlockTrigger(BlockState state, Direction side, @Nullable Level world);

        /**
         * This method checks the structure and sets the new one.
         *
         * @return if the structure was valid and transformed
         */
        boolean createStructure(Level world, BlockPos pos, Direction side, Player player);

        /**
         * TODO
         */
        List<StructureTemplate.StructureBlockInfo> getStructure(@Nullable Level world);

        /**
         * returns the scale modifier to be applied when rendering the structure in the IE manual
         */
        float getManualScale();

        Vec3i getSize(@Nullable Level world);

        void disassemble(Level world, BlockPos startPos, boolean mirrored, Direction clickDirectionAtCreation);

        BlockPos getTriggerOffset();

        //todo: ???
        void initializeClient();

        //todo: add more class
        String getDisplayName();
    }

    /**
     * This event is fired BEFORE the multiBlock is attempted to be formed.<br>
     * No checks of the structure have been made. The event simply exists to cancel the formation of the multiBlock before it ever happens.
     */
    @Cancelable
    public static class MultiBlockFormEvent extends PlayerEvent {
        private final IMultiBlock multiBlock;
        private final BlockPos clickedBlock;
        private final ItemStack hammer;

        public MultiBlockFormEvent(Player player, IMultiBlock multiBlock, BlockPos clickedBlock, ItemStack hammer) {
            super(player);
            this.multiBlock = multiBlock;
            this.clickedBlock = clickedBlock;
            this.hammer = hammer;
        }

        public IMultiBlock getMultiBlock() {
            return multiBlock;
        }

        public BlockPos getClickedBlock() {
            return clickedBlock;
        }

        public ItemStack getHammer() {
            return hammer;
        }
    }
}
