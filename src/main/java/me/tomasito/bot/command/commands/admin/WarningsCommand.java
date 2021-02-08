package me.tomasito.bot.command.commands.admin;

import java.io.IOException;
import java.util.List;

import me.tomasito.bot.VeryBadDesign;
import me.tomasito.bot.command.CommandContext;
import me.tomasito.bot.command.ICommand;

public class WarningsCommand implements ICommand {

    @Override
    public void handle(CommandContext ctx) throws IOException, InterruptedException {
        ctx.getChannel().sendMessage("This command is Work in Progress").queue();
        /*
        List<String> args = ctx.getArgs();
        String prefix = VeryBadDesign.PREFIXES.get(ctx.getGuild().getIdLong());

        if (args.isEmpty()){
            ctx.getChannel().sendMessage("Missing arguments. Correct usage: `" + prefix + "warns <@user>`").queue();
            return;
        }

        if (ctx.getMessage().getMentionedMembers().isEmpty()){
            ctx.getChannel().sendMessage("Incorrect arguments. Correct usage: `" + prefix + "warns <@user>`").queue();
            return;
        }

        boolean isInList = false;
        for (int i = 0; i < WarningCommand.valuesList.size(); i++){
            Long guild = WarningCommand.valuesList.get(i).getGuild();
            String user = WarningCommand.valuesList.get(i).getUser();

            if (guild == ctx.getGuild().getIdLong() && user.equals(ctx.getMessage().getMentionedMembers().get(0).getAsMention())){
                ctx.getChannel().sendMessage(ctx.getMessage().getMentionedMembers().get(0).getAsMention() + " has " + WarningCommand.valuesList.get(i).getWarns() + " warnings").queue();
                isInList = true;
                return;
            }
        }

        if (!isInList){
            ctx.getChannel().sendMessage(ctx.getMessage().getMentionedMembers().get(0).getAsMention() + " has 0 warnings.").queue();
        }*/
    }
    

    @Override
    public String getName() {
        return "warns";
    }

    @Override
    public String getHelp() {
        return "Shows how many warnings has the specified user. \n" + 
                "Usage: `!-warns <@user>`";
    }

    @Override
    public List<String> getAliases() {
        return List.of("warnings");
    }
}
