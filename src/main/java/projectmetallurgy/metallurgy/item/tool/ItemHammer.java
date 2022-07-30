package projectmetallurgy.metallurgy.item.tool;

import net.minecraft.world.item.Item;
import projectmetallurgy.metallurgy.creativeTab.MetallurgyCreativeTab;

public class ItemHammer extends Item {

    public ItemHammer() {
        super(new Properties().tab(MetallurgyCreativeTab.metal).durability(75));
    }
}
