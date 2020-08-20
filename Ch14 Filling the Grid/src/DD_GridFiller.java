import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * @author Dolunay Dagci
 * CISS111-360
 * 3.10.19
 * Fixed code: 3.17.19
 * Assignment: Ch14 Filling the Grid
 * In this program, when user clicks a square, a circle appears until the user clicks it again.
 */
public class DD_GridFiller extends JFrame {

    private int gridRows;
    private int gridColumns;

    private Color[][] circleColor;  //color of circles
    private Color lineColor;       //color of lines

    /**
     * constructor
     */
    public DD_GridFiller() {
        setTitle("My Grid Filler");
        setSize(600,600);
        setLayout(new GridLayout(4,4));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        circleColor = new Color[4][4]; //stores the colors in arrays
        gridRows = 4;
        gridColumns = 4;
        lineColor = Color.RED;
        setPreferredSize( new Dimension(90*4, 90*4) );
        setBackground(Color.BLACK); // set the background color for this panel.
        addMouseListener(new MouseListener());

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int top, left;  // position for top left corner of the window
        left = ( screenSize.width - getWidth() ) / 2;
        top = ( screenSize.height - getHeight() ) / 2;
        setLocation(left,top);
        setResizable(false);

        setVisible(true);
        pack();
    }

    /**
     *
     * @param g Graphics object
     */
    public void paint(Graphics g) {
        g.setColor(getBackground());
        g.fillRect(0,0,getWidth(),getHeight());
        int row, col;
        double cellWidth = (double)getWidth() / gridColumns;  //get cell width
        double cellHeight = (double)getHeight() / gridRows;   //get cell height

        //create circles in every cell
        for (row = 0; row < gridRows; row++) {
            for (col = 0; col < gridColumns; col++) {
                if (circleColor[row][col] != null) {
                    int x1 = (int)(col*cellWidth);
                    int y1 = (int)(row*cellHeight);
                    g.setColor(circleColor[row][col]);
                    g.fillOval(x1-2, y1-2, 23*4, 23*4);
                }
            }
        }
        //CREATES THE LINES
        if (lineColor != null) {
            g.setColor(lineColor);
            for (row = 1; row < gridRows; row++) {
                int y = (int)(row*cellHeight);
                g.drawLine(0,y,getWidth(),y);
            }
            for (col = 1; col < gridRows; col++) {
                int x = (int)(col*cellWidth);
                g.drawLine(x,0,x,getHeight());
            }
        }
    }


    /**
     * Finds the row
     * @param pixelY location on x-axis
     * @return rows
     */
    private int findRow(int pixelY) {
        return (int)(((double)pixelY)/getHeight()*gridRows);
    }

    /**
     * Finds the column
     * @param pixelX location of y-axis
     * @return columns
     */
    private int findColumn(int pixelX) {
        return (int)(((double)pixelX)/getWidth()* gridColumns);
    }

    private class MouseListener implements java.awt.event.MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            int row, col; // the row and column in the grid of squares where the user clicked.
            row = findRow( e.getY() ); col = findColumn( e.getX() );  //find the location of cells clicked

            if (circleColor[row][col] == null) circleColor[row][col] = new Color(0,223,197); //if there's no circle inside the cell, circle appears
            else circleColor[row][col] = null;  //if there's a circle inside the cell, circle disappears

            repaint(); // redraw the panel by calling the paintComponent method.
        }
        @Override
        public void mousePressed(MouseEvent e) { }
        @Override public void mouseReleased(MouseEvent e) { }
        @Override public void mouseEntered(MouseEvent e) { }
        @Override public void mouseExited(MouseEvent e) { }
    }
    public static void main (String[] args) {
        new DD_GridFiller();
    }
}
