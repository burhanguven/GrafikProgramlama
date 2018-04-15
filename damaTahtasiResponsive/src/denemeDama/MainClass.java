package denemeDama;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.StrokeBorder;

import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;
import java.util.Random;

class Surface extends JPanel {
	
	int sayi=0;
	
    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g.create();
    
       int w = getWidth();
       int h = getHeight();
       
       super.paintComponent(g2d);
       this.setBackground(Color.WHITE);
      
       int x=w/8;
       int y=h/8;
       
       g2d.setColor(Color.black);
       
       for(int i=0; i<w; i+=x*2)
       {
    	   for(int j=0; j<h; j+=y*2)
    	  {
    		   g2d.fillRect(i, j, x, y);   
    	   }  
       }
       for(int i=x; i<w; i+=x*2)
       {
    	   for(int j=y; j<h; j+=y*2)
    	   {
    		   g2d.fillRect(i, j, x, y);
    	   }
      }       
        g2d.dispose();
        
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }
}

public class MainClass extends JFrame{
	
	public MainClass()
	{
		initUI();
	}
	private void initUI() {
	
		 add(new Surface());
		 Random rnd=new Random();
		 
		 int w=rnd.nextInt(1000);
		
		 
        setTitle("Responsive Dama Tahtasi");
        setSize(w, w);
        setLocationRelativeTo(null);        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  EventQueue.invokeLater(new Runnable() {
	            @Override
	            public void run() {

	            	MainClass ex = new MainClass();
	                ex.setVisible(true);
	            }
	        });
	}

}
