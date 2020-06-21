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

package net.playlegend.legendserviceregistry.common;

public class LazyLoad<T extends Service> {

  private final Class<T> serviceName;
  private final ServiceAccessor accessor;
  private T service;

  public LazyLoad(final Class<T> serviceName, final ServiceAccessor accessor) {
    this.serviceName = serviceName;
    this.accessor = accessor;
  }

  public T get() {
    if (service == null) {
      service = accessor.access(serviceName);
    }
    if (service == null) {
      throw new IllegalStateException("Service is not loaded yet!");
    }
    return service;
  }
}
