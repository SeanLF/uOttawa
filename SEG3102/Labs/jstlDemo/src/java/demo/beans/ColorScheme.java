package demo.beans;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.*;

public class ColorScheme extends TreeMap {
   final String RGB = "/rgb.txt";
   
   protected final String scanPattern = 
      "^\\s*(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\S.*)$";
   Pattern _pat = null;

   // Default constructor does nothing special
   public ColorScheme() {
      super();
      try {
         _pat = Pattern.compile(scanPattern);
         init();
      } catch (PatternSyntaxException psx) {
         System.err.println(psx);
      }
   }

   
   public final void init(){
        InputStream stream = null;
        try {
            stream = getClass( ).getResourceAsStream(RGB);
            // Load colors from stream
            load(stream);
 
        } catch (FileNotFoundException ex) {
           Logger.getLogger(ColorScheme.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
           Logger.getLogger(ColorScheme.class.getName()).log(Level.SEVERE, null, ex);
       } finally {
           try {
               stream.close();
           } catch (IOException ex) {
               Logger.getLogger(ColorScheme.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
   }
   
   // Load colors from "rgb" file as InputStream.
   public void load(InputStream is)
      throws IOException {

      // This object can only be loaded once
      if (size() != 0) {
         return;
      }

      BufferedReader br =
         new BufferedReader(new InputStreamReader(is));
      String line;

      line = br.readLine();
      while (line != null) {
         // Trim white space from leading/trailing ends
         line = line.trim();

         // Create pattern matcher
         Matcher matcher = _pat.matcher(line);

         // Only those that match are processed
         if (matcher.matches()) {
            ColorBean cb =
               ColorBean.newInstance(matcher.group(1),
                                     matcher.group(2),
                                     matcher.group(3),
                                     matcher.group(4));
            put(matcher.group(4).trim(), cb);
         }

         line = br.readLine();
      }
   }

   protected ColorBean getColor(String name) {
      return (ColorBean)get(name);
   }

   protected int getSize() {
      return size();
   }

   protected void testLoad(String[] args) {
      try {
         InputStream is = new FileInputStream(args[0]);

         // Load colors
         load(is);

         Iterator i = values().iterator();
         while (i.hasNext()) {
            ColorBean cb = (ColorBean)(i.next());
            System.out.println(cb.getName() + ": " + cb.getRgbHex());
         }
      } catch (IOException ex) {
         System.err.println(ex);
         System.exit(1);
      }
   }      

   public static void main(String[] args) {
      new ColorScheme().testLoad(args);
   }
}
