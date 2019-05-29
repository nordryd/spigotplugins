package com.nordryd.enums;

/**
 * <p>
 * Enum for different colors in terms of <b>RGB</b>.
 * </p>
 * 
 * @author Nordryd
 */
public enum ParticleColor
{
	NATURAL(0, 0, 0), WHITE(255, 255, 255), BLACK(1, 1, 1), RED(255, 0, 0), GREEN(0, 255, 0), BLUE(0, 0, 255), YELLOW(255, 255, 0), MAGENTA(255, 0,
			255), CYAN(0, 255, 255);

	private int red, green, blue;

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
