package projectmetallurgy.metallurgy.item.tool;

import projectmetallurgy.metallurgy.creativeTab.MetallurgyCreativeTab;

public class ItemStonePestle extends ItemPestle{
    public ItemStonePestle(){
        super(new Properties().durability(75).defaultDurability(75).tab(MetallurgyCreativeTab.metal));
    }
}
