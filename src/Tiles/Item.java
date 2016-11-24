package Tiles;

import Screen.Color;
import Screen.Display;

public class Item {
	private char representation;
	private Color itemColor;
	public Item(char representation, Color itemColor) {
		this.representation = representation;
		this.itemColor = itemColor;
	}

	public String getRepresentation() {
		return Display.makeColor(""+representation, itemColor, Color.NOBACKGROUND);
	}

};
