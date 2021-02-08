package me.tomasito.bot.command.commands.admin;

import java.util.List;
import me.tomasito.bot.IsInteger;
import me.tomasito.bot.VeryBadDesign;
import me.tomasito.bot.command.CommandContext;
import me.tomasito.bot.command.ICommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageHistory;
import net.dv8tion.jda.api.entities.TextChannel;

public class ClearCommand implements ICommand{
    
    public void handle (CommandContext ctx){
        final TextChannel channel = ctx.getChannel();
        final Member member = ctx.getMember();
        final Member selfMember = ctx.getSelfMember();
        final List<String> args = ctx.getArgs();
        String prefix = VeryBadDesign.PREFIXES.get(ctx.getGuild().getIdLong());

        if (args.size() < 1 ) {
            channel.sendMessage("Missing arguments.").queue();
            return;
        }

        if (!IsInteger.isNumber(args.get(0))){
            channel.sendMessage("Invalid arguments. Correct usage: `" + prefix + "clear <number of messages, from 1 up to 100>`").queue();
        }

        if (Integer.parseInt(args.get(0)) < 1 || Integer.parseInt(args.get(0)) > 100){
            channel.sendMessage("Invalid arguments. Message retrieval limit is between 1 and 100 messages.").queue();
            return;
        }

        if (!member.hasPermission(Permission.MANAGE_SERVER)) {
            channel.sendMessage("You are missing permission to execute this command.").queue();
            return;
        }

        if (!selfMember.hasPermission(Permission.MANAGE_SERVER)) {
            channel.sendMessage("I am missing permissions to execute this command.").queue();
            return;
        }

        MessageHistory history = new MessageHistory (channel);
        List<Message> messages = history.retrievePast(Integer.valueOf(args.get(0))).complete();
        channel.purgeMessages(messages);
    }

    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public String getHelp() {
        return "Purge messages from a text channel. \n" + 
                "Usage: `!-clear <number of messages, from 1 up to 100>`";
    }
}
