package net.playlegend.legendserviceregistry.proxy;

import lombok.Getter;
import net.md_5.bungee.api.plugin.Plugin;
import net.playlegend.legendserviceregistry.common.ServiceAccessor;
import net.playlegend.legendserviceregistry.common.ServiceAccessorOwner;

public abstract class LegendProxyPlugin extends Plugin implements ServiceAccessorOwner {

    @Getter
    private final ServiceAccessor accessor = new ServiceAccessor(this);

    @Override
    public String getName() {
        return this.getDescription().getName();
    }

}
