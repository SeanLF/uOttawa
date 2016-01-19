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

public class LangScheme extends TreeMap {
   final String Lang = "/lang.txt";
   
   protected final String scanPattern = 
"^\\s*(\\d+),+([^,]+),+([^,]+),+([^,]+),+([^,]+),+([^,]+),+([^,]+)$";
//      "^\\s*(\\d+)\\p{Punct}+(\\S.+)\\p{Punct}+(\\S.+)\\p{Punct}+(\\S.+)\\p{Punct}+(\\S.+)\\p{Punct}+(\\S.+)\\p{Punct}+(\\S.*)$"; // whitespace (digit) whitespace (digit) whitespace (digit) whitespace (not whitespace
   Pattern _pat = null;

   // Default constructor does nothing special
   public LangScheme() {
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
            stream = getClass( ).getResourceAsStream(Lang);
            // Load colors from stream
            load(stream);
 
        } catch (FileNotFoundException ex) {
           Logger.getLogger(LangScheme.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
           Logger.getLogger(LangScheme.class.getName()).log(Level.SEVERE, null, ex);
       } finally {
           try {
               stream.close();
           } catch (IOException ex) {
               Logger.getLogger(LangScheme.class.getName()).log(Level.SEVERE, null, ex);
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
            LangBean lb =
               LangBean.newInstance(matcher.group(1), // cid
                                     matcher.group(2), // first
                                     matcher.group(3), // last
                                     matcher.group(4), // company
                                     matcher.group(5), // city
                                     matcher.group(6), // username
                                     matcher.group(7)); // lang 
            put(matcher.group(1).trim(), lb);
         }

         line = br.readLine();
      }
   }

   protected LangBean getName(String name) {
      return (LangBean)get(name);
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
            LangBean lb = (LangBean)(i.next());
            System.out.println(lb.getCid() + ": " + lb.getFirstname() + " " + lb.getLastname() + " " + lb.getCompany()+ " " + lb.getCity() + " " + lb.getUsername() + " " + lb.getLanguage());
         }
      } catch (IOException ex) {
         System.err.println(ex);
         System.exit(1);
      }
   }      

   public static void main(String[] args) {
      new LangScheme().testLoad(args);
   }
}
