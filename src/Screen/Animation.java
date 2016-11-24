package Screen;

public class Animation extends Thread {

    public Animation(){}

    @Override
    public void run(){
        while (true){
            Map.getNextFrame();

            System.out.print("\033[H\033[2J");

            Display.displayMap(Map.asciiMap);

            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}
