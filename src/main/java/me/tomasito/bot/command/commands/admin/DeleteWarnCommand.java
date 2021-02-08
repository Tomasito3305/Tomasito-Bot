package me.tomasito.bot.command.commands.admin;

import java.io.IOException;
import java.util.List;

import me.tomasito.bot.command.CommandContext;
import me.tomasito.bot.command.ICommand;

public class DeleteWarnCommand implements ICommand {

    @Override
    public void handle(CommandContext ctx) throws IOException, InterruptedException {
        ctx.getChannel().sendMessage("This command is Work in Progress").queue();
        /*if (ctx.getArgs().isEmpty()){
            ctx.getChannel().sendMessage("Missing arguments").queue();
            return;
        }

        if (ctx.getMessage().getMentionedMembers().isEmpty()){
            ctx.getChannel().sendMessage("Incorrect arguments. Correct usage: `!-deletewarn <@user>`").queue();
            return;
        }

        boolean isInList = false;

        for (int i = 0; i < WarningCommand.valuesList.size(); i++){
            Long guild = WarningCommand.valuesList.get(i).getGuild();
            String user = WarningCommand.valuesList.get(i).getUser();

            if (guild == ctx.getGuild().getIdLong() && user.equals(ctx.getMessage().getMentionedMembers().get(0).getAsMention())){
                WarningCommand.valuesList.get(i).deleteWarns();
                ctx.getChannel().sendMessage("Successfully deleted one warning to " + ctx.getMessage().getMentionedMembers().get(0).getAsMention()).queue();
                isInList = true;
            }
        }

        if (!isInList){
            ctx.getChannel().sendMessage("The argued user does not have any warnings").queue();
            return;
        }*/
    }

    @Override
    public String getName() {
        return "deletewarn";
    }

    @Override
    public String getHelp() {
        return "Deletes one warning to the argued user. \n" + 
                "Usage: `!-deletewarn <@user>`";
    }

    @Override
    public List<String> getAliases() {
        return List.of("deletewarning", "delwarn", "delwarning");
    }
    
}
