package zeitgeist.common;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class zei_Recipes {
	public static void init() {
		GameRegistry.addShapelessRecipe(new ItemStack(zei_Ids.craftSet1, 9, 11), new Object[] { Item.ingotIron });
		GameRegistry.addRecipe(new ItemStack(Item.ingotIron, 1), new Object[] { "OOO", "OOO", "OOO", 'O', new ItemStack(zei_Ids.craftSet1, 1, 11) });
		GameRegistry.addRecipe(new ItemStack(Block.workbench, 1), new Object[] { "DD", "DD", 'D', new ItemStack(zei_Ids.tech, 1, 1) });
		GameRegistry.addSmelting(zei_Ids.soulCore, new ItemStack(zei_Ids.soulCore, 2, 0), 0);
		ItemStack lens = new ItemStack(zei_Ids.craftSet1, 1, 0);
		ItemStack eyePiece = new ItemStack(zei_Ids.craftSet1, 1, 1);
		ItemStack smallGear = new ItemStack(zei_Ids.craftSet1, 1, 2);
		ItemStack reinforcedSlab = new ItemStack(zei_Ids.craftSet1, 1, 3);
		ItemStack chainLink = new ItemStack(zei_Ids.craftSet1, 1, 4);
		ItemStack chain = new ItemStack(zei_Ids.craftSet1, 1, 5);
		ItemStack sprocket = new ItemStack(zei_Ids.craftSet1, 1, 6);
		ItemStack joint = new ItemStack(zei_Ids.craftSet1, 1, 7);
		ItemStack sharp = new ItemStack(zei_Ids.craftSet1, 1, 8);
		ItemStack smallPlate = new ItemStack(zei_Ids.craftSet1, 1, 9);
		ItemStack chisel = new ItemStack(zei_Ids.chisel, 1, 0);
		ItemStack rod = new ItemStack(zei_Ids.craftSet1, 1, 11);
		ItemStack cylinder = new ItemStack(zei_Ids.craftSet1, 1, 10);
		ItemStack sealant = new ItemStack(Item.slimeBall, 1); // new
																// ItemStack(zei_Ids.craftSet1,
																// 1,11);
		ItemStack piston = new ItemStack(zei_Ids.craftSet1, 1, 12);
		ItemStack actuator = new ItemStack(zei_Ids.craftSet1, 1, 13);
		ItemStack canvas = new ItemStack(zei_Ids.craftSet1, 1, 14);
		ItemStack wing = new ItemStack(zei_Ids.craftSet1, 1, 15);
		ItemStack gearbox = new ItemStack(zei_Ids.gearbox, 1, 0);
		ItemStack passive = new ItemStack(zei_Ids.craftSet1, 1, 17);
		ItemStack aggressive = new ItemStack(zei_Ids.craftSet1, 1, 18);
		ItemStack jaw = new ItemStack(zei_Ids.craftSet1, 1, 19);
		ItemStack sensor = new ItemStack(zei_Ids.craftSet1, 1, 20);
		ItemStack drill = new ItemStack(zei_Ids.craftSet1, 1, 21); // 7
		// ItemStack heatSink = new ItemStack(zei_Ids.craftSet2, 1, 8);
		ItemStack rollerChain = new ItemStack(zei_Ids.craftSet1, 1, 22);
		// ItemStack highSpring = new ItemStack(zei_Ids.craftSet2, 1, 10);
		ItemStack leg = new ItemStack(zei_Ids.craftSet1, 1, 27);
		ItemStack wBody = new ItemStack(zei_Ids.craftSet1, 1, 24);
		ItemStack wHead = new ItemStack(zei_Ids.craftSet1, 1, 23);
		ItemStack spring = new ItemStack(zei_Ids.spring, 1, 0);
		// ItemStack plantMatter = new ItemStack(zei_Ids.stuffs, 1, 8);
		ItemStack chalk = new ItemStack(zei_Ids.chalk2, 1, 0);
		GameRegistry.addRecipe(new ItemStack(zei_Ids.craftSet1, 6, 0), new Object[] // lens
				{ "g", "g", "g", 'g', Block.glass });
		GameRegistry.addRecipe(eyePiece, new Object[] // eyePiece
				{ "lcl", 'l', lens, 'c', cylinder });
		GameRegistry.addRecipe(new ItemStack(zei_Ids.craftSet1, 16, 2), new Object[] // smallGear
				{ " s ", "sss", " s ", 's', Block.stone });
		GameRegistry.addRecipe(new ItemStack(zei_Ids.craftSet1, 4, 3), new Object[] // reinforcedplate
				{ "sss", "rrr", "sss", 's', Block.pressurePlateStone, 'r', rod });
		GameRegistry.addRecipe(new ItemStack(zei_Ids.craftSet1, 16, 4), new Object[] // chainLink
				{ " r ", "r r", " r ", 'r', rod });
		GameRegistry.addRecipe(chain, new Object[] { "ccc", "c c", "ccc", 'c', chainLink });
		GameRegistry.addRecipe(new ItemStack(zei_Ids.craftSet1, 16, 6), new Object[] // sprocket
				{ "rsr", "sss", "rsr", 's', Block.stone, 'r', rod });
		GameRegistry.addRecipe(joint, new Object[] { "grg", 'g', smallGear, 'r', rod });
		GameRegistry.addRecipe(joint, new Object[] { "g", "r", "g", 'g', smallGear, 'r', rod });
		GameRegistry.addRecipe(new ItemStack(zei_Ids.craftSet1, 16, 8), new Object[] // sharp
				{ " i ", "iii", 'i', Item.ingotIron });
		GameRegistry.addRecipe(chisel, new Object[] { "r", "w", 'w', Block.planks, 'r', rod });
		GameRegistry.addRecipe(cylinder, new Object[] { " l ", "ppp", " l ", 'p', smallPlate, 'l', chainLink });
		GameRegistry.addRecipe(piston, new Object[] { "c", "r", "c", 'c', cylinder, 's', sealant, 'r', rod });
		GameRegistry.addRecipe(new ItemStack(zei_Ids.craftSet1, 4, 13), new Object[] // actuator
				{ "Drj", "  r", "  R", 'R', Item.redstone, 'D', chalk, 'r', rod, 'j', joint });
		GameRegistry.addRecipe(new ItemStack(zei_Ids.craftSet1, 3, 14), new Object[] // canvas
				{ "sss", "sls", "sbs", 's', Item.stick, 'l', Item.leather, 'b', Item.bucketWater });
		GameRegistry.addRecipe(wing, new Object[] { "sss", "ccc", 'c', canvas, 's', Item.stick });
		GameRegistry.addRecipe(wing, new Object[] { "sc", "sc", "sc", 'c', canvas, 's', Item.stick });
		GameRegistry.addRecipe(canvas, new Object[] { " s ", "sps", " s ", 'p', Item.paper, 's', Item.stick });
		GameRegistry.addRecipe(new ItemStack(zei_Ids.windmill, 1, 0), new Object[] { " w ", "wgw", " w ", 'w', wing, 'g', smallGear });
		GameRegistry.addRecipe(gearbox, new Object[] { " s ", "rgr", "glg", 's', Block.stone, 'r', rod, 'g', smallGear, 'l', rollerChain });
		GameRegistry.addRecipe(gearbox, new Object[] { " s ", "glg", "rgr", 's', Block.stone, 'r', rod, 'g', smallGear, 'l', rollerChain });
		GameRegistry.addRecipe(gearbox, new Object[] { "rgr", "glg", " s ", 's', Block.stone, 'r', rod, 'g', smallGear, 'l', rollerChain });
		GameRegistry.addRecipe(gearbox, new Object[] { "glg", "rgr", " s ", 's', Block.stone, 'r', rod, 'g', smallGear, 'l', rollerChain });
		GameRegistry.addRecipe(passive, new Object[] { "a a", "pgp", " g ", 'p', smallPlate, 'a', actuator, 'g', smallGear });
		GameRegistry.addRecipe(aggressive, new Object[] { "a a", "rgr", "rgr", 'r', Block.torchRedstoneActive, 'a', actuator, 'g', smallGear });
		GameRegistry.addRecipe(jaw, new Object[] { "sss", "sps", "sps", 's', sharp, 'p', smallPlate });
		GameRegistry.addRecipe(sensor, new Object[] { "tGt", "RtR", "r r", 't', Block.torchRedstoneActive, 'G', Block.glass, 'r', rod, 'R', Item.redstone });
		/*
		 * ModLoader.addRecipe(new ItemStack(zei_Ids.craftSet2, 2, 8), new
		 * Object[] //heatsink { "rrr", "rir", "rrr", 'r', rod, 'i',
		 * Item.ingotIron });
		 */
		GameRegistry.addRecipe(spring, new Object[] { "r", "r", "r", 'r', chainLink });
		GameRegistry.addShapelessRecipe(new ItemStack(Item.slimeBall, 3), new Object[] { new ItemStack(Block.sapling, 1, 0), Item.bucketWater, Item.clay });
		GameRegistry.addShapelessRecipe(new ItemStack(Item.slimeBall, 3), new Object[] { new ItemStack(Block.sapling, 1, 1), Item.bucketWater, Item.clay });
		GameRegistry.addShapelessRecipe(new ItemStack(Item.slimeBall, 3), new Object[] { new ItemStack(Block.sapling, 1, 2), Item.bucketWater, Item.clay });
		GameRegistry.addShapelessRecipe(new ItemStack(Item.slimeBall, 3), new Object[] { new ItemStack(Block.sapling, 1, 3), Item.bucketWater, Item.clay });
		GameRegistry.addShapelessRecipe(rollerChain, new Object[] { sprocket, sprocket, chain });
		GameRegistry.addRecipe(leg, new Object[] { " j ", "ini", "psp", 'n', spring, 'j', joint, 's', Block.stone, 'p', smallPlate, 'i', piston });
		GameRegistry.addRecipe(drill, new Object[] { "kcg", 'k', Item.pickaxeStone, 'c', cylinder, 'g', smallGear });
		GameRegistry.addRecipe(drill, new Object[] { "k", "c", "g", 'k', Item.pickaxeStone, 'c', cylinder, 'g', smallGear });
		GameRegistry.addRecipe(wBody, new Object[] { "prp", "pdp", "rGr", 'p', smallPlate, 'G', gearbox, 'r', rod, 'd', drill });
		GameRegistry.addRecipe(wHead, new Object[] { "ppp", "ePs", " g ", 'p', smallPlate, 's', sensor, 'e', eyePiece, 'P', passive, 'g', smallGear });
		GameRegistry.addRecipe(new ItemStack(zei_Ids.craftSet1, 1, 41), new Object[] // mortar
				{ "o o", " o ", 'o', Block.stone });
		GameRegistry.addRecipe(new ItemStack(zei_Ids.craftSet1, 1, 42), new Object[] // pestle
				{ "  o", " o ", "o  ", 'o', Block.stone });
		GameRegistry.addRecipe(new ItemStack(zei_Ids.mortar, 1, 0), new Object[] // mortar
																					// AND
																					// pestle
				{ "x", "o", 'o', new ItemStack(zei_Ids.craftSet1, 1, 41), 'x', new ItemStack(zei_Ids.craftSet1, 1, 42) });
		GameRegistry.addShapelessRecipe(new ItemStack(Item.gunpowder, 4), new Object[] { new ItemStack(zei_Ids.craftSet1, 1, 43), new ItemStack(zei_Ids.craftSet1, 1, 44), Item.coal });
		GameRegistry.addShapelessRecipe(new ItemStack(zei_Ids.soulCore, 1, 1), new Object[] // make
																							// rounded
																							// stone
				{ chisel, Block.stone });
		GameRegistry.addShapelessRecipe(new ItemStack(zei_Ids.soulCore, 1, 2), new Object[] // make
																							// stone
																							// ball
				{ chisel, new ItemStack(zei_Ids.soulCore, 1, 1) });
		GameRegistry.addShapelessRecipe(new ItemStack(zei_Ids.soulCore, 1, 3), new Object[] // make
																							// incomplete
																							// core
				{ chisel, new ItemStack(zei_Ids.soulCore, 1, 2) });
		GameRegistry.addShapelessRecipe(new ItemStack(zei_Ids.soulCore, 1, 4), new Object[] // make
																							// empty
																							// core
				{ new ItemStack(zei_Ids.craftSet1, 1, 43), new ItemStack(zei_Ids.soulCore, 1, 3) });
		GameRegistry.addShapelessRecipe(new ItemStack(zei_Ids.soulCore, 1, 4), new Object[] // make
																							// empty
																							// core
				{ Item.sugar, new ItemStack(zei_Ids.soulCore, 1, 3) });
		GameRegistry.addRecipe(new ItemStack(zei_Ids.chalk2, 16, 0), new Object[] // make
																					// partial
																					// chalk
				{ "###", "#l#", "###", '#', new ItemStack(zei_Ids.craftSet1, 1, 43), 'l', new ItemStack(Item.dyePowder, 1, 4) });
		GameRegistry.addRecipe(new ItemStack(zei_Ids.chalk2, 16, 0), new Object[] // make
																					// partial
																					// chalk
				{ "###", "#l#", "###", '#', Item.sugar, 'l', new ItemStack(Item.dyePowder, 1, 4) });
		GameRegistry.addRecipe(new ItemStack(zei_Ids.stoneFigureItem, 3, 1), new Object[] { "###", "#c#", "###", '#', Block.cobblestone, 'c', new ItemStack(zei_Ids.chalk2, 1, 0) });
		GameRegistry.addRecipe(new ItemStack(zei_Ids.itemBeacon, 1, 0), new Object[] { "s", "#", "#", '#', Block.stone, 's', new ItemStack(zei_Ids.craftSet1, 1, 20) });
		GameRegistry.addRecipe(new ItemStack(zei_Ids.itemSentry, 1, 0), new Object[] { " ll", "h##", " ll", '#', new ItemStack(zei_Ids.craftSet1, 1, 26), 'l', new ItemStack(zei_Ids.craftSet1, 1, 27),
				'h', new ItemStack(zei_Ids.craftSet1, 1, 28) });
		GameRegistry.addRecipe(new ItemStack(zei_Ids.itemWorker, 1, 0), new Object[] // worker
				{ " A ", "CBC", 'A', new ItemStack(zei_Ids.craftSet1, 1, 23), 'B', new ItemStack(zei_Ids.craftSet1, 1, 24), 'C', new ItemStack(zei_Ids.craftSet1, 1, 27) });
		GameRegistry.addRecipe(new ItemStack(zei_Ids.craftSet1, 1, 28), new Object[] // sentry
																						// head
				{ "sS", " G", "sa", 's', new ItemStack(zei_Ids.craftSet1, 1, 19), 'G', gearbox, 'a', aggressive, 'S', sensor });
		GameRegistry.addRecipe(new ItemStack(zei_Ids.craftSet1, 1, 26), new Object[] // sentry
																						// body
				{ "gpg", "rGr", "gpg", 'g', smallGear, 'p', smallPlate, 'G', gearbox, 'r', rod });
		GameRegistry.addRecipe(new ItemStack(zei_Ids.turnBlock, 1, 0), new Object[] // shaft
				{ "sss", 's', Item.stick });
		GameRegistry.addRecipe(new ItemStack(zei_Ids.miller, 1, 0), new Object[] // miller
				{ "sss", " g ", "sss", 's', Block.cobblestone, 'g', smallGear });
		/*
		 * ModLoader.addRecipe(new ItemStack(zei_Ids.plate, 3, 0), new Object[]
		 * //plater block { "s","s","s", 's' , Block.stone });
		 */
		GameRegistry.addRecipe(new ItemStack(zei_Ids.latchBlock, 1, 0), new Object[] { "##g", "s #", "#r#", '#', smallPlate, 'r', Item.redstone, 'g', smallGear, 's', spring });
		GameRegistry.addRecipe(new ItemStack(zei_Ids.craftSet1, 1, 25), new Object[] // toter
																						// head
				{ "psp", "lPl", "ppp", 'p', smallPlate, 's', sensor, 'l', lens, 'P', passive });
		GameRegistry.addRecipe(new ItemStack(zei_Ids.itemTote, 1, 0), new Object[] { "ll", "hL", "ll", 'l', leg, 'h', new ItemStack(zei_Ids.craftSet1, 1, 25), 'L',
				new ItemStack(zei_Ids.latchBlock, 1, 0) });
		GameRegistry.addRecipe(new ItemStack(zei_Ids.craftSet1, 1, 19), new Object[] // jaw
				{ "lll", "sss", 'l', sharp, 's', smallPlate });
		GameRegistry.addRecipe(new ItemStack(zei_Ids.craftSet1, 1, 19), new Object[] // jaw
				{ "sss", "lll", 'l', sharp, 's', smallPlate });
		GameRegistry.addShapelessRecipe(new ItemStack(zei_Ids.soulCore, 1, 4), new Object[] // clean
																							// yer
																							// soul!
				{ new ItemStack(zei_Ids.soulCore, 1, 0) });
		GameRegistry.addShapelessRecipe(new ItemStack(zei_Ids.soulCore, 1, 4), new Object[] // clean
																							// yer
																							// soul!
				{ new ItemStack(zei_Ids.soulCore, 1, 5) });
		GameRegistry.addShapelessRecipe(new ItemStack(zei_Ids.soulCore, 1, 4), new Object[] // clean
																							// yer
																							// soul!
				{ new ItemStack(zei_Ids.soulCore, 1, 6) });
		// /EVIL TEST RECIPES///
		GameRegistry.addRecipe(new ItemStack(zei_Ids.cannon, 1, 0), new Object[] { " # ", "###", '#', Block.cobblestone });
		GameRegistry.addRecipe(new ItemStack(zei_Ids.soulCore, 4, 0), new Object[] { "##", "# ", '#', Block.dirt });
		GameRegistry.addRecipe(new ItemStack(zei_Ids.soulCore, 4, 5), new Object[] { " #", "# ", '#', Block.dirt });
		GameRegistry.addRecipe(new ItemStack(zei_Ids.soulCore, 4, 6), new Object[] { "#", "#", '#', Block.dirt });
		GameRegistry.addRecipe(new ItemStack(zei_Ids.craftSet1, 64, 23), new Object[] { "# ", "##", '#', Block.dirt });
		GameRegistry.addRecipe(new ItemStack(zei_Ids.texture, 1, 0), new Object[] { "ww", "ww", 'w', Block.cloth });
	}
}
