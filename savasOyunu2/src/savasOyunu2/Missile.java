package savasOyunu2;


public class Missile extends Sprite {

    private final int BOARD_WIDTH = 990;
   private final int MISSILE_SPEED =5;
    private final double strAngle;
    
    public Missile(int x, int y, double strAngle) {
        super(x, y);
        this.strAngle=strAngle;
        initMissile();
    }
    
    private void initMissile() {
        
        loadImage("missile.png");
        getImageDimensions();        
    }
    public void move() {
        //merminin ��k�� a��s� ve h�z� 
        double dx=Math.cos(Math.toRadians(strAngle)) * MISSILE_SPEED;
        double dy=Math.sin(Math.toRadians(strAngle)) * MISSILE_SPEED;
      
        x += dx;
        y += dy;
        
        if (x >= BOARD_WIDTH)
            vis = false;
    }
}