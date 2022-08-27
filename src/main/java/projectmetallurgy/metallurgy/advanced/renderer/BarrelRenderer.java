package projectmetallurgy.metallurgy.advanced.renderer;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;
import projectmetallurgy.metallurgy.block.blockEntity.BarrelBlockEntity;

import static projectmetallurgy.metallurgy.advanced.renderer.Util.renderCubeFace;


public class BarrelRenderer implements BlockEntityRenderer<BarrelBlockEntity> {

    private BlockEntityRendererProvider.Context context;
    public BarrelRenderer(BlockEntityRendererProvider.Context context){
        this.context = context;
    }

    @Override
    public void render(@NotNull BarrelBlockEntity tileEntity, float pPartialTick, PoseStack matrixStack, MultiBufferSource buffer, int pPackedLight, int pPackedOverlay) {
        FluidStack fluidStack = new FluidStack(Fluids.WATER,1000);
        Minecraft mc = Minecraft.getInstance();
        if (fluidStack != null && !fluidStack.isEmpty() && mc.level != null) {
            float height = fluidStack.getAmount() / 1000f * (11f / 16);
            ResourceLocation LOCATION_BLOCKS_TEXTURE = TextureAtlas.LOCATION_BLOCKS;
            FluidAttributes fluid = fluidStack.getFluid().getAttributes();
            ResourceLocation fluidStill = fluid.getStillTexture();
            TextureAtlasSprite fluidStillSprite = Minecraft.getInstance().getTextureAtlas(LOCATION_BLOCKS_TEXTURE).apply(fluidStill);
            int biomeWaterColor = BiomeColors.getAverageWaterColor(tileEntity.getLevel(),tileEntity.getBlockPos());
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.setShaderTexture(0, LOCATION_BLOCKS_TEXTURE);

            matrixStack.pushPose();
            Tesselator tessellator = Tesselator.getInstance();
            BufferBuilder bufferBuilder = tessellator.getBuilder();

            RenderSystem.enableDepthTest();
            RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
            bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR);

            renderCubeFace(matrixStack, bufferBuilder, 0, 1f / 16, 0, 1, height, 1, biomeWaterColor | 0xff000000, fluidStillSprite);
            tessellator.end();

            matrixStack.popPose();
        }


    }

}
