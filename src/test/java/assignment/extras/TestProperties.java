package assignment.extras;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {
	
	
	public static void main(String[] args) throws IOException {
		
		
		System.out.println(System.getProperty("user.dir"));
		Properties config = new Properties();
		Properties element = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\Config.properties");
		config.load(fis);
		fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\element.properties");
		element.load(fis);
	}

}
