package projectmetallurgy.metallurgy.advanced.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import projectmetallurgy.metallurgy.block.blockEntity.MortarBlockEntity;

public class MortarRenderer implements BlockEntityRenderer<MortarBlockEntity> {
    private BlockEntityRendererProvider.Context context;
    public MortarRenderer(BlockEntityRendererProvider.Context context){
        this.context = context;
    }

    @Override
    public void render(MortarBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        float scale = 0.5f;
        pPoseStack.pushPose();
        pPoseStack.translate(0.5f, 0.9f, 0.5f);
        pPoseStack.mulPose(Vector3f.XN.rotationDegrees(90));
        pPoseStack.scale(scale, scale, scale);
        ItemStack stack = pBlockEntity.itemStackOn.copy();
        BakedModel ibakedmodel = Minecraft.getInstance().getItemRenderer().getModel(stack, pBlockEntity.getLevel(), null,0);
        Minecraft.getInstance().getItemRenderer().render(stack, ItemTransforms.TransformType.FIXED,true,pPoseStack,pBufferSource,pPackedLight,pPackedOverlay,ibakedmodel);
        //Minecraft.getInstance().getBlockRenderer().renderSingleBlock(Blocks.GLASS.defaultBlockState(),pPoseStack,pBufferSource,pPackedLight,pPackedOverlay,EmptyModelData.INSTANCE);
        pPoseStack.popPose();
    }
}
