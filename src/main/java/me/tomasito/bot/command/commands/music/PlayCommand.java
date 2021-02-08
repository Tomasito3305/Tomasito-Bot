package me.tomasito.bot.command.commands.music;

import me.tomasito.bot.command.CommandContext;
import me.tomasito.bot.command.ICommand;
import me.tomasito.bot.lavaplayer.GuildMusicManager;
import me.tomasito.bot.lavaplayer.PlayerManager;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;

import java.util.List;

import org.apache.commons.validator.UrlValidator;

public class PlayCommand implements ICommand {
    public static String search = "";

    @SuppressWarnings("ConstantConditions")
    @Override
    public void handle(CommandContext ctx) throws InterruptedException {
        final TextChannel channel = ctx.getChannel();
        final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(ctx.getGuild());
        UrlValidator url = new UrlValidator();

        if (ctx.getArgs().isEmpty()) {
            musicManager.scheduler.player.setPaused(false);
            channel.sendMessage("The player has been resumed").queue();
            return;
        }
        
        final Member member = ctx.getMember();
        final GuildVoiceState memberVoiceState = member.getVoiceState();

        if (!memberVoiceState.inVoiceChannel()) {
            channel.sendMessage("You need to be in a voice channel for this command to work").queue();
            return;
        }

        String link = String.join(" ", ctx.getArgs());

        if (!(url.isValid(ctx.getArgs().get(0)))) {
            search = link;
            link = "ytsearch:" + link;
        }

        final AudioManager audioManager = ctx.getGuild().getAudioManager();
        final VoiceChannel memberChannel = memberVoiceState.getChannel();
        audioManager.openAudioConnection(memberChannel);
        PlayerManager.getInstance().loadAndPlay(channel, link);
    }

    @Override
    public String getName() {
        return "play";
    }

    @Override
    public String getHelp() {
        return "Plays a song\n" +
                "Usage: `!-play <youtube link or keywords>`";
    }

    @Override
    public List<String> getAliases() {
        return List.of("resume", "p");
    }
}
