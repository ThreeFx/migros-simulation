public class Display
{
    // TODO remove TODOs when done
    // TODO do TODOs
    // TODO rename variable:
    private static final String WTF = "\u001B[";
    
    final public static void displayMap(Tile[][] asciiMap)
    {
        //Why use strings? Well we can use colors then!
        //Note: This is fairly slow, because sysout tends not to buffer.
        int numberOfBackspaces = 0xcafebabe; //magic
        for (int i = 0; i < numberOfBackspaces; ++i) System.out.print('\b');
        for (int i = 0; i < asciiMap.length; ++i)
        {
            for (int j = 0; j < asciiMap[i].length; ++j)
            {
                if (asciiMap[i][j] != null)
                    asciiMap[i][j].printTile();
                else
                    new Tile('·', j, i).printTile();
            }
            System.out.print('\n'); //new line character
        }
        System.out.flush();
    }
    
    private static String colorToString(Color c, int state)
    {
        //state symbols: foreground=3 or background=4
        int colorOrd = c.ordinal();
        if (colorOrd > 7)
            return WTF + "0m";
        else
            return WTF + state + "m";
    }
    
    final public static String makeColor(String in, Color foregroundColor, Color backgroundColor)
    {
        //Default is white and black for now
        
        //Colors might get changed into enums. For now use this list:
        //0 = black, 1 = red, 2 = green, 3 = yellow, 4 = blue
        //5 = magenta, 6 = cyan, 7 = white
        //This will return the string with colored syntax
        
        return colorToString(foregroundColor, 3) + colorToString(backgroundColor, 4) + in + colorToString(Color.NORMAL, 0);
    }
}
