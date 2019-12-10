package com.pierre.jframe;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.*;
import javax.imageio.*;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class ImageTest {
	private Image image;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				ImageFrame imageFrame = new ImageFrame();
				imageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				imageFrame.setVisible(true);
			}
		});
		
	}
	
	class ImageFrame extends JFrame{
		public static final int DEFAULT_WIDTH = 400;
		public static final int DEFAULT_HEIGHT = 400;
		public ImageFrame() {
			// TODO Auto-generated constructor stub
			setTitle("Imagetest");
			setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
			//add component to frame
			ImageComponent imageComponent = new ImageComponent();
			add(imageComponent);
		}
	}
	
	//a component that displays a tiled image
	class ImageComponent extends JComponent{
		public ImageComponent() {
			//acquire the image
			try {
				image = ImageIO.read(new File("1234.gif"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void paintComponent(Graphics graphics) {
		
		if (image == null) {
			return;
		}
		int imageWidth = image.getWidth((ImageObserver) this);
		int imageHeight = image.getHeight((ImageObserver) this);
		//draw the image in the top-left corner
		graphics.drawImage(image, 0, 0, null);
		// tile the image across the component
		for (int i = 0; i * imageWidth <= getWidth(); i++) {
			for (int j = 0; j * imageHeight <= getHeight(); j++) {
				if (i+j>0) {
					graphics.copyArea(0, 0, imageWidth, imageHeight, i+imageWidth, j+imageHeight);
				}
			}
			
		}
	}

}

/**
 * @Override
public BufferedImage apply(BufferedImage img) {
	
	int size = (int) (squareSize * Math.min(img.getWidth(), img.getHeight()));
	int shift = (int) (distance * Math.min(img.getWidth(), img.getHeight()));
	
	for(int i = 0; i < passes; i++) {
		
		int x = (int) (rand.nextDouble() * (img.getWidth()-size));
		int y = (int) (rand.nextDouble() * (img.getHeight()-size));
		
		Graphics g = img.getGraphics();
		g.copyArea(x, y, size, size, (int) ((rand.nextDouble()-.5) * shift), (int) ((rand.nextDouble()-.5) * shift));	
	}
	
	return img;
}
 */

