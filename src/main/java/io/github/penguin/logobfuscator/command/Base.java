package io.github.penguin.logobfuscator.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import io.github.penguin.logobfuscator.LogObfuscator;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public class Base {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext buildContext, Commands.CommandSelection commandSelection) {
        var command = Commands.literal("logobfuscator")
                        .executes((CommandContext<CommandSourceStack> context) -> {
                            String version = FabricLoader.getInstance()
                                    .getModContainer(LogObfuscator.MOD_ID)
                                    .map(container -> container.getMetadata().getVersion().getFriendlyString())
                                    .orElse("[unknown version]");

                            if (commandSelection.includeIntegrated) {
                                context.getSource().sendSystemMessage(Component.literal("LogObfuscator " + version + " is installed on the client"));
                            }
                            else if (commandSelection.includeDedicated) {
                                context.getSource().sendSystemMessage(Component.literal("LogObfuscator " + version + " is installed on the server"));
                            }
                            return Command.SINGLE_SUCCESS;
                        });

        dispatcher.register(command);

    }
}
