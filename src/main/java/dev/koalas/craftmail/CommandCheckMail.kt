package dev.koalas.craftmail

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class CommandCheckMail(plugin: CraftMail?) : CommandBase<CraftMail?>(plugin) {
    override fun runCommand(sender: CommandSender?, command: Command?, label: String?, args: Array<String>?): Boolean {
        val player = sender as? Player

        if (player != null) {
            // TODO: implement database access to stored mail
            // TODO: implement a gui to view mail
            return true
        }
        else {
            return false
        }
    }

    fun constructBooks(data: Array<String>?): Array<ItemStack> {
        val books: Array<ItemStack> = arrayOf<ItemStack>()

        

        return books
    }
}