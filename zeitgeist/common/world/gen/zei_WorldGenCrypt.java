package zeitgeist.common.world.gen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import zeitgeist.common.zei_Ids;

public class zei_WorldGenCrypt extends WorldGenerator {
	int size = 7;

	public zei_WorldGenCrypt() {
	}

	private void boxy(World world, Random random, int height, int width, int length, int i, int j, int k) {
		i = width * (i / width);
		k = length * (k / length);
		int w2 = width / 2;
		int l2 = length / 2;
		int h2 = height / 2;
		int w3 = width - 2;
		int l3 = length - 2;
		int h3 = height - 2;
		int w4 = w2 - 1;
		int l4 = l2 - 1;
		int h4 = h2 - 1;
		int a1 = world.getTopSolidOrLiquidBlock(i + w2, k + l2);
		int a2 = world.getTopSolidOrLiquidBlock(i - w2, k + l2);
		int a3 = world.getTopSolidOrLiquidBlock(i - w2, k - l2);
		int a4 = world.getTopSolidOrLiquidBlock(i + w2, k - l2);
		int maxa = j;
		if (a1 < maxa) {
			maxa = a1;
		}
		if (a2 < maxa) {
			maxa = a2;
		}
		if (a3 < maxa) {
			maxa = a3;
		}
		if (a4 < maxa) {
			maxa = a4;
		}
		j = maxa - 3;
		for (int x = -w2; x <= width - w2; x++) {
			for (int z = -l2; z <= length - l2; z++) {
				for (int y = 0; y <= height; y++) {
					world.setBlock(i + x, j + y, z + k, 0);
				}
			}
		}
		// twice because frass is an asshole about that kinda thing
		for (int x = -w2; x <= width - w2; x++) {
			for (int z = -l2; z <= length - l2; z++) {
				for (int y = 0; y <= height; y++) {
					world.setBlock(i + x, j + y, z + k, 0);
				}
			}
		}
		struct(world, 0, height, width, length, i, j, k);
		/*
		 * for(int z=0;z<=length;z++){ for(int x=0;x<=width;x++){
		 * world.setBlockWithNotify(i+(x-w2), j+height, k+(z-l2),
		 * 43);//Block.sandStone.blockID); } }
		 */
		for (int z = 1; z <= length - 1; z++) {
			for (int x = 1; x <= width - 1; x++) {
				world.setBlock(i + (x - (w2)), j + height + 1, k + (z - (l2)), 44, 1, 2);
			}
		}
		int R = random.nextInt(8);
		if (R == 1) {
			// world.setBlockWithNotify(i,j+1,k,zei_Ids.crystal);
		} else if (R == 2) {
			// world.setBlockWithNotify(i,j+1,k,zei_Ids.heal);
		} else if (R == 3) {
			world.setBlock(i, j + 1, k, Block.chest.blockID);
			TileEntityChest tileentitychest = (TileEntityChest) world.getBlockTileEntity(i, j + 1, k);
			tileentitychest.setInventorySlotContents(0, new ItemStack(Block.sapling, 9));
			tileentitychest.setInventorySlotContents(1, new ItemStack(Block.grass, 2));
			tileentitychest.setInventorySlotContents(2, new ItemStack(Item.seeds, 3));
			tileentitychest.setInventorySlotContents(3, new ItemStack(Block.wood, 1));
		} else if (R == 3) {
			world.setBlock(i, j + 1, k, zei_Ids.boing);
		} else if (R == 4) {
			// world.setBlockWithNotify(i,j+1,k,AutomatonLogger.sky);
			// WorldGenerator obj = new AM_WorldGenCity();
			// obj.generate(world, world.rand, i, 25, k);
		} else if (R == 5) {
			// world.setBlockAndMetadataWithNotify(i,j+1,k,zei_Ids.deployer,2);
		} else {
			// world.setBlockWithNotify(i,j+1,k,zei_Ids.fakeCrystal);
		}
	}

