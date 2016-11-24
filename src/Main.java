import Screen.Animation;
import Screen.Map;

public class Main {

    public static void main(String[] args) {

        Map.initializeMap("Art/smallmap.ASCIIART");

        Animation animation = new Animation();

        animation.run();

    }
}
