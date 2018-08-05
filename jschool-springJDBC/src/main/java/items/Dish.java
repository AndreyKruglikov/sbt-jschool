package items;

import com.google.gson.Gson;

public class Dish {

    private long   id;
    private String name;
    private Recipe recipe;

    public String getRecipeAsJson() {
        Gson gson = new Gson();
        return gson.toJson(recipe);
    }

    public Recipe restoreRecipeFromJson(String recipeStr) {
        Gson gson = new Gson();
        return gson.fromJson(recipeStr, Recipe.class);
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
