package me.prison.prisonplugin;


import me.prison.prisonplugin.board.QubiqueBoard;
import me.prison.prisonplugin.gui.CreateAndSetGUI;
import me.prison.prisonplugin.listeners.PrisonCore;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class Prison extends JavaPlugin {

    private static Economy economy = null;

    public static Prison instance;

    public QubiqueBoard qubiqueBoard = new QubiqueBoard();

    @Override
    public void onEnable() {
        //economy
        if (!setupEconomy() ) {
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        //
        getServer().getPluginManager().registerEvents(new CreateAndSetGUI(),this);
        getServer().getPluginManager().registerEvents(new PrisonCore(),this);
        instance = this;
    }

    public static Prison getInstance() { return instance; }

    private boolean setupEconomy()
    {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return (economy != null);
    }

    public static Economy getEconomy() {
        return economy;
    }

}
