package items;


import java.io.Serializable;

public class Ingredient implements Serializable {

    private static final long serialVersionUID = -1L;

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
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        if (!name.equals(that.name)) return false;
        return quantitativeComposition.equals(that.quantitativeComposition);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + quantitativeComposition.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "name='" + name + '\'' +
                ", quantitativeComposition='" + quantitativeComposition + '\'' +
                '}';
    }
}
