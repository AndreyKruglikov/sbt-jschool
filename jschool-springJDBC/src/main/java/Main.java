import dao.DishesDao;
import items.Dish;
import items.Ingredient;
import items.Recipe;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

@Configuration
@ComponentScan(basePackages = "com.sbt.jschool")
public class Main {

    public static void main(String... args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Main.class);
        DishesDao dishesDao = (DishesDao) applicationContext.getBean(DishesDao.class);
        dishesDao.createDishesTable();

        Recipe recipe = new Recipe(new Ingredient("boiled potatoes", "4"),
                                   new Ingredient("hard cheese", "100 g"),
                                   new Ingredient("hard cheese", "100 g"));
        Dish dish = new Dish();
        dish.setName("dish from potatoes");
        dish.setRecipe(recipe);

        dishesDao.insertDish(dish);

        List<Dish> dishes = dishesDao.getDishesListByName("potato");
        for (Dish dish1 : dishes) {
            System.out.println(dish1);
        }
    }

    @Bean
    public DriverManagerDataSource dataSource() {
        return new DriverManagerDataSource("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1", "sa", "");
    }

    @Bean
    public DishesDao dishesDao() {
        return new DishesDao();
    }
}
