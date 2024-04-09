package com.github.teruteru128.logger;

import org.bukkit.plugin.Plugin;

public class LoggerFactory {
    private final Logger logger;

    public LoggerFactory(Plugin plugin, String level) {
        logger = new Logger(plugin.getLogger(), level);
    }

    public Logger getInstance() {
        return logger;
    }
}
