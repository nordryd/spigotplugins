package com.nordryd.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Logger;

/**
 * <p>
 * {@link Thread} class to check for updates to the Spigot API.
 * </p>
 * 
 * @author Nordryd
 */
public class UpdateChecker extends Thread
{
	private final Logger logger;

	private UpdateChecker(Logger logger) {
		this.logger = logger;
	}

	@Override
	public void run() {
		URLConnection conn = null;

		try {
			conn = new URL(IReference.API_LINK).openConnection();

			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			logger.info("Checking for updates...");

			if (reader.readLine().equals(IReference.CURRENT_VERSION)) {
				logger.info("No updates available atm.");
			}
			else {
				logger.info("There's an update! Download it at " + IReference.RESOURCE_URL + ".");
			}
		}
		catch (MalformedURLException mue) {
			System.err.println(IValues.PREFIX + " MalformedURLException occurred. Please check URL.");
		}
		catch (IOException ie) {
			System.err.println(IValues.PREFIX + " IOException occurred.");
		}
	}

	/**
	 * Checks the Spigot API database for updates.
	 * 
	 * @param logger
	 *            The {@link Logger} to log messages to.
	 */
	public static void checkForUpdates(Logger logger) {
		UpdateChecker updateChecker = new UpdateChecker(logger);
		updateChecker.start();
	}
}
