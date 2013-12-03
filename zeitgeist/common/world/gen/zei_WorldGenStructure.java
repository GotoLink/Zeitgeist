package zeitgeist.common.world.gen;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import zeitgeist.common.zei_Ids;

public class zei_WorldGenStructure extends WorldGenerator {
	public int M = 7;
	public int set = 0;
	public int W;
	ArrayList<ArrayList<Integer>> chests = new ArrayList<ArrayList<Integer>>();

	public zei_WorldGenStructure() {
	}

	public void addTunnels(World world, int i, int j, int k) {
		int bb = 4;
		int ww = (W / 2);
		int WWW = W * 2;
		int F;
		F = world.getBlockId(i + WWW, j, k);
		if (F == zei_Ids.sky || F == zei_Ids.importantBuildingThingy || F == zei_Ids.arch) {
			// System.out.println("add tunnel south(-x)");
			int xe = (1 + i + ww);
			for (int x = xe - 3; x < xe + 3; x++) {
				for (int z = k - 3; z <= k + 3; z++) {
					world.setBlock(x, j, z, bb);
				}
			}
			for (int x = xe - 3; x < xe + 3; x++) {
				for (int y = j + 1; y < j + 5; y++) {
					world.setBlock(x, y, k - 3, bb);
				}
			}
			for (int x = xe - 3; x < xe + 3; x++) {
				for (int y = j + 1; y < j + 5; y++) {
					world.setBlock(x, y, k + 3, bb);
				}
			}
			for (int x = xe - 3; x < xe + 3; x++) {
				for (int z = k - 3; z <= k + 3; z++) {
					world.setBlock(x, j + 5, z, bb);
				}
			}
			for (int x = xe - 3; x < xe + 3; x++) {
				for (int z = k - 2; z <= k + 2; z++) {
					for (int y = j + 1; y < j + 5; y++) {
						world.setBlock(z, y, x, 0);
					}
				}
			}
		}
		F = world.getBlockId(i - WWW, j, k);
		if (F == zei_Ids.sky || F == zei_Ids.importantBuildingThingy || F == zei_Ids.arch) {
			// world.setBlock(i-ww,j,k,bb);
			// System.out.println("add tunnel north(+x)");
			int xe = (i - ww);
			for (int x = xe - 3; x < xe + 3; x++) {
				for (int z = k - 3; z <= k + 3; z++) {
					world.setBlock(x, j, z, bb);
				}
			}
			for (int x = xe - 3; x < xe + 3; x++) {
				for (int y = j + 1; y < j + 5; y++) {
					world.setBlock(x, y, k - 3, bb);
				}
			}
			for (int x = xe - 3; x < xe + 3; x++) {
				for (int y = j + 1; y < j + 5; y++) {
					world.setBlock(x, y, k + 3, bb);
				}
			}
			for (int x = xe - 3; x < xe + 3; x++) {
				for (int z = k - 3; z <= k + 3; z++) {
					world.setBlock(x, j + 5, z, bb);
				}
			}
			for (int x = xe - 3; x < xe + 3; x++) {
				for (int z = k - 2; z <= k + 2; z++) {
					for (int y = j + 1; y < j + 5; y++) {
						world.setBlock(z, y, x, 0);
					}
				}
			}
		}
		F = world.getBlockId(i, j, k + WWW);
		if (F == zei_Ids.sky || F == zei_Ids.importantBuildingThingy || F == zei_Ids.arch) {
			// System.out.println("add tunnel west(+z)");
			int xe = (k + ww);
			for (int x = xe - 3; x < xe + 3; x++) {
				for (int z = i - 3; z <= i + 3; z++) {
					world.setBlock(z, j, x, bb);
				}
			}
			for (int x = xe - 3; x < xe + 3; x++) {
				for (int y = j + 1; y < j + 5; y++) {
					world.setBlock(i - 3, y, x, bb);
				}
			}
			for (int x = xe - 3; x < xe + 3; x++) {
				for (int y = j + 1; y < j + 5; y++) {
					world.setBlock(i + 3, y, x, bb);
				}
			}
			for (int x = xe - 3; x < xe + 3; x++) {
				for (int z = i - 3; z <= i + 3; z++) {
					world.setBlock(z, j + 5, x, bb);
				}
			}
			for (int x = xe - 3; x < xe + 3; x++) {
				for (int z = i - 2; z <= i + 2; z++) {
					for (int y = j + 1; y < j + 5; y++) {
						world.setBlock(z, y, x, 0);
					}
				}
			}
		}
		F = world.getBlockId(i, j, k - WWW);
		if (F == zei_Ids.sky || F == zei_Ids.importantBuildingThingy || F == zei_Ids.arch) {
			// System.out.println("add tunnel east(-z)");
			int xe = (k - ww);
			for (int x = (xe - 3); x <= (xe + 3); x++) {
				for (int z = (i - 3); z <= (i + 3); z++) {
					world.setBlock(z, j, x, bb);
				}
			}
			for (int x = xe - 3; x <= xe + 3; x++) {
				for (int y = j + 1; y < j + 5; y++) {
					world.setBlock(i - 3, y, x, bb);
				}
			}
			for (int x = xe - 3; x <= xe + 3; x++) {
				for (int y = j + 1; y < j + 5; y++) {
					world.setBlock(i + 3, y, x, bb);
				}
			}
			for (int x = (xe - 3); x <= (xe + 3); x++) {
				for (int z = (i - 3); z <= (i + 3); z++) {
					world.setBlock(z, j + 5, x, bb);
				}
			}
			for (int x = xe - 3; x <= xe + 3; x++) {
				for (int z = i - 2; z <= i + 2; z++) {
					for (int y = j + 1; y < j + 5; y++) {
						world.setBlock(z, y, x, 0);
					}
				}
			}
		}
	}

