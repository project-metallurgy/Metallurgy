package projectmetallurgy.metallurgy.item.color;

import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import projectmetallurgy.metallurgy.block.iron.BlockHematite;
import projectmetallurgy.metallurgy.item.raw.ItemRawHematite;
import projectmetallurgy.metallurgy.item.raw.ItemRawOre;

@Mod.EventBusSubscriber
public class RegColor {
@SubscribeEvent
    public static void reg(ColorHandlerEvent.Item event){

    event.getItemColors().register(new TestItemColor(),new ItemRawHematite());

}
}
