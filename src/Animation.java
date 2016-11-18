public class Animation extends Thread {

    public Animation(){}

    @Override
    public void run(){
        while (true){
            System.out.print("\033[H\033[2J");

            Map.getNextFrame();

            Display.displayMap(Map.asciiMap);

            try {
                sleep(Math.max(100, 0)); // ???
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}
