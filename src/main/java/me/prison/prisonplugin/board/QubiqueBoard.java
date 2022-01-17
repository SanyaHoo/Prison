package me.prison.prisonplugin.board;

import me.prison.prisonplugin.Prison;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class QubiqueBoard {

    public void setScoreBoard(Player p) {
        Economy economy = Prison.getEconomy();
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = board.registerNewObjective("Qubique","dummy");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName("§bPrison");

        Team privelege = board.registerNewTeam("privelegia");

        obj.getScore("§bИнфо").setScore(10);
        obj.getScore("Ник §8» §f" + p.getDisplayName()).setScore(9);
        obj.getScore("Фракция §8» §f").setScore(8);
        obj.getScore("§1").setScore(7);
        obj.getScore("Онлайн §8»").setScore(6);
        obj.getScore("Уровень §8» " + p.getLevel()).setScore(5);
        obj.getScore("Деньги §8» §f" + economy.getBalance(p)).setScore(4);
        obj.getScore("Блоки §8» §f").setScore(3);
        obj.getScore("Мобы §8» §f").setScore(2);
        obj.getScore("Убийства §8» §f").setScore(1);
        obj.getScore("    www.qubique.ru").setScore(0);
        privelege.addEntry("§e");
        privelege.setPrefix("§e");

        p.setScoreboard(board);
    }

}