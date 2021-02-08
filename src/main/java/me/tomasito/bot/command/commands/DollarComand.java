package me.tomasito.bot.command.commands;

import me.tomasito.bot.VeryBadDesign;
import me.tomasito.bot.command.CommandContext;

import java.awt.Color;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import me.tomasito.bot.command.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;

public class DollarComand implements ICommand {
    @Override
    public void handle(CommandContext ctx) throws IOException {
        List<String> args = ctx.getArgs();
        TextChannel channel = ctx.getChannel();
        String prefix = VeryBadDesign.PREFIXES.get(ctx.getGuild().getIdLong());

        if (args.isEmpty()) {
            channel.sendMessage("ERROR: No arguments specified. Correct usage: `" + prefix + "dollar <country>.").queue();
            return;
        }

        if (ctx.getAuthor().isBot()) {
            return;
        }

        String url = "https://www.google.com/search?q=how much is the dollar in " + args.get(0);
        Document doc = Jsoup.connect(url).header("Accept-Language", "en, en-gb;q=0.8, en;q=0.7").get();
        String html = doc.html();

        try {
            Files.write(Paths.get("C:\\Users\\TomiG\\Desktop\\Proyectos de Java\\Tomasito's Bot\\paths.txt"),
                        html.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements links = doc.select("span");
        boolean b = false;
        boolean a = false;
        String dollar = "";

        for (Element link : links) {
            String text = link.text();
            if (text.equals("United States Dollar")){
                b = true;
                continue;
            }
            if (b){
                dollar += text;
                a = true;
                b = false;
                continue;
            }
            if (a){
                dollar += " " + text;
                break;
            }
        }

        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Dollar");
        builder.setColor(Color.GREEN);
        builder.addField("1 US dollar equals to ", dollar, false);
        channel.sendMessage(builder.build()).queue();
    }

    @Override
    public String getName() {
        return "dollar";
    }

    @Override
    public String getHelp() {
        return "Shows how much the dollar is in the country argued. \n" +
                "Usage: `!-dollar <country>`";
    }

}
