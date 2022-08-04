package projectmetallurgy.metallurgy.block.device;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.ForgeConfigSpec;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import projectmetallurgy.metallurgy.advanced.DataSupplier;
import projectmetallurgy.metallurgy.advanced.client.ParticleRegistry;
import projectmetallurgy.metallurgy.advanced.client.SoundRegistry;
import projectmetallurgy.metallurgy.block.blockEntity.BlockEntityRegistry;
import projectmetallurgy.metallurgy.block.blockEntity.MortarBlockEntity;
import projectmetallurgy.metallurgy.block.blockEntity.StoneAnvilBlockEntity;
import projectmetallurgy.metallurgy.item.ItemRegistry;
import projectmetallurgy.metallurgy.item.raw.ItemRawOre;

import java.util.function.Function;

public class BlockMortar extends Block implements EntityBlock{

    public static ForgeConfigSpec.ConfigValue<Integer> VeinstoneCorrection;
    public static ForgeConfigSpec.ConfigValue<Integer> MinGranularity;
    public static ForgeConfigSpec.ConfigValue<Double> SmashingRatio;

    public BlockMortar() {
        super(BlockBehaviour.Properties.of(Material.STONE).noOcclusion().destroyTime(0.5F));
    }
    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return BlockEntityRegistry.MORTAR_BLOCK_ENTITY.get().create(pos, state);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        MortarBlockEntity mortar = (MortarBlockEntity) pLevel.getBlockEntity(pPos);
        ItemStack itemStack = pPlayer.getMainHandItem();
        //手上和砧上都是矿
        if (itemStack.getItem() instanceof ItemRawOre && mortar.itemStackOn.getItem() instanceof ItemRawOre){
            return InteractionResult.PASS;
        }
        else
            //拿起物品
            if (itemStack.is(Items.AIR) && mortar.itemStackOn.getItem() instanceof ItemRawOre){
                pPlayer.getInventory().add(mortar.itemStackOn.copy());
                mortar.itemStackOn= new ItemStack(Items.AIR);
                mortar.setChanged();
                pLevel.setBlockAndUpdate(pPos,pState);
                return InteractionResult.SUCCESS;
            }
            else
                //放物品
                if (itemStack.getItem() instanceof ItemRawOre && mortar.itemStackOn.is(Items.AIR)) {
                    ItemStack stack2place = itemStack.copy();
                    stack2place.setCount(1);
                    itemStack.setCount(itemStack.getCount()-1);
                    mortar.itemStackOn = stack2place;
                    mortar.setChanged();
                    pLevel.setBlockAndUpdate(pPos,pState);
                    return InteractionResult.SUCCESS;
                }
                else
                    //研磨物品
                    if (itemStack.is(ItemRegistry.stonePestleItem.get()) && mortar.itemStackOn.getItem() instanceof ItemRawOre){
                        Function<CompoundTag,CompoundTag> smashingFunc = DataSupplier.smashProcessors.get(mortar.itemStackOn.getItem().getClass());
                        CompoundTag tag = smashingFunc.apply(mortar.itemStackOn.getTag());
                        mortar.itemStackOn.setTag(tag);
                        mortar.setChanged();
                        pLevel.setBlockAndUpdate(pPos,pState);
                        itemStack.setDamageValue(itemStack.getDamageValue()-1);
                        if (pLevel.isClientSide) {
                            pLevel.playLocalSound(pPos.getX(), pPos.getY(), pPos.getZ(), SoundRegistry.smashingSound.get(), SoundSource.BLOCKS, 2f, 1f, true);
                            int i =0;
                            while (i<4) {
                                pLevel.addParticle(ParticleRegistry.ROCK.get(), pPos.getX()+0.5, pPos.getY()+0.9, pPos.getZ()+0.5, 1, 0.15, 1);
                                i += 1;
                            }
                        }
                        //质量为零就磨烂了
                        if (mortar.itemStackOn.getTag().getInt("mass")==0){
                            mortar.itemStackOn.setCount(0);
                            if (pLevel.isClientSide) {
                                int i = 0;
                                while (i<20) {
                                    pLevel.addParticle(ParticleRegistry.ROCK.get(), pPos.getX()+0.5, pPos.getY()+0.9, pPos.getZ()+0.5, 1, 0.15, 1);
                                    i += 1;
                                }
                            }
                        }
                        return InteractionResult.SUCCESS;
                    }
        return InteractionResult.PASS;
    }
}