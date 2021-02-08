package me.tomasito.bot.command.commands;
import java.awt.Color;

import com.fasterxml.jackson.databind.JsonNode;
import me.duncte123.botcommons.messaging.EmbedUtils;
import me.duncte123.botcommons.web.WebUtils;
import me.tomasito.bot.command.CommandContext;
import me.tomasito.bot.command.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;

public class JokeCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        final TextChannel channel = ctx.getChannel();

        WebUtils.ins.getJSONObject("https://apis.duncte123.me/joke").async((json) -> {
            if (!json.get("success").asBoolean()) {
                channel.sendMessage("Something went wrong, try again later").queue();
                System.out.println(json);
                return;
            }

            final JsonNode data = json.get("data");
            final String title = data.get("title").asText();
            final String url = data.get("url").asText();
            final String body = data.get("body").asText();

            final EmbedBuilder embed = EmbedUtils.defaultEmbed()
                    .setColor(Color.GREEN)
                    .setTitle(title, url)
                    .setDescription(body);

            channel.sendMessage(embed.build()).queue();
        });
    }

    @Override
    public String getName() {
        return "joke";
    }

    @Override
    public String getHelp() {
        return "Shows a random joke. It's not my fault if the jokes are bad :(";
    }
}
