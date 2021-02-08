package me.tomasito.bot.command.commands;

import java.awt.Color;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import me.tomasito.bot.IsInteger;
import me.tomasito.bot.VeryBadDesign;
import me.tomasito.bot.command.CommandContext;
import me.tomasito.bot.command.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;

public class WeatherCommand implements ICommand {

    @Override
    public void handle(CommandContext ctx) throws IOException, InterruptedException {
        String prefix = VeryBadDesign.PREFIXES.get(ctx.getGuild().getIdLong());

        if (ctx.getArgs().isEmpty()){
            ctx.getChannel().sendMessage("Missing arguments").queue();
            return;
        }

        if (IsInteger.isNumber(ctx.getArgs().get(0))){
            ctx.getChannel().sendMessage("Incorrect arguments. Correct usage: `" + prefix + "weather <country>`").queue();
        }

        String url = "https://www.google.com/search?q=weather " + ctx.getArgs().get(0);
        Document doc = Jsoup.connect(url).header("Accept-Language", "en, en-gb;q=0.8, en;q=0.7").header("Accept-Encoding","gzip,deflate,sdch").get();
        String html = doc.html();

        try {
            Files.write(Paths.get("C:\\Users\\TomiG\\Desktop\\Proyectos de Java\\Tomasito's Bot\\paths.txt"),
                        html.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements links = doc.select("span.wob_t.TVtOme");
        String temperature = "";

        for (Element link : links){
            temperature = link.text();
        }

        links = doc.select("span.vk_gy.vk_sh");
        String weather = "";

        for (Element link : links){
            weather = link.text();
        }

        links = doc.select("span#wob_hm");
        String humidity = "";

        for (Element link : links){
            humidity = link.text();
        }

        links = doc.select("span#wob_pp");
        String precipitations = "";

        for (Element link : links){
            precipitations = link.text();
        }

        links = doc.select("span#wob_ws.wob_t");
        String wind = "";

        for (Element link : links){
            wind = link.text();
        }

        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(Color.GREEN);
        builder.setTitle(ctx.getArgs().get(0).toUpperCase() + " weather");
        builder.addField("Weather:", weather, true);
        builder.addField("Temperature:", temperature + "°C", true);
        builder.addBlankField(true);
        builder.addField("Precipitation:", precipitations, true);
        builder.addField("Humidity:", humidity, true);
        builder.addBlankField(true);
        builder.addField("Wind:", wind, true);

        ctx.getChannel().sendMessage(builder.build()).queue();
    }

    @Override
    public String getName() {
        return "weather";
    }

    @Override
    public String getHelp() {
        return "Shows how Is the weather like in the argued country. \n" + 
                "Usage: `!-weather <country>`";
    }

    
}
