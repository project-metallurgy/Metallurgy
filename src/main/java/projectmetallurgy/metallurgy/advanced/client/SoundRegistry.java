package projectmetallurgy.metallurgy.advanced.client;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import projectmetallurgy.metallurgy.Metallurgy;

public class SoundRegistry {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Metallurgy.MOD_ID);
    public static final RegistryObject<SoundEvent> crushingSound = SOUNDS.register("crushing", () -> new SoundEvent(new ResourceLocation(Metallurgy.MOD_ID, "crushing")));

}
