package net.playlegend.legendserviceregistry.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.Getter;

/**
 * This class represents an {@link ServiceAccessor}.
 */
public class ServiceAccessor {

    /**
     * Contains all services of a plugin.
     */
    private final List<Class<? extends Service>> dependentServices = new ArrayList<Class<? extends Service>>();

    /**
     * Owner of this {@link ServiceAccessor}.
     */
    @Getter
    private ServiceAccessorOwner owner;

    /**
     * Create a new instance of a {@link ServiceAccessor}.
     * @param owner {@link ServiceAccessorOwner} of the accessor.
     */
    public ServiceAccessor(final ServiceAccessorOwner owner) {
        this.owner = owner;
        ServiceRegistry.registerAccessor(owner, this);
    }

    /**
     * Get a {@link Service} from a {@link Class} out of plugin specific {@link Service}s.
     * @param clazz {@link Class} of the service.
     * @param <T>
     * @return the requested {@link Service}.
     */
    public <T extends Service> T access(final Class<T> clazz) {
        dependentServices.add(clazz);
        return ServiceRegistry.access(clazz);
    }

    /**
     * Register a {@link Service} for a plugin.
     * @param clazz {@link Service}s {@link Class}.
     * @param service that should be registered.
     * @param <T>
     */
    public <T extends Service> void register(final Class<T> clazz, final T service) {
        ServiceRegistry.registerService(clazz, service);
    }

    /**
     * Get all {@link Service}s of a plugin.
     * @return a {@link List} that contains all registered {@link Service}s.
     */
    public List<Class<? extends Service>> getDependentServices() {
        return Collections.unmodifiableList(dependentServices);
    }
}
