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
