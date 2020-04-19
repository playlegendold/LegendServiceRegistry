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
