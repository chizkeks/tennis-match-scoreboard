package org.petprojects.tennis.util;

import lombok.Getter;
import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@UtilityClass
public class HibernateUtil {
    Configuration config = new Configuration().configure();
    @Getter
    private static final SessionFactory sessionFactory = config.buildSessionFactory();
}
