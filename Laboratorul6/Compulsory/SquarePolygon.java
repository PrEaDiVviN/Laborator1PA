package com.company;

import java.awt.*;

public class SquarePolygon extends Polygon {
    public SquarePolygon(int x0, int y0, int radius) {
            double x = x0 - radius/2;
            double y = y0 - radius/2;
            this.addPoint((int) x, (int) y);
            x = x0 + radius/2;
            y = y0 - radius/2;
            this.addPoint((int) x, (int) y);
            x = x0 + radius/2;
            y = y0 + radius/2;
            this.addPoint((int) x, (int) y);
            x = x0 - radius/2;
            y = y0 + radius/2;
            this.addPoint((int) x, (int) y);
    }
}
