package net.playlegend.legendserviceregistry.proxy;

import lombok.Getter;
import net.md_5.bungee.api.plugin.Plugin;
import net.playlegend.legendserviceregistry.common.ServiceAccessor;
import net.playlegend.legendserviceregistry.common.ServiceAccessorOwner;

/**
 * {@link LegendProxyPlugin} should be extended in every proxy plugin.
 */
public abstract class LegendProxyPlugin extends Plugin implements ServiceAccessorOwner {

  /**
   * Plugin specific {@link ServiceAccessor}.
   */
  @Getter
  private final ServiceAccessor accessor = new ServiceAccessor(this);

  /**
   * Get the name of the {@link Plugin}.
   */
  @Override
  public String getName() {
    return this.getDescription().getName();
  }

}
