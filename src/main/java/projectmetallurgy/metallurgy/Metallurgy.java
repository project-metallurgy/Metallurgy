package projectmetallurgy.metallurgy;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import projectmetallurgy.metallurgy.block.BlockRegistry;
import projectmetallurgy.metallurgy.item.ItemRegistry;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

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

    public Metallurgy() {
        var modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        Field CODEC = null;

        try {
            CODEC = OreConfiguration.class.getDeclaredField("CODEC");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

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
        BlockRegistry.BLOCKS.register(modEventBus);
        ItemRegistry.ITEMS.register(modEventBus);

    }
    public static void setFinalStatic(Field field, Object newValue) throws NoSuchFieldException, IllegalAccessException {
        field.setAccessible(true);
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);

        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        field.set(null, newValue);
    }
}
