import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class MouseComponent extends JComponent {
    private static final int DEFAULT_WIDTH = 1600;
    private static final int DEFAULT_HEIGHT = 1600;

    private static final int SIDE_LENGTH = 30;
    private static ArrayList<Rectangle2D> squares;
    private static Rectangle2D current; // the square containing the mouse cursor

    public MouseComponent() {
        squares = new ArrayList<>();
        current = null;

        addMouseListener(new MouseHandler());
        addMouseMotionListener(new MouseMotionHandler());
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        // draw all squares
        for (Rectangle2D r : squares)
            g2.draw(r);
    }

    /**
     * Finds the first square containing a point.
     *
     * @param p a point
     * @return the first square that contains p
     */
    public static Rectangle2D find(Point2D p) {
        for (Rectangle2D r : squares) {
            if (r.contains(p)) return r;
        }
        return null;
    }

    /**
     * Adds a square to the collection.
     *
     * @param p the center of the square
     */
    public void add(Point2D p) {
        double x = p.getX();
        double y = p.getY();
        current = new Rectangle2D.Double(x - (double) SIDE_LENGTH / 2, y - (double) SIDE_LENGTH / 2, SIDE_LENGTH,
                SIDE_LENGTH);
        squares.add(current);
        repaint();
    }

    /*Removes a square from a given point
     * @param s the square to remove*/
    public void remove(Rectangle2D s)
    {
        if (s == null) return;
        if (s == current) current = null;
        squares.remove(s);
        repaint();
    }


    private class MouseHandler extends MouseAdapter{
        public void mousePressed(MouseEvent event)
        {
            // add a new square if the cursor isn't inside a square
            current = find(event.getPoint());
            if (current == null) add(event.getPoint());
        }

        public void mouseClicked(MouseEvent event)
        {
            // remove the current square if double-clicked
            current = find(event.getPoint());
            if (current != null && event.getClickCount() >= 2) remove(current);
        }

    }

    private class MouseMotionHandler implements MouseMotionListener{
        @Override
        public void mouseMoved(MouseEvent event) {
            // Get the point where the mouse moved
            Point mousePoint = event.getPoint();

            // Check if the mouse is over a square
            if (find(mousePoint) == null) {
                // If not over a square, set the cursor to the default arrow
                setCursor(Cursor.getDefaultCursor());
            } else {
                // If over a square, change the cursor to a cross-hair
                setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
            }
        }


        @Override
        public void mouseDragged(MouseEvent event) {
            if (current != null)
            {
                int x = event.getX();
                int y = event.getY();

                // Move the square to center at (x, y)
                current.setFrame(x - (double) SIDE_LENGTH / 2, y - (double) SIDE_LENGTH / 2, SIDE_LENGTH, SIDE_LENGTH);
                repaint();

            }

        }
    }}


