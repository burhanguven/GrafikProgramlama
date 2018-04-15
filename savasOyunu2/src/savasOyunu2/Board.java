package savasOyunu2;


import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.text.StyledEditorKit.FontFamilyAction;

public class Board extends JPanel implements ActionListener {

    private Timer timer;
    private Craft craft;
    private Alien alien;
    private ArrayList<Alien> aliens;
    private boolean ingame;
    private final int B_WIDTH = 500;
    private final int B_HEIGHT = 400;
    private final int DELAY = 15;
    private int Score = -10;
    private int x=0;
    

    public Board() {

        initBoard();
    }
    private void initBoard() {
        addMouseListener(new LAdapter());
        addKeyListener(new TAdapter());
        addMouseMotionListener(new MAdapter());
        addKeyListener(new TAdapter());
        setFocusable(true);//mermi atýþýný tekrar ediyor.
        setBackground(Color.WHITE);
        ingame = true;

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        //SÝLAHIN EKRANIN NERESÝNDE OLDUÐUNU BELÝRLENÝR.
        craft = new Craft(B_WIDTH/ 2, B_HEIGHT - 64);
        initAliens();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void initAliens() {
        aliens = new ArrayList<>();
        Random rand = new Random();
        //sineðin ekranda dolaþma aralýðý
        int  n = rand.nextInt(480) + 1;
        int  m = rand.nextInt(300) + 1;

        final int[][] pos = {
        		//sineðin baþlangýç noktasý
          {n, m}
        };
        
        for (int[] p : pos) {
            //sinek 10 kere vuruluna kadar dönecek
        	if(x==10 ){
            Score += 10;
            break;
        }
            else{
            aliens.add(new Alien(p[0], p[1]));
            x++;
            Score += 10;
            }
            
        }
        
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (craft.missile >= 0) {
            if (ingame) {

                drawObjects(g);
                doDrawing(g);

            } else {

                drawGameOver(g);
            }
        }
        else{
            drawGameOver(g);
        }
        Toolkit.getDefaultToolkit().sync();
    }

    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        ArrayList<Missile> ms = craft.getMissiles();

        for (Missile m : ms) {
        	//mermiyi ekrana basar.
            if (m.isVisible()) {
                g.drawImage(m.getImage(), m.getX(), m.getY(), this);
            }
        }
        //silahýn hareket metodunun çaðýldýðý kýsým.
        AffineTransform tx = new AffineTransform();
        tx.rotate(Math.toRadians(craft.angle), craft.getX() + craft.image.getWidth(null) / 2, craft.getY() + craft.image.getHeight(null) / 2);

        g2d.transform(tx);

        if (craft.isVisible()) {
            g.drawImage(craft.getImage(), craft.getX(), craft.getY(),
                    this);
        }
    }
    private void drawObjects(Graphics g) {

        for (Alien a : aliens) {
            if (a.isVisible()) {
                g.drawImage(a.getImage(), a.getX(), a.getY(), this);
            }
        }

        Font small = new Font("Year supply of fairy cakes", Font.BOLD, 15);

        g.setColor(Color.black);
        g.setFont(small);

        g.drawString("Puan: " + Score, B_WIDTH - 90, 30);
        g.drawString("Kalan Mermi: " + craft.missile, B_WIDTH-300, B_HEIGHT - 5);
    }

    private void drawGameOver(Graphics g) {

        String msg = "Oyun Bitti";
        Font small = new Font("Year supply of fairy cakes", Font.BOLD, 15);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.black);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2,
                B_HEIGHT / 2);
        g.drawString("Puan: "+ Score, B_WIDTH/2-30, B_HEIGHT-100);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        inGame();

        updateMissiles();
        updateAliens();
        checkCollisions();

        repaint();
    }

    private void inGame() {

        if (!ingame) {
            timer.stop();
        }
    }


    private void updateMissiles() {

        ArrayList<Missile> ms = craft.getMissiles();

        for (int i = 0; i < ms.size(); i++) {

            Missile m = ms.get(i);
            if (m.isVisible()) {
                m.move();
            } else {
                ms.remove(i);
            }
        }
    }

    private void updateAliens() {

        if (aliens.isEmpty()) {

            ingame = false;
            return;
        }

        for (int i = 0; i < aliens.size(); i++) {

            Alien a = aliens.get(i);
            if (a.isVisible()) {
                a.move();
            } else {
                //Score += 10;
                aliens.remove(i);
            }
        }
    }

    public void checkCollisions() {

        Rectangle r3 = craft.getBounds();

      

        ArrayList<Missile> ms = craft.getMissiles();

        for (Missile m : ms) {

            Rectangle r1 = m.getBounds();

            for (Alien alien : aliens) {

                Rectangle r2 = alien.getBounds();

                if (r1.intersects(r2)) {
                    m.setVisible(false);
                    alien.setVisible(false);
                    initAliens();
                }
            }
        }
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            craft.keyPressed(e);
        }
    }
    private class LAdapter extends MouseAdapter{

        
        @Override
        public void mouseClicked(MouseEvent e) {
 
            craft.mouseClicked(e);
        }
        
        
    }
    private class MAdapter extends MouseMotionAdapter{

        @Override
        public void mouseMoved(MouseEvent e) {
            
            craft.mouseMoved(e);
        }
       
         
    }
}
