package projectmetallurgy.metallurgy.advanced;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.atomic.AtomicBoolean;

import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import projectmetallurgy.metallurgy.block.BlockCopperOre;
import projectmetallurgy.metallurgy.item.ItemRegistry;

@Mod.EventBusSubscriber
public class MineralAreaHandler {
@SubscribeEvent
    public static void onOreMine(BreakEvent event){
    if (event.getState().getBlock() instanceof BlockCopperOre&&!event.getPlayer().getLevel().isClientSide){
        event.setCanceled(true);
        event.getWorld().setBlock(event.getPos(), Blocks.AIR.defaultBlockState(),0);
        BlockPos pos = event.getPos();
        MineralAreaData areaData = MineralAreaData.get(event.getPlayer().level);

        AtomicBoolean isRecorded = new AtomicBoolean(false);

// FIXME: 2022/5/28
        System.out.println("TEST! It's really work!");

        areaData.mineralAreas.keySet().forEach(points ->
                {
                    if(MineralAreaData.inArea(pos,points)){
                        isRecorded.set(true);
                        CompoundTag tag =  areaData.mineralAreas.get(points);

                        ItemStack itemStack2drop = new ItemStack(ItemRegistry.copperOre.get());
                        itemStack2drop.setTag(tag);

                        event.getWorld().addFreshEntity(
                                new ItemEntity(event.getPlayer().level
                                ,pos.getX(),pos.getY(),pos.getZ()
                                        ,itemStack2drop)
                        );
                    }
                }
        );

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
                            CompoundTag tag2 =  areaData.mineralAreas.get(points2);

                            ItemStack itemStack2drop = new ItemStack(ItemRegistry.copperOre.get());
                            itemStack2drop.setTag(tag2);

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
}

}
