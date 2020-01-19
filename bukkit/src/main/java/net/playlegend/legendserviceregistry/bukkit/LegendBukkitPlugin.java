package net.playlegend.legendserviceregistry.bukkit;

import lombok.Getter;
import net.playlegend.legendserviceregistry.common.ServiceAccessor;
import net.playlegend.legendserviceregistry.common.ServiceAccessorOwner;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public abstract class LegendBukkitPlugin extends JavaPlugin implements ServiceAccessorOwner {

    private final ServiceAccessor accessor = new ServiceAccessor(this);

}
