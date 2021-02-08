package me.tomasito.bot.command.commands;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import me.tomasito.bot.command.CommandContext;
import me.tomasito.bot.command.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;

public class SendFeedbackCommand implements ICommand {

    @Override
    public void handle(CommandContext ctx) throws IOException, InterruptedException {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(Color.GREEN);
        builder.setTitle("Contact the creator:");
        builder.addField("Discord tag:", "`Tomasito#3305`", true);
        builder.addField("Instagram:", "`@tomi.chikito`", true);
        builder.setFooter("Any feedback will help with the development of the bot! :)");
        ctx.getChannel().sendMessage(builder.build()).queue();
    }

    @Override
    public String getName() {
        return "sendfeedback";
    }

    @Override
    public String getHelp() {
        return "Shows the Discord tag of the creator of the bot to send feedback (bug reports and command ideas)";
    }
    
    @Override
    public List<String> getAliases() {
        return List.of("feedback", "creator");
    }
}
