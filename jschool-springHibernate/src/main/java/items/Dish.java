package items;

import com.google.gson.Gson;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Dish")
public class Dish implements Serializable {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long   id;
    private String name;
    @Type(type = "types.RecipeType")
    private Recipe recipe;

    public Dish() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", \nname='" + name + '\'' +
                ", \nrecipe=" + recipe +
                '}';
    }
}
