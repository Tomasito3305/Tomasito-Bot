package me.tomasito.bot.command.commands.music;

import java.io.IOException;

import me.tomasito.bot.command.CommandContext;
import me.tomasito.bot.command.ICommand;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;

public class LeaveCommand implements ICommand {

    @Override
    public void handle(CommandContext ctx) throws IOException, InterruptedException {
        
        final TextChannel channel = ctx.getChannel();
        final Member member = ctx.getMember();
        final GuildVoiceState memberVoiceState = member.getVoiceState();
        final Member self = ctx.getSelfMember();
        final GuildVoiceState selfVoiceState = self.getVoiceState();
        
        if (!memberVoiceState.inVoiceChannel()) {
            channel.sendMessage("You need to be in a voice channel for this command to work").queue();
            return;
        }

        if (!selfVoiceState.inVoiceChannel()){
            channel.sendMessage("I need to be in a voice channel for this command to work").queue();
            return;
        }

        final AudioManager audioManager = ctx.getGuild().getAudioManager();
        final VoiceChannel selfChannel = selfVoiceState.getChannel();
        channel.sendMessageFormat("Leaving `\uD83D\uDD0A %s`", selfChannel.getName()).queue();
        audioManager.closeAudioConnection();

    }

    @Override
    public String getName() {
        return "leave";
    }

    @Override
    public String getHelp() {
        return "Makes the bot leave the voice channel";
    }
    
}
