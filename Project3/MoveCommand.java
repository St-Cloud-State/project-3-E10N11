import java.util.*;
import java.awt.*;
class MoveCommand extends Command {
  private Vector itemList;
  private double dx, dy; 
  public MoveCommand(double x, double y) {
    dx = x;
    dy = y;
    itemList = new Vector();
    Enumeration enumeration = model.getSelectedItems();
    while (enumeration.hasMoreElements()) {
      Item item = (Item)(enumeration.nextElement());
      itemList.add(item);
    }
  }
  public boolean undo() {
    Enumeration enumeration = itemList.elements();
    while (enumeration.hasMoreElements()) {
      Item item = (Item)(enumeration.nextElement());
      item.translate((-1* dx), (-1 * dy));
    }
    return true;
  }
  public boolean redo() {
    execute();
    return true;
  }

  public void execute() {
    Enumeration enumeration = itemList.elements();
    while (enumeration.hasMoreElements()) {
      Item item = (Item)(enumeration.nextElement());
      item.translate(dx, dy);
    }
  }
}