	public void applyChests(World world, Random random) {
		for (int ii = 0; ii < chests.size(); ii++) {
			int i = chests.get(ii).get(0);
			int j = chests.get(ii).get(1);
			int k = chests.get(ii).get(2);
			world.setBlock(i, j, k, 54);
			chestMe(world, random, i, j, k);
		}
	}

	public void chestMe(World world, Random random, int i, int j, int k) {
		TileEntityChest tileentitychest = (TileEntityChest) world.getBlockTileEntity(i, j, k);
		// random.nextInt(tileentitychest.getSizeInventory())
		ItemStack itemstack = pickCheckLootItem(random);
		tileentitychest.setInventorySlotContents(13, itemstack);
	}

	// int JJ=0;
	@Override
	public boolean generate(World world, Random random, int i, int j, int k) {
		// JJ=j;
		// int bb=world.getBlockId(i,j-1,k);
		// if(bb==2 || bb==12 || bb==AutomatonLogger.frass){
		int m2 = M / 2;
		int m = M - 1;
		int m5 = M + 1;
		int m6 = M + 3;
		int m22 = m / 2;
		int m33 = m22 - 1;
		// System.out.println(i+","+k);
		W = M;
		// i=(i/W)*W;
		// k=(k/W)*W;
		j = zei_Ids.builderLevel;
		if (world.getBlockId(i, j, k) == zei_Ids.tech) {
			return true;
		}
		System.out.println("city generating.........");
		// for(int ii=0;ii<M;ii++){
		// for(int jj=0;jj<M;jj++){
		int RRR = set;
		if (RRR == 0) {
			emptyOut(world, 24, m, m, i, j, k);
		} else {
			cavernize(world, random, 27, m, m, i, j, k);
			return true;
		}
		// }
		// }
		// for(int ii=0;ii<M;ii++){
		// for(int jj=0;jj<M;jj++){
		if (RRR == 0) {
			boolean[] bo = { true, true, true, true };
			int w = W;
			// if(jj<m ){
			int bb1 = world.getBlockId(i, j, k + w);
			// int mm1=world.getBlockMetadata(i,j,k+w);
			bo[0] = !(bb1 == zei_Ids.sky || (bb1 == zei_Ids.importantBuildingThingy));
			/*
			 * if(bo[0] && bb1!=AutomatonLogger.frass){ //AM_WorldGenStructure
			 * foliage = new AM_WorldGenStructure(); //foliage.set=1;
			 * //foliage.generate( world, random, i, j, k+w);
			 * world.setBlockAndMetadata
			 * (i,AutomatonLogger.builderLevel,k,AutomatonLogger
			 * .importantBuildingThingy,1); }
			 */
			// bo[0]=!cell[ii][jj+1];
			// }
			// if(jj>0){
			// bo[1]=!cell[ii][jj-1];
			int bb2 = world.getBlockId(i, j, k - w);
			// int mm2=world.getBlockMetadata(i,j,k-w);
			bo[1] = !(bb2 == zei_Ids.sky || (bb2 == zei_Ids.importantBuildingThingy));
			/*
			 * if(bo[1] && bb2!=AutomatonLogger.frass){ //AM_WorldGenStructure
			 * foliage = new AM_WorldGenStructure(); //foliage.set=1;
			 * //foliage.generate( world, random, i, j, k-w);
			 * world.setBlockAndMetadata
			 * (i,AutomatonLogger.builderLevel,k,AutomatonLogger
			 * .importantBuildingThingy,1); }
			 */
			// }
			// if(ii<m ){
			// bo[2]=!cell[ii+1][jj];
			int bb3 = world.getBlockId(i + w, j, k);
			// int mm3=world.getBlockMetadata(i+w,j,k);
			bo[2] = !(bb3 == zei_Ids.sky || (bb3 == zei_Ids.importantBuildingThingy));
			/*
			 * if(bo[2] && bb3!=AutomatonLogger.frass){ //AM_WorldGenStructure
			 * foliage = new AM_WorldGenStructure(); //foliage.set=1;
			 * //foliage.generate( world, random, i+w, j, k);
			 * world.setBlockAndMetadata
			 * (i,AutomatonLogger.builderLevel,k,AutomatonLogger
			 * .importantBuildingThingy,1); }
			 */
			// }
			// if(ii>0){
			// bo[3]=!cell[ii-1][jj];
			int bb4 = world.getBlockId(i - w, j, k);
			// int mm4=world.getBlockMetadata(i-w,j,k);
			bo[3] = !(bb4 == zei_Ids.sky || (bb4 == zei_Ids.importantBuildingThingy));
			/*
			 * if(bo[3] && bb4!=AutomatonLogger.frass){ //AM_WorldGenStructure
			 * foliage = new AM_WorldGenStructure(); //foliage.set=1;
			 * //foliage.generate( world, random, i-w, j, k);
			 * world.setBlockAndMetadata
			 * (i,AutomatonLogger.builderLevel,k,AutomatonLogger
			 * .importantBuildingThingy,1); }
			 */
			// }
			boxy(world, random, bo, 7, m, m, i, j, k);
			boxy(world, random, bo, 7, m5, m5, i, j + 8, k);
			boxy(world, random, bo, 7, m6, m6, i, j + 16, k);
			for (int z = 0; z <= m; z++) {
				for (int x = 0; x <= m; x++) {
					world.setBlock(i + (x - m22), j, k + (z - m22), zei_Ids.sky, 1, 2);
				}
			}
		}
		// }
		// }
		int r = m2;
		/*
		 * for(int x=j;x<=j+20;x++){ world.setBlockAndMetadata(i, x, k,
		 * AutomatonLogger.frass,1); //AndMetadata }
		 */
		world.markBlockRangeForRenderUpdate(i - r, j - 3, k - r, i + r, j + 24, k + r);
		// addTunnels(world,i,j,k);
		// applyChests(world,random);
		// cell=null;
		chests = null;
		System.out.println("done");
		return true;
	}

