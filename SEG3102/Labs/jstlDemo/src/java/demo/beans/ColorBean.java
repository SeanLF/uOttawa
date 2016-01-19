/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package demo.beans;

import java.awt.Color;

public class ColorBean extends Color {
   String _name = null;

   public ColorBean(int r, int g, int b, String name) {
      super(r, g, b);
      _name = name.trim();
   }
   
   public static ColorBean newInstance(String rs, String gs,
                                       String bs, String name) {
      int r = Integer.valueOf(rs);
      int g = Integer.valueOf(gs);
      int b = Integer.valueOf(bs);
      return new ColorBean(r, g, b, name);
   }

   // Read-only property accessor
   public String getRgbHex() {
      int i = getRGB() & 0xffffff;
      return Integer.toString(i, 16);
   }

   // Get complementary color
   public String getRgbComplement() {
      int i =
         ((255 - getRed()) << 16) |
         ((255 - getGreen()) << 8) |
         (255 - getBlue());
      return Integer.toString(i, 16);
   }

   public String getName() {
      return _name;
   }
}
