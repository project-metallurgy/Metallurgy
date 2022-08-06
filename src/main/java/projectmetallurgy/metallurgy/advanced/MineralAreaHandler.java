package projectmetallurgy.metallurgy.advanced;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

import projectmetallurgy.metallurgy.item.raw.ItemRawOre;

@Mod.EventBusSubscriber
public class MineralAreaHandler {
/*
@SubscribeEvent

    public static void onOreMine(BreakEvent event){
    if (event.getState().getBlock() instanceof MetaOre &&!event.getPlayer().getLevel().isClientSide){
        event.setCanceled(true);
        event.getWorld().setBlock(event.getPos(), Blocks.AIR.defaultBlockState(),0);
        BlockPos pos = event.getPos();
        MineralAreaData areaData = MineralAreaData.get(event.getPlayer().level);

        AtomicBoolean isRecorded = new AtomicBoolean(false);

        for (int[][] points:areaData.mineralAreas.keySet()){

            if(MineralAreaData.inArea(pos,points)) {

                isRecorded.set(true);
                CompoundTag tag = areaData.mineralAreas.get(points);
                Block block = event.getState().getBlock();
                ItemStack itemStack2drop = new ItemStack(Items.AIR);

                // FIXME: 2022/6/2 Add more ores cases here!
                if (block instanceof BlockChalcopyrite) {
                    itemStack2drop = new ItemStack(ItemRegistry.chalcopyriteItem.get());
                    itemStack2drop.setTag(tag);
                }


                event.getWorld().addFreshEntity(
                        new ItemEntity(event.getPlayer().level
                                , pos.getX(), pos.getY(), pos.getZ()
                                , itemStack2drop)
                );
                break;
            }
        }


        if (!isRecorded.get()){
            int [][] points = new int[2][2];
            points[0][0] = pos.getX()-50;
            points[0][1] = pos.getZ()-50;
            points[1][0] = pos.getX()+50;
            points[1][1] = pos.getZ()+50;
            CompoundTag tag =  new CompoundTag();
            tag.putString("test","testString");

            areaData.mineralAreas.put(points,tag);
            areaData.mineralAreas.keySet().forEach(points2 ->
                    {
                        if(MineralAreaData.inArea(pos,points2)){
                            isRecorded.set(true);
                            CompoundTag tag2 = areaData.mineralAreas.get(points2);

                            Block block = event.getState().getBlock();
                            ItemStack itemStack2drop = new ItemStack(Items.AIR);

                            // FIXME: 2022/6/2 Add more ores cases here!
                            if (block instanceof BlockChalcopyrite) {
                                itemStack2drop = new ItemStack(ItemRegistry.chalcopyriteItem.get());
                                itemStack2drop.setTag(tag);
                            }

                            event.getWorld().addFreshEntity(
                                    new ItemEntity(event.getPlayer().level
                                            ,pos.getX(),pos.getY(),pos.getZ()
                                            ,itemStack2drop)
                            );
                        }
                    }
            );
        }



    }
}*/

@SubscribeEvent
    public static void onDrop (EntityJoinWorldEvent event){
    if (event.getEntity() instanceof ItemEntity&& !event.getWorld().isClientSide)
    {
        ItemEntity entity = (ItemEntity) event.getEntity();
        if (entity.getItem().getTag() == null   &&  entity.getItem().getItem() instanceof ItemRawOre){
            BlockPos pos = event.getEntity().getOnPos();
            MineralAreaData areaData = MineralAreaData.get(event.getWorld());

            AtomicBoolean isRecorded = new AtomicBoolean(false);
            Map<int[][], CompoundTag> pointsSet = areaData.mineralAreas.get(entity.getItem().getItem().getClass());

            if (pointsSet==null){
                areaData.mineralAreas.put((Class<? extends ItemRawOre>) entity.getItem().getItem().getClass(),new HashMap<>());
                pointsSet = areaData.mineralAreas.get(entity.getItem().getItem().getClass());
            }

            for (int[][] points:pointsSet.keySet()){
                if(MineralAreaData.inArea(pos,points)) {
                    isRecorded.set(true);
                    CompoundTag tag = pointsSet.get(points);
                    entity.getItem().setTag(tag);
                    break;
                }
            }

            if (!isRecorded.get()){
                int [][] points = new int[2][2];
                points[0][0] = pos.getX()-50;
                points[0][1] = pos.getZ()-50;
                points[1][0] = pos.getX()+50;
                points[1][1] = pos.getZ()+50;
                Class clazz =entity.getItem().getItem().getClass();
                Supplier<CompoundTag> tagSupplier = DataSupplier.tagSuppliers.get(clazz);
                CompoundTag tag =  tagSupplier.get();
                pointsSet.put(points,tag);

                for(int[][] points2:pointsSet.keySet()){
                    if(MineralAreaData.inArea(pos,points2)){
                        CompoundTag tag2 = pointsSet.get(points2);
                        entity.getItem().setTag(tag2);
                        break;
                    }
                }
            }
            areaData.setDirty();
        }
    }
}
}