	private void boxy(World world, Random random, boolean[] boo, int height, int width, int length, int i, int j, int k) {
		struct(world, random, boo, height, width, length, i, j, k);
	}

	private void cavernize(World world, Random random, int height, int width, int length, int i, int j, int k) {
		int w2 = width / 2;
		int l2 = length / 2;
		int newheight = random.nextInt((height / 2)) + (height / 2);
		int he = newheight - (height / 6);
		for (int z = 0; z <= length; z++) {
			for (int x = 0; x <= width; x++) {
				world.setBlock(i + (x - w2), j, k + (z - l2), zei_Ids.arch, 1, 2); // AndMetadata
			}
		}
		for (int z = 0; z <= length; z++) {
			for (int y = 1; y <= he; y++) {
				for (int x = 0; x <= width; x++) {
					world.setBlock(i + (x - w2), j + y, k + (z - l2), 0);
				}
			}
		}
		for (int z = 0; z <= length; z++) {
			for (int y = he; y <= newheight; y++) {
				for (int x = 0; x <= width; x++) {
					// System.out.println(newheight+" : "+y);
					if (random.nextInt(1 + newheight - y) != 0) {
						world.setBlock(i + (x - w2), j + y, k + (z - l2), 0);
					}
				}
			}
		}
		int RR = random.nextInt(7);
		if (RR > 2) {
			WorldGenerator obj;
			switch (RR) {
			case 4:
				obj = new zei_WorldGenDerk();
				break;
			case 5:
				obj = new zei_WorldGenPool();
				break;
			default:
				obj = new zei_WorldGenBigFakeTree();
				break;
			}
			obj.generate(world, random, i, j + 1, k);
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

	private void emptyOut(World world, int height, int width, int length, int i, int j, int k) {
		int w2 = width / 2;
		int l2 = length / 2;
		for (int z = 0; z <= length; z++) {
			for (int y = 0; y <= height; y++) {
				for (int x = 0; x <= width; x++) {
					world.setBlock(i + (x - w2), j + y, k + (z - l2), 0);
					// System.out.println("HERE: "+(x-w2));
				}
			}
		}
	}

	private ItemStack pickCheckLootItem(Random random) {
		int i = random.nextInt(19);
		if (i == 0) {
			return new ItemStack(zei_Ids.chalk2, random.nextInt(20) + 1, 0);
		}
		if (i == 1) {
			return new ItemStack(Item.ingotIron, random.nextInt(4) + 1);
		}
		if (i == 2) {
			return new ItemStack(zei_Ids.soulCore, 1, 4);
		}
		if (i == 3) {
			return new ItemStack(zei_Ids.soulCore, 1, 5);
		}
		if (i == 4) {
			return new ItemStack(zei_Ids.itemWorker, random.nextInt(4) + 1, 0);
		}
		if (i == 5) {
			// return new ItemStack(zei_Ids.crystal, random.nextInt(4) + 1,0);
		}
		if (i == 7) {
			return new ItemStack(Item.ingotGold, random.nextInt(4) + 1);
		}
		if (i == 10) {
			return new ItemStack(Item.silk, random.nextInt(40) + 1);
		}
		if (i == 11) {
			return new ItemStack(zei_Ids.itemSentry, random.nextInt(3) + 1, 0);
		}
		if (i == 12) {
			return new ItemStack(zei_Ids.soulCore, random.nextInt(20) + 3, 0);
		}
		if (i == 13) {
			return new ItemStack(Item.diamond, 1);
		}
		if (i == 14) {
			return new ItemStack(zei_Ids.craftSet1, random.nextInt(15) + 3, 0);
		}
		if (i == 15) {
			return new ItemStack(Item.reed, random.nextInt(15) + 3);
		}
		if (i == 16) {
			return new ItemStack(Item.appleRed, random.nextInt(10) + 1);
		}
		if (i == 17) {
			return new ItemStack(Item.seeds, random.nextInt(7) + 1);
		} else {
			return new ItemStack(Block.sapling, random.nextInt(30) + 1);
		}
	}

	private void struct(World world, Random random, boolean[] boo, int height, int width, int length, int i, int j, int k) {
		int b = zei_Ids.tech;
		int m = 2;
		int m2 = 3;
		int m3 = 8;
		int b3 = zei_Ids.crink;
		int w2 = width / 2;
		int l2 = length / 2;
		int h2 = height / 2;
		int w3 = width - 2;
		int l3 = length - 2;
		int h3 = height - 2;
		int w4 = w2 - 1;
		int l4 = l2 - 1;
		int h4 = h2 - 1;
		int h5 = h2 + 2;
		int w5 = w3 / 2;
		int l5 = l3 / 2;
		boolean zup = boo[0];
		boolean zown = boo[1];
		boolean xup = boo[2];
		boolean xown = boo[3];
		int h99 = height + 1;
		for (int y = 0; y <= h99; y++) {
			world.setBlock(i + w2, j + y, k + l2, b, m, 2); // AndMetadata
		}
		for (int y = 0; y <= h99; y++) {
			world.setBlock(i - w2, j + y, k + l2, b, m, 2);
		}
		for (int y = 0; y <= h99; y++) {
			world.setBlock(i + w2, j + y, k - l2, b, m, 2);
		}
		for (int y = 0; y <= h99; y++) {
			world.setBlock(i - w2, j + y, k - l2, b, m, 2);
		}
		int zh = 1;
		if (zup) {
			for (int x = 0; x <= w3; x++) {
				for (int y = 0; y <= h5; y++) {
					world.setBlock((x - w4) + i, j + y, k + l2 - zh, b, m, 2);
				}
			}
			for (int x = 0; x < w5; x++) {
				world.setBlock((x - w4) + i, j + h2, k + l2 - zh, b3, m3, 2);
			}
			for (int x = w5 + 1; x <= w3; x++) {
				world.setBlock((x - w4) + i, j + h2, k + l2 - zh, b3, m3, 2);
			}
			for (int x = 0; x <= w3; x++) {
				world.setBlock((x - w4) + i, j + h5, k + l2 - zh, zei_Ids.sky, 0, 2);
			}
			for (int x = 0; x <= w3; x++) {
				for (int y = h5 + 1; y <= height; y++) {
					world.setBlock((x - w4) + i, j + y, k + l2, b, m2, 2);
				}
			}
		}
		if (zown) {
			for (int x = 0; x <= w3; x++) {
				for (int y = 0; y <= h5; y++) {
					world.setBlock((x - w4) + i, j + y, k - l2 + zh, b, m, 2);
				}
			}
			for (int x = 0; x < w5; x++) {
				world.setBlock((x - w4) + i, j + h2, k - l2 + zh, b3, m3, 2);
			}
			for (int x = w5 + 1; x <= w3; x++) {
				world.setBlock((x - w4) + i, j + h2, k - l2 + zh, b3, m3, 2);
			}
			for (int x = 0; x <= w3; x++) {
				world.setBlock((x - w4) + i, j + h5, k - l2 + zh, zei_Ids.sky, 0, 2);
			}
			for (int x = 0; x <= w3; x++) {
				for (int y = h5 + 1; y <= height; y++) {
					world.setBlock((x - w4) + i, j + y, k - l2, b, m2, 2);
				}
			}
		}
		if (xup) {
			for (int z = 0; z <= l3; z++) {
				for (int y = 0; y <= h5; y++) {
					world.setBlock(i + w2 - zh, j + y, k + (z - l4), b, m, 2);
				}
			}
			for (int z = 0; z < l5; z++) {
				world.setBlock(i + w2 - zh, j + h2, k + (z - l4), b3, m3, 2);
			}
			for (int z = l5 + 1; z <= l3; z++) {
				world.setBlock(i + w2 - zh, j + h2, k + (z - l4), b3, m3, 2);
			}
			for (int z = 0; z <= l3; z++) {
				world.setBlock(i + w2 - zh, j + h5, k + (z - l4), zei_Ids.sky, 0, 2);
			}
			for (int z = 0; z <= l3; z++) {
				for (int y = h5 + 1; y <= height; y++) {
					world.setBlock(i + w2, j + y, k + (z - l4), b, m2, 2);
				}
			}
		}
		if (xown) {
			for (int z = 0; z <= l3; z++) {
				for (int y = 0; y <= h5; y++) {
					world.setBlock(i - w2 + zh, j + y, k + (z - l4), b, m, 2);
				}
			}
			for (int z = 0; z < l5; z++) {
				world.setBlock(i - w2 + zh, j + h2, k + (z - l4), b3, m3, 2);
			}
			for (int z = l5 + 1; z <= l3; z++) {
				world.setBlock(i - w2 + zh, j + h2, k + (z - l4), b3, m3, 2);
			}
			for (int z = 0; z <= l3; z++) {
				world.setBlock(i - w2 + zh, j + h5, k + (z - l4), zei_Ids.sky, 0, 2);
			}
			for (int z = 0; z <= l3; z++) {
				for (int y = h5 + 1; y <= height; y++) {
					world.setBlock(i - w2, j + y, k + (z - l4), b, m2, 2);
				}
			}
		}
		l3--;
		w3--;
		// w5+=2;
		// l5+=2;
		if (random.nextInt(4) != 0) {
			for (int z = 1; z <= l3; z++) {
				for (int x = 1; x <= w3; x++) {
					world.setBlock(i + (x - w5), j, k + (z - l5), 1);
				}
			}
			if (random.nextInt(5) == 0) {
				ArrayList<Integer> L = new ArrayList<Integer>();
				L.add(i);
				L.add(j + 1);
				L.add(k);
				chests.add(L);
				// world.setBlock(i, j+1, k, 54);
			} else if (random.nextInt(5) == 0) {
				// world.setBlockAndMetadata(i, j+1, k,
				// zei_Ids.deployer,random.nextInt(2)+1);
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
