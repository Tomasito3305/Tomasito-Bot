package me.tomasito.bot.command.commands.music;

import java.awt.Color;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import me.tomasito.bot.IsInteger;
import me.tomasito.bot.command.CommandContext;
import me.tomasito.bot.command.ICommand;
import me.tomasito.bot.lavaplayer.GuildMusicManager;
import me.tomasito.bot.lavaplayer.PlayerManager;
import net.dv8tion.jda.api.EmbedBuilder;

public class LyricsCommand implements ICommand {

    @Override
    public void handle(CommandContext ctx) throws IOException, InterruptedException {

        final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(ctx.getGuild());

        if (musicManager.scheduler.player.getPlayingTrack() == null){
            ctx.getChannel().sendMessage("These isn't any song currently playing").queue();
            return;
        }

        String url = "https://www.google.com/search?q= " + PlayCommand.search + " lyrcs";
        Document doc = Jsoup.connect(url).get();
        String html = doc.html();

        try {
            Files.write(Paths.get("C:\\Users\\TomiG\\Desktop\\Proyectos de Java\\Tomasito's Bot\\paths.txt"),
                        html.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements paragraphs = doc.select("div.PZPZlf");
        String finishedLyric = "";
        String finishedLyric2 = "";
        String finishedLyric3 = "";
        String finishedLyric4 = "";
        String finishedLyric5 = "";
        String finishedLyric6 = "";
        String finishedLyric7 = "";
        String finishedLyric8 = "";
        String finishedLyric9 = "";
        int numberOfLetters = 0;
        
        for (Element paragraph : paragraphs) {
            String text = paragraph.toString() + " ";
            int index = 0;
            boolean key = false;
            for (int i = 0; i < text.length(); i++){
                numberOfLetters++;
                if (key){
                    if ((text.charAt(i) == '<') && (Character.isLowerCase(text.charAt(i - 1)) || text.charAt(i - 1) == '(' || text.charAt(i - 1) == ')' || text.charAt(i - 1) == '\'' || IsInteger.isNumber(String.valueOf (text.charAt(i - 1))) || text.charAt(i - 1) == '?' || text.charAt(i - 1) == '!' || text.charAt(i - 1) == '-' || text.charAt(i - 1) == ')'  || text.charAt(i - 1) == ',' || text.charAt(i - 1) == '.')){
                        
                        if (numberOfLetters >= 16000){
                            finishedLyric9 += text.substring(index + 1, i) + "\n";
                        } else if (numberOfLetters >= 14000){
                            finishedLyric8 += text.substring(index + 1, i) + "\n";
                        } else if (numberOfLetters >= 12000){
                            finishedLyric7 += text.substring(index + 1, i) + "\n";
                        } else if (numberOfLetters >= 10000){
                            finishedLyric6 += text.substring(index + 1, i) + "\n";
                        } else if (numberOfLetters >= 8000){
                            finishedLyric5 += text.substring(index + 1, i) + "\n";
                        } else if (numberOfLetters >= 6000){
                            finishedLyric4 += text.substring(index + 1, i) + "\n";
                        } else if (numberOfLetters >= 4000){
                            finishedLyric3 += text.substring(index + 1, i) + "\n";
                        } else if (numberOfLetters >= 2000){
                            finishedLyric2 += text.substring(index + 1, i) + "\n";
                        } else if (numberOfLetters < 2000){
                            finishedLyric += text.substring(index + 1, i) + "\n";
                        }
                        key = false;
                    }
                }
                if ((text.charAt(i) == '>') && (Character.isUpperCase(text.charAt(i + 1)))){
                    index = i;
                    key = true;
                }
            }
        }
        if (!finishedLyric9.equals("")){
            EmbedBuilder builder = new EmbedBuilder();

            builder.setTitle(musicManager.scheduler.player.getPlayingTrack().getInfo().title + " lyrics:");
            builder.setColor(Color.GREEN);
            builder.addField("", finishedLyric, false);
            builder.addField("", finishedLyric2, false);
            builder.addField("", finishedLyric3, false);
            builder.addField("", finishedLyric4, false);
            builder.addField("", finishedLyric5, false);
            builder.addField("", finishedLyric6, false);
            builder.addField("", finishedLyric7, false);
            builder.addField("", finishedLyric8, false);
            builder.addField("", finishedLyric, false);
            ctx.getChannel().sendMessage(builder.build()).queue();
        } else if (!finishedLyric8.equals("")){
            EmbedBuilder builder = new EmbedBuilder();

            builder.setTitle(musicManager.scheduler.player.getPlayingTrack().getInfo().title + " lyrics:");
            builder.setColor(Color.GREEN);
            builder.addField("", finishedLyric, false);
            builder.addField("", finishedLyric2, false);
            builder.addField("", finishedLyric3, false);
            builder.addField("", finishedLyric4, false);
            builder.addField("", finishedLyric5, false);
            builder.addField("", finishedLyric6, false);
            builder.addField("", finishedLyric7, false);
            builder.addField("", finishedLyric8, false);
            ctx.getChannel().sendMessage(builder.build()).queue();
        } else if (!finishedLyric7.equals("")){
            EmbedBuilder builder = new EmbedBuilder();

            builder.setTitle(musicManager.scheduler.player.getPlayingTrack().getInfo().title + " lyrics:");
            builder.setColor(Color.GREEN);
            builder.addField("", finishedLyric, false);
            builder.addField("", finishedLyric2, false);
            builder.addField("", finishedLyric3, false);
            builder.addField("", finishedLyric4, false);
            builder.addField("", finishedLyric5, false);
            builder.addField("", finishedLyric6, false);
            builder.addField("", finishedLyric7, false);
            ctx.getChannel().sendMessage(builder.build()).queue();
        } else if (!finishedLyric6.equals("")){
            EmbedBuilder builder = new EmbedBuilder();

            builder.setTitle(musicManager.scheduler.player.getPlayingTrack().getInfo().title + " lyrics:");
            builder.setColor(Color.GREEN);
            builder.addField("", finishedLyric, false);
            builder.addField("", finishedLyric2, false);
            builder.addField("", finishedLyric3, false);
            builder.addField("", finishedLyric4, false);
            builder.addField("", finishedLyric5, false);
            builder.addField("", finishedLyric6, false);
            ctx.getChannel().sendMessage(builder.build()).queue();
        } else if (!finishedLyric5.equals("")){
            EmbedBuilder builder = new EmbedBuilder();

            builder.setTitle(musicManager.scheduler.player.getPlayingTrack().getInfo().title + " lyrics:");
            builder.setColor(Color.GREEN);
            builder.addField("", finishedLyric, false);
            builder.addField("", finishedLyric2, false);
            builder.addField("", finishedLyric3, false);
            builder.addField("", finishedLyric4, false);
            builder.addField("", finishedLyric5, false);
            ctx.getChannel().sendMessage(builder.build()).queue();
        } else if (!finishedLyric4.equals("")){
            EmbedBuilder builder = new EmbedBuilder();

            builder.setTitle(musicManager.scheduler.player.getPlayingTrack().getInfo().title + " lyrics:");
            builder.setColor(Color.GREEN);
            builder.addField("", finishedLyric, false);
            builder.addField("", finishedLyric2, false);
            builder.addField("", finishedLyric3, false);
            builder.addField("", finishedLyric4, false);
            ctx.getChannel().sendMessage(builder.build()).queue();
        } else if (!finishedLyric3.equals("")){
            EmbedBuilder builder = new EmbedBuilder();

            builder.setTitle(musicManager.scheduler.player.getPlayingTrack().getInfo().title + " lyrics:");
            builder.setColor(Color.GREEN);
            builder.addField("", finishedLyric, false);
            builder.addField("", finishedLyric2, false);
            builder.addField("", finishedLyric3, false);
            ctx.getChannel().sendMessage(builder.build()).queue();
        } else if (!finishedLyric2.equals("")){
            EmbedBuilder builder = new EmbedBuilder();

            builder.setTitle(musicManager.scheduler.player.getPlayingTrack().getInfo().title + " lyrics:");
            builder.setColor(Color.GREEN);
            builder.addField("", finishedLyric, false);
            builder.addField("", finishedLyric2, false);
            ctx.getChannel().sendMessage(builder.build()).queue();
        } else if (!finishedLyric.equals("")){
            EmbedBuilder builder = new EmbedBuilder();

            builder.setTitle(musicManager.scheduler.player.getPlayingTrack().getInfo().title + " lyrics:");
            builder.setColor(Color.GREEN);
            builder.addField("", finishedLyric, false);
            ctx.getChannel().sendMessage(builder.build()).queue();
        } else if (finishedLyric.equals("")){
            ctx.getChannel().sendMessage("No lyrics has been found for you.").queue();
        }
    }

    @Override
    public String getName() {
        return "lyrics";
    }

    @Override
    public String getHelp() {
        return "Shows the lyrics of the currently playing song";
    }
    
}
