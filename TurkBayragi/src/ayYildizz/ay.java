package ayYildizz;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
//import javafx.scene.shape.Ellipse;
import javax.swing.JFrame;
import javax.swing.JPanel;
class Surface extends JPanel {
         private final double points[][] = { 
        { 500, 225 }, { 525, 175 }, { 550, 225 }, 
        { 600, 225 }, { 565, 268 }, { 580, 320 }, { 530, 290 }, 
        { 475, 320 }, { 490, 270 }, { 450, 225 } 
    };
    public void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING,
               RenderingHints.VALUE_RENDER_QUALITY);    
        g2d.setRenderingHints(rh);

        g2d.setPaint(Color.white);
        
      
        
        Area a3 = new Area(new Ellipse2D.Double(120, 100, 330, 330));
        Area a4 = new Area(new Ellipse2D.Double(220, 140, 250, 250));        
       // 
        a3.subtract(a4);
        g2d.fill(a3);
   
          g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                             RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setPaint(Color.white);
        g2d.translate(25, 5);

        GeneralPath star = new GeneralPath();
        setBackground(Color.RED);
        star.moveTo(points[0][0], points[0][1]);

        for (int k = 1; k < points.length; k++)
            star.lineTo(points[k][0], points[k][1]);

        star.closePath();
        g2d.fill(star);        
        
        g2d.dispose();
    }

    @Override
    public void paintComponent(Graphics g) {
                
        super.paintComponent(g);
        doDrawing(g);
    }           
}

public class ay extends JFrame {

    public ay() {

        initUI();
    }

    private void initUI() {
        
        add(new Surface());
        
        setTitle("Turk Bayragi");
        setSize(1000, 600);
        setLocationRelativeTo(null);        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                ay ex = new ay();
                ex.setVisible(true);
            }
        });
    }
}