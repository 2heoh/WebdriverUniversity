package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadConfigFile {
	protected InputStream input = null;
	protected Properties prop = new Properties();

	public ReadConfigFile() {
		try {
			String fileName = System.getProperty("user.dir") + "/src/main/java/properties/config.properties";
			FileInputStream inputStream = new FileInputStream(fileName);
			prop.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getBrowser() {
		if (prop.getProperty("browser") == null)
			return "";
		return prop.getProperty("browser");
	}
}
