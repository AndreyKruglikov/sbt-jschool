package items;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Recipe implements Serializable {

    private static final long serialVersionUID = 1L;

    private Set<Ingredient> ingredients;

    public Recipe() {
        ingredients = new HashSet<>();
    }

    public Recipe(Ingredient... ingredients) {
        this.ingredients = new HashSet<Ingredient>();
        for (Ingredient ingredient : ingredients) {
            this.ingredients.add(ingredient);
        }
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Ingredient ingredient : ingredients) {
            result.append(ingredient.getName());
            result.append(": ");
            result.append(ingredient.getQuantitativeComposition());
            result.append("\n");
        }
        return result.toString();
    }
}
