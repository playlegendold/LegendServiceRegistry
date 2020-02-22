package net.playlegend.legendserviceregistry.bukkit;

import lombok.Getter;
import net.playlegend.legendserviceregistry.common.ServiceAccessor;
import net.playlegend.legendserviceregistry.common.ServiceAccessorOwner;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * {@link LegendBukkitPlugin} should be extended in every bukkit plugin.
 */
@Getter
public abstract class LegendBukkitPlugin extends JavaPlugin implements ServiceAccessorOwner {

    /**
     * Plugin specific {@link ServiceAccessor}.
     */
    private final ServiceAccessor accessor = new ServiceAccessor(this);

}
