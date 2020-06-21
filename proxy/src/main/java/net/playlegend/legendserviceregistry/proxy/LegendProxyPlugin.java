/*
 * This file is part of the LegendServiceRegistry distribution (https://github.com/playlegend/LegendServiceRegistry).
 * Copyright (c) 2020 Minecraft Legend Development
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

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
