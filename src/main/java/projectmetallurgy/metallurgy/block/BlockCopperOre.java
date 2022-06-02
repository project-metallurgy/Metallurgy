package projectmetallurgy.metallurgy.block;

import com.google.gson.JsonObject;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;
import projectmetallurgy.metallurgy.advanced.MineralAreaData;
import projectmetallurgy.metallurgy.item.ItemRegistry;

import java.util.List;
import java.util.UUID;

public class BlockCopperOre extends Block {
    public BlockCopperOre(){
        super(BlockBehaviour.Properties.of(Material.STONE).strength(1.5f, 6.0f).sound(SoundType.STONE).requiresCorrectToolForDrops());
    }


    public static class MineModifier extends LootModifier {

        /**
         * Constructs a LootModifier.
         *
         * @param conditionsIn the ILootConditions that need to be matched before the loot is modified.
         */
        protected MineModifier(LootItemCondition[] conditionsIn) {
            super(conditionsIn);
        }

        @NotNull
        @Override
        protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
            if (generatedLoot.get(0).is(ItemRegistry.copperOre.get())) {
                context.getLevel().getServer().sendMessage(new TextComponent("test"), UUID.randomUUID());

                MineralAreaData mineralAreaData = MineralAreaData.get(context.getLevel());
            }
            return generatedLoot;
        }

        public static class Serializer extends GlobalLootModifierSerializer<MineModifier> {

            @Override
            public MineModifier read(ResourceLocation location, JsonObject object, LootItemCondition[] ailootcondition) {
                return new MineModifier(ailootcondition);
            }

            @Override
            public JsonObject write(MineModifier instance) {
                return makeConditions(instance.conditions);
            }
        }

    }

}
