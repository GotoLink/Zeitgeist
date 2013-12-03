package zeitgeist.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Field;

import net.minecraft.src.ModLoader;

public class zei_Ids {
	// public static int tvRenderId=27;
	// public static int crinkRenderId=28;
	public static int allTech = 0;
	// public static int arbor = 143;
	public static int arch = 144;
	public static int arch2 = 135;
	public static int architect = 138;
	public static int beaconBlock = 176;
	public static int blend = 183;
	public static int blenderRenderId;
	// public static int duplex = 125;
	public static int boing = 133;
	// public static boolean tvOut=false;
	public static int builderLevel = 8;
	public static int cannon = 179;
	public static int chalk = 177;
	public static int chalk2 = 1989;
	// public static String username="Aninon";
	public static int chalkRendererId;
	public static int chisel = 772;
	public static int climbClaw = 3202;
	// public static int tempItem1=730;
	public static int craftSet1 = 768;
	// public static int crystal = 134;
	public static int crink = 140;
	public static int dapling = 185;
	public static int dispBlock = 173;
	public static int entityAncient = 114;
	public static int entityBeacon = 87;
	public static int entityBeam = 108;
	public static int entityBobby = 82;
	public static int entityCannonBall = 118;
	public static int entityDeltaLaser = 109;
	public static int entityDispBot = 116;
	public static int entityFactotum = 101;
	public static int entityGolem1 = 85;
	public static int entityGolem2 = 86;
	public static int entityGolemPure = 115;
	public static int entityGoreMan = 112;
	public static int entityGuardian = 102;
	public static int entityGuardTurret = 80;
	public static int entityHelios = 81;
	public static int entityKnave = 110;
	public static int entityLimb = 113;
	public static int entityMaker = 103;
	public static int entityNoop = 111;
	public static int entityOmni = 89;
	public static int entityRemnant = 107;
	public static int entitySeeker = 106;
	public static int entitySentry = 77;
	public static int entitySleeper = 105;
	public static int entitySlider = 78;
	public static int entityTote = 117;
	public static int entityWatcher = 79;
	public static int entityWorker = 108;
	public static int entityZiz = 104;
	public static int frassDirtSpread = 1;
	public static int frassWaterSpread = 1;
	// public static int fakeCrystal = 136;
	// public static int glowy = 137;
	public static int gearbox = 141;
	// public static int heal = 127;
	public static int grower = 134;
	// public static int smack = 752;
	// public static int itemBot=753;
	// public static int superCore=754;
	// public static int blaster = 755;
	public static int guard = 756;
	// public static int deployer = 140;
	public static int importantBuildingThingy = 143;
	// public static int itemOmni=745;
	// public static int itemBally=746;
	// public static int cheatStick = 747;
	// public static int automatonCore=749;
	public static int itemBeacon = 750;
	// public static int pickTech=759;
	public static int itemFactotum = 760;
	public static int itemSentry = 751;
	public static int itemTote = 776;
	public static int itemWorker = 744;
	public static int latchBlock = 174;
	public static int letter = 774;
	public static int liquidRenderId;
	public static int maxDeployableEntities = 300;
	public static int miller = 170;
	// public static int stuffs = 757;
	public static int mortar = 758;
	public static int plague = 182;
	public static int plagueItem = 773;
	public static int plagueRenderId;
	public static int plantMass;
	public static int runeRenderId;
	public static int saltOre = 136;
	public static int sentryBlock = 188;
	public static int sentryBlockRenderId;
	// public static int daymaker=762;
	// public static int techifier=763;
	// public static int naturizer=764;
	// public static int misc = 765;
	public static int skull = 766;
	public static int skullA = 767;
	public static int sky = 142;
	public static int slabBlock = 187;
	public static int soulCore = 1991;
	// public static int craftSet2 = 769;
	// public static int craftSet3 = 770;
	public static int spring = 771;
	public static int stoneFigure = 178;
	public static int stoneFigureItem = 761;
	public static int stoneFigureRenderId;
	public static int sulfOre = 137;
	public static int tech = 139;
	public static int testLiquid = 180;
	public static int testLiquid2 = 181;
	public static int texBlock = 172;
	// public static int plate = 151;
	// public static int mailbox=152;
	// public static int duoFurnace=153;
	public static int texBlock2 = 171;
	public static int texture = 775;
	public static int toteBlock = 175;
	public static int toteBlockRenderId;
	public static int turnBlock = 189;
	public static int turnBlockRenderId;
	public static int windmill = 184;
	public static int workerBlock = 186;
	public static int workerBlockRenderId;
	private static boolean turnOff = false;
	static File f;
	Class c = zei_Ids.class;

