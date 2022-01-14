package org.black_ixx.playerpoints;

import lombok.Getter;
import lombok.val;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class PlayerPoints extends JavaPlugin {

    @Getter private final PlayerPointsAPI API = new PlayerPointsAPI();

    @Override
    public void onEnable() {
        val logger = Logger.getLogger("PlayerPoints (FAKE)");
        logger.info("Plugin iniciado com sucesso");
    }

    public static PlayerPoints getInstance() {
        return getPlugin(PlayerPoints.class);
    }

}
