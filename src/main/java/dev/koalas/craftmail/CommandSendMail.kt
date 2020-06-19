package dev.koalas.craftmail

import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class CommandSendMail(plugin: CraftMail?) : CommandBase<CraftMail?>(plugin) {
    override fun runCommand(sender: CommandSender?, command: Command?, label: String?, args: Array<String>?): Boolean {
        sender!!.sendMessage("Subcommand sendmail executed")
        return true
    }
}