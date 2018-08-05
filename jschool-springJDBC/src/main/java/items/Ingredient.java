package items;

import java.util.Objects;

public class Ingredient {

    private String name;
    private String quantitativeComposition;

    public Ingredient(String name, String quantitativeComposition) {
        this.name = name;
        this.quantitativeComposition = quantitativeComposition;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantitativeComposition(String quantitativeComposition) {
        this.quantitativeComposition = quantitativeComposition;
    }

    public String getName() {
        return name;
    }

    public String getQuantitativeComposition() {
        return quantitativeComposition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ingredient)) return false;
        Ingredient that = (Ingredient) o;
        return Objects.equals(getName(), that.getName()) &&
                Objects.equals(getQuantitativeComposition(), that.getQuantitativeComposition());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getQuantitativeComposition());
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "name='" + name + '\'' +
                ", quantitativeComposition='" + quantitativeComposition + '\'' +
                '}';
    }
}
