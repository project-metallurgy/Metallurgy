package projectmetallurgy.metallurgy.multiblock;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;

public interface IMultiblockClientData {

  /// Get total ingredients for the multiblock structure.
  NonNullList<ItemStack> getIngredients();

  // Can render formed multiblock structure model.
  boolean canRenderFormedStructure();

  // Render the multiblock structure
  void renderFormedStructure(PoseStack transform, MultiBufferSource buffer);
}
