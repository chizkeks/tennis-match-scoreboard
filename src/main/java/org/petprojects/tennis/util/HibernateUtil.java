package org.petprojects.tennis.util;

import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@UtilityClass
public class HibernateUtil {
    Configuration config = new Configuration().configure();
    public static SessionFactory getSessionFactory() {
        return config.buildSessionFactory();
    }

    /*public static Session getSessionMultithreading() {
            return (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(),
                    new Class[]{Session.class},
                    ((proxy, method, args) -> method.invoke(factory.getCurrentSession(), args)));

    }*/
}
