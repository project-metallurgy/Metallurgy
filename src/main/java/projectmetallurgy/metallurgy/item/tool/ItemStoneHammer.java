package projectmetallurgy.metallurgy.item.tool;

import net.minecraft.world.item.Item;
import projectmetallurgy.metallurgy.creativeTab.MetallurgyCreativeTab;

public class ItemStoneHammer extends ItemHammer {
    public ItemStoneHammer() {
        super(new Properties().durability(75).durability(75).tab(MetallurgyCreativeTab.metal));
    }
}
