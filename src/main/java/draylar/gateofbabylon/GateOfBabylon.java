package draylar.gateofbabylon;

import draylar.gateofbabylon.particles.ColoredCritParticleEffect;
import draylar.gateofbabylon.registry.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class GateOfBabylon implements ModInitializer {

    public static final ItemGroup GROUP = FabricItemGroupBuilder.build(id("group"), () -> new ItemStack(GOBItems.DIAMOND_SPEAR));

    public static final ParticleType<ColoredCritParticleEffect> COLORED_CRIT;

    static {
        COLORED_CRIT = Registry.register(Registry.PARTICLE_TYPE, GateOfBabylon.id("colored_crit"), FabricParticleTypes.complex(ColoredCritParticleEffect.PARAMETERS_FACTORY));
    }

    public static Identifier id(String name) {
        return new Identifier("gateofbabylon", name);
    }

    @Override
    public void onInitialize() {
        GOBEffects.init();
        GOBItems.init();
        GOBEnchantments.init();
        GOBEntities.init();
        GOBBlocks.init();
        GOBSounds.init();
    }
}
