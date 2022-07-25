package projectmetallurgy.metallurgy.item.raw;

import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import projectmetallurgy.metallurgy.creativeTab.MetallurgyCreativeTab;

import javax.annotation.Nullable;
import java.util.List;

public class ItemRawOre extends Item {
    public ItemRawOre() {
        super(new Properties().tab(MetallurgyCreativeTab.metal));
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if (pStack.getTag() != null) {
            int mass = pStack.getTag().getInt("mass");
            int granularity = pStack.getTag().getInt("granularity");

            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append(I18n.get("label.mass"));
            stringBuilder.append(mass);
            pTooltipComponents.add(new TextComponent(stringBuilder.toString()));

            pTooltipComponents.add(new TextComponent(I18n.get("label.granularity")+granularity));
        }
    }
}
