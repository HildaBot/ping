package ch.jamiete.hilda.ping;

import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.function.Consumer;
import ch.jamiete.hilda.Hilda;
import ch.jamiete.hilda.commands.ChannelCommand;
import net.dv8tion.jda.core.entities.Message;

public class PingCommand extends ChannelCommand {

    public PingCommand(final Hilda hilda) {
        super(hilda);

        this.setName("ping");
        this.setAliases(Arrays.asList(new String[] { "pong" }));
        this.setDescription("Bot replies pong.");
    }

    @Override
    public void execute(final Message message, final String[] arguments, final String label) {
        message.getChannel().sendMessage("Pong...").queue(new Consumer<Message>() {

            @Override
            public void accept(final Message pong) {
                pong.editMessage("Pong! :ping_pong: (" + message.getCreationTime().until(pong.getCreationTime(), ChronoUnit.MILLIS) + "ms)").queue();
            }

        });
    }

}
