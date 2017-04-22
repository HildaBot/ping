package ch.jamiete.hilda.ping;

import ch.jamiete.hilda.Hilda;
import ch.jamiete.hilda.plugins.HildaPlugin;

public class Main extends HildaPlugin {

    public Main(Hilda hilda) {
        super(hilda);
    }

    @Override
    public void onEnable() {
        getHilda().getCommandManager().registerChannelCommand(new PingCommand(getHilda()));
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onLoad() {
    }

}
