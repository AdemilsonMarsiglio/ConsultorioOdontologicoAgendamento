package br.com.upf.agendamento.model.util;

import java.util.Properties;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    private static final ServiceRegistry serviceRegistry;
    private static Properties properties;

    static {
        try {
            Configuration annotationConfiguration = new Configuration().configure();

            properties = annotationConfiguration.getProperties();

            serviceRegistry = new ServiceRegistryBuilder().applySettings(properties).buildServiceRegistry();

            sessionFactory = annotationConfiguration.buildSessionFactory(serviceRegistry);
        } catch (HibernateException ex) {
            System.err.println("Erro ao criar a SessionFactory." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static void setProperty(String key, String value) {
        properties.setProperty(key, value);
    }

    public static void reload() {
    }
}
