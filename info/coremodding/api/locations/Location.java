package info.coremodding.api.locations;

import net.minecraft.world.World;

/**
 * @author James A location for easy transfer
 */
public class Location {

	/**
	 * The x postion
	 */
	public int x = 0;

	/**
	 * The y position
	 */
	public int y = -1;

	/**
	 * The z position
	 */
	public int z = 0;

	/**
	 * The direction
	 */
	public EDirection dir = EDirection.all;

	/**
	 * The world
	 */
	public World world = null;

	/**
	 * @param x
	 *            The x position
	 * @param z
	 *            The z position
	 */
	public Location(int x, int z) {
		this.setZ(z);
		this.setX(x);
	}

	/**
	 * @param x
	 *            The x position
	 * @param y
	 *            The y position
	 * @param z
	 *            The z position
	 */
	public Location(int x, int y, int z) {
		this.setY(y);
		this.setZ(z);
		this.setX(x);
	}

	/**
	 * @param x
	 *            The x position
	 * @param z
	 *            The z position
	 * @param dir
	 *            The direction
	 */
	public Location(int x, int z, EDirection dir) {
		this.setZ(z);
		this.setX(x);
		this.setDir(dir);
	}

	/**
	 * @param x
	 *            The x position
	 * @param y
	 *            The y position
	 * @param z
	 *            The z position
	 * @param dir
	 *            The direction
	 */
	public Location(int x, int y, int z, EDirection dir) {
		this.setY(y);
		this.setZ(z);
		this.setX(x);
		this.setDir(dir);
	}

	/**
	 * @param x
	 *            The x position
	 * @param z
	 *            The z position
	 * @param world
	 *            The world
	 */
	public Location(int x, int z, World world) {
		this.setZ(z);
		this.setX(x);
		this.setWorld(world);
	}

	/**
	 * @param x
	 *            The x position
	 * @param y
	 *            The y position
	 * @param z
	 *            The z position
	 * @param world
	 *            The world
	 */
	public Location(int x, int y, int z, World world) {
		this.setY(y);
		this.setZ(z);
		this.setX(x);
		this.setWorld(world);
	}

	/**
	 * @param x
	 *            The x position
	 * @param z
	 *            The z position
	 * @param dir
	 *            The direction
	 * @param world
	 *            The world
	 */
	public Location(int x, int z, EDirection dir, World world) {
		this.setZ(z);
		this.setX(x);
		this.setDir(dir);
		this.setWorld(world);
	}

	/**
	 * @param x
	 *            The x position
	 * @param y
	 *            The y position
	 * @param z
	 *            The z position
	 * @param dir
	 *            The direction
	 * @param world
	 *            The world
	 */
	public Location(int x, int y, int z, EDirection dir, World world) {
		this.setY(y);
		this.setZ(z);
		this.setX(x);
		this.setDir(dir);
		this.setWorld(world);
	}

	/**
	 * @return The x position
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * @param x
	 *            The x position
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return The y position
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * @param y
	 *            The y position
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return The z position
	 */
	public int getZ() {
		return this.z;
	}

	/**
	 * @param z
	 *            The z position
	 */
	public void setZ(int z) {
		this.z = z;
	}

	/**
	 * @return The direction
	 */
	public EDirection getDir() {
		return this.dir;
	}

	/**
	 * @param dir
	 *            The direction
	 */
	public void setDir(EDirection dir) {
		this.dir = dir;
	}

	/**
	 * @return The world
	 */
	public World getWorld() {
		return this.world;
	}

	/**
	 * @param world
	 *            The world
	 */
	public void setWorld(World world) {
		this.world = world;
	}
}
