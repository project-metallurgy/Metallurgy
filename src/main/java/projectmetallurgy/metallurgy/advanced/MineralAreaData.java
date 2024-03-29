package projectmetallurgy.metallurgy.advanced;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;
import projectmetallurgy.metallurgy.item.raw.ItemRawOre;

import java.util.*;

public class MineralAreaData extends SavedData {

    public MineralAreaData(){
    }

    public static boolean inArea(BlockPos pos,int[][] points){
        int[] point1 = points[0];
        int[] point2 = points[1];
        return pos.getX() >= point1[0]
                &&
                pos.getX() <= point2[0]
                &&
                pos.getZ() >= point1[1]
                &&
                pos.getZ() <= point2[1];
    }

    private static final String NAME = "MINERAL_AREA";
    public Map<Class<?extends ItemRawOre>,Map<int[][],CompoundTag>> mineralAreas = new HashMap<>();



    public static MineralAreaData get(Level level){
        if (level instanceof ServerLevel serverLevel) {
            return serverLevel.getDataStorage().computeIfAbsent(MineralAreaData::read,MineralAreaData::new,NAME);
        }
        else {
            throw new RuntimeException("Cannot get mineralData from client.");
        }
    }

    public static MineralAreaData read(CompoundTag tag){
        MineralAreaData data = new MineralAreaData();
        data.mineralAreas=new HashMap<>();

        tag.getAllKeys().forEach(key -> {
            Class<? extends ItemRawOre> itemRawOreClass ;
            try {
                itemRawOreClass = (Class<? extends ItemRawOre>) Class.forName(key);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            CompoundTag entryOfCurrentOre = tag.getCompound(key);
            entryOfCurrentOre.getAllKeys().forEach((randomName)->{
                CompoundTag dataTag = entryOfCurrentOre.getCompound(randomName);
                int[] point1 = dataTag.getIntArray("point1");
                int[] point2 = dataTag.getIntArray("point2");

                int[][] pointArray = new int[2][2];
                pointArray[0] = point1;
                pointArray[1] = point2;
                Map<int[][],CompoundTag> innerMap =new HashMap<>();
                innerMap.put(pointArray,dataTag.getCompound("tag"));
                data.mineralAreas.put(itemRawOreClass,innerMap);
            });
            /*
            int[] point1 = singleEntry.getIntArray("point1");
            int[] point2 = singleEntry.getIntArray("point2");

            int[][] pointArray = new int[2][2];
            pointArray[0] = point1;
            pointArray[1] = point2;
            data.mineralAreas.put(pointArray,singleEntry.getCompound("tag"));
            */
            }
        );
        return data;
    }

    @Override
    public CompoundTag save(CompoundTag pCompoundTag) {

        CompoundTag tag = new CompoundTag();
        this.mineralAreas.forEach(((clazz, mapOfAreaAndTag) -> {
            CompoundTag containerTag = new CompoundTag();
            mapOfAreaAndTag.forEach((points,oreTag)->{
                CompoundTag singleEntry = new CompoundTag();
                singleEntry.putIntArray("point1",points[0]);
                singleEntry.putIntArray("point2",points[1]);
                singleEntry.put("tag",oreTag);
                containerTag.put(UUID.randomUUID().toString(),singleEntry);
            });

        tag.put(clazz.getName(),containerTag);
        }
         ));
        return tag;
    }


}
