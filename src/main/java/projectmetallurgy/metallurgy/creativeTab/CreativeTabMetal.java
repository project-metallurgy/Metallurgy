package projectmetallurgy.metallurgy.creativeTab;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import projectmetallurgy.metallurgy.item.ItemRegistry;

public class CreativeTabMetal extends CreativeModeTab {
    public CreativeTabMetal() {
        super("metallurgy_metal");
    }

    @Override
    public @NotNull ItemStack makeIcon() {
        return new ItemStack(ItemRegistry.rawHematiteItem.get());
    }
}
