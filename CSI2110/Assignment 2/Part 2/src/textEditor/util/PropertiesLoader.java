package textEditor.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
/**
 * Class responsible for loading the properties file and exposing its content to the rest of the program
 */
public class PropertiesLoader {

	private final static String PROPERTIES_FILE = "./properties/textEditor.properties"; 
	
	private static Properties properties;
	private static File ewlFIle;
	private static File aboutFile;
	private static int maxUndos;
	
	static {
		properties = new Properties();
		try {
			properties.load(new FileInputStream(PROPERTIES_FILE));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ewlFIle = new File(properties.getProperty("ewlFile"));
		aboutFile = new File(properties.getProperty("aboutFile"));
		maxUndos = new Integer(properties.getProperty("maxUndos"));
		
		
	}
	
	
	public static File getEwlFile(){
		return ewlFIle;
	}
	
	public static File getAboutFile(){
		return aboutFile;
	}
	
	public static int getMaxUndos(){
		return maxUndos;
	}
	
	
}
