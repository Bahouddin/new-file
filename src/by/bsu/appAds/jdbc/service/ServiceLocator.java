package by.bsu.appAds.jdbc.service;

import java.util.HashMap;
import java.util.Map;

import by.bsu.appAds.jdbc.dao.DAOManager;
import by.bsu.appAds.jdbc.dao.DAOManagerFactory;

public class ServiceLocator {
    private static ServiceLocator instance;

    private Map<Class<?>, Class<?>> services = new HashMap<Class<?>, Class<?>>();

    public void registerService(Class<?> serviceClass, Class<?> service) {
        services.put(serviceClass, service);
    }

    public static void setLocator(ServiceLocator locator) {
        instance = locator;
    }

    @SuppressWarnings("unchecked")
    public static <Type> Type getService(Class<Type> serviceClass) {
        try {
            return (Type)instance.services.get(serviceClass)
                    .getConstructor(DAOManager.class)
                    .newInstance(DAOManagerFactory.getInstance());
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
