package org.black_ixx.playerpoints;

import lombok.val;
import org.bukkit.Bukkit;

import java.util.UUID;

/**
 * @author Yuhtin
 * Github: https://github.com/Yuhtin
 */
public class PlayerPointsAPI {

    private final NextCashAPI instance;

    public PlayerPointsAPI() {
        this.instance = NextCashAPI.getInstance();
    }

    public int look(String name) {

        val accountByOwner = instance.findAccountByOwner(name);
        return accountByOwner.map(account -> (int) account.getBalance()).orElse(0);

    }

    public int look(UUID uuid) {

        val accountByOwner = instance.findAccountByOwner(Bukkit.getPlayer(uuid).getName());
        return accountByOwner.map(account -> (int) account.getBalance()).orElse(0);

    }

    public boolean take(String name, int amount) {

        val accountByOwner = instance.findAccountByOwner(name);
        if (!accountByOwner.isPresent() || accountByOwner.get().getBalance() < amount) return false;

        accountByOwner.get().withdrawAmount(amount);
        return true;

    }

    public boolean take(UUID uuid, int amount) {

        val accountByOwner = instance.findAccountByOwner(Bukkit.getOfflinePlayer(uuid).getName());
        if (!accountByOwner.isPresent() || accountByOwner.get().getBalance() < amount) return false;

        accountByOwner.get().withdrawAmount(amount);
        return true;

    }

    public boolean give(UUID uuid, int amount) {
        val accountByOwner = instance.findAccountByOwner(Bukkit.getOfflinePlayer(uuid).getName());
        if (!accountByOwner.isPresent()) return false;

        accountByOwner.get().depositAmount(amount);
        return true;
    }

    public boolean give(String name, int amount) {
        val accountByOwner = instance.findAccountByOwner(name);
        if (!accountByOwner.isPresent()) return false;

        accountByOwner.get().depositAmount(amount);
        return true;
    }

    public boolean pay(String name, String target, int amount) {

        if (!take(name, amount)) return false;
        return give(target, amount);

    }

    public boolean set(String name, int amount) {
        val accountByOwner = instance.findAccountByOwner(name);
        if (!accountByOwner.isPresent()) return false;

        accountByOwner.get().setBalance(amount);
        return true;
    }

    public boolean set(UUID uuid, int amount) {
        val accountByOwner = instance.findAccountByOwner(Bukkit.getOfflinePlayer(uuid).getName());
        if (!accountByOwner.isPresent()) return false;

        accountByOwner.get().setBalance(amount);
        return true;
    }

    public boolean reset(String name, int amount) {
        return set(name, 0);
    }

    public boolean reset(UUID uuid, int amount) {
        return set(uuid, 0);
    }

}
