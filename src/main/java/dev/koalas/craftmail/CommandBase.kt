package dev.koalas.craftmail

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.plugin.Plugin
import java.util.*

/**
 * A simple class for implementing sub commands.
 *
 * Just register one of these as the executor for your plugin's root command and then register sub commands to this
 * CommandBase with [.registerSubCommand].
 *
 * @param <P> The implementing plugin.
</P> */
abstract class CommandBase<P : Plugin?>
/**
 * Creates a new CommandBase for the given plugin.
 *
 * @param plugin The plugin that owns this command.
 */(
        /**
         * Returns the plugin that owns this command.
         */
        val plugin: P) : CommandExecutor {
    private val subCommands: MutableMap<String, CommandExecutor> = HashMap()

    /**
     * Registers a sub command to this command.
     *
     * @param label The label for the sub command.
     * @param subCommand The sub command to register which can either be a plain CommandExecutor or another
     * CommandBase if further command nesting is desired.
     */
    fun registerSubCommand(label: String, subCommand: CommandExecutor) {
        subCommands[label.toLowerCase()] = subCommand
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        var label: String? = label
        if (args.isNotEmpty()) {
            val child = subCommands[args[0].toLowerCase()]
            if (child != null) {
                label = args[0]
                val newArgs: Array<String?> = arrayOfNulls(args.size - 1)
                System.arraycopy(args, 1, newArgs, 0, newArgs.size)
                return child.onCommand(sender, command, label, newArgs)
            }
        }
        return runCommand(sender, command, label, args)
    }

    /**
     * Executes the given commands and returns its success.
     *
     * Note that the success returned may propagate up to the root command.
     *
     * @param sender Source of the command.
     * @param rootCommand The top level command that was executed.
     * @param label Alias of the command that was used - the sub command label being used.
     * @param args Arguments for the sub command.
     * @return true if a valid command, false otherwise.
     */
    abstract fun runCommand(sender: CommandSender?, rootCommand: Command?, label: String?, args: Array<String>?): Boolean

}