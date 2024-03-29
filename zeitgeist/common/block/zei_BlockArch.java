package zeitgeist.common.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import zeitgeist.common.zei_Ids;

public class zei_BlockArch extends Block {
	static int D[];

	protected zei_BlockArch(int par1) {
		super(par1, Material.glass);
		zei_BlockArch.loadSprites();
	}

	@Override
	public int colorMultiplier(IBlockAccess par1IBlockAccess, int i, int j, int k) {
		switch (par1IBlockAccess.getBlockMetadata(i, j, k)) {
		case 1:
			return 0x8A8A8A;
		case 2:
			return 0x469DC2;
		case 3:// return 0x148C24;
			int var5 = 0;
			int var6 = 0;
			int var7 = 0;
			for (int var8 = -1; var8 <= 1; ++var8) {
				for (int var9 = -1; var9 <= 1; ++var9) {
					int var10 = par1IBlockAccess.getBiomeGenForCoords(i + var9, k + var8).getBiomeGrassColor();
					var5 += (var10 & 16711680) >> 16;
					var6 += (var10 & 65280) >> 8;
					var7 += var10 & 255;
				}
			}
			return (var5 / 9 & 255) << 16 | (var6 / 9 & 255) << 8 | var7 / 9 & 255;
		case 4:
			return 0x806045;
		case 5:
			return 0xffffff;
		default:
			return 0xADA890;
		}
	}

	@Override
	public int damageDropped(int i) {
		return 0;
	}

	public int getBlockTextureFromSideAndMetadata(int i, int j) {
		if (j == 5) {
			return D[1];
		}
		return D[0];
	}

	@Override
	public void onBlockAdded(World world, int i, int j, int k) {
		if (world.getBlockMetadata(i, j, k) == 5) {
			int it = 1;
			while (j - it > 20) {
				place(world, i, j - it, k);
				it++;
			}
			world.setBlock(i, 20, k, zei_Ids.architect);
		}
	}

	public void onBlockRemoval(World world, int i, int j, int k) {
		int bbb = world.getBlockMetadata(i, j, k);
		if (bbb == 4) {
			world.setBlock(i, j, k, Block.dirt.blockID);
		} else if (bbb == 1) {
			world.setBlock(i, j, k, Block.stone.blockID);
		} else if (bbb == 2) {
			world.setBlock(i, j, k, 9);
		} else if (bbb == 3) {
			world.setBlock(i, j, k, 2);
		}
		dropBlockAsItem_do(world, i, j, k, new ItemStack(blockID, 1, 0));
	}

	@Override
	public int quantityDropped(Random par1Random) {
		return 0;
	}

	public static boolean place(World world, int i, int j, int k) {
		int id = world.getBlockId(i, j, k);
		if (id == 3) {
			world.setBlock(i, j, k, zei_Ids.arch, 4, 3);
			return true;
		} else if (id == 1 || id == 4) {
			world.setBlock(i, j, k, zei_Ids.arch, 1, 3);
			return true;
		} else if (id == 8 || id == 9) {
			world.setBlock(i, j, k, zei_Ids.arch, 2, 3);
			return true;
		} else if (id == 2) {
			world.setBlock(i, j, k, zei_Ids.arch, 3, 3);
			return true;
		}/*
		 * else if(id!=zei_Ids.arch && id!=zei_Ids.arch2&& id!=0){
		 * world.setBlockAndMetadataWithNotify(i, j, k,zei_Ids.arch, 0);return
		 * true; }
		 */
		return false;
	}

	static void loadSprites() {
		D = new int[2];
		D[0] = 0;// zei_Universal.modOverride("/terrain.png",
					// "/zeitgeist/arch1.png");
		D[1] = 0;// zei_Universal.modOverride("/terrain.png",
					// "/zeitgeist/arch3.png");
	}
}
