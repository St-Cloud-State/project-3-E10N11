import java.awt.Point;
import java.util.ArrayList;

public class Polygon extends Item {
  private ArrayList<Point> points;

  public Polygon() {
    points = new ArrayList<>();
  }

  public void addPoint(Point point) {
    points.add(point);
  }

  public void closePolygon() {
    if (points.size() > 2) {
      points.add(points.get(0)); // Close the polygon by adding the first point at the end
    }
  }

  public boolean includes(Point point) {
    // This is to implement the point-in-polygon algorithm
    // But it is not required so we just return false
    return false;
  }

  public void render(UIContext uiContext) {
    // Draw nothing if no points
    if (points.size() == 0) {
        return;
    }

    // Draw lines between consecutive points
    for (int i = 0; i < points.size() - 1; i++) {
        Point p1 = points.get(i);
        Point p2 = points.get(i + 1); 
        uiContext.drawLine(p1, p2);
    }

    // If polygon is closed (last point connects to first), draw final line
    if (points.size() > 2 && points.get(0) == points.get(points.size() - 1)) {
        uiContext.drawLine(points.get(points.size() - 1), points.get(0));
    }
}

  @Override
  public void translate(double dx, double dy) {
      for (int i = 0; i < points.size() - 1; i++) {
        points.get(i).translate((int)dx, (int)dy);
    }  
  }
}