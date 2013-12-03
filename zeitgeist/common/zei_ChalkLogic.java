package zeitgeist.common;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class zei_ChalkLogic {
	static int A[][];
	static boolean down = false;
	static boolean left = false;
	static boolean right = false;
	static int rx = 0;
	static int rz = 0;
	public static Map<String, Method> spellList = new HashMap<String, Method>();
	static boolean up = false;

	/**
	 * Add Spell to chalk designs, trigger is design layout, command is the
	 * function name of the spell, and orientationMatters specifies if the
	 * design should not be rotated, setting this to true will save processing!
	 * <b> IF DESIGN LOOKS THE SAME NO MATTER HOW ITS ROTATED, SET THIS TO TRUE
	 * </b>
	 */
	public static void addSpell(String trigger, String command, boolean orientationMatters) throws SecurityException, NoSuchMethodException {
		Method M = zei_Spells.class.getMethod(command, World.class, int.class, int.class, int.class, EntityPlayer.class);
		spellList.put(trigger, M);
		System.out.print("\nSPELL: " + trigger);
		if (!orientationMatters) {
			String trig2 = rotateSpell(trigger);
			if (!trig2.contains(trigger)) {
				spellList.put(trig2, M);
				System.out.print("\n\t2:" + trig2);
				String trig3 = flipUp(trigger);
				if (!trig3.contains(trigger)) {
					spellList.put(trig3, M);
					System.out.print("\n\t3:" + trig3);
				}
				String trig4 = flipSide(trig2);
				if (!trig4.contains(trig2)) {
					spellList.put(trig4, M);
					System.out.print("\n\t4:" + trig4);
				}
			}
		}
	}

	public static void eraseChalk(World world, int i, int j, int k, int width, int height) {
		int startX = i - width / 2;
		int endX = (i + width / 2) + 1;
		int startZ = k - height / 2;
		int endZ = (k + height / 2) + 1;
		for (int z = startZ; z < endZ; z++) {
			for (int x = startX; x < endX; x++) {
				if (world.getBlockId(x, j, z) == zei_Ids.chalk) {
					world.setBlock(x, j, z, 0);
				}
			}
		}
	}

	public static String flipSide(String trigger) {
		String line = "";
		int i = 0;
		String temp = trigger;
		int size = trigger.indexOf(";");
		while ((i = temp.indexOf(";")) != -1 && temp.length() > size) {
			line += new StringBuffer(temp.substring(0, i)).reverse() + ";";
			temp = temp.substring(i + 1);
		}
		return line;
	}

	public static String flipUp(String trigger) {
		String line = "";
		int i = 0;
		String temp = trigger;
		int size = trigger.indexOf(";");
		while ((i = temp.lastIndexOf(";") - size) >= 0 && temp.length() >= size) {
			line += temp.substring(i);
			temp = temp.substring(0, i);
		}
		return line;
	}

	public static void init() {
		try {
			addSpell("33;33;", "absorbPlant", true);
			addSpell("010;101;010;", "spawnEvil", true);
			addSpell("050;505;050;", "makeBigExplosion", true);
			addSpell("01210;11011;20402;11011;01210;", "spawnPig", true);
			addSpell("010;121;010;", "makeTree", true);
			addSpell("111;101;030;", "makeDay", true);
			addSpell("101;111;030;", "makeNight", true);
			// addSpell("101;020;101;", "spawnLoot", true);
			addSpell("121;202;121;", "awful", true);
			addSpell("121;232;121;", "makeMimic", true);
			addSpell("666;666;666;", "dimensionZei", true);
			addSpell("2;", "dimensionZei", true);
			// addSpell("4;", "godamnRain", true);
			// addSpell("3;", "spawnThing", true);
			// addSpell("1;","absorbSoul",true);
			addSpell("050;151;606;141;", "spawnEvilThingy", false);
			addSpell("00500;00100;51515;00100;00500;", "sentries", true);
			addSpell("0002000;0001000;0002000;2120212;0002000;0001000;0002000;", "DELETESTUFF", true);
			addSpell("11111;10401;14541;10401;11111;", "absorbSoul", true);
			addSpell("00100;01110;11511;01110;00100;", "copySoul", true);
			// addSpell("51;51;", "kittens", false);
			// addSpell("01610;" + "11311;" + "40604;" + "11311;" +
			// "01610;","makeTimeMachine",false);
			// zei_Spells.class.getMethod()
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	public static void neighbor(World world, int i, int j, int k) {
		left = i > 0;
		right = i < 15;
		down = i > 0;
		up = i < 15;
		if (left && world.getBlockId(rx + (i - 1), j, k + rz) == zei_Ids.chalk) {
			if (A[i - 1][k] == 0) {
				A[i - 1][k] = world.getBlockMetadata(rx + (i - 1), j, k + rz);
				neighbor(world, i - 1, j, k);
			}
		}
		if (left && up && world.getBlockId(rx + (i - 1), j, rz + k + 1) == zei_Ids.chalk) {
			if (A[i - 1][k + 1] == 0) {
				A[i - 1][k + 1] = world.getBlockMetadata(rx + (i - 1), j, rz + k + 1);
				neighbor(world, i - 1, j, k + 1);
			}
		}
		if (up && world.getBlockId(rx + i, j, rz + k + 1) == zei_Ids.chalk) {
			if (A[i][k + 1] == 0) {
				A[i][k + 1] = world.getBlockMetadata(rx + i, j, rz + k + 1);
				neighbor(world, i, j, k + 1);
			}
		}
		if (up && right && world.getBlockId(rx + i + 1, j, rz + k + 1) == zei_Ids.chalk) {
			if (A[i + 1][k + 1] == 0) {
				A[i + 1][k + 1] = world.getBlockMetadata(rx + i + 1, j, rz + k + 1);
				neighbor(world, i + 1, j, k + 1);
			}
		}
		if (right && world.getBlockId(rx + i + 1, j, rz + k) == zei_Ids.chalk) {
			if (A[i + 1][k] == 0) {
				A[i + 1][k] = world.getBlockMetadata(rx + i + 1, j, rz + k);
				neighbor(world, i + 1, j, k);
			}
		}
		if (down && right && world.getBlockId(rx + i + 1, j, rz + (k - 1)) == zei_Ids.chalk) {
			if (A[i + 1][k - 1] == 0) {
				A[i + 1][k - 1] = world.getBlockMetadata(rx + i + 1, j, rz + (k - 1));
				neighbor(world, i + 1, j, k - 1);
			}
		}
		if (down && world.getBlockId(rx + i, j, rz + (k - 1)) == zei_Ids.chalk) {
			if (A[i][k - 1] == 0) {
				A[i][k - 1] = world.getBlockMetadata(rx + i, j, rz + (k - 1));
				neighbor(world, i, j, k - 1);
			}
		}
		if (left && down && world.getBlockId(rx + (i - 1), j, rz + (k - 1)) == zei_Ids.chalk) {
			if (A[i - 1][k - 1] == 0) {
				A[i - 1][k - 1] = world.getBlockMetadata(rx + (i - 1), j, rz + (k - 1));
				neighbor(world, i - 1, j, k - 1);
			}
		}
	}

	public static boolean parseRecipes(World world, int i, int j, int k, int width, int height, EntityPlayer entityPlayer, String s) {
		System.out.println(s);
		boolean usedRecipe = false;
		if (spellList.containsKey(s)) {
			if (world.isRemote) {
				return false;
			}
			try {
				Object obj = spellList.get(s).invoke(zei_Spells.class, new Object[] { world, i, j, k, entityPlayer });
				// usedRecipe=true;
				if (obj != null) {
					usedRecipe = (Boolean) obj;
				} else {
					usedRecipe = true;
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return usedRecipe;
	}

	public static String rotateSpell(String trigger) {
		String line = "";
		String temp = trigger;
		int i = 0;
		int j = 0;
		int size = trigger.indexOf(";");
		while (j < size) {
			temp = trigger;
			line += trigger.substring(j, j + 1);
			while ((i = temp.indexOf(";")) != -1 && (temp.length() - 1) > size) {
				line += temp.substring(i + 1 + j, i + 2 + j);
				temp = temp.substring(i + 1);
			}
			line += ";";
			j++;
		}
		return line;
	}

	public static void translate(World world, int i, int j, int k, EntityPlayer entityPlayer) {
		// data="X";
		rx = i - 7;
		rz = k - 7;
		A = new int[16][16];
		A[7][7] = world.getBlockMetadata(i, j, k);
		// list.add(new zei_ChalkVar("X",0,0));
		neighbor(world, 7, j, 7);
		int minx = 15;
		int maxx = 0;
		int miny = 15;
		int maxy = 0;
		for (int x = 0; x < 16; x++) {
			// System.out.println("");
			for (int y = 0; y < 16; y++) {
				// System.out.print(""+A[x][y]+",");
				if (A[x][y] > 0) {
					if (x > maxx) {
						maxx = x;
					}
					if (x < minx) {
						minx = x;
					}
					if (y > maxy) {
						maxy = y;
					}
					if (y < miny) {
						miny = y;
					}
				}
			}
		}
		int dx = (maxx - minx) + 1;
		int dy = (maxy - miny) + 1;
		int B[][] = new int[dx][dy];
		int midx = rx + (minx + dx / 2);
		int midy = rz + (miny + dy / 2);
		for (int x = minx; x <= maxx; x++) {
			for (int y = miny; y <= maxy; y++) {
				B[x - minx][y - miny] = A[x][y];
			}
		}
		// System.out.println("---"+dy+","+dx);
		String S = "";
		for (int x = 0; x < dx; x++) {
			// System.out.println("");
			for (int y = 0; y < dy; y++) {
				S += ("" + B[x][y]);
				// System.out.print(""+B[x][y]+",");
			}
			S += ";";
		}
		// System.out.println(S);
		if (parseRecipes(world, midx, j, midy, dx, dy, entityPlayer, S)) {
			eraseChalk(world, midx, j, midy, dx, dy);
		}
	}
}
