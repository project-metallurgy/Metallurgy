package projectmetallurgy.metallurgy.item.tag;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.RegistryObject;

public class MetallurgyItemTags {
    public static final TagKey<Item> HAMMERS = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("hammers"));
}
