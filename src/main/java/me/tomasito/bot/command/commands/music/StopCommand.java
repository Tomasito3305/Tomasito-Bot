package me.tomasito.bot.command.commands.music;

import java.util.List;

import me.tomasito.bot.command.CommandContext;
import me.tomasito.bot.command.ICommand;
import me.tomasito.bot.lavaplayer.GuildMusicManager;
import me.tomasito.bot.lavaplayer.PlayerManager;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

public class StopCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        final TextChannel channel = ctx.getChannel();
        final Member self = ctx.getSelfMember();
        final GuildVoiceState selfVoiceState = self.getVoiceState();

        if (!selfVoiceState.inVoiceChannel()) {
            channel.sendMessage("I need to be in a voice channel for this to work").queue();
            return;
        }

        final Member member = ctx.getMember();
        final GuildVoiceState memberVoiceState = member.getVoiceState();

        if (!memberVoiceState.inVoiceChannel()) {
            channel.sendMessage("You need to be in a voice channel for this command to work").queue();
            return;
        }

        if (!memberVoiceState.getChannel().equals(selfVoiceState.getChannel())) {
            channel.sendMessage("You need to be in the same voice channel as me for this to work").queue();
            return;
        }

        final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(ctx.getGuild());

        if (musicManager.scheduler.player.getPlayingTrack() == null){
            channel.sendMessage("There isn't any track to stop or the track is already stopped").queue();
            return;
        }
        
        musicManager.scheduler.player.setPaused(true);

        channel.sendMessage("The player has been stopped").queue();
    }

    @Override
    public String getName() {
        return "stop";
    }

    @Override
    public String getHelp() {
        return "Stops the current song";
    }

    @Override
    public List<String> getAliases() {
        return List.of("pause");
    }
}
