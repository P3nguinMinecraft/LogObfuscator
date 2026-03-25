package io.github.penguin.logobfuscator.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import net.minecraft.server.players.PlayerList;

@Mixin(PlayerList.class)
public abstract class PlayerListMixin {
    /**
     * 0: player name
     * 1: IP address
     * 2: entity id
     * 3: x
     * 4: y
     * 5: z
     */
    @ModifyArgs(
        method = "placeNewPlayer",
        at = @At(
            value = "INVOKE",
            target = "Lorg/slf4j/Logger;info(Ljava/lang/String;[Ljava/lang/Object;)V"
        )
    )
    private void modify(Args args) {
        Object[] loggedArgs = args.get(1);
        if (loggedArgs == null || loggedArgs.length < 6) {
            return;
        }

        loggedArgs[1] = "OBF_IP";
        loggedArgs[3] = "OBF_X";
        loggedArgs[4] = "OBF_Y";
        loggedArgs[5] = "OBF_Z";
    }
}