	private void column(World world, int height, int i, int j, int k) {
		for (int x = -2; x <= 2; x++) {
			for (int z = -2; z <= 2; z++) {
				for (int y = 0; y >= -height; y--) {
					world.setBlock(i + x, j + y, k + z, Block.cobblestoneMossy.blockID);
				}
			}
		}
		for (int x = -1; x <= 1; x++) {
			for (int z = -1; z <= 1; z++) {
				for (int y = 0; y >= -height; y--) {
					world.setBlock(i + x, j + y, k + z, 0);
				}
			}
		}
	}

	@Override
	public boolean generate(World world, Random random, int i, int j, int k) {
		int bb = world.getBlockId(i, j - 1, k);
		if (bb == 2 || bb == 12 || bb == zei_Ids.arch) {
			boxy(world, random, 5, 4, 4, i, j + 3, k);
		}
		return true;
	}

	private void struct(World world, int O, int height, int width, int length, int i, int j, int k) {
		int b = zei_Ids.tech;
		int w2 = width / 2;
		int l2 = length / 2;
		int h2 = height / 2;
		int w3 = width - 2;
		int l3 = length - 2;
		int h3 = height - 2;
		int w4 = w2 - 1;
		int l4 = l2 - 1;
		int h4 = h2 - 1;
		for (int x = 0; x <= w3; x++) {
			for (int y = 0; y <= height; y++) {
				world.setBlock((x - w4) + i, j + y, k + l2, b);
			}
		}
		for (int x = 0; x <= w3; x++) {
			for (int y = 0; y <= height; y++) {
				world.setBlock(((x - w4)) + i, j + y, k - l2, b);
			}
		}
		// int l9=l2-1;
		for (int z = 1; z < length; z++) {
			for (int y = 0; y <= height; y++) {
				world.setBlock(i + w2, j + y, k + (z - l2), b);
			}
		}
		for (int z = 1; z < length; z++) {
			for (int y = 0; y <= height; y++) {
				world.setBlock(i - w2, j + y, k + (z - l2), b);
			}
		}
		for (int y = 0; y < height; y++) {
			world.setBlock(i - w2, j + y, k - l2, b, 4, 3);
		}
		for (int y = 0; y < height; y++) {
			world.setBlock(i - w2, j + y, k + l2, b, 4, 3);
		}
		for (int y = 0; y < height; y++) {
			world.setBlock(i + w2, j + y, k + l2, b, 4, 3);
		}
		for (int y = 0; y < height; y++) {
			world.setBlock(i + w2, j + y, k - l2, b, 4, 3);
		}
		world.setBlock(i + w2, j + height, k - l2, 44, 1, 3);
		world.setBlock(i + w2, j + height, k + l2, 44, 1, 3);
		world.setBlock(i - w2, j + height, k + l2, 44, 1, 3);
		world.setBlock(i - w2, j + height, k - l2, 44, 1, 3);
		/*
		 * for(int z=0;z<=l3;z++){ for(int y=0;y<=h3;y++){ for(int
		 * x=0;x<=w3;x++){ world.setBlockWithNotify(i+(x-w4), j+y+1, k+(z-l4),
		 * 0); } } }
		 */
		for (int z = 0; z <= l3; z++) {
			for (int x = 0; x <= w3; x++) {
				world.setBlock(i + (x - w4), j, k + (z - l4), Block.sandStone.blockID);
			}
		}
		/*
		 * switch(O){ case
		 * 1:world.setBlockWithNotify(i-w2,j+1,k,0);world.setBlockWithNotify
		 * (i-w2,j+2,k,0);world.setBlockWithNotify(i-w2,j+3,k,0);return; case
		 * 2:world
		 * .setBlockWithNotify(i+w2,j+1,k,0);world.setBlockWithNotify(i+w2
		 * ,j+2,k,0);world.setBlockWithNotify(i+w2,j+3,k,0);return; case
		 * 3:world.
		 * setBlockWithNotify(i,j+1,k-l2,0);world.setBlockWithNotify(i,j+
		 * 2,k-l2,0);world.setBlockWithNotify(i,j+3,k-l2,0);return; case
		 * 4:world.
		 * setBlockWithNotify(i,j+1,k+l2,0);world.setBlockWithNotify(i,j+
		 * 2,k+l2,0);world.setBlockWithNotify(i,j+3,k+l2,0);return; }
		 */
	}
}
