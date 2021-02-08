package me.tomasito.bot.command.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.tomasito.bot.IsInteger;
import me.tomasito.bot.VeryBadDesign;
import me.tomasito.bot.command.CommandContext;
import me.tomasito.bot.command.ICommand;

public class ReminderCommand implements ICommand {

    public static ArrayList<ReminderSaver> reminders = new ArrayList<ReminderSaver>();

    @Override
    public void handle(CommandContext ctx) throws IOException, InterruptedException {
        String prefix = VeryBadDesign.PREFIXES.get(ctx.getGuild().getIdLong());
        
        if (ctx.getArgs().isEmpty()){
            ctx.getChannel().sendMessage("Missing arguments").queue();
            return;
        }

        if (!IsInteger.isNumber(ctx.getArgs().get(0))){
            ctx.getChannel().sendMessage("Incorrect arguments. Correct usage: `" + prefix + "reminder <minutes>`").queue();
            return;
        }

        Timer timer = new Timer();
        TimerTask reminder = new TimerTask(){
            int loop = 1;
            boolean isFinished = false;
            boolean isFinished2 = true;

            @Override
            public void run (){
                if (loop < 2){
                    loop++;
                } else {
                    isFinished = true;
                }

                if (isFinished && isFinished2){
                    ctx.getChannel().sendMessage("Hey " + ctx.getAuthor().getAsMention() + ", your reminder for " + ctx.getArgs().get(0) + " minute(s) Is here!").queue();
                    isFinished2 = false;
                }
            }
        };
        
        timer.schedule(reminder, 0L, (1 * 1000 * 60 * Integer.valueOf(ctx.getArgs().get(0))) * 1L);
        ctx.getChannel().sendMessage("Successfully added one reminder to the database. Reminder set to remind at " + ctx.getArgs().get(0) + " minute(s).").queue();
    }

    @Override
    public String getName() {
        return "reminder";
    }

    @Override
    public String getHelp() {
        return "Sets a reminder. \n" + 
                "Usage: `!-reminder <minutes>`";
    }

    @Override
    public List<String> getAliases() {
        return List.of("setreminder", "remindme", "remind");
    }
}

class ReminderSaver {

    public Long guild;
    public String user;
    public int time;

    public ReminderSaver (Long guildId, String userId, int timeSet){
        guild = guildId;
        user = userId;
        time = timeSet;
    }

    public Long getGuild() {
        return guild;
    }

    public String getUser() {
        return user;
    }

    public int getTime() {
        return time;
    }
}
