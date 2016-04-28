package com.umons.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JButton;

public class MyButton extends JButton{

	private static final long serialVersionUID = 1L;
	
	String text;
	Color color;
	Font customFont;
	
	public MyButton(String text, Color color) {
		super(text);
		this.text = text;
		this.color = color;
		try {
            //create the font to use. Specify the size!
			InputStream myStream = new BufferedInputStream(new FileInputStream("./FunSized.ttf"));
            customFont = Font.createFont(Font.TRUETYPE_FONT, myStream);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
        }catch (IOException e) {
            e.printStackTrace();
        }
        catch(FontFormatException e) {
            e.printStackTrace();
        }
		this.setPreferredSize(new Dimension(250, 60));
	}
	
	public void paintComponent(Graphics g) {
	    Graphics2D g2d = (Graphics2D)g;
	    int ascent = g2d.getFontMetrics().getAscent();
	    int width = g2d.getFontMetrics().stringWidth(this.text);
	    g2d.setColor(color);
	    g2d.fillRect(0, 0, 500, 60);
	    g2d.setColor(Color.white);
	    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    g2d.drawString(this.text, (int)(this.getSize().getWidth()-width)/2, ((int)(this.getSize().getHeight()-ascent)/2)+10);
	}
	
	
}
