package dao;

import items.Dish;

import java.util.List;

/**
 * Created by andrey on 09.08.18.
 */
public interface DishDAO {
    public void         save(Dish dish);
    public List<Dish>   getDishListByName(String name);
    public List<Dish>   list();
}
