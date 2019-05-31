package com.nordryd.enums;

import org.bukkit.Color;

/**
 * <p>
 * Enum for different colors in terms of <b>RGB</b>.
 * </p>
 * 
 * @author Nordryd
 */
public enum ParticleColor
{
	WHITE(255, 255, 255), BLACK(1, 1, 1), RED(255, 0, 0), GREEN(0, 255, 0), BLUE(0, 0, 255), YELLOW(255, 255, 0), MAGENTA(255, 0, 255), CYAN(0, 255,
			255), DARK_TURQUOISE(0, 206, 209), LIGHT_SEA_GREEN(32, 178, 170), IVORY(255, 255, 240), LAVENDAR(230, 230,
					250), RED_VIOLET(208, 32, 144), VIOLET(160, 32,
							240), LIME_GREEN(50, 205, 50), GOLD(255, 215, 0), ORANGE(255, 165, 0), MAROON(176, 48, 96), FIREBRICK(178, 34, 34);

	private final int red, green, blue;

	private ParticleColor(int red, int green, int blue) {
		this.red = red;
		this.blue = blue;
		this.green = green;
	}

	/**
	 * @return <b>Red</b> value.
	 */
	public int getRed() {
		return red;
	}

	/**
	 * @return <b>Green</b> value.
	 */
	public int getGreen() {
		return green;
	}

	/**
	 * @return <b>Blue</b> value.
	 */
	public int getBlue() {
		return blue;
	}

	public Color getColorFromRGB() {
		return Color.fromRGB(red, green, blue);
	}

	/**
	 * @return Normalized <b>red</b> value &isin; [0, 1].
	 */
	public double getNormalizedRed() {
		return red / 255.0;
	}

	/**
	 * @return Normalized <b>red</b> value &isin; [0, 1].
	 */
	public double getNormalizedGreen() {
		return green / 255.0;
	}

	/**
	 * @return Normalized <b>red</b> value &isin; [0, 1].
	 */
	public double getNormalizedBlue() {
		return blue / 255.0;
	}
}
