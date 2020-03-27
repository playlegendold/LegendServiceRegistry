package net.playlegend.legendserviceregistry.common;

import lombok.Getter;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This class represents an {@link ServiceAccessor}.
 */
public class ServiceAccessor {

  private final Set<Class<? extends Service>> dependentServices = new HashSet<>();

  /**
   * Owner of this {@link ServiceAccessor}.
   */
  @Getter
  private final ServiceAccessorOwner owner;

  /**
   * Create a new instance of a {@link ServiceAccessor}.
   *
   * @param owner {@link ServiceAccessorOwner} of the accessor.
   */
  public ServiceAccessor(final ServiceAccessorOwner owner) {
    this.owner = owner;
    ServiceRegistry.registerAccessor(owner, this);
  }

  /**
   * Get a {@link Service} from a {@link Class} out of plugin specific {@link Service}s.
   *
   * @param clazz {@link Class} of the service.
   * @return the requested {@link Service}.
   */
  public <T extends Service> T access(final Class<T> clazz) {
    dependentServices.add(clazz);
    return ServiceRegistry.access(clazz);
  }

  /**
   * Register a {@link Service} for a plugin.
   *
   * @param clazz   {@link Service}s {@link Class}.
   * @param service that should be registered.
   */
  public <T extends Service> void register(final Class<T> clazz, final T service) {
    ServiceRegistry.registerService(clazz, service);
  }

  /**
   * Get all dependent {@link Service}s of a plugin.
   *
   * @return a {@link List} that contains all dependent {@link Service}s of this accessor.
   */
  public Set<Class<? extends Service>> getDependentServices() {
    return Collections.unmodifiableSet(dependentServices);
  }

  /**
   * Get all registered {@link Service}s.
   *
   * @return a {@link Map} that contains all registered {@link Service}s.
   */
  public Map<Class<? extends Service>, Service> getActiveServices() {
    return ServiceRegistry.getAllServices();
  }

}
