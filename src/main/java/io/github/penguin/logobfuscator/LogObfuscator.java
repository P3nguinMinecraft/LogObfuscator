package io.github.penguin.logobfuscator;

import io.github.penguin.logobfuscator.command.Base;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogObfuscator implements ModInitializer {
    public static String MOD_ID = "logobfuscator";
    public static Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    @Override
    public void onInitialize() {
        CommandRegistrationCallback.EVENT.register(Base::register);
        LOGGER.info("LogObfuscator initialized!");
    }
}