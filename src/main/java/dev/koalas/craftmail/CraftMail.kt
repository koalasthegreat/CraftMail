package dev.koalas.craftmail

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.plugin.java.JavaPlugin

class CraftMail : JavaPlugin() {
    override fun onEnable() {
        // Plugin startup logic

        // Anonymously register /mail command
        val commandBase: CommandBase<CraftMail?> = object : CommandBase<CraftMail?>(this) {
            override fun runCommand(sender: CommandSender?, rootCommand: Command?, label: String?, args: Array<String>?): Boolean {
                sender!!.sendMessage("The root command does nothing! Use a sub command.")
                return false
            }
        }

        // Command registration
        val commandCheckMail = CommandCheckMail(this)
        val commandSendMail = CommandSendMail(this)
        commandBase.registerSubCommand("check", commandCheckMail)
        commandBase.registerSubCommand("mail", commandSendMail)
        getCommand("mail")!!.setExecutor(commandBase)

        logger.info("CraftMail is ready")
    }
}