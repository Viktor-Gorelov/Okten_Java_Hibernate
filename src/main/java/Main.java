import enums.Type;
import models.Car;
import models.DriveLicense;
import models.Owner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();
        Metadata metadata = new MetadataSources(serviceRegistry)
                .addAnnotatedClass(Car.class)
                .addAnnotatedClass(Owner.class)
                .addAnnotatedClass(DriveLicense.class)
                .getMetadataBuilder()
                .build();
        try (
                SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
                Session session = sessionFactory.openSession()
                ){
            Transaction transaction = session.beginTransaction();
            DriveLicense driveLicense = new DriveLicense();
            driveLicense.setSeries("ABC78910");
            session.save(driveLicense);

            List<Car> cars = new ArrayList<>();
            Car car1 = new Car(1,"BMW", Type.OUTLANDER,300,43000,2020);
            cars.add(car1);
            Car car2 = new Car(1,"Volkswagen", Type.SEDAN,270,40000,2019);
            cars.add(car2);

            session.save(car1);
            session.save(car2);
            Owner owner = new Owner(1,"Frank",cars,driveLicense);
            session.save(owner);
            transaction.commit();
        }
    }
}
