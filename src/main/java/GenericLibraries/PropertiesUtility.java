package GenericLibraries;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * This class contains reusable methods to perform operation on properties file
 * @author singa
 */
public class PropertiesUtility 
{
	public Properties property;
	/**
	 * This method is used to Initialize properties from
	 * @param filepath
	 */
	public void propretiesInitialization(String filepath)
	{
		FileInputStream fis=null;
		try {
			fis=new FileInputStream(filepath);
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		property =new Properties();
		try {
			property.load(fis);
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}/**
	 * This method is used to read data from the properties
	 * @param key
	 * @return
	 */
public String readFromProperties(String key)
{
	return property.getProperty(key);
}
}
