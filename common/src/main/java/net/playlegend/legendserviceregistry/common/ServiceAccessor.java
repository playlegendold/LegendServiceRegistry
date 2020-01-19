package net.playlegend.legendserviceregistry.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.Getter;

public class ServiceAccessor {

    private final List<Class<? extends Service>> dependentServices = new ArrayList<Class<? extends Service>>();

    @Getter
    private ServiceAccessorOwner owner;

    public ServiceAccessor(ServiceAccessorOwner owner) {
        this.owner = owner;
        ServiceRegistry.registerAccessor(owner, this);
    }

    public <T extends Service> T access(Class<T> clazz) {
        dependentServices.add(clazz);
        return ServiceRegistry.access(clazz);
    }

    public <T extends Service> void register(Class<T> clazz, T service) {
        ServiceRegistry.registerService(clazz, service);
    }

    public List<Class<? extends Service>> getDependentServices() {
        return Collections.unmodifiableList(dependentServices);
    }

}
