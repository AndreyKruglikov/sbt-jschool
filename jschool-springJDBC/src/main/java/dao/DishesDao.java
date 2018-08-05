package dao;

import items.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DishesDao {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Autowired
    public void setJdbcTemplate(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void createDishesTable() {
        String sql = "create table dishes(" +
                "id int auto_increment primary key," +
                "name varchar(50) not null," +
                "recipe varchar not null)";
        jdbcTemplate.execute(sql);
    }

    public void insertDish(Dish dish) {
        String sql = "insert into dishes (name, recipe) values (:name, :recipe)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", dish.getName());
        params.addValue("recipe", dish.getRecipeAsJson());
        namedParameterJdbcTemplate.update(sql, params);
    }

    public List<Dish> getDishesListByName(String name) {
        String sql = "select * from dishes where upper(name) like :name";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", "%" + name.toUpperCase() + "%");
        return namedParameterJdbcTemplate.query(sql, params, new RowMapper<Dish>() {
            @Override
            public Dish mapRow(ResultSet resultSet, int i) throws SQLException {
                Dish dish = new Dish();
                dish.setId(resultSet.getInt("id"));
                dish.setName(resultSet.getString("name"));
                dish.setRecipe(dish.restoreRecipeFromJson(resultSet.getString("recipe")));
                return dish;
            }
        });
    }
}
