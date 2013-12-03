package zeitgeist.common.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import zeitgeist.common.zei_Ids;

public class zei_BlockOre extends Block {
	public zei_BlockOre(int par1, int par2) {
		super(par1, Material.rock);
	}

	/*
	 * public int quantityDroppedWithBonus(int par1, Random par2Random) { if
	 * (par1 > 0 && blockID != idDropped(0, par2Random, par1)) { int i =
	 * par2Random.nextInt(par1 + 2) - 1; if (i < 0) { i = 0; } return
	 * quantityDropped(par2Random) * (i + 1); } else { return
	 * quantityDropped(par2Random); } }
	 */
	@Override
	public int damageDropped(int par1) {
		return blockID == zei_Ids.saltOre ? 43 : 44;
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return zei_Ids.craftSet1;
	}

	@Override
	public int quantityDropped(Random par1Random) {
		if (blockID == zei_Ids.saltOre) {
			return 1 + par1Random.nextInt(8);
		} else {
			return 4;
		}
	}
}
