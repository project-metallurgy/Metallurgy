package projectmetallurgy.metallurgy.block.device;

import net.minecraft.client.renderer.block.model.ItemOverride;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BrewingStandBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.*;
import net.minecraftforge.client.IBlockRenderProperties;
import net.minecraftforge.client.RenderProperties;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.extensions.IForgeBlock;
import org.jetbrains.annotations.Nullable;
import projectmetallurgy.metallurgy.advanced.DataSupplier;
import projectmetallurgy.metallurgy.advanced.client.SoundRegistry;
import projectmetallurgy.metallurgy.block.blockEntity.StoneAnvilBlockEntity;
import projectmetallurgy.metallurgy.item.ItemRegistry;
import projectmetallurgy.metallurgy.item.raw.ItemRawOre;

import java.util.function.Function;

public class BlockStoneAnvil extends Block implements EntityBlock {

    public static ForgeConfigSpec.ConfigValue<Double> LossRatio;
    public static ForgeConfigSpec.ConfigValue<Integer> MinGranularity;
    public static ForgeConfigSpec.ConfigValue<Double> CrushingRatio;

    public static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 8, 16);

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        StoneAnvilBlockEntity anvil = (StoneAnvilBlockEntity) pLevel.getBlockEntity(pPos);
        ItemStack itemStack = pPlayer.getMainHandItem();
        //手上和砧上都是矿
        if (itemStack.getItem() instanceof ItemRawOre && anvil.itemStackOn.getItem() instanceof ItemRawOre){
            return InteractionResult.PASS;
        }
        else
        //拿起物品
        if (itemStack.is(Items.AIR) && anvil.itemStackOn.getItem() instanceof ItemRawOre){
            pPlayer.getInventory().add(anvil.itemStackOn.copy());
            anvil.itemStackOn= new ItemStack(Items.AIR);
            anvil.setChanged();
            pLevel.setBlockAndUpdate(pPos,pState);
            return InteractionResult.SUCCESS;
        }
        else
        //放物品
        if (itemStack.getItem() instanceof ItemRawOre && anvil.itemStackOn.is(Items.AIR)) {
            ItemStack stack2place = itemStack.copy();
            stack2place.setCount(1);
            itemStack.setCount(itemStack.getCount()-1);
            anvil.itemStackOn = stack2place;
            anvil.setChanged();
            pLevel.setBlockAndUpdate(pPos,pState);
            return InteractionResult.SUCCESS;
        }
        else
        //粉碎物品
        if (itemStack.is(ItemRegistry.stoneHammerItem.get()) && anvil.itemStackOn.getItem() instanceof ItemRawOre){
            Function<CompoundTag,CompoundTag> crushingFunc = DataSupplier.crushProcessors.get(anvil.itemStackOn.getItem().getClass());
            CompoundTag tag = crushingFunc.apply(anvil.itemStackOn.getTag());
            anvil.itemStackOn.setTag(tag);
            anvil.setChanged();
            pLevel.setBlockAndUpdate(pPos,pState);
            if (pLevel.isClientSide) pLevel.playLocalSound(pPos.getX(),pPos.getY(),pPos.getZ(),SoundRegistry.crushingSound.get(),SoundSource.BLOCKS,1f,1f,true);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    public BlockStoneAnvil() {
        super(BlockBehaviour.Properties.of(Material.STONE).noOcclusion().destroyTime(0.5F));
    }
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new StoneAnvilBlockEntity(pPos,pState);
    }

}
