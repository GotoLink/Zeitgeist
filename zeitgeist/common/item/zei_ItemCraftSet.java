package zeitgeist.common.item;

import zeitgeist.server.zei_Ids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.util.Icon;

public class zei_ItemCraftSet extends Item {
	public zei_ItemCraftSet(int i) {
		super(i);
		setHasSubtypes(true);
		setMaxDamage(0);
		loadSprites();
	}

	Icon[] textur;
	String names[];
	String full[];

	private void add(int i, String simple, String Full) {
		names[i] = simple;
		full[i] = Full;
		if (simple == "") {
			textur[i] = 0;
		} else {
			textur[i] = "/zeitgeist/"
					+ simple + ".png");
		}
	}

	public void init() {
		for (int i = 0; i < 48; i++) {
			if (names[i] != null)
				ModLoader.addName(new ItemStack(zei_Ids.craftSet1, 1, i),
						full[i]);
		}
	}

	public void loadSprites() {
		textur = new int[48];
		names = new String[48];
		full = new String[48];
		add(0, "lens", "Lens");
		add(1, "eyePiece", "Eyepiece");
		add(2, "stonecog", "stonecog");
		add(3, "", "");
		add(4, "loop", "Metal Ring");
		add(5, "chain", "Chain");
		add(6, "sprocket", "Sprocket");
		add(7, "joint", "Joint");
		add(8, "sharp", "Sharpened Point");
		add(9, "smallPlate", "Small Plate");
		add(10, "cylinder", "Cylinder");
		add(11, "rod", "Rod");
		add(12, "piston", "Tiny Piston");
		add(13, "actuator", "Soul Actuator");
		add(14, "canvas", "Canvas");
		add(15, "wing", "Wing");
		add(16, "", "");
		add(17, "passive", "Controller (Passive)");
		add(18, "aggressive", "Controller (Aggressive)");
		add(19, "jaw", "Jaw");
		add(20, "sensor", "NVS");
		add(21, "drill", "Pickaxe Module");
		add(22, "rollerChain", "Roller Chain");
		add(23, "wHead", "Focal Head");
		add(24, "smallBod", "Digger Body");
		add(25, "totehead", "Small Head");
		add(26, "medBod", "Greater Body");
		add(27, "smallLeg", "Small Leg");
		add(28, "sHead", "Sentry Head");
		/*
		 * add(29,"",""); add(30,"",""); add(31,"",""); add(32,"","");
		 * add(33,"",""); add(34,"",""); add(35,"",""); add(36,"","");
		 */
		// 41 mortar
		// 42 pestle
		textur[43] = 
				"/zeitgeist/salt.png");
		textur[44] =
				"/zeitgeist/sulf.png");
		// textur[13]=ModLoader.addOverride("/gui/items.png",
		// "/zeitgeist/stoneS1.png");
		// textur[46] = ModLoader.addOverride("/gui/items.png",
		// "/zeitgeist/saltChalk.png");
	}

	public Icon getIconFromDamage(int i) {
		//
		return textur[i];
	}

	public String getItemNameIS(ItemStack itemstack) {
		// System.out.println("hello your damage is: "+names[itemstack.getItemDamage()]);
		return super.getUnlocalizedName() + "." + names[itemstack.getItemDamage()];
	}
	/*
	 * public static final String names[] = { "lens", "eyePiece", "smallGear",
	 * "reinforcedSlab", "chainLink", "chain", "sprocket", "joint", "sharp",
	 * "smallPlate", "cylinder", "rod", "piston", "actuator", "canvas", "wing",
	 * 
	 * "gearBox", "controlPassive", "controlAggressive", "jaw", "foot", "",
	 * "sensor", "drill", "heatSink", "rollerChain", "highSpring",
	 * "duplicitiveUnit", "smallHead", "mediumHead", "largeHead",
	 * "claws","woodLeg", "woodArm", "woodGearBox", "woodGear", "fiberChain",
	 * "woodBind", "woodBody", "woodJoint", "woodHead", "mortar", "pestle",
	 * "salt", "sulf", "", "halfChalk", "" };
	 */
}
