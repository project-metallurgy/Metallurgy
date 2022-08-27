package projectmetallurgy.metallurgy.advanced.renderer;

import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Matrix4f;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class Util {

    @OnlyIn(Dist.CLIENT)
    public static void renderCubeFace(PoseStack matrixStack, BufferBuilder buffer, float minX, float minY, float minZ, float maxX, float maxY, float maxZ, int fluidColor, TextureAtlasSprite textureSprite) {
        Matrix4f mat = matrixStack.last().pose();
        float uMin = textureSprite.getU0();
        float uMax = textureSprite.getU1();
        float vMin = textureSprite.getV0();
        float vMax = textureSprite.getV1();

        buffer.vertex(mat, minX, minY, minZ).uv(uMin, vMax).color(fluidColor).endVertex();
        buffer.vertex(mat, minX, minY, maxZ).uv(uMax, vMax).color(fluidColor).endVertex();
        buffer.vertex(mat, minX, maxY, maxZ).uv(uMax, vMin).color(fluidColor).endVertex();
        buffer.vertex(mat, minX, maxY, minZ).uv(uMin, vMin).color(fluidColor).endVertex();

        buffer.vertex(mat, maxX, minY, minZ).uv(uMin, vMax).color(fluidColor).endVertex();
        buffer.vertex(mat, maxX, maxY, minZ).uv(uMax, vMax).color(fluidColor).endVertex();
        buffer.vertex(mat, maxX, maxY, maxZ).uv(uMax, vMin).color(fluidColor).endVertex();
        buffer.vertex(mat, maxX, minY, maxZ).uv(uMin, vMin).color(fluidColor).endVertex();


        buffer.vertex(mat, minX, minY, minZ).uv(uMin, vMax).color(fluidColor).endVertex();
        buffer.vertex(mat, maxX, minY, minZ).uv(uMax, vMax).color(fluidColor).endVertex();
        buffer.vertex(mat, maxX, minY, maxZ).uv(uMax, vMin).color(fluidColor).endVertex();
        buffer.vertex(mat, minX, minY, maxZ).uv(uMin, vMin).color(fluidColor).endVertex();


        buffer.vertex(mat, minX, maxY, minZ).uv(uMin, vMax).color(fluidColor).endVertex();
        buffer.vertex(mat, minX, maxY, maxZ).uv(uMax, vMax).color(fluidColor).endVertex();
        buffer.vertex(mat, maxX, maxY, maxZ).uv(uMax, vMin).color(fluidColor).endVertex();
        buffer.vertex(mat, maxX, maxY, minZ).uv(uMin, vMin).color(fluidColor).endVertex();

        buffer.vertex(mat, minX, minY, minZ).uv(uMin, vMax).color(fluidColor).endVertex();
        buffer.vertex(mat, minX, maxY, minZ).uv(uMax, vMax).color(fluidColor).endVertex();
        buffer.vertex(mat, maxX, maxY, minZ).uv(uMax, vMin).color(fluidColor).endVertex();
        buffer.vertex(mat, maxX, minY, minZ).uv(uMin, vMin).color(fluidColor).endVertex();

        buffer.vertex(mat, minX, minY, maxZ).uv(uMin, vMax).color(fluidColor).endVertex();
        buffer.vertex(mat, maxX, minY, maxZ).uv(uMax, vMax).color(fluidColor).endVertex();
        buffer.vertex(mat, maxX, maxY, maxZ).uv(uMax, vMin).color(fluidColor).endVertex();
        buffer.vertex(mat, minX, maxY, maxZ).uv(uMin, vMin).color(fluidColor).endVertex();
    }
}
