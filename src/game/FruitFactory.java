package game;
import java.util.Random;
public class FruitFactory {
    private static final int FRUIT_TYPE_COUNT = 6;
    public static Fruit createRandom(int col, int row, Random rand) {
        int type = rand.nextInt(FRUIT_TYPE_COUNT);
        int px = col * 32, py = row * 32;
        return switch (type) {
            case 0 -> new Durian(px, py, "Durian");
            case 1 -> new Apple(px, py, "Apple");
            case 2 -> new Kiwi(px, py, "Kiwi");
            case 3 -> new DragonFruit(px, py, "Dragon Fruit");
            case 4 -> new Watermelon(px, py, "Watermelon");
            default -> new Chilli(px, py, "Chilli");
        };
    }

    private FruitFactory() {}
}