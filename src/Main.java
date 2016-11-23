import Screen.Animation;
import Screen.Map;

public class Main {

    public static void main(String[] args) {

        Map.initializeMap(100, 30);

        Animation animation = new Animation();

        animation.run();

    }
}
