package draylar.gateofbabylon.particles;

import net.fabricmc.fabric.api.client.particle.v1.FabricSpriteProvider;
import net.minecraft.client.particle.DamageParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import org.jetbrains.annotations.Nullable;

public class ConstantColorDamageParticle extends DamageParticle {

    protected ConstantColorDamageParticle(ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
        super(clientWorld, d, e, f, g, h, i);
    }

    @Override
    public void tick() {
        final float g = this.colorGreen;
        final float b = this.colorBlue;
        super.tick();
        this.colorBlue = b;
        this.colorGreen = g;
    }

    public static class DefaultFactory implements ParticleFactory<ColoredCritParticleEffect> {

        private final SpriteProvider spriteProvider;

        public DefaultFactory(FabricSpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Nullable
        @Override
        public Particle createParticle(ColoredCritParticleEffect parameters, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            final var particle = new ConstantColorDamageParticle(world, x, y, z, velocityX, velocityY, velocityZ);
            particle.setSprite(spriteProvider);
            particle.setMaxAge(20);
            particle.setColor(parameters.getColor().getX(), parameters.getColor().getY(), parameters.getColor().getZ());
            return particle;
        }
    }
}
