import org.fusesource.jansi.*;
import org.fusesource.jansi.Ansi.Color;

public class Main
{
    public static void main(String[] args)
    {
        AnsiConsole.systemInstall();
        Ansi ansi = Ansi.ansi();
        
        // example from jansi
        System.out.println(
                ansi.eraseScreen().fg(Color.RED).a("Hello").fg(Color.GREEN).a(" World").reset()
        );
        /*
        Map.initializeMap(20, 20);
        
        Animation animation = new Animation();
        
        animation.run();
        */
        AnsiConsole.systemUninstall();
    }
}
