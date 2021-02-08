package me.tomasito.bot.command.commands.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.tomasito.bot.VeryBadDesign;
import me.tomasito.bot.command.CommandContext;
import me.tomasito.bot.command.ICommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class WarningCommand implements ICommand {
    public static ArrayList<values> valuesList = new ArrayList<values>();

    @Override
    public void handle(CommandContext ctx) throws IOException, InterruptedException {
        ctx.getChannel().sendMessage("This command is Work in Progress").queue();
        /*
        List<String> args = ctx.getArgs();
        final TextChannel channel = ctx.getChannel();
        String prefix = VeryBadDesign.PREFIXES.get(ctx.getGuild().getIdLong());

        if (args.isEmpty()){
            ctx.getChannel().sendMessage("Missing arguments").queue();
            return;
        }

        if (ctx.getMessage().getMentionedMembers().isEmpty()){
            ctx.getChannel().sendMessage("Incorrect arguments. Correct usage: `" + prefix + "warn <@user>`").queue();
            return;
        }

        final Message message = ctx.getMessage();
        final Member target = message.getMentionedMembers().get(0);
        final Member member = ctx.getMember();
        final Member selfMember = ctx.getSelfMember();

        if (!member.canInteract(target) || !member.hasPermission(Permission.BAN_MEMBERS)) {
            channel.sendMessage("You are missing permission to warn this member and ban him.").queue();
            return;
        }

        boolean isInList = false;

        for (int i = 0; i < valuesList.size(); i++){
            Long guild = valuesList.get(i).getGuild();
            String user = valuesList.get(i).getUser();

            if (guild == ctx.getGuild().getIdLong() && user.equals(ctx.getMessage().getMentionedMembers().get(0).getAsMention())){
                isInList = true;
                valuesList.get(i).addWarns();
                ctx.getChannel().sendMessage("Succesfully added one warning to " + ctx.getMessage().getMentionedMembers().get(0).getAsMention() + ". At 3 warnings you will get banned.").queue();
                if (valuesList.get(i).getWarns() >= 3){
                    if (!selfMember.canInteract(target) || !selfMember.hasPermission(Permission.BAN_MEMBERS)) {
                        channel.sendMessage("I am missing permissions to ban that member").queue();
                        return;
                    }
                    ctx.getGuild()
                    .ban(target, 3)
                    .reason("You got to 3 warnings!")
                    .queue(
                        (__) -> channel.sendMessage("Succesfully banned " + ctx.getMessage().getMentionedMembers().get(0).getAsMention() + " for 3 days.").queue(),
                        (error) -> channel.sendMessageFormat("Could not ban %s", error.getMessage()).queue()
                    );
                }
                return;
            }
        }

        if (!isInList) {
            valuesList.add(new values(ctx.getMessage().getMentionedMembers().get(0).getAsMention(), 1, ctx.getGuild().getIdLong()));
            ctx.getChannel().sendMessage("Succesfully added one warning to " + ctx.getMessage().getMentionedMembers().get(0).getAsMention() + ". At 3 warnings you will get banned.").queue();
            return;
        }

        for (int i = 0; i < valuesList.size(); i++){
            System.out.println(valuesList.get(i).getWarns());
        }*/
    }

    @Override
    public String getName() {
        return "warn";
    }

    @Override
    public String getHelp() {
        return "Warns users until they get banned. If an user gets 3 warnings, the user will be banned automaticlly for 3 days. \n"
                + "Usage: `!-warn <@user>`";
    }

    @Override
    public List<String> getAliases() {
        return List.of("warning");
    }
}

class values {
    static String user;
    static int warns;
    static Long guild;

    public values(String userAsMention, int warnsAsInteger, Long guildId) {
        user = userAsMention;
        warns = warnsAsInteger;
        guild = guildId;
    }

    public String getUser() {
        return user;
    }

    public int getWarns() {
        return warns;
    }

    public Long getGuild() {
        return guild;
    }

    public void addWarns() {
        warns++;
    }

    public void deleteWarns(){
        warns--;
    }
}