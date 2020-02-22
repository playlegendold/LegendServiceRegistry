package net.playlegend.legendserviceregistry.common;

/**
 * This interface represents a {@link Service} from our {@link ServiceRegistry}.
 */
public interface Service {

    /**
     * Check with this method wether the selected service is available or not.
     * @return true when it is available.
     */
    boolean isAvailable();

}
