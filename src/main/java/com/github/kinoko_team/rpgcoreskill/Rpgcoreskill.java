package com.github.kinoko_team.rpgcoreskill;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.BlockIterator;

public final class Rpgcoreskill extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equals("hello")) {

            Player player = (Player)sender;

            // sendTitle(Title,SubTitle,FadeInTime,StayTime,FadeOutTime)
            player.sendTitle("Hello World!", "", 10, 70, 20);
        }
        return false;
    }

    /***
     * 稲妻（いなずま）を落とす
     * @param event
     */
    @EventHandler
    public void onSummonLightning(PlayerInteractEvent event) {


        Player player = event.getPlayer();

        if (player.getInventory().getItemInMainHand().getType() == Material.YELLOW_WOOL) {


            Block focusBlock = getCursorFocusBlock(player);


            if (focusBlock != null) {

                focusBlock.getWorld().strikeLightning(focusBlock.getLocation());
            }
        }
    }
    /***
     * マウスのカーソルがフォーカスしているブロックを取得（しゅとく）する
     */
    private Block getCursorFocusBlock(Player player) {


        BlockIterator blocks = new BlockIterator(player, 100);


        while (blocks.hasNext()) {


            Block block = blocks.next();

            if ( block.getType() != Material.AIR ) {

                return block;
            }
        }

        return null;
    }
}