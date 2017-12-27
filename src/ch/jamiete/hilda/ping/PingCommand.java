package ch.jamiete.hilda.ping;

import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.function.Consumer;
import ch.jamiete.hilda.Hilda;
import ch.jamiete.hilda.commands.ChannelCommand;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.MessageBuilder.Formatting;
import net.dv8tion.jda.core.entities.Message;

public class PingCommand extends ChannelCommand {
    private final Ping ping;

    public PingCommand(final Hilda hilda, final Ping ping) {
        super(hilda);

        this.ping = ping;

        this.setName("ping");
        this.setAliases(Arrays.asList(new String[] { "pong" }));
        this.setDescription("Bot replies pong.");
    }

    @Override
    public void execute(final Message message, final String[] arguments, final String label) {
        MessageBuilder mb = new MessageBuilder();

        mb.append(":ping_pong: Pong! ");
        mb.append(this.ping.monitor.getMinutes()).append(" minute average ");
        mb.append(this.ping.monitor.getAverage() + "ms", Formatting.BOLD);

        message.getChannel().sendMessage(mb.build()).queue(new Consumer<Message>() {

            @Override
            public void accept(Message pong) {
                pong.editMessage(pong.getContentDisplay() + " (current message response " + message.getCreationTime().until(pong.getCreationTime(), ChronoUnit.MILLIS) + "ms)").queue();
            }

        });
    }

}
