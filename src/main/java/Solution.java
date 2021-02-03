import java.io.*;
import java.util.*;

public class Solution {
    public static final String outputFileName = "solution.txt"; //need to change
    public static List<Pizza> allPizzas = new ArrayList<>();
    public static Map<Set<String>, List<Pizza>> duplicatesMap = new HashMap<>(); //количество дублей = List.size();
    public static int pizzaQuantity;
    public static int teamTwoQuantity;
    public static int teamThreeQuantity;
    public static int teamFourQuantity;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            String pizzaInfo = reader.readLine();
            fillQuantityValues(pizzaInfo);
            //TODO: 1. some func to organize delivery plan;
            int indexOfPizza = 0;
            while ((pizzaInfo = reader.readLine()) != null) {
                Pizza pizza = makePizza(indexOfPizza, pizzaInfo);
                allPizzas.add(pizza);
                addToDupMap(pizza);
                findPartnerWhileParse(pizza);
                indexOfPizza++;
            }
            //TODO: 2. Sort all pizzas by combination of two
            //TODO: 3.0 Create class delivery;
            //TODO: 3.1 According to delivery plan to the teams of two fill delivery entity
            //TODO: 3.2  create delivery array and add to them delivery entities.
            //TODO: 3.3 all used pizzas for delivery should be deleted from allPizzas
            //TODO: look for bigger combinations (of 3, of 4);

            //проверка заполненности партнеров для пицц
            for (Pizza pizza : allPizzas) {
                System.out.println("index: " + pizza.index);
                if (pizza.maxOppositePizza != null)
                    System.out.println("partner index: " + pizza.maxOppositePizza.index);
                else
                    System.out.println("partner index: null");
                System.out.println("their union: " + pizza.valueUnionTwo);
                System.out.println();
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static void addToDupMap(Pizza pizza) {
        Set<String> setPizza = pizza.setIngredients; // инициализация, чтобы все время не обращаться к объекту
        if (!duplicatesMap.containsKey(setPizza)) { // проверка, есть ли пицца с таким составом, если нет создаем
            duplicatesMap.put(setPizza, new ArrayList<>());
        }
        duplicatesMap.get(setPizza).add(pizza); // добавляем в список значений пиццу
    }

    public static void findPartnerWhileParse(Pizza pizza) {
        Integer index = pizza.index;
        Set<String> setPizza = pizza.setIngredients; // инициализация, чтобы все время не обращаться к объекту
        if (index > 0) {
            int max = 0;
            for (int i = index - 1; i >= 0; i--) {
                HashSet<String> union = new HashSet<>();
                Pizza pizzaCompare = allPizzas.get(i);
                union.addAll(pizzaCompare.setIngredients);
                union.addAll(setPizza);
                int quantity = union.size();
                // проверка на занятость пиццы и обновление только если текущая комбинация больше
                if (max < quantity && quantity != pizza.ingredientsQuantity && quantity > pizzaCompare.valueUnionTwo) {
                    max = quantity;
                    pizza.lazySet(pizzaCompare, quantity, true);
                    pizzaCompare.lazySet(pizza, quantity, true);
                } else if (max == quantity) { //сравнение по количеству повторений, приоритет наиболее встречающейся
                    int quantityClonesCurrent = duplicatesMap.get(pizzaCompare.setIngredients).size();
                    int quantityClonesSetled = duplicatesMap.get(pizza.maxOppositePizza.setIngredients).size();
                    if (quantityClonesCurrent > quantityClonesSetled) {  // не сработает, если это клоны одной и той же пиццы
                        pizza.lazySet(pizzaCompare, quantity, true);
                        duplicatesMap.get(pizzaCompare.setIngredients).remove(pizzaCompare);
                    }
                }
            }
        }
    }

    public static void fillQuantityValues(String quantityInfo) throws IOException {
        String[] resultDivided = quantityInfo.split(" ");
        System.out.println(resultDivided);
        Solution.pizzaQuantity = Integer.parseInt(resultDivided[0]);
        Solution.teamTwoQuantity = Integer.parseInt(resultDivided[1]);
        Solution.teamThreeQuantity = Integer.parseInt(resultDivided[2]);
        Solution.teamFourQuantity = Integer.parseInt(resultDivided[3]);
    }

    public static Pizza makePizza(int index, String info) {
        String[] infoDivided = info.split(" ");
        Set<String> ingredients = new HashSet<>();
        ingredients.addAll(Arrays.asList(infoDivided)); //add all elements
        ingredients.remove(infoDivided[0]); //delete number of ingredients from set;
        Pizza pizza = new Pizza(index, ingredients);
        return pizza;
    }
}
