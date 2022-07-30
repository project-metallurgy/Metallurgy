package projectmetallurgy.metallurgy.advanced.renderer;

import com.lowdragmc.lowdraglib.core.mixins.TileEntityRendererDispatcherMixin;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Matrix4f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.texture.AtlasSet;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.ColorResolver;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidAttributes;
import org.jetbrains.annotations.NotNull;
import org.jline.utils.Colors;
import org.lwjgl.opengl.GL11;
import projectmetallurgy.metallurgy.block.blockEntity.BarrelBlockEntity;

import java.awt.image.PackedColorModel;

public class BarrelRender  implements BlockEntityRenderer<BarrelBlockEntity> {
    
    @Override
    public void render(@NotNull BarrelBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {

        Minecraft minecraft =  Minecraft.getInstance();
        Matrix4f matrix4f = pPoseStack.last().pose();

        FlowingFluid fluid = Fluids.WATER;

        FluidAttributes fluidAttributes = fluid.getSource().getAttributes();
        int fluidHEXColor = fluidAttributes.getColor();
        ResourceLocation stillTexture = fluidAttributes.getStillTexture();
        TextureAtlasSprite atlasSprite = minecraft.getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(stillTexture);
        minecraft.getTextureManager().bindForSetup(TextureAtlas.LOCATION_BLOCKS);

        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder buffer = tesselator.getBuilder();
        buffer.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR_TEX);
        float u0 = atlasSprite.getU0();
        float u1 = atlasSprite.getU1();
        float v0 = atlasSprite.getV0();
        float v1 = atlasSprite.getV1();

        buffer.vertex(matrix4f,0,0,0)
                .color(fluidAttributes.getColor())
                .uv(u0,v0)
                .endVertex();
        buffer.vertex(matrix4f,0,1,0)
                .color(fluidAttributes.getColor())
                .uv(u0,v1)
                .endVertex();
        buffer.vertex(matrix4f,1,1,0)
                .color(fluidAttributes.getColor())
                .uv(u1,v1)
                .endVertex();
        buffer.vertex(matrix4f,1,0,0)
                .color(fluidAttributes.getColor())
                .uv(u1,v0)
                .endVertex();
        tesselator.end();

    }

}
