package dev.splityosis.sysengine;

import dev.splityosis.sysengine.actions.ActionTypeRegistry;
import dev.splityosis.sysengine.commandlib.CommandLib;
import dev.splityosis.sysengine.commandlib.manager.CommandManager;
import dev.splityosis.sysengine.configlib.ConfigLib;
import dev.splityosis.sysengine.configlib.manager.ConfigManager;
import dev.splityosis.sysengine.plugin.commands.SYSEngineCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class SYSEngine extends JavaPlugin {

    public static SYSEngine plugin;
    private static boolean isInitialized = false;

    private CommandManager commandManager;
    private ConfigManager configManager;

    @Override
    public void onEnable() {
        plugin = this;
        initialize(this);

        commandManager = CommandLib.createCommandManager(this);
        configManager = ConfigLib.createConfigManager(this);

        commandManager.registerCommands(new SYSEngineCommand());
    }


    @Override
    public void onDisable() {

    }

    /**
     * Initializes the libraries and whatever needs to be initialized for this engine.
     * You only need to call this if you are shading in the engine, else this gets called for you.
     *
     * @param plugin The plugin initializing the libraries
     * @Note This disables the plugin if it fails
     */
    public static void initialize(JavaPlugin plugin) {
        if (isInitialized) return;
        isInitialized = true;

        // Removed since reverting to older version.
//        if (!NBT.preloadApi()) {
//            plugin.getLogger().warning("NBT-API wasn't initialized properly, disabling the plugin");
//            plugin.getPluginLoader().disablePlugin(plugin);
//            return;
//        }

        ConfigLib.initialize();
        ActionTypeRegistry.initialize();
    }

}
