package net.playlegend.legendserviceregistry.common;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

final class ServiceRegistry {

    private static final Map<Class<? extends Service>, Service> SERVICES = new ConcurrentHashMap<>();
    private static final Map<ServiceAccessorOwner, ServiceAccessor> ACCESSORS = new HashMap<>();

    private ServiceRegistry() {
        //not called
    }

    protected static void registerAccessor(final ServiceAccessorOwner owner, final ServiceAccessor accessor) {
        if (ACCESSORS.containsKey(owner)) {
            throw new IllegalArgumentException(owner.getName() + " already registered an service accessor!");
        }
        ACCESSORS.put(owner, accessor);
    }

    protected static <T extends Service> void registerService(final Class<T> clazz, final T service) {
        SERVICES.put(clazz, service);
    }

    protected static <T extends Service> T access(final Class<T> clazz) {
        return (T) SERVICES.get(clazz);
    }
}
