package util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public class AuthCode {
	    private static final long serialVersionUID = 1L;
	    private static Random    r             = new Random();
	    private static char[]    chs           = "ABCDEFGHIabcdefgh1235".toCharArray();
	   
	    private static final int NUMBER_OF_CHS = 4;
	    private static final int IMG_WIDTH     = 75;
	    private static final int IMG_HEIGHT    = 25;
	    private static  String code="";
 public static BufferedImage getImage() {
	 
	  BufferedImage image = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, BufferedImage.TYPE_INT_RGB); 
      Graphics2D g = (Graphics2D) image.getGraphics();
      Color c = new Color(200, 200, 255); 
      g.setColor(c);
      g.fillRect(0, 0, IMG_WIDTH, IMG_HEIGHT); 
      g.setFont(new Font("Arial Black", Font.PLAIN, 15));

      StringBuffer sb = new StringBuffer();  
      int index; 
      for (int i = 0; i < NUMBER_OF_CHS; i++) {
          index = r.nextInt(chs.length);  
          g.setColor(new Color(r.nextInt(88), r.nextInt(210), r.nextInt(150))); 
          g.drawString(chs[index] + "", 15 * i + 3, 18);  
          sb.append(chs[index]);
      }
      code=sb.toString();
      return image;
 }
 public static String code() {
	 return code;
 }
}
