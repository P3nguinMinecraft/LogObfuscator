package io.github.penguin.logobfuscator.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.villager.Villager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.Locale;

@Mixin(Villager.class)
public class VillagerMixin {
    @ModifyArg(
            method = "die",
            at = @At(
                    value = "INVOKE",
                    target = "Lorg/slf4j/Logger;info(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V"

            ),
            index = 1
    )
    private Object modifyDie(Object arg) {
        if (!(arg instanceof Villager villager)) {
            return arg;
        }

        return String.format(
                Locale.ROOT,
                "%s['%s'/%d, l='%s', x=%s, y=%s, z=%s]",
                villager.getClass().getSimpleName(),
                villager.getPlainTextName(),
                villager.getId(),
                villager.level(),
                "OBF_X",
                "OBF_Y",
                "OBF_Z"
        );
    }

    @ModifyArg(
            method = "thunderHit",
            at = @At(
                    value = "INVOKE",
                    target = "Lorg/slf4j/Logger;info(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V"

            ),
            index = 1
    )
    private Object modifyThunder(Object arg) {
        if (!(arg instanceof Villager villager)) {
            return arg;
        }

        return String.format(
                Locale.ROOT,
                "%s['%s'/%d, l='%s', x=%s, y=%s, z=%s]",
                villager.getClass().getSimpleName(),
                villager.getPlainTextName(),
                villager.getId(),
                villager.level(),
                "OBF_X",
                "OBF_Y",
                "OBF_Z"
        );
    }
}
