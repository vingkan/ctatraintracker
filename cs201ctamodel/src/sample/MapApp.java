package sample;

import java.awt.Color;

import eu.jacquet80.minigeo.MapWindow;
import eu.jacquet80.minigeo.Point;
import eu.jacquet80.minigeo.Segment;

public class MapApp {

	public static void main(String[] args) {

		MapWindow window = new MapWindow();
		Segment segment = new Segment(
			new Point(48, 2),
			new Point(45, -1),
			Color.RED
		);
		window.addSegment(segment);
		
		window.setVisible(true);

	}

}
