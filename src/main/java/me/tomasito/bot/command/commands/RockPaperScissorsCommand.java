package me.tomasito.bot.command.commands;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import me.tomasito.bot.command.CommandContext;
import me.tomasito.bot.command.ICommand;

public class RockPaperScissorsCommand implements ICommand {

    @Override
    public void handle(CommandContext ctx) throws IOException, InterruptedException {
        if (ctx.getArgs().isEmpty()){
            ctx.getChannel().sendMessage("Missing arguments").queue();
            return;
        }

        Random rand = new Random();

        int random = rand.nextInt(3);
        String choose = "";

        switch (random){
            case 0:
                choose = "rock";
                break;
            case 1:
                choose = "paper";
                break;
            case 2:
                choose = "scissors";
                break;
        }

        if (choose.equalsIgnoreCase(ctx.getArgs().get(0))){
            ctx.getChannel().sendMessage("I chose: `" + choose + "`. \n" + 
                                        "The game Is a tie!")
                                        .queue();
            return;
        }

        if (choose.equals("rock") && ctx.getArgs().get(0).equalsIgnoreCase("paper")){
            ctx.getChannel().sendMessage("I chose: `" + choose + "`. \n" + 
                                        "You won!")
                                        .queue();
            return;
        }

        if (choose.equals("rock") && ctx.getArgs().get(0).equalsIgnoreCase("scissors")){
            ctx.getChannel().sendMessage("I chose: `" + choose + "`. \n" + 
                                        "I won!")
                                        .queue();
            return;
        }

        if (choose.equals("paper") && ctx.getArgs().get(0).equalsIgnoreCase("rock")){
            ctx.getChannel().sendMessage("I chose: `" + choose + "`. \n" + 
                                        "I won!")
                                        .queue();
            return;
        }

        if (choose.equals("paper") && ctx.getArgs().get(0).equalsIgnoreCase("scissors")){
            ctx.getChannel().sendMessage("I chose: `" + choose + "`. \n" + 
                                        "You won!")
                                        .queue();
            return;
        }

        if (choose.equals("scissors") && ctx.getArgs().get(0).equalsIgnoreCase("paper")){
            ctx.getChannel().sendMessage("I chose: `" + choose + "`. \n" + 
                                        "I won!")
                                        .queue();
            return;
        }

        if (choose.equals("scissors") && ctx.getArgs().get(0).equalsIgnoreCase("rock")){
            ctx.getChannel().sendMessage("I chose: `" + choose + "`. \n" + 
                                        "You won!")
                                        .queue();
            return;
        }

        ctx.getChannel().sendMessage("Invalid arguments. Correct usage: `!-rps <rock, paper or scissors>`").queue();
    }

    @Override
    public String getName() {
        return "rps";
    }

    @Override
    public String getHelp() {
        return "Play rock, paper, scissors against the bot. \n" + 
                "Usage: `!-rps <rock, paper or scissors>`";
    }
    
    @Override
    public List<String> getAliases() {
        return List.of("rockpaperscissors", "rockpaperandscissors");
    }
}
