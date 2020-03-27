package net.playlegend.legendserviceregistry.bukkit;

import lombok.Getter;
import net.playlegend.legendserviceregistry.common.ServiceAccessor;
import net.playlegend.legendserviceregistry.common.ServiceAccessorOwner;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;
import org.jetbrains.annotations.NotNull;

import java.io.File;

/**
 * {@link LegendBukkitPlugin} should be extended in every bukkit plugin.
 */
@Getter
public abstract class LegendBukkitPlugin extends JavaPlugin implements ServiceAccessorOwner {

  /**
   * Plugin specific {@link ServiceAccessor}.
   */
  private final ServiceAccessor accessor = new ServiceAccessor(this);

  public LegendBukkitPlugin() {
    super();
  }

  public LegendBukkitPlugin(@NotNull final JavaPluginLoader loader, @NotNull final PluginDescriptionFile description,
                            @NotNull final File dataFolder, @NotNull final File file) {
    super(loader, description, dataFolder, file);
  }
}
