package com.github.teruteru128.logger;

import org.apache.logging.log4j.Level;
import org.bukkit.plugin.Plugin;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class Logger {
    private static final Map<Plugin, Logger> map = Collections.synchronizedMap(new HashMap<>());
    private final java.util.logging.Logger _logger;
    private final Level configuredLevel;

    Logger(java.util.logging.Logger logger, String level) {
        this(logger, Level.toLevel(level, Level.INFO));
    }

    Logger(java.util.logging.Logger logger, Level level) {
        this._logger = logger;
        this.configuredLevel = level;
    }

    public static void register(Plugin plugin, String logLevel) {
        map.put(plugin, new Logger(plugin.getLogger(), logLevel));
    }

    public static Logger getInstance(Plugin plugin) {
        return map.get(plugin);
    }

    public static void unregister(Plugin plugin) {
        map.remove(plugin);
    }

    public void fatal(String msg) {
        this.log(Level.FATAL, java.util.logging.Level.SEVERE, msg);
    }

    public void error(String msg) {
        this.log(Level.ERROR, java.util.logging.Level.SEVERE, msg);
    }

    public void warn(String msg) {
        this.log(Level.WARN, java.util.logging.Level.WARNING, msg);
    }

    public void info(String msg) {
        this.log(Level.INFO, java.util.logging.Level.INFO, msg);
    }

    public void debug(String msg) {
        this.log(Level.DEBUG, java.util.logging.Level.INFO, msg);
    }

    public void trace(String msg) {
        this.log(Level.TRACE, java.util.logging.Level.INFO, msg);
    }

    public void log(org.apache.logging.log4j.Level viewLevel, java.util.logging.Level logLevel, String msg) {
        if (viewLevel.compareTo(this.configuredLevel) >= 0) {
            this._logger.log(logLevel, msg);
        }
    }

    public java.util.logging.Logger getLogger() {
        return this._logger;
    }
}
