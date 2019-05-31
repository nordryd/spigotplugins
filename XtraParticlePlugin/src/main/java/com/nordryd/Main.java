package com.nordryd;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import com.nordryd.enums.Config;
import com.nordryd.event.misc.MiscEventListener;
import com.nordryd.util.Reference.InfoMessages;
import com.nordryd.util.UpdateChecker;

/**
 * <p>
 * Main class for <b>XtraParticlePlugin</b>.
 * </p>
 * <p>
 * Function pointers:<br>
 * Consumer&lt;Runnable&gt; -&gt; runnable class
 * </p>
 * 
 * @author Nordryd
 */
public class Main extends JavaPlugin
{
	private final Logger logger = getLogger();

	@Override
	public void onEnable() {
		logger.info(InfoMessages.ON_ENABLE);
		logger.info(InfoMessages.CONTACT);

		UpdateChecker.checkForUpdates(logger);
		Config.addDefaultConfig(this);

		MiscEventListener.initializeEventListeners(this);
	}

	@Override
	public void onDisable() {
		getLogger().info(InfoMessages.ON_DISABLE);
	}
}
