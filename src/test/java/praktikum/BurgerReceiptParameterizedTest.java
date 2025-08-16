package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BurgerReceiptParameterizedTest {

    private final String expectedReceipt;

    @Mock
    private final Ingredient[] ingredients;

    @Mock
    private Bun bun;

    public BurgerReceiptParameterizedTest(Bun bun, Ingredient[] ingredients, String expectedReceipt) {
        this.bun = bun;
        this.ingredients = ingredients;
        this.expectedReceipt = expectedReceipt;
    }

    @Parameterized.Parameters(name = "Тестовые данные: {0}, {1}, {2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {
                        new Bun("black bun", 100),
                        new Ingredient[]{},
                        "(==== black bun ====)\r\n" +
                                "(==== black bun ====)\r\n\r\n" +
                                "Price: 200,000000\r\n"
                },
                {
                        new Bun("white bun", 200),
                        new Ingredient[]{
                                new Ingredient(IngredientType.SAUCE, "sour cream", 200)
                        },
                        "(==== white bun ====)\r\n" +
                                "= sauce sour cream =\r\n" +
                                "(==== white bun ====)\r\n\r\n" +
                                "Price: 600,000000\r\n"
                },
                {
                        new Bun("red bun", 300),
                        new Ingredient[]{
                                new Ingredient(IngredientType.FILLING, "dinosaur", 200),
                                new Ingredient(IngredientType.SAUCE, "chili sauce", 300)
                        },

                        "(==== red bun ====)\r\n" +
                                "= filling dinosaur =\r\n" +
                                "= sauce chili sauce =\r\n" +
                                "(==== red bun ====)\r\n\r\n" +
                                "Price: 1100,000000\r\n"
                },
                {new Bun("black bun", 100),
                        new Ingredient[]{
                                new Ingredient(IngredientType.SAUCE, "hot sauce", 100),
                                new Ingredient(IngredientType.SAUCE, "sour cream", 200),
                                new Ingredient(IngredientType.SAUCE, "chili sauce", 300),
                                new Ingredient(IngredientType.FILLING, "cutlet", 100),
                                new Ingredient(IngredientType.FILLING, "dinosaur", 200),
                                new Ingredient(IngredientType.FILLING, "sausage", 300)
                        },
                        "(==== black bun ====)\r\n" +
                                "= sauce hot sauce =\r\n" +
                                "= sauce sour cream =\r\n" +
                                "= sauce chili sauce =\r\n" +
                                "= filling cutlet =\r\n" +
                                "= filling dinosaur =\r\n" +
                                "= filling sausage =\r\n" +
                                "(==== black bun ====)\r\n\r\n" +
                                "Price: 1400,000000\r\n"
                }
        });
    }

    @Test
    public void getReceiptParameterizedTest() {
        Burger burger = new Burger();
        burger.setBuns(bun);

        for (Ingredient ingredient : ingredients) {
            burger.addIngredient(ingredient);
        }
        System.out.println(expectedReceipt);
        System.out.println(burger.getReceipt());
        assertEquals(expectedReceipt, burger.getReceipt());
    }
}