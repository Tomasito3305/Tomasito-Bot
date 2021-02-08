package me.tomasito.bot.command.commands;

import me.tomasito.bot.CommandManager;
import me.tomasito.bot.VeryBadDesign;
import me.tomasito.bot.command.CommandContext;
import me.tomasito.bot.command.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.Color;
import java.util.List;

public class HelpCommand implements ICommand {

    private final CommandManager manager;

    public HelpCommand(CommandManager manager) {
        this.manager = manager;
    }

    @Override
    public void handle(CommandContext ctx) {
        List<String> args = ctx.getArgs();
        TextChannel channel = ctx.getChannel();

        if (args.isEmpty()) {
            EmbedBuilder embed = new EmbedBuilder();
            String prefix = VeryBadDesign.PREFIXES.get(ctx.getGuild().getIdLong());

            embed.setTitle("List of Charles's Bot commands:\n");
            embed.setColor(Color.GREEN);
            embed.addField("For mod utilities:","`" + (prefix) + "setprefix`, " +
                                                "`" + (prefix) + "kick`, " +
                                                "`" + (prefix) + "ban`, " + 
                                                "`" + (prefix) + "ping`, " + 
                                                "`" + (prefix) + "clear` ",
                                                false
            );
            embed.addField("For music utilities:", "`" + (prefix) + "join`, " + 
                                                   "`" + (prefix) + "leave`, " +
                                                   "`" + (prefix) + "nowplaying`, " +
                                                   "`" + (prefix) + "play`, \n" + 
                                                   "`" + (prefix) + "queue`, " + 
                                                   "`" + (prefix) + "repeat`, " + 
                                                   "`" + (prefix) + "skip`, " + 
                                                   "`" + (prefix) + "stop`, \n" +
                                                   "`" + (prefix) + "clearqueue`, " +
                                                   "`" + (prefix) + "lyrics` ",
                                                   false
            );
            embed.addField("For miscellaneous commands:", "`" + (prefix) + "dollar`, " +
                                                          "`" + (prefix) + "joke`, " + 
                                                          "`" + (prefix) + "meme`, " + 
                                                          "`" + (prefix) + "iq`, " + 
                                                          "`" + (prefix) + "setreminder`, " + 
                                                          "`" + (prefix) + "time`, " + 
                                                          "`" + (prefix) + "weather`, " +
                                                          "`" + (prefix) + "servers`, " + 
                                                          "`" + (prefix) + "flipcoin`, " + 
                                                          "`" + (prefix) + "rps` ",
                                                          false
            );
            embed.addField("For more info about a certain comand:", "`" + (prefix) + "help <command>`", false);
            embed.addField("You can also send feedback to any of my social networks.", "`" + (prefix) + "sendfeedback`", false);
            embed.addField("And use this command to thank my friends who helped in the development and difussion of the bot!", "`" + (prefix) + "thx`", false);
            //`

            channel.sendMessage(embed.build()).queue();
            return;
        }

        String search = args.get(0);
        ICommand command = manager.getCommand(search);

        if (command == null) {
            channel.sendMessage("Nothing found for " + search).queue();
            return;
        }

        channel.sendMessage(command.getHelp()).queue();
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getHelp() {
        return "Shows the list of commands in the bot\n" +
                "Usage: `!-help <command>`";
    }

    @Override
    public List<String> getAliases() {
        return List.of("commands", "cmds", "commandlist");
    }
}
