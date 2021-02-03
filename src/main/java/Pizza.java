import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Pizza {
    public Set<String> setIngredients;
    public Integer index;
    public int ingredientsQuantity;
    public Pizza maxOppositePizza;
    public int valueUnionTwo;
    public List<Pizza> listOfThreeOpposite;
    public int valueUnionThree;
    public List<Pizza> listOfFourOpposite;
    public int valueUnionFour;
    public boolean isLockedForT2;
    public boolean isLockedForT3;
    public boolean isLockedForT4;
    public boolean isSent;

    /*
    чтобы в каждом объекте не юзать, вот именно эту инфу надо в HashMap;
    public List<Pizza> clonesPizza = new ArrayList<>();
    public int quantityRemained;*/


    public void setMaxOppositePizza(Pizza maxOppositePizza) {
        this.maxOppositePizza = maxOppositePizza;
    }

    public void setValueUnionTwo(int valueUnionTwo) {
        this.valueUnionTwo = valueUnionTwo;
    }

    public void setLockedForT2(boolean lockedForT2) {
        isLockedForT2 = lockedForT2;
    }

    public void lazySet(Pizza maxOppositePizza, int valueUnionTwo, boolean isLockedForT2) {
        setMaxOppositePizza(maxOppositePizza);
        setValueUnionTwo(valueUnionTwo);
        setLockedForT2(isLockedForT2);
    }

    public Pizza(int index, Set<String> ingredients) {
        this.index = index;
        this.setIngredients = ingredients;
        this.ingredientsQuantity = setIngredients.size();
//        this.maxOppositePizza = null;
        this.valueUnionTwo = 0;
        this.valueUnionThree = 0;
        this.valueUnionFour = 0;
        this.listOfThreeOpposite = new ArrayList<>();
        this.listOfFourOpposite = new ArrayList<>();
        this.isLockedForT2 = false;
        this.isLockedForT3 = false;
        this.isLockedForT4 = false;
        this.isSent = false;
    }

    public Pizza() {
        this.isLockedForT2 = false;
        this.isLockedForT3 = false;
        this.isLockedForT4 = false;
        this.isSent = false;
    }
}
