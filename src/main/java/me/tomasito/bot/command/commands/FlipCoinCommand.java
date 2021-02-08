package me.tomasito.bot.command.commands;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import me.tomasito.bot.command.CommandContext;
import me.tomasito.bot.command.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;

public class FlipCoinCommand implements ICommand {

    @Override
    public void handle(CommandContext ctx) throws IOException, InterruptedException {
        ctx.getChannel().sendMessage("This command is Work in Progress").queue();
        /*
        Random rand = new Random();
        File tails = new File("C:\\Users\\TomiG\\Desktop\\Proyectos de Java\\Tomasito's Bot\\tails.png");
        File heads = new File("C:\\Users\\TomiG\\Desktop\\Proyectos de Java\\Tomasito's Bot\\heads.jpg");

        if (rand.nextInt(2) == 1){
            EmbedBuilder builder = new EmbedBuilder();
            builder.setColor(Color.GREEN);
            builder.setImage("attachment://heads.jpg")
                    .setTitle("The coin has been flipped and landed in heads! \n \n");
            ctx.getChannel().sendFile(heads, heads.getName()).embed(builder.build()).queue();
            return;
        } else {
            EmbedBuilder builder = new EmbedBuilder();
            builder.setColor(Color.GREEN);
            builder.setImage("attachment://tails.png")
                    .setTitle("The coin has been flipped and landed in tails! \n \n");
            ctx.getChannel().sendFile(tails, tails.getName()).embed(builder.build()).queue();
            return;
        }*/
    }

    @Override
    public String getName() {
        return "flipcoin";
    }

    @Override
    public String getHelp() {
        return "Flips a coin";
    }
    
    @Override
    public List<String> getAliases() {
        return List.of("flipacoin", "coin");
    }
}
