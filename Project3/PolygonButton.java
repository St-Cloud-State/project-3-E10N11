import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class PolygonButton extends JButton implements ActionListener {
    protected JPanel drawingPanel;
    protected View view;
    private MouseHandler mouseHandler;
    private PolygonCommand polygonCommand;
    private UndoManager undoManager;

    public PolygonButton(UndoManager undoManager, View jFrame, JPanel jPanel) {
        super("Polygon");
        this.undoManager = undoManager;
        addActionListener(this);
        view = jFrame;
        drawingPanel = jPanel;
        mouseHandler = new MouseHandler();
    }

    public void actionPerformed(ActionEvent event) {
        view.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        drawingPanel.addMouseListener(mouseHandler);
    }

    private class MouseHandler extends MouseAdapter {
        private int pointCount = 0;

        public void mouseClicked(MouseEvent event) {
            if (SwingUtilities.isLeftMouseButton(event)) {
                Point point = View.mapPoint(event.getPoint());
                if (pointCount == 0) {
                    polygonCommand = new PolygonCommand(point);
                    undoManager.beginCommand(polygonCommand);
                } else {
                    polygonCommand.setPolygonPoint(point);
                }
                pointCount++;
                drawingPanel.repaint();
            } else if (SwingUtilities.isRightMouseButton(event)) {
                polygonCommand.closePolygon();
                drawingPanel.removeMouseListener(this);
                view.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                undoManager.endCommand(polygonCommand);
                pointCount = 0;
                drawingPanel.repaint();
            }
        }
    }
}