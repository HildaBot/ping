package ch.jamiete.hilda.ping;

import ch.jamiete.hilda.Hilda;
import ch.jamiete.hilda.plugins.HildaPlugin;

public class Ping extends HildaPlugin {

    public Ping(final Hilda hilda) {
        super(hilda);
    }

    @Override
    public void onEnable() {
        this.getHilda().getCommandManager().registerChannelCommand(new PingCommand(this.getHilda()));
    }

}
