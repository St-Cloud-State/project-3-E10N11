import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.List;

public class MoveButton extends JButton implements ActionListener {
    protected JPanel drawingPanel;
    protected View view;
    private MouseHandler mouseHandler;
    private MoveCommand moveCommand;
    private UndoManager undoManager;

    public MoveButton(UndoManager undoManager, View jFrame, JPanel jPanel) {
        super("Move");
        this.undoManager = undoManager;
        addActionListener(this);
        view = jFrame;
        drawingPanel = jPanel;
        mouseHandler = new MouseHandler();
    }

    public void actionPerformed(ActionEvent event) {
        view.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        // Change cursor when button is clicked
        drawingPanel.addMouseListener(mouseHandler);
        // Start listening for mouseclicks on the drawing panel
    }

    private class MouseHandler extends MouseAdapter {
        private Point startPoint;

        public void mousePressed(MouseEvent event) {
            view.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
            startPoint = event.getPoint();
        }

        public void mouseReleased(MouseEvent event) {
            view.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            if (startPoint == null) return;

            Point endPoint = event.getPoint();
            double dx = endPoint.getX() - startPoint.getX();
            double dy = endPoint.getY() - startPoint.getY();
            
            //This need to handle the move information
            moveCommand = new MoveCommand(dx, dy);
            undoManager.beginCommand(moveCommand);
            moveCommand.execute();
            undoManager.endCommand(moveCommand);

            drawingPanel.removeMouseListener(mouseHandler);
            view.refresh(); // Refresh the view to show updated positions
        }
    }
}
