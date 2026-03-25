package io.github.penguin.logobfuscator.mixin;

import java.util.Locale;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @ModifyArg(
        method = "die",
        at = @At(
            value = "INVOKE",
            target = "Lorg/slf4j/Logger;info(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V"

        ),
        index = 1
    )
    private Object modify(Object arg) {
        if (!(arg instanceof Entity entity)) {
            return arg;
        }

        return String.format(
                Locale.ROOT,
                "%s['%s'/%d, l='%s', x=%s, y=%s, z=%s]",
                entity.getClass().getSimpleName(),
                entity.getPlainTextName(),
                entity.getId(),
                entity.level(),
                "OBF_X",
                "OBF_Y",
                "OBF_Z"
        );
    }
}

