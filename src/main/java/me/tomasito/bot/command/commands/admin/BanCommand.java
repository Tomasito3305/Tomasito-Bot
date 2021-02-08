package me.tomasito.bot.command.commands.admin;

import java.io.IOException;
import java.util.List;

import me.tomasito.bot.IsInteger;
import me.tomasito.bot.VeryBadDesign;
import me.tomasito.bot.command.CommandContext;
import me.tomasito.bot.command.ICommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class BanCommand implements ICommand {

    @Override
    public void handle(CommandContext ctx) throws IOException, InterruptedException {
        final TextChannel channel = ctx.getChannel();
        List<String> args = ctx.getArgs();
        String prefix = VeryBadDesign.PREFIXES.get(ctx.getGuild().getIdLong());

        if (args.size() < 3){
            channel.sendMessage("Correct usage: `" + prefix + "ban <@user> <days> <reason>`").queue();
            return;
        }

        final Message message = ctx.getMessage();
        final Member target = message.getMentionedMembers().get(0);
        final Member member = ctx.getMember();
        final Member selfMember = ctx.getSelfMember();

        if (Integer.valueOf(args.get(1)) > 7){
            ctx.getChannel().sendMessage("Invalid arguments. Max days argued permited are `7`").queue();
            return;
        }

        if (ctx.getMessage().getMentionedMembers().isEmpty()){
            ctx.getChannel().sendMessage("Invalid arguments. Correct usage: `!-ban <@user> <days> <reason>`").queue();
            return;
        }

        if (!member.canInteract(target) || !member.hasPermission(Permission.BAN_MEMBERS)) {
            channel.sendMessage("You are missing permission to ban this member").queue();
            return;
        }

        if (!selfMember.canInteract(target) || !selfMember.hasPermission(Permission.BAN_MEMBERS)) {
            channel.sendMessage("I am missing permissions to ban that member").queue();
            return;
        }

        if (!IsInteger.isNumber(args.get(1))){
            channel.sendMessage("Incorrect arguments. Correct usage: `!-ban <@user> <days> <reason>`").queue();
        }

        final String reason = String.join(" ", args.subList(1, args.size()));

        ctx.getGuild()
                .ban(target, Integer.valueOf(args.get(1)))
                .reason(reason)
                .queue(
                        (__) -> channel.sendMessage("Ban was successful").queue(),
                        (error) -> channel.sendMessageFormat("Could not ban %s", error.getMessage()).queue()
                );
    }

    @Override
    public String getName() {
        return "ban";
    }

    @Override
    public String getHelp() {
        return "Bans the argued user. \n" + 
                "Usage: `!-ban <@user> <days> <reason>`";
    }
    
}
