public class Display {

	final public static void displayMap(Tile[][] asciiMap) {
		//Why use strings? Well we can use colors then!
		//Note: This is fairly slow, because sysout tends not to buffer.
		int numberOfBackspaces = 0xcafebabe; //magic
		for(int i = 0; i < numberOfBackspaces; ++i) System.out.print('\b');
		for(int i = 0; i < asciiMap.length; ++i) {
			for(int j = 0; j < asciiMap[i].length; ++j) {
				if(asciiMap[i][j] != null)
					asciiMap[i][j].printTile();
				else
					new Tile('·', j, i).printTile();
			}
			System.out.print('\n'); //new line character	
		}	
		System.out.flush();
	}

	private static String colorToString(Color c, int state) {
		//state symbols: foreground=3 or background=4
		switch (c) {
			case BLACK:
				return "\u001B["+state+"0m";
			case RED:
				return "\u001B["+state+"1m";
			case GREEN:
				return "\u001B["+state+"2m";
			case YELLOW:
				return "\u001B["+state+"3m";
			case BLUE:
				return "\u001B["+state+"4m";
			case MAGENTA:
				return "\u001B["+state+"5m";
			case CYAN:
				return "\u001B["+state+"6m";
			case WHITE:
				return "\u001B["+state+"7m";
			case NORMAL:
			default:
				return "\u001B[0m";
		}	
	}

	final public static String makeColor(String in, Color foregroundColor, Color backgroundColor) {
		//Default is white and black for now

		//Colors might get changed into enums. For now use this list:
		//0 = black, 1 = red, 2 = green, 3 = yellow, 4 = blue
		//5 = magenta, 6 = cyan, 7 = white
		//This will return the string with colored syntax

		return colorToString(foregroundColor, 3) + colorToString(backgroundColor, 4) + in + colorToString(Color.NORMAL, 0);
	}
}
