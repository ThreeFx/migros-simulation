public class Main {

	public static void main(String[] args) {

		Map.initializeMap(20, 20);

		// play 100 frames
		for(int f=0;f<100;f++){
			System.out.print("\033[H\033[2J");

			Map.getNextFrame();

			Display.displayMap(Map.asciiMap);

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

    }
}
