package projectmetallurgy.metallurgy.advanced.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

public class RockParticle extends TextureSheetParticle {

    protected RockParticle(ClientLevel p_108328_, double p_108329_, double p_108330_, double p_108331_, SpriteSet spriteSet, double p_108332_, double p_108333_, double p_108334_ ) {
        super(p_108328_, p_108329_, p_108330_, p_108331_, p_108332_, p_108333_, p_108334_);
        this.lifetime = 20;
        this.friction = 0.8F;
        this.quadSize *= 0.85F;
        this.setSpriteFromAge(spriteSet);
    }

    private void fadeOut() {
        this.alpha = (-(1/(float)lifetime) * age + 1);
    }

    @Override
    public void tick() {
        super.tick();
        fadeOut();
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType>{
        private final SpriteSet sprites;

        public Provider(SpriteSet spriteSet) {
            this.sprites = spriteSet;
        }

        public Particle createParticle(SimpleParticleType particleType, ClientLevel level,
                                       double x, double y, double z,
                                       double dx, double dy, double dz) {
            return new RockParticle(level, x, y, z,this.sprites, dx, dy, dz);
        }
    }

}
