package projectmetallurgy.metallurgy.advanced.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import projectmetallurgy.metallurgy.Metallurgy;
import projectmetallurgy.metallurgy.advanced.client.particle.RockParticle;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ParticleRegistry {
    public static final DeferredRegister<ParticleType<?>> PARTICLE = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Metallurgy.MOD_ID);
    public static RegistryObject<SimpleParticleType> ROCK = PARTICLE.register("rock",()->new SimpleParticleType(true));

    @SubscribeEvent
    public static void reg(ParticleFactoryRegisterEvent event){
        //Minecraft.getInstance().particleEngine.register(ROCK.get(), new RockParticle.Provider(event));
    }
}
