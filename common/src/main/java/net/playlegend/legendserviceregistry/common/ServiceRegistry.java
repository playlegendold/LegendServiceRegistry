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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Represents the structure and data of a {@link ServiceRegistry}.
 */
final class ServiceRegistry {

  /**
   * Contains all registered {@link Service}s.
   */
  private static final Map<Class<? extends Service>, Service> SERVICES = new ConcurrentHashMap<>();
  /**
   * Contains all {@link ServiceAccessor}s.
   */
  private static final Map<ServiceAccessorOwner, ServiceAccessor> ACCESSORS = new HashMap<>();

  private ServiceRegistry() {
    // not called
  }

  /**
   * Register a {@link ServiceAccessor} for a specific {@link ServiceAccessorOwner}.
   *
   * @param owner    {@link ServiceAccessorOwner}.
   * @param accessor {@link ServiceAccessor}.
   */
  protected static void registerAccessor(final ServiceAccessorOwner owner, final ServiceAccessor accessor) {
    if (ACCESSORS.containsKey(owner)) {
      throw new IllegalArgumentException(owner.getName() + " already registered an service accessor!");
    }
    ACCESSORS.put(owner, accessor);
  }

  /**
   * Register a {@link Service} for a given class.
   *
   * @param clazz   class.
   * @param service {@link Service}.
   */
  protected static <T extends Service> void registerService(final Class<T> clazz, final T service) {
    SERVICES.put(clazz, service);
  }

  /**
   * Get a registered {@link Service} to work with it.
   *
   * @param clazz service class.
   * @return {@link Service} of the given class.
   */
  protected static <T extends Service> T access(final Class<T> clazz) {
    return (T) SERVICES.get(clazz);
  }

  protected static Map<Class<? extends Service>, Service> getAllServices() {
    return Collections.unmodifiableMap(SERVICES);
  }
}
