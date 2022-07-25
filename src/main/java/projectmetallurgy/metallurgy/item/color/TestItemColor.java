package projectmetallurgy.metallurgy.item.color;

import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import org.w3c.dom.css.RGBColor;
import projectmetallurgy.metallurgy.block.MetaOre;
import projectmetallurgy.metallurgy.item.raw.ItemRawOre;

import java.awt.*;

public class TestItemColor implements ItemColor {

    @Override
    public int getColor(ItemStack pStack, int pTintIndex) {

        return DyeColor.BLACK.getFireworkColor();
    }
}
