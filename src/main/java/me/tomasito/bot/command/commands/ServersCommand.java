package me.tomasito.bot.command.commands;

import java.io.IOException;

import me.tomasito.bot.command.CommandContext;
import me.tomasito.bot.command.ICommand;
import net.dv8tion.jda.api.JDA;

public class ServersCommand implements ICommand {

    @Override
    public void handle(CommandContext ctx) throws IOException, InterruptedException {
        JDA jda = ctx.getJDA();
        ctx.getChannel().sendMessage("This bot Is in " + jda.getGuilds().size() + " servers.").queue();
    }

    @Override
    public String getName() {
        return "servers";
    }

    @Override
    public String getHelp() {
        return "Tells how many servers is the bot in.";
    }
    
}
