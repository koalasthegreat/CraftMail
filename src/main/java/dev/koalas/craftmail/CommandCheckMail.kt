package dev.koalas.craftmail

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandCheckMail(plugin: CraftMail?) : CommandBase<CraftMail?>(plugin) {
    override fun runCommand(sender: CommandSender?, command: Command?, label: String?, args: Array<String>?): Boolean {
        if (sender!! is Player) {
            sender.sendMessage("Subcommand checkmail executed")
            return true
        }
        else return false
    }
}