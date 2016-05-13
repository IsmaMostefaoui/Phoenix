package com.umons.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class MyButton extends JButton{

	private static final long serialVersionUID = 1L;
	
	String text;
	Color color;
	Color colorDefault;
	Font customFont;
	boolean alphaRollover;
	
	public MyButton(String text, Color color) {
		super(text);
		this.text = text;
		this.color = color;
		colorDefault = color;
		alphaRollover = false;
		try {
            //create the font to use. Specify the size!
			InputStream myStream = new BufferedInputStream(new FileInputStream("./misc/cowboy.ttf"));
            customFont = Font.createFont(Font.TRUETYPE_FONT, myStream);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
        }catch (IOException e) {
            e.printStackTrace();
        }
        catch(FontFormatException e) {
            e.printStackTrace();
        }
		customFont = new Font(customFont.getName(), Font.PLAIN, 27);
		this.setPreferredSize(new Dimension(250, 60));
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				MyButton.this.color = colorDefault;
				MyButton.this.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 0)));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				MyButton.this.color = MyButton.this.color.brighter();
				MyButton.this.setBorder(BorderFactory.createLineBorder(MyButton.this.color.brighter().brighter(), 2));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
	}
	
	public void paintComponent(Graphics g) {
	    Graphics2D g2d = (Graphics2D)g;
	    int ascent = g2d.getFontMetrics().getAscent();
	    int width = g2d.getFontMetrics().stringWidth(this.text);
	    g2d.setColor(color);
	   /*if (alphaRollover){
	    	g2d.setComposite( AlphaComposite.getInstance( AlphaComposite.SRC_OVER, 0.4f ) ); // g2d dessinea avec 40% de transparence
	    }else {
	    	g2d.setComposite( AlphaComposite.getInstance( AlphaComposite.SRC_OVER, 1.0f ) ); // g2d dessinea avec 40% de transparence
	    }*/
	    g2d.fillRect(0, 0, 500, 100);
	    g2d.setColor(Color.BLACK);
	    g2d.setFont(customFont);
	    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    g2d.drawString(this.text, (int)(this.getSize().getWidth()-width)/2, ((int)(this.getSize().getHeight() - ascent)/2) + ascent);
	}
	
	
}
