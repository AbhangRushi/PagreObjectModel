package automation.helper;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import org.apache.log4j.Logger;

public class DownloadVerifier 
{
	Logger logger = Logger.getLogger(DownloadVerifier.class);
	
	public String ReadTextFile(String path) throws IOException
	{
		logger.info("======== In Read File Data ========");
		logger.info("File Path ::=> " + path);
		Scanner scanner = new Scanner(new File(path));
		String contents = scanner.useDelimiter("\\Z").next();
		logger.info(contents);
		scanner.close();
		return contents;
	}

}
