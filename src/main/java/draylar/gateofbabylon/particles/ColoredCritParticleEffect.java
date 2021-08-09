package draylar.gateofbabylon.particles;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import draylar.gateofbabylon.GateOfBabylon;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.particle.AbstractDustParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.util.math.Vec3f;

public class ColoredCritParticleEffect implements ParticleEffect {

    public static final Codec<ColoredCritParticleEffect> CODEC;
    public static final ParticleEffect.Factory<ColoredCritParticleEffect> PARAMETERS_FACTORY;

    static {

        CODEC = RecordCodecBuilder.create(instance -> {
            return instance.group(Vec3f.CODEC.fieldOf("color").forGetter(o -> {
                return o.color;
            })).apply(instance, ColoredCritParticleEffect::new);
        });

        PARAMETERS_FACTORY = new Factory<>() {
            @Override
            public ColoredCritParticleEffect read(ParticleType<ColoredCritParticleEffect> type, StringReader reader) throws CommandSyntaxException {
                return new ColoredCritParticleEffect(AbstractDustParticleEffect.readColor(reader));
            }

            @Override
            public ColoredCritParticleEffect read(ParticleType<ColoredCritParticleEffect> type, PacketByteBuf buf) {
                return new ColoredCritParticleEffect(AbstractDustParticleEffect.readColor(buf));
            }
        };

    }

    private final Vec3f color;

    public ColoredCritParticleEffect(Vec3f color) {
        this.color = color;
    }

    @Override
    public ParticleType<?> getType() {
        return GateOfBabylon.COLORED_CRIT;
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeFloat(color.getX());
        buf.writeFloat(color.getY());
        buf.writeFloat(color.getZ());
    }

    @Override
    public String asString() {
        return "colored crit";
    }

    public Vec3f getColor() {
        return color;
    }
}
