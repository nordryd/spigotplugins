package com.nordryd.util;

import static java.lang.System.out;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class UpdateChecker extends Thread
{
	URLConnection conn = null;
	
	@Override
	public void run() {
		try {
			conn = new URL(Reference.API_LINK).openConnection();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			if(reader.readLine().equals(Reference.CURRENT_VERSION)) {
				out.println(Values.PREFIX + " No updates available.");
			}
			else {
				out.println(Values.PREFIX + " There is an update! Download it at " + Reference.RESOURCE_URL);
			}
		}
		catch(MalformedURLException mue) {
			
		}
		catch(IOException ie) {
			
		}
	}
}
