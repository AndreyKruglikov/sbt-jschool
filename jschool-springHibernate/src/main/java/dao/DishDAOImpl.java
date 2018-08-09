package dao;

import items.Dish;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import java.util.List;
import java.util.Queue;

/**
 * Created by andrey on 09.08.18.
 */
@Component
public class DishDAOImpl implements  DishDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Dish dish) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(dish);
            transaction.commit();
        }
    }

    @Override
    public List<Dish> getDishListByName(String name) {
        List<Dish> dishList;
        try (Session session = this.sessionFactory.openSession()) {
            Query query = session.createSQLQuery("select * from dish where upper(name) like :name")
                                 .addEntity(Dish.class)
                                 .setParameter("name", "%" + name.toUpperCase() + "%");
            dishList = query.getResultList();
        }
        return dishList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Dish> list() {
        List<Dish> dishList;
        try (Session session = this.sessionFactory.openSession()) {
            dishList = session.createQuery("from Dish").list();
        }
        return dishList;
    }
}
