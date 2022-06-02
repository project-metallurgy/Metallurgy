package projectmetallurgy.metallurgy.block;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import projectmetallurgy.metallurgy.Metallurgy;

public class LootDataProvider extends GlobalLootModifierProvider {
    private static final DeferredRegister<GlobalLootModifierSerializer<?>> GLM = DeferredRegister.create(ForgeRegistries.LOOT_MODIFIER_SERIALIZERS.get(), Metallurgy.MOD_ID);
    private static final RegistryObject<BlockCopperOre.MineModifier.Serializer> OER_HARVEST = GLM.register("ore_harvest", BlockCopperOre.MineModifier.Serializer::new);

    public LootDataProvider(DataGenerator gen, String modid) {
        super(gen, modid);
    }

    @Override
    protected void start() {
        add("ore_harvest", OER_HARVEST.get(), new BlockCopperOre.MineModifier(
                new LootItemCondition[]{})
        );
    }
}
