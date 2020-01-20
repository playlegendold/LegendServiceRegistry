package net.playlegend.legendserviceregistry.proxy;

import net.md_5.bungee.api.plugin.Plugin;
import net.playlegend.legendserviceregistry.common.ServiceAccessorOwner;

public abstract class LegendProxyPlugin extends Plugin implements ServiceAccessorOwner {

    @Override
    public String getName() {
        return this.getDescription().getName();
    }

}
