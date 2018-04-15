package savasOyunu2;

public class Alien extends Sprite {

    private final int INITIAL_X = 400;
    private final int INITIAL_Y = 400;
    private static final int DIAMETER=20;
    int aci=30;
    int hiz=3;
    double radyan =deg2rad(aci);
    double xvel=Math.cos(radyan)*hiz;
    double yvel=Math.sin(radyan)*hiz;
    int pos_x=0;
    int pos_y=0;
    
    int xa=(int)xvel;
    int ya=(int)yvel;

    public Alien(int x, int y) {
        super(x, y);

        initAlien();
    }
    private double deg2rad(int derece) {
		// TODO Auto-generated method stub
		return derece*(Math.PI/180);
	}
	private void initAlien() {

        loadImage("sda.png");
        getImageDimensions();
    }
    public void move() 
    {
    	if (x + xa < 0)
			xa = (int)+xvel;
		if (x + xa > 480 - DIAMETER)
			xa =(int)-xvel;
		if (y + ya < 0)
			ya = (int)+yvel;
		if (y + ya > 300 - DIAMETER)
			ya=(int)+-yvel;
		
		pos_x+=xvel;
		pos_y+=yvel;
		
		x = x + xa;
		y = y + ya;	
    }
   
}