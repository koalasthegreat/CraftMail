package dev.koalas.craftmail

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.Server
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandSendItem(plugin: CraftMail?) : CommandBase<CraftMail?>(plugin) {
    override fun runCommand(sender: CommandSender?, command: Command?, label: String?, args: Array<String>?): Boolean {
        if (sender!! is Player) {
            val player = sender as Player

            if (args != null) {
                val recipient = Bukkit.getPlayer(args[0])

                if (recipient != null) {
                    if (recipient.inventory.firstEmpty() != -1) {
                        val item = player.inventory.itemInMainHand

                        if (item.type != Material.AIR) {
                            player.setItemOnCursor(null)
                            recipient.inventory.addItem(item)
                            player.sendMessage("Item sent successfully to " + recipient.name)
                            recipient.sendMessage("Item recieved from " + player.name)
                        } else {
                            player.sendMessage("You need to be holding an item in order to send it to another player")
                            return false
                        }
                    } else {
                        player.sendMessage("Recipient's inventory is full")
                        return false
                    }
                } else {
                    player.sendMessage("Player not found")
                }
            } else {
                player.sendMessage("No player specified")
                return false
            }
        }
        return false
    }
}
