package sample.configuration;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Static class for obtaining SessionFactory
 */
public class HibernateSessionFactory {
    private static SessionFactory sessionFactory = buildSessionFactory();

    /**
     * Initializing SessionFactory
     * Settings from hibernate.cfg.xml
     * @return SessionFactory
     */
    protected static SessionFactory buildSessionFactory() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
            throw new ExceptionInInitializerError("Initial SessionFactory failed" + e);
        }
        return sessionFactory;
    }

    /**
     * Getting a SessionFactory
     * @return SessionFactory
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Closing the cache and connection pool
     */
    public static void shutdown() {
        // Закрыть кеши и пулы соединений
        getSessionFactory().close();
    }
}