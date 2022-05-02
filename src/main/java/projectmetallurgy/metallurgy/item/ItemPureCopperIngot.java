package projectmetallurgy.metallurgy.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import projectmetallurgy.metallurgy.Metallurgy;
import projectmetallurgy.metallurgy.creativeTab.MetallurgyCreativeTab;

public class ItemPureCopperIngot extends Item {
    public ItemPureCopperIngot() {
        super(new Properties().tab(MetallurgyCreativeTab.metal));
    }
}
