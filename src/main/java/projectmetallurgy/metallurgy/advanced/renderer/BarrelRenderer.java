package projectmetallurgy.metallurgy.advanced.renderer;

import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import projectmetallurgy.metallurgy.block.blockEntity.BarrelBlockEntity;

public class BarrelRenderer implements BlockEntityRenderer<BarrelBlockEntity> {

    private BlockEntityRendererProvider.Context context;
    public BarrelRenderer(BlockEntityRendererProvider.Context context){
        this.context = context;
    }

    @Override
    public void render(@NotNull BarrelBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {


    }

}
