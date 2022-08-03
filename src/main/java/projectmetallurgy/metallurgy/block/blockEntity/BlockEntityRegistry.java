package projectmetallurgy.metallurgy.block.blockEntity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import projectmetallurgy.metallurgy.Metallurgy;
import projectmetallurgy.metallurgy.block.BlockRegistry;

public class BlockEntityRegistry {
    public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Metallurgy.MOD_ID);

    public static final RegistryObject<BlockEntityType<BarrelBlockEntity>> BARREL_BLOCK_ENTITY =
            TILE_ENTITY_TYPES.register
                    ("barrel_block_entity",
                    () -> BlockEntityType.Builder.of(BarrelBlockEntity::new, BlockRegistry.barrelBlock.get()).build(null)
            );

    public static final RegistryObject<BlockEntityType<MortarBlockEntity>> MORTAR_BLOCK_ENTITY =
            TILE_ENTITY_TYPES.register
                    ("mortar_block_entity",
                    () -> BlockEntityType.Builder.of(MortarBlockEntity::new, BlockRegistry.mortarBlock.get()).build(null)
            );

    public static RegistryObject<BlockEntityType<StoneAnvilBlockEntity>> STONE_ANVIL_BLOCK_ENTITY =
        TILE_ENTITY_TYPES
                .register("stone_anvil_block_entity",
                () ->
                        BlockEntityType.Builder.of(StoneAnvilBlockEntity::new,BlockRegistry.stoneAnvilBlock.get())
                                .build(null));
}

