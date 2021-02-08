package me.tomasito.bot;

import me.tomasito.bot.command.CommandContext;
import me.tomasito.bot.command.ICommand;
import me.tomasito.bot.command.commands.*;
import me.tomasito.bot.command.commands.admin.*;
import me.tomasito.bot.command.commands.music.*;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import javax.annotation.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class CommandManager {
    private final List<ICommand> commands = new ArrayList<>();

    public CommandManager() {
        addCommand(new PingCommand());
        addCommand(new HelpCommand(this));
        addCommand(new KickCommand());
        addCommand(new MemeCommand());
        addCommand(new JokeCommand());
        addCommand(new ClearCommand());
        addCommand(new SetPrefixCommand());
        addCommand(new DollarComand());
        addCommand(new ClearqueueCommand());
        addCommand(new LeaveCommand());
        addCommand(new IQCommand());
        addCommand(new LyricsCommand());
        addCommand(new BanCommand());
        addCommand(new WarningCommand());
        addCommand(new WarningsCommand());
        addCommand(new FlipCoinCommand());
        addCommand(new SendFeedbackCommand());
        addCommand(new ReminderCommand());
        addCommand(new WeatherCommand());
        addCommand(new TimeCommand());
        addCommand(new JoinCommand());
        addCommand(new NowPlayingCommand());
        addCommand(new PlayCommand());
        addCommand(new StopCommand());
        addCommand(new SkipCommand());
        addCommand(new QueueCommand());
        addCommand(new RepeatCommand());
        addCommand(new ServersCommand());
        addCommand(new DeleteWarnCommand());
        addCommand(new RockPaperScissorsCommand());
        addCommand(new ThanksCommand());
    }

    private void addCommand(ICommand cmd) {
        boolean nameFound = this.commands.stream().anyMatch((it) -> it.getName().equalsIgnoreCase(cmd.getName()));

        if (nameFound) {
            throw new IllegalArgumentException("A command with this name is already present");
        }

        commands.add(cmd);
    }

    public List<ICommand> getCommands() {
        return commands;
    }

    @Nullable
    public ICommand getCommand(String search) {
        String searchLower = search.toLowerCase();

        for (ICommand cmd : this.commands) {
            if (cmd.getName().equals(searchLower) || cmd.getAliases().contains(searchLower)) {
                return cmd;
            }
        }

        return null;
    }

    void handle(GuildMessageReceivedEvent event, String prefix) throws IOException, InterruptedException {
        String[] split = event.getMessage().getContentRaw()
                .replaceFirst("(?i)" + Pattern.quote(prefix), "")
                .split("\\s+");

        String invoke = split[0].toLowerCase();
        ICommand cmd = this.getCommand(invoke);

        if (cmd != null) {
            event.getChannel().sendTyping().queue();
            List<String> args = Arrays.asList(split).subList(1, split.length);

            CommandContext ctx = new CommandContext(event, args);

            cmd.handle(ctx);
        }
    }

}
