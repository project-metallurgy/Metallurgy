package projectmetallurgy.metallurgy.item.tool;

import net.minecraft.world.item.Item;
import projectmetallurgy.metallurgy.creativeTab.MetallurgyCreativeTab;

public class ItemStoneHammer extends Item {
    public ItemStoneHammer() {
        super(new Properties().tab(MetallurgyCreativeTab.metal).durability(75).stacksTo(1));
    }
}
