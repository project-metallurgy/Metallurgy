package projectmetallurgy.metallurgy;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.slf4j.Logger;
import projectmetallurgy.metallurgy.advanced.DataSupplier;
import projectmetallurgy.metallurgy.advanced.MetallurgyConfigs;
import projectmetallurgy.metallurgy.advanced.client.ParticleRegistry;
import projectmetallurgy.metallurgy.advanced.client.SoundRegistry;
import projectmetallurgy.metallurgy.block.BlockRegistry;
import projectmetallurgy.metallurgy.block.blockEntity.BlockEntityRegistry;
import projectmetallurgy.metallurgy.item.ItemRegistry;
import projectmetallurgy.metallurgy.item.raw.ItemRawChalcopyrite;
import projectmetallurgy.metallurgy.item.raw.ItemRawHematite;
import projectmetallurgy.metallurgy.item.raw.ItemRawMalachite;
import projectmetallurgy.metallurgy.item.raw.ItemRawOre;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Random;

@Mod(Metallurgy.MOD_ID)
public class Metallurgy {

    public static final String MOD_ID = "metallurgy";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final Codec<OreConfiguration> CODEC = RecordCodecBuilder.create((p_67849_)
            -> p_67849_.group(Codec.list(OreConfiguration.TargetBlockState.CODEC).fieldOf("targets").forGetter((p_161027_)
                    -> p_161027_.targetStates), Codec.intRange(0, 80).fieldOf("size").forGetter((p_161025_)
                    -> p_161025_.size),
            Codec.floatRange(0.0F, 1.0F).fieldOf("discard_chance_on_air_exposure").forGetter((p_161020_)
                    -> p_161020_.discardChanceOnAirExposure)).apply(p_67849_, OreConfiguration::new)
    );

    public static final Random RANDOM = new Random();

    public Metallurgy() {
        var modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        DataSupplier.onSetup();
        // TODO: 2022/8/4 Add all of the item raw ores here! 
        List<Class<? extends ItemRawOre>> listOfItemRawOre = ItemRawOre.listOfItemRawOre;
        listOfItemRawOre.add(ItemRawHematite.class);
        listOfItemRawOre.add(ItemRawMalachite.class);
        listOfItemRawOre.add(ItemRawChalcopyrite.class);

        boolean isProduction = FMLEnvironment.production;
        // FIXME: 2022/7/25 Remember to turn oreGen on!

        Field CODEC = null;

        try {
            if (!isProduction) {
                CODEC = OreConfiguration.class.getDeclaredField("CODEC");
            }else {
                CODEC = Class.forName("net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration").getDeclaredField("f_67837_");
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println(isProduction);
        try {
            CODEC.setAccessible(true);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        try {
            setFinalStatic(CODEC,Metallurgy.CODEC);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, MetallurgyConfigs.CONFIG_SPEC,"metallurgy-configs.toml");
        BlockRegistry.BLOCKS.register(modEventBus);
        ItemRegistry.ITEMS.register(modEventBus);
        SoundRegistry.SOUNDS.register(modEventBus);
        ParticleRegistry.PARTICLE.register(modEventBus);
        BlockEntityRegistry.TILE_ENTITY_TYPES.register(modEventBus);
    }


    public static void setFinalStatic(Field field, Object newValue) throws NoSuchFieldException, IllegalAccessException {
        field.setAccessible(true);
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);

        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        field.set(null, newValue);
    }
}
