import enums.Type;
import models.Car;
import models.Word;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();
        Metadata metadata = new MetadataSources(serviceRegistry)
                .addAnnotatedClass(Word.class)
                .addAnnotatedClass(Car.class)
                .getMetadataBuilder()
                .build();
        try (
            SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
            Session session = sessionFactory.openSession()
        ){
            Transaction transaction = session.beginTransaction();
            // Word apple = new Word(1,"apple");
            //session.save(apple);
            // session.createQuery("from Word", Word.class).list().forEach(System.out::println);
            Car car = new Car(2,"Ferrari", Type.SEDAN,220,23000,2018);
            session.save(car);
            transaction.commit();
        }
    }
}
