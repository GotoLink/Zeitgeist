package zeitgeist.common.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import zeitgeist.common.zei_Ids;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class zei_Items {
	public static void init() {
		zei_ItemSoulCore.mrChecker();
		LanguageRegistry.addName(mortar, "Mortar & Pestle");
		LanguageRegistry.addName(worker, "Worker");
		LanguageRegistry.addName(beacon, "Beacon");
		LanguageRegistry.addName(sentry, "Sentry");
		LanguageRegistry.addName(guard, "Guard Turret");
		// blaster.iconIndex = ModLoader.addOverride("/gui/items.png",
		// "/zeitgeist/blaster.png");
		// ModLoader.addName(blaster, "Pulse Rifle");
		LanguageRegistry.addName(factotum, "Factotum");
		// pickTech.iconIndex = ModLoader.addOverride("/gui/items.png",
		// "/zeitgeist/picktech.png");
		// ModLoader.addName(pickTech, "Charged Pick");
		LanguageRegistry.addName(spring, "Spring");
		LanguageRegistry.addName(soulCore, "Core");
		LanguageRegistry.addName(letter, "Letter");
		LanguageRegistry.addName(texture, "Texture");
		LanguageRegistry.addName(itemTote, "Toter");
		LanguageRegistry.addName(new ItemStack(soulCore, 1, 0),
				"Synthetic Soul");
		LanguageRegistry
				.addName(new ItemStack(soulCore, 1, 1), "Rounded Stone");
		LanguageRegistry.addName(new ItemStack(soulCore, 1, 2), "Stone Sphere");
		LanguageRegistry.addName(new ItemStack(soulCore, 1, 3),
				"Unfinished Core");
		LanguageRegistry.addName(new ItemStack(soulCore, 1, 4), "Empty Core");
		LanguageRegistry.addName(new ItemStack(soulCore, 1, 5), "Pure Soul");
		LanguageRegistry
				.addName(new ItemStack(soulCore, 1, 6), "Tenebrae Soul");
		LanguageRegistry.addName(new ItemStack(soulCore, 1, 7), "");
		LanguageRegistry.addName(new ItemStack(soulCore, 1, 8), "");
		LanguageRegistry.addName(new ItemStack(soulCore, 1, 9), "");
		LanguageRegistry.addName(new ItemStack(soulCore, 1, 10), "");
		LanguageRegistry.addName(new ItemStack(soulCore, 1, 11), "");
		LanguageRegistry.addName(new ItemStack(soulCore, 1, 12), "");
		LanguageRegistry.addName(new ItemStack(soulCore, 1, 13), "");
		LanguageRegistry.addName(new ItemStack(soulCore, 1, 14), "");
		LanguageRegistry.addName(chalk2, "Chalk");
		LanguageRegistry.addName(skull, "Human Skull");
		LanguageRegistry.addName(skullAnimal, "Animal Skull");
		LanguageRegistry.addName(chisel, "Chisel");
		((zei_ItemCraftSet) craftSet).init();
		LanguageRegistry.addName(plagueItem, "???");
		LanguageRegistry.addName(stoneFigureItem, "Mimic Stone");
	}

	public static Item plagueItem = (new zei_ItemBlocks(
			zei_Ids.plagueItem - 256, 0)).setTextureName("zeitgeist:ABC.png")
			.setUnlocalizedName("plagueItem");
	public static Item stoneFigureItem = (new zei_ItemBlocks(
			zei_Ids.stoneFigureItem - 256, 1)).setTextureName(
			"zeitgeist:stoneFigureItem.png").setUnlocalizedName("plagueItem");
	public static Item worker = (new zei_ItemBot(zei_Ids.itemWorker - 256, 0))
			.setTextureName("zeitgeist:worker.png")
			.setUnlocalizedName("automaton").setMaxStackSize(8);
	// public static Item itemOmni = (new
	// zei_ItemBot(zei_Ids.itemOmni-256,5)).setIconCoord(2,
	// 9).setItemName("itemOmni");
	// public static Item itemBally = (new
	// zei_ItemBot(zei_Ids.itemBally-256,3)).setIconCoord(3,
	// 9).setItemName("itemBally");
	// public static Item cheatStick = (new zei_ItemWerg(zei_Ids.cheatStick -
	// 256)).setIconCoord(5, 3).setItemName("cheatStick");
	// public static Item automatonCore = (new
	// zei_ItemGolem(zei_Ids.automatonCore-256)).setIconCoord(1,
	// 9).setItemName("automatonCore");
	public static Item beacon = (new zei_ItemBot(zei_Ids.itemBeacon - 256, 2))
			.setTextureName("zeitgeist:beacon.png")
			.setUnlocalizedName("beacon");
	public static Item sentry = (new zei_ItemBot(zei_Ids.itemSentry - 256, 1))
			.setTextureName("zeitgeist:sentry.png").setUnlocalizedName("biter")
			.setMaxStackSize(1);
	// public static Item itemBot = (new
	// zei_ItemBot(zei_Ids.itemBot-256,3)).setIconCoord(4,
	// 10).setItemName("itemBot");
	// public static Item superCore = (new
	// Item(zei_Ids.superCore-256)).setIconCoord(3,
	// 11).setItemName("superCore");
	// public static Item blaster = (new zei_ItemBlaster(zei_Ids.blaster -
	// 256)).setIconCoord(1, 11).setItemName("blaster");
	public static Item guard = (new zei_ItemBot(zei_Ids.guard - 256, 6))
			.setTextureName("zeitgeist:guard.png").setUnlocalizedName("guard")
			.setMaxStackSize(64);
	// public static Item stuffs = (new zei_ItemStuffs(zei_Ids.stuffs -
	// 256)).setIconCoord(5, 8).setItemName("stuffs");
	public static Item mortar;
	public static Item chisel;
	public static Item factotum = (new zei_ItemBot(zei_Ids.itemFactotum - 256,
			4)).setTextureName("zeitgeist:itemfactotum.png")
			.setUnlocalizedName("factotum").setMaxStackSize(1);
	// public static Item pickTech = (new zei_ItemAPickaxe(zei_Ids.pickTech-256,
	// Arrays.asList("TECH",0,3,4,100f,2))).setIconCoord(2,
	// 6).setItemName("pickTech");
	// public static Item regulator = (new
	// zei_ItemFunctional(zei_Ids.regulator-256,3)).setIconCoord(4,
	// 10).setItemName("regulator").setMaxStackSize(1);
	// public static Item daymaker = (new
	// zei_ItemFunctional(zei_Ids.daymaker-256,1)).setIconCoord(4,
	// 10).setItemName("daymaker");
	// public static Item techifier = (new
	// zei_ItemFunctional(zei_Ids.techifier-256,2)).setIconCoord(4,
	// 10).setItemName("techifier");
	// public static Item smack = (new zei_ItemSmack(zei_Ids.smack -
	// 256)).setIconCoord(4, 10).setItemName("smack");
	// public static Item naturizer = (new
	// zei_ItemFunctional(zei_Ids.naturizer-256,4)).setIconCoord(4,
	// 10).setItemName("naturizer");
	// public static Item misc = (new zei_ItemMisc(zei_Ids.misc -
	// 256)).setIconCoord(5, 8).setItemName("misc");
	public static Item chalk2 = (new zei_ItemChalk(zei_Ids.chalk2 - 256))
			.setTextureName("zeitgeist:chalk.png").setUnlocalizedName("chalk");
	// public static Item climbClaw = (new zei_ItemClimbClaw(zei_Ids.climbClaw -
	// 256)).setIconIndex(1).setItemName("ClimbClaw");
	public static Item skull = (new Item(zei_Ids.skull - 256)).setTextureName(
			"zeitgeist:skull.png").setUnlocalizedName("Human Skull");
	public static Item skullAnimal = (new Item(zei_Ids.skullA - 256))
			.setTextureName("zeitgeist:skullA.png").setUnlocalizedName(
					"Animal Skull");
	public static Item craftSet = (new zei_ItemCraftSet(zei_Ids.craftSet1 - 256))
			.setUnlocalizedName("craftSet1");
	// public static Item craftSet2 = (new zei_ItemCraftSet2(zei_Ids.craftSet2 -
	// 256)).setItemName("craftSet2");
	// public static Item craftSet3 = (new zei_ItemCraftSet3(zei_Ids.craftSet3 -
	// 256)).setItemName("craftSet3");
	public static Item spring = (new Item(zei_Ids.spring - 256))
			.setTextureName("zeitgeist:spring.png")
			.setUnlocalizedName("spring");
	public static Item soulCore = (new zei_ItemSoulCore(zei_Ids.soulCore - 256))
			.setTextureName("zeitgeist:stoneF.png").setUnlocalizedName(
					"soulCore");
	public static Item letter = (new zei_ItemLetter(zei_Ids.letter - 256))
			.setIconCoord(12, 3).setUnlocalizedName("letter");
	public static Item texture = (new zei_ItemTexture(zei_Ids.texture - 256))
			.setIconCoord(12, 3).setUnlocalizedName("texture");
	public static Item itemTote = (new zei_ItemBot(zei_Ids.itemTote - 256, 3))
			.setTextureName("zeitgeist:itemTote.png")
			.setUnlocalizedName("tote");
	public static Item EntityTester;
	static {
		mortar = (new Item(zei_Ids.mortar - 256))
				.setTextureName("/zeitgeist/mortar.png").setMaxStackSize(1)
				.setContainerItem(mortar).setUnlocalizedName("mortar");
		mortar.setContainerItem(mortar);
		chisel = (new zei_ItemChisel(zei_Ids.chisel - 256)).setMaxStackSize(1)
				.setTextureName("zeitgeist:Chisel.png")
				.setUnlocalizedName("chisel");
		chisel.setContainerItem(chisel);
		// Salvage = new
		// Item(zei_Ids.Salvage-256).setIconIndex(ModLoader.addOverride("/gui/items.png",
		// "/GURG/Salvage.png")).setItemName("Salvage");
		// EntityTester = (new
		// GurItemEntityTester(3042)).setIconIndex(ModLoader.addOverride("/gui/items.png",
		// "/GURG/StoneGolemDeployer.png")).setItemName("EntityTester");
		// Bobbypin = new
		// GurItemBobbypin(zei_Ids.Bobbypin-256).setIconIndex(ModLoader.addOverride("/gui/items.png",
		// "/GURG/Bobbypin.png")).setItemName("Bobbypin");
		// BugMeat = (new ItemFood(zei_Ids.BugMeat-256 , 3,
		// false)).setIconIndex(ModLoader.addOverride("/gui/items.png",
		// "/GURG/BugMeat.png")).setItemName("BugMeat");
		// CookedBugMeat = (new ItemFood(zei_Ids.CookedBugMeat-256, 6,
		// false)).setIconIndex(ModLoader.addOverride("/gui/items.png",
		// "/GURG/BugMeatCooked.png")).setItemName("CookedBugMeat");
	}
}
