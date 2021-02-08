package me.tomasito.bot.command.commands;

import java.awt.Color;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import me.tomasito.bot.command.CommandContext;
import me.tomasito.bot.command.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;

public class TimeCommand implements ICommand {

    @Override
    public void handle(CommandContext ctx) throws IOException, InterruptedException {
        List<String> args = ctx.getArgs();

        if (args.isEmpty()){
            ctx.getChannel().sendMessage("Missing arguments").queue();
            return;
        }

        String url = "https://www.google.com/search?q=current time " + args.get(0);
        Document doc = Jsoup.connect(url).get();
        String html = doc.html();

        try {
            Files.write(Paths.get("C:\\Users\\TomiG\\Desktop\\Proyectos de Java\\Tomasito's Bot\\paths.txt"),
                        html.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements links = doc.select("div.gsrt.vk_bk.dDoNo.FzvWSb.XcVN5d");
        String time = "";
        
        for (Element link : links){
            time = link.html();
        }

        links = doc.select("span.KfQeJ");
        String zonaHoraria = "";
        
        for (Element link : links){
            zonaHoraria = link.html();
        }

        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Time in " + args.get(0));
        builder.setColor(Color.GREEN);
        builder.addField(time, zonaHoraria, false);

        ctx.getChannel().sendMessage(builder.build()).queue();
    }

    @Override
    public String getName() {
        return "time";
    }

    @Override
    public String getHelp() {
        return "Shows the current time of the specified country. \n" + 
                "Usage: `!-time <country>`";
    }
    
    @Override
    public List<String> getAliases() {
        return List.of("currenttime", "currentime", "hour");
    }
}
