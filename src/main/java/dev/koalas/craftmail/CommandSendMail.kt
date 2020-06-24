package dev.koalas.craftmail

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandSendMail(plugin: CraftMail?) : CommandBase<CraftMail?>(plugin) {
    override fun runCommand(sender: CommandSender?, command: Command?, label: String?, args: Array<String>?): Boolean {
        // TODO: rewrite this better
        val player = sender as? Player

        if (player != null) {
            if (args != null) {
                val recipient = Bukkit.getPlayer(args[1])

                if (recipient != null) {
                    val book = player.inventory.itemInMainHand

                    if (book.type == Material.WRITTEN_BOOK) {
                        // TODO: store book data in database

                        recipient.sendMessage(player.name + " just sent you a letter!")
                        player.sendMessage("Your letter has been sent to " + recipient.name + ".")
                        return true
                    }
                    else {
                        player.sendMessage("Only written books in your main hand are sendable through mail")
                        return false
                    }
                }
                else {
                    player.sendMessage("Recipient not found")
                    return false
                }
            }
            else {
                player.sendMessage("No player argument given")
                return false
            }
        }
        else {
            return false
        }
    }
}