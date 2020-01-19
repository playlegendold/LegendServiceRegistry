package net.playlegend.legendserviceregistry.common;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class ServiceRegistry {

    private static final Map<Class<? extends Service>, Service> services = new ConcurrentHashMap<Class<? extends Service>, Service>();
    private static final Map<ServiceAccessorOwner, ServiceAccessor> accessors = new HashMap<ServiceAccessorOwner, ServiceAccessor>();

    protected static void registerAccessor(ServiceAccessorOwner owner, ServiceAccessor accessor) {
        if (accessors.containsKey(owner)) {
            throw new IllegalArgumentException(owner.getName() + " already registered an service accessor!");
        }
        accessors.put(owner, accessor);
    }

    protected static <T extends Service> void registerService(Class<T> clazz, T service) {
        services.put(clazz, service);
    }

    protected static <T extends Service> T access(Class<T> clazz) {
        return (T) services.get(clazz);
    }
}
