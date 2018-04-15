package savasOyunu2;


import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.event.MouseEvent;

public class Craft extends Sprite {

    private int dx;
    private int dy;
    public double angle=-90;
    private ArrayList<Missile> missiles;
    public int missile=200;

    public Craft(int x, int y) {
        super(x, y);

        initCraft();
    }

    private void initCraft() {
        
        missiles = new ArrayList<>();
        loadImage("silah.png");
        getImageDimensions();
    }


    public ArrayList getMissiles() {
        return missiles;
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();


        if (key == KeyEvent.VK_LEFT) {
            angle -= 5;
        }

        if (key == KeyEvent.VK_RIGHT) {
            angle += 5;
        }

        if (key == KeyEvent.VK_SPACE) {
            fire();
        }
    }
    public void mouseMoved(MouseEvent e) {
          
              angle=e.getX();
    }

    public void mouseClicked(MouseEvent e) {
          if(e.getButton()==e.BUTTON3){
              fire();
          } 
     }
    public void fire() {
        missiles.add(new Missile(x + width/2, y + height, angle));
        missile--;
    }
  
}
