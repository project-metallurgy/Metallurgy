package projectmetallurgy.metallurgy.advanced.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.client.model.data.EmptyModelData;
import net.minecraftforge.registries.ForgeRegistries;
import projectmetallurgy.metallurgy.block.blockEntity.StoneAnvilBlockEntity;
import software.bernie.shadowed.eliotlash.mclib.math.functions.limit.Min;

public class StoneAnvilRenderer implements BlockEntityRenderer<StoneAnvilBlockEntity> {
    private BlockEntityRendererProvider.Context context;
    public StoneAnvilRenderer(BlockEntityRendererProvider.Context context){
        this.context = context;
    }

    @Override
    public void render(StoneAnvilBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        /*
        matrixStackIn.push();
        matrixStackIn.translate(1, 0, 0);
        BlockRendererDispatcher blockRenderer = Minecraft.getInstance().getBlockRendererDispatcher();
        BlockState state = Blocks.CHEST.getDefaultState();
        blockRenderer.renderBlock(state, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, EmptyModelData.INSTANCE);
        matrixStackIn.pop();

        matrixStackIn.push();
        matrixStackIn.translate(0, 1, 0);
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        ItemStack stack = new ItemStack(Items.DIAMOND);
        IBakedModel ibakedmodel = itemRenderer.getItemModelWithOverrides(stack, tileEntityIn.getWorld(), null);
        itemRenderer.renderItem(stack, ItemCameraTransforms.TransformType.FIXED, true, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, ibakedmodel);
        matrixStackIn.pop();
        */
        /*
        pPoseStack.pushPose();
        pPoseStack.translate(1,0,0);
        BlockEntityRenderDispatcher dispatcher = Minecraft.getInstance().getBlockEntityRenderDispatcher();
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        ItemStack itemStack = new ItemStack(Items.DIAMOND);
        BakedModel ibakedmodel = itemRenderer.getModel(itemStack, pBlockEntity.getLevel(),null,0);
        itemRenderer.render(itemStack, ItemTransforms.TransformType.FIXED, true, pPoseStack, pBufferSource, pPackedLight,pPackedOverlay, ibakedmodel);
        pPoseStack.popPose();
         */

        float scale = 0.3f;
        pPoseStack.pushPose();
        pPoseStack.translate(0.5f, 1f, 0.5f);
        pPoseStack.scale(scale, scale, scale);

        Minecraft.getInstance().getBlockRenderer().renderSingleBlock(Blocks.GLASS.defaultBlockState(),pPoseStack,pBufferSource,pPackedLight,pPackedOverlay,EmptyModelData.INSTANCE);
        pPoseStack.popPose();
    }
}