	public zei_Ids() {
		if (!turnOff) {
			File foo = new File(".");
			f = new File(foo, "/Zeitgeist.properties");
			if (f.exists()) {
				System.out.println("reading zeitgeist config!");
				try {
					FileReader fs = new FileReader(f);
					BufferedReader in = new BufferedReader(fs);
					String s;
					while ((s = in.readLine()) != null) {
						if (!s.startsWith("//")) {
							int i = s.indexOf("=");
							int val;
							if (s.substring(i + 1, s.length()) == "#") {
								val = ModLoader.getUniqueEntityId();
							} else {
								val = Integer.parseInt(s.substring(i + 1, s.length()));
							}
							try {
								Field f = c.getDeclaredField(s.substring(0, i));
								f.setInt(this, val);
							} catch (Exception ex) {
							}
						}
					}
					in.close();
				} catch (Exception c) {
				}
			} else {
				System.out.println("no zeitgeist config found! creating new one!");
			}
			writeIt();
		}
	}

	public void writeIt() {
		Field[] fields = c.getFields();
		int n = fields.length;
		try {
			FileWriter fs = new FileWriter(f);
			BufferedWriter out = new BufferedWriter(fs);
			out.write("//Zeitgeist config file! :D hooray!\n");
			out.write("//\n//\n//SET ALL NEW TERRAIN AND BIOMES TO GENERATE AS TECH( 0=off, 1=on):\n");
			out.write("allTech=" + allTech + "\n");
			out.write("//ALLOW FRASS TO SPREAD ONTO WATER THAT'S NOT IN THE TECH BIOME(DANGEROUS)\n");
			out.write("frassWaterSpread=" + frassWaterSpread + "\n");
			out.write("//ALLOW FRASS TO SPREAD ONTO DIRT/GRASS THAT'S NOT IN THE TECH BIOME(EVEN MOAR DANGEROUS)\n");
			out.write("frassDirtSpread=" + frassDirtSpread + "\n");
			out.write("//How many entities can be in the world before the deployer stops putting out mobs?:\n");
			out.write("maxDeployableEntities=" + maxDeployableEntities + "\n");
			out.write("//\n//\n");
			boolean skip = true;
			for (int i = 0; i < n; i++) {
				String name = fields[i].getName();
				if (name == "itemWorker") {
					skip = false;
					out.write("//\n//\n//\n//ITEMS:\n//\n");
				} else if (name == "boing") {
					skip = false;
					out.write("//\n//\n//\n//BLOCKS:\n//\n");
				} else if (name == "entityWorker") {
					skip = false;
					out.write("//\n//\n//\n//Entities (if you're SURE there's a conflict, set to #, \n//as in ' Guard=# ', modloader will find an empty id!:\n//\n");
				}
				if (!skip) {
					out.write(name + "=" + fields[i].getInt(this) + "\n");
				}
				// System.out.println(fields[i].getName()+","+fields[i].isAccessible());
			}
			// out.write("//\n//\n//RENDER ID'S (these are VERY unlikely you'll need to change)\n");
			// out.write("tvRenderId="+tvRenderId+"\n");
			// out.write("crinkRenderId="+crinkRenderId+"\n");
			out.close();
		} catch (Exception c) {
		}
	}

	public static void OFF() {
		turnOff = true;
	}
}
