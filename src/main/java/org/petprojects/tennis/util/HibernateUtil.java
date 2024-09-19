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
}
