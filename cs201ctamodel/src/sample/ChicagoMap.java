package sample;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eu.jacquet80.minigeo.MapWindow;
import eu.jacquet80.minigeo.POI;
import eu.jacquet80.minigeo.Point;
import eu.jacquet80.minigeo.Segment;


public class ChicagoMap {
    private static Pattern POINT = Pattern.compile("^.*?(-?\\d+\\.\\d+)\\s+(-?\\d+\\.\\d+)$");
    
    public static void main(String[] args) throws IOException {
        MapWindow window = new MapWindow();
        
        BufferedReader r = new BufferedReader(new FileReader("chicago.poly.txt"));
        String line;
        Point cur, prec = null;
        int readCount = 0;
        int errCount = 0;
        while((line = r.readLine()) != null) {
            readCount++;
            Matcher m = POINT.matcher(line);
            if(m.matches()) {
                double lon = Double.parseDouble(m.group(1));
                double lat = Double.parseDouble(m.group(2));
                cur = new Point(lat, lon);
                if(prec != null) window.addSegment(new Segment(prec, cur, lon<0 ? Color.BLUE : Color.RED));
                prec = cur;
            } else errCount++;
        }
        
        window.addPOI(new POI(new Point(41.9869192, -87.9398331), "Chicago"));
        //window.
        
        System.out.println("Read " + readCount + " lines; ignored " + errCount);
        
        window.setVisible(true);
    }

}