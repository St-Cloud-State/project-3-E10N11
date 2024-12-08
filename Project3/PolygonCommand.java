import java.awt.Point;
import java.util.ArrayList;

public class PolygonCommand extends Command {
  private Polygon polygon;

  public PolygonCommand(Point initialPoint) {
    polygon = new Polygon();
    polygon.addPoint(initialPoint);
  }

  public void setPolygonPoint(Point point) {
    polygon.addPoint(point);
  }

  public void execute() {
    model.addItem(polygon);
  }

  public boolean undo() {
    model.removeItem(polygon);
    return true;
  }

  public boolean redo() {
    execute();
    return true;
  }

  public void closePolygon() {
    polygon.closePolygon();
  }
}