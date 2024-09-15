import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TestRunner {
    public static void main(String[] args) {
        Configuration config = new Configuration().configure();
        @Cleanup SessionFactory sf = config.buildSessionFactory();
        @Cleanup Session session = sf.openSession();
        session.beginTransaction();

        session.getTransaction().commit();

    }
}
