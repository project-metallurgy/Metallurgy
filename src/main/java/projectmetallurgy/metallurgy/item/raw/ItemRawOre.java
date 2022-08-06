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
import java.util.ArrayList;
import java.util.List;

public class ItemRawOre extends Item {

    public static List<Class<? extends ItemRawOre>> listOfItemRawOre = new ArrayList<>();

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

            //temp
            int cu_mass = pStack.getTag().getInt("cu_mass");
            int fe_mass = pStack.getTag().getInt("fe_mass");
            int veinstone_mass = pStack.getTag().getInt("veinstone_mass");
            double eta = pStack.getTag().getDouble("eta");
            if (cu_mass != 0){
                pTooltipComponents.add(new TextComponent("铜质量："+cu_mass));
            }
            if (fe_mass != 0){
                pTooltipComponents.add(new TextComponent("铁质量："+fe_mass));
            }
            if (veinstone_mass != 0){
                pTooltipComponents.add(new TextComponent("脉石质量："+veinstone_mass));
            }
            if (eta != 0){
                pTooltipComponents.add(new TextComponent("分离比："+eta));
            }
            //

            pTooltipComponents.add(new TextComponent(stringBuilder.toString()));

            pTooltipComponents.add(new TextComponent(I18n.get("label.granularity")+granularity));
        }
    }
}
