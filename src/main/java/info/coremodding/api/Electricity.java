package info.coremodding.api;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

/**
 * General Electricity class.
 */
public class Electricity
{
	public static long DEFAULT_VOLTAGE = 240;

	/**
	 * A general material that can be used by machines. Breakable by hand, suitable for machines.
	 */
	public static final Material machine = new Material(MapColor.ironColor);
}
