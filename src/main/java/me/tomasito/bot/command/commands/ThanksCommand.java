package me.tomasito.bot.command.commands;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import me.tomasito.bot.command.CommandContext;
import me.tomasito.bot.command.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;

public class ThanksCommand implements ICommand {

    @Override
    public void handle(CommandContext ctx) throws IOException, InterruptedException {
        EmbedBuilder builder = new EmbedBuilder();

        builder.setTitle("Thx!");
        builder.setColor(Color.GREEN);
        builder.addField("Instagram:", "", false);
        builder.addField("", "@clown.tweets", true);
        builder.addField("", "@lucas_metalhead", true);
        builder.addField("", "@tremendofasx", true);
        builder.addBlankField(false);
        builder.addField("Discord:", "", false);
        builder.addField("", "𝐼𝓈𝒶𝓇𝒶 https_isara#0712", true);
        builder.addField("", "LucasMetalhead#8479", true);
        builder.setFooter("Follow them on Instagram!", "https://i.pinimg.com/564x/7e/57/9b/7e579b53f4e596e8fcd1db4f5d2cd31e.jpg");

        ctx.getChannel().sendMessage(builder.build()).queue();
    }

    @Override
    public String getName() {
        return "thx";
    }

    @Override
    public String getHelp() {
        return "Shows the instagram of the people who helped on the development and difussion of the bot <3";
    }

    @Override
    public List<String> getAliases() {
        return List.of("thanks", "<3");
    }
    
}
