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
import org.lwjgl.opengl.GL20;
import projectmetallurgy.metallurgy.block.blockEntity.StoneAnvilBlockEntity;
import software.bernie.shadowed.eliotlash.mclib.math.functions.limit.Min;

public class StoneAnvilRenderer implements BlockEntityRenderer<StoneAnvilBlockEntity> {
    private BlockEntityRendererProvider.Context context;
    public StoneAnvilRenderer(BlockEntityRendererProvider.Context context){
        this.context = context;
    }

    @Override
    public void render(StoneAnvilBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {

        float scale = 0.3f;
        pPoseStack.pushPose();

        pPoseStack.translate(0.5f, 0.52f, 0.5f);
        pPoseStack.mulPose(Vector3f.XN.rotationDegrees(90));
        pPoseStack.scale(scale, scale, scale);
        ItemStack stack = new ItemStack(Items.ACACIA_BOAT);
        BakedModel ibakedmodel = Minecraft.getInstance().getItemRenderer().getModel(stack, pBlockEntity.getLevel(), null,0);
        
        Minecraft.getInstance().getItemRenderer().render(new ItemStack(Items.ACACIA_BOAT), ItemTransforms.TransformType.FIXED,true,pPoseStack,pBufferSource,pPackedLight,pPackedOverlay,ibakedmodel);
        //Minecraft.getInstance().getBlockRenderer().renderSingleBlock(Blocks.GLASS.defaultBlockState(),pPoseStack,pBufferSource,pPackedLight,pPackedOverlay,EmptyModelData.INSTANCE);
        pPoseStack.popPose();
    }
}
