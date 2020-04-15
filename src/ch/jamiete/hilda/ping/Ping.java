package ch.jamiete.hilda.ping;

import ch.jamiete.hilda.Hilda;
import ch.jamiete.hilda.plugins.HildaPlugin;
import java.util.concurrent.TimeUnit;

public class Ping extends HildaPlugin {
    public final PingMonitor monitor = new PingMonitor(this.getHilda());

    public Ping(final Hilda hilda) {
        super(hilda);
    }

    @Override
    public void onEnable() {
        this.getHilda().getCommandManager().registerChannelCommand(new PingCommand(this.getHilda(), this));
        this.getHilda().getExecutor().scheduleAtFixedRate(this.monitor, 0, 30, TimeUnit.SECONDS);
    }

}
