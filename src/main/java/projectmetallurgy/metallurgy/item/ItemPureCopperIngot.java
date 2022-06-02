package projectmetallurgy.metallurgy.item;

import net.minecraft.world.item.Item;
import projectmetallurgy.metallurgy.creativeTab.MetallurgyCreativeTab;

public class ItemPureCopperIngot extends Item {
    public ItemPureCopperIngot() {
        super(new Properties().tab(MetallurgyCreativeTab.metal));
    }
}
