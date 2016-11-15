public class StoreMap {
	String[] asciiMap; //Why use strings? Well we can use colors then!
	
	public void displayMap() {
		//Note: This is fairly slow, because sysout tends not to buffer.
		int numberOfBackspaces = 0xcafebabe; //magic
		for(int i = 0; i < numberOfBackspaces; ++i) System.out.print('\b');
		for(int i = 0; i < asciiMap.length; ++i) {
				System.out.println(asciiMap[i]);
		}	
		System.out.flush();
	}
}
