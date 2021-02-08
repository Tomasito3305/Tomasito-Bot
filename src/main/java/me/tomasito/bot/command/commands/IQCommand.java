package me.tomasito.bot.command.commands;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import me.tomasito.bot.command.CommandContext;
import me.tomasito.bot.command.ICommand;

public class IQCommand implements ICommand {

    @Override
    public void handle(CommandContext ctx) throws IOException, InterruptedException {
        String author = ctx.getAuthor().getAsMention();
        Random rand = new Random();
        List<String> args = ctx.getArgs();
        if (args.isEmpty()){
            ctx.getChannel().sendMessage(author + " has " + rand.nextInt(160) + " iq points").queue();
            return;
        }

        ctx.getChannel().sendMessage(args.get(0) + " has " + rand.nextInt(160) + " iq points").queue();
    }

    @Override
    public String getName() {
        return "iq";
    }

    @Override
    public String getHelp() {
        return "Tells your IQ points\n" + 
                "Usage: `!-iq <user (optional)>`";
    }
    
}
