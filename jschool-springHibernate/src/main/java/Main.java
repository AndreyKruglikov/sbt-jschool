import dao.DishDAO;
import dao.DishDAOImpl;
import items.Dish;
import items.Ingredient;
import items.Recipe;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * Created by andrey on 09.08.18.
 */
@Configuration
@ComponentScan(basePackages = "")
public class Main {

    public static void main(String... args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Main.class);
        DishDAO dishesDao = (DishDAO) applicationContext.getBean(DishDAOImpl.class);

        Recipe recipe = new Recipe(new Ingredient("boiled potatoes", "4"),
                new Ingredient("hard cheese", "100 g"),
                new Ingredient("hard cheese", "100 g"));
        Dish dish = new Dish();
        dish.setName("dish from potatoes");
        dish.setRecipe(recipe);

//        dishesDao.save(dish);

        List<Dish> dishes = dishesDao.getDishListByName("potato");
        for (Dish dish1 : dishes) {
            System.out.println(dish1);
        }
    }

    @Bean
    public DriverManagerDataSource setDataSource() {
        return new DriverManagerDataSource("jdbc:postgresql://localhost/dbName", "user", "");
    }

    @Bean
    public SessionFactory setSessionFactory(DataSource dataSource) throws IOException {
        final LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setPackagesToScan("");
        final Properties property = new Properties();
        property.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        property.setProperty("hibernate.show_sql", "true");
        property.setProperty("hibernate.hbm2ddl", "validate");
        factoryBean.setHibernateProperties(property);
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }
}
