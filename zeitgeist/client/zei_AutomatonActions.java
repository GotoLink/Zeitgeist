package zeitgeist.client;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class zei_AutomatonActions {
	static int H = 3;
	static int M = 5;
	static int nerk[][];
	static int nerp[][];

	public zei_AutomatonActions() {
	}

	static {
		sphereGen(M);
		sphereGen2(H);
	}

	/*
	 * public static void Frassify(World world, EntityPlayer entity) { int i =
	 * MathHelper.floor_double(entity.posX); int j =
	 * MathHelper.floor_double(entity.posY); int k =
	 * MathHelper.floor_double(entity.posZ); Frassify(world, i, j, k); } public
	 * static void Frassify(World world, int i, int j, int k) { for (int n = 0;
	 * n < nerp.length; n++) { int h = world.getBlockId(i + nerp[n][0], j +
	 * nerp[n][1], k + nerp[n][2]); if (h > 0 && h < zei_BlockFrass.max) { int
	 * FIX = zei_BlockFrass.blocks[h]; if (FIX > 0) { int l = i + nerp[n][0];
	 * int i1 = j + nerp[n][1]; int j1 = k + nerp[n][2]; switch (FIX) { case 1:
	 * world.setBlockWithNotify(l, i1, j1, zei_Ids.frass); case 2:
	 * world.setBlockAndMetadataWithNotify(l, i1, j1, zei_Ids.frass, 2); break;
	 * case 3: world.setBlockAndMetadataWithNotify(l, i1, j1, zei_Ids.frass2,
	 * 0); break; case 4: world.setBlockAndMetadataWithNotify(l, i1, j1,
	 * zei_Ids.frass2, 1); break; case 5: world.setBlockAndMetadataWithNotify(l,
	 * i1, j1, zei_Ids.frass, 3); break; case 6: world.setBlockWithNotify(l, i1,
	 * j1, zei_Ids.grower); break; case 7:
	 * world.setBlockAndMetadataWithNotify(l, i1, j1, h == 17 ? zei_Ids.tech :
	 * zei_Ids.crink, h == 17 ? 1 : 0); break; case 8:
	 * world.setBlockWithNotify(l, i1, j1, zei_Ids.dapling); break;//((world
	 * .rand.nextInt(3)==0)?zei_Ids.dapling:zei_Ids.techPlant));break; case 9:
	 * world.setBlockAndMetadataWithNotify(l, i1, j1, zei_Ids.frass, 1); break;
	 * case 10: world.setBlockWithNotify(l, i1, j1, 0); break; } /* if(h==9){
	 * world.setBlock(i+nerp[n][0],j+nerp[n][1],k+nerp[n][2],AutomatonLogger
	 * .frass2); }else if(h==8){
	 * world.setBlockAndMetadata(i+nerp[n][0],j+nerp[n]
	 * [1],k+nerp[n][2],AutomatonLogger.frass2,1); }else if(h==12){
	 * world.setBlockAndMetadata
	 * (i+nerp[n][0],j+nerp[n][1],k+nerp[n][2],AutomatonLogger.frass,2); }else
	 * if(h==82){
	 * world.setBlockAndMetadata(i+nerp[n][0],j+nerp[n][1],k+nerp[n][2
	 * ],AutomatonLogger.frass,0); }else if(h==18){
	 * world.setBlock(i+nerp[n][0],j
	 * +nerp[n][1],k+nerp[n][2],AutomatonLogger.crink); }else if(h==17){
	 * world.setBlockAndMetadata
	 * (i+nerp[n][0],j+nerp[n][1],k+nerp[n][2],AutomatonLogger.tech,1); }else
	 * if(h==2){ world.setBlock(i+nerp[n][0],j+nerp[n][1],k+nerp[n][2],3); }else
	 * if(Block.blocksList[h].blockMaterial==Material.plants
	 * ||Block.blocksList[h].blockMaterial==Material.cactus){
	 * world.setBlock(i+nerp
	 * [n][0],j+nerp[n][1],k+nerp[n][2],AutomatonLogger.grower); } } } } } int r
	 * = M - 1; world.markBlocksDirty(i - r, j - r, k - r, i + r, j + r, k + r);
	 * //world.setBlockAndMetadataWithNotify }
	 */
	public static void Blockify(int block, World world, EntityPlayer entity) {
		int i = MathHelper.floor_double(entity.posX);
		int j = MathHelper.floor_double(entity.posY);
		int k = MathHelper.floor_double(entity.posZ);
		Blockify(block, world, i, j, k);
	}

	public static void Blockify(int block, World world, int i, int j, int k) {
		for (int n = 0; n < nerp.length; n++) {
			if (world.getBlockId(i + nerp[n][0], j + nerp[n][1], k + nerp[n][2]) != 0) {
				world.setBlock(i + nerp[n][0], j + nerp[n][1], k + nerp[n][2], block);
			}
		}
	}

	public static int FValue(int x, int y, int z, int R) {
		return x * x + y * y + z * z - R * R;
	}

	/*
	 * public static void Naturalization(World world, EntityPlayer entity) { int
	 * i = MathHelper.floor_double(entity.posX); int j =
	 * MathHelper.floor_double(entity.posY); int k =
	 * MathHelper.floor_double(entity.posZ); Naturalization(world, i, j, k); }
	 * public static void Naturalization(World world, int i, int j, int k) { for
	 * (int n = 0; n < nerp.length; n++) { int h = world.getBlockId(i +
	 * nerp[n][0], j + nerp[n][1], k + nerp[n][2]); int m =
	 * world.getBlockMetadata(i + nerp[n][0], j + nerp[n][1], k + nerp[n][2]);
	 * if (h > 96) { if (h == 3 || (h == zei_Ids.frass && (m == 0 || m == 3))) {
	 * world.setBlock(i + nerp[n][0], j + nerp[n][1], k + nerp[n][2], 0);
	 * world.setBlock(i + nerp[n][0], j + nerp[n][1], k + nerp[n][2], 2); } else
	 * if (h == zei_Ids.frass || h == zei_Ids.frass2) { world.setBlock(i +
	 * nerp[n][0], j + nerp[n][1], k + nerp[n][2], 0); } else if (h ==
	 * zei_Ids.crink) { world.setBlock(i + nerp[n][0], j + nerp[n][1], k +
	 * nerp[n][2], 18); } else if (h == zei_Ids.tech && m == 1) {
	 * world.setBlock(i + nerp[n][0], j + nerp[n][1], k + nerp[n][2], 17); }
	 * else if (h == zei_Ids.tech) { world.setBlock(i + nerp[n][0], j +
	 * nerp[n][1], k + nerp[n][2], 4); } } else if (h == zei_Ids.grower || h ==
	 * zei_Ids.dapling) { int r = world.rand.nextInt(4); switch (r) { case 0:
	 * world.setBlock(i + nerp[n][0], j + nerp[n][1], k + nerp[n][2],
	 * Block.sapling.blockID); break; case 1: world.setBlock(i + nerp[n][0], j +
	 * nerp[n][1], k + nerp[n][2], Block.plantYellow.blockID); break; case 2:
	 * world.setBlock(i + nerp[n][0], j + nerp[n][1], k + nerp[n][2],
	 * Block.plantRed.blockID); break; default: world.setBlock(i + nerp[n][0], j
	 * + nerp[n][1], k + nerp[n][2], Block.reed.blockID); break; } } } int r = M
	 * - 1; world.markBlocksDirty(i - r, j - r, k - r, i + r, j + r, k + r);
	 * //world.setBlockAndMetadataWithNotify }
	 */
	public static void Hollow(World world, EntityPlayer entity) {
		// entity.rayTrace(10d,1f);
		Vec3 vs = Vec3.createVectorHelper(entity.posX, entity.posY, entity.posZ);// Vec3D
		// vs
		// =
		// entity.getPosition(1f);
		Vec3 vp = entity.getLook(1f);
		int D = 3;
		double d = D;
		// Vec3D ve = vs.addVector(, vp.yCoord * d, vp.zCoord * d);
		int i = (int) Math.round(vs.xCoord + vp.xCoord * d);
		int j = (int) Math.round(vs.yCoord + vp.yCoord * d);
		int k = (int) Math.round(vs.zCoord + vp.zCoord * d);
		/*
		 * int count=0; boolean cell[][][]=new boolean[D*2][D*2][D*2] for(int
		 * b=0;b<D;b++){ int xx=(int)Math.round(vs.xCoord +vp.xCoord *
		 * ((double)b)); int yy=(int)Math.round(vs.yCoord +vp.yCoord *
		 * ((double)b)); int zz=(int)Math.round(vs.zCoord +vp.zCoord *
		 * ((double)b)); count++; } int nerk[][]=new int[count][3]; int co=0;
		 * for(int xi=0;xi<D*2;xi++){ for(int yi=0;yi<D*2;yi++){ for(int
		 * zi=0;zi<D*2;zi++){ if(cell[xi][yi][zi]){ nerk[co][0]=xi;
		 * nerk[co][1]=yi; nerk[co][2]=zi; co++; } }}}
		 * System.out.println(nerk[0][0]+","+nerk[0][1]+","+nerk[0][2]);
		 * //System.out.println(vs.posY); //System.out.println(vp.yCoord); int
		 * I=i; int J=j; int K=k;
		 */
		// int xs=vs.xCoord
		for (int n = 0; n < nerk.length; n++) {
			int h = world.getBlockId(i + nerk[n][0], j + nerk[n][1], k + nerk[n][2]);
			/*
			 * switch (h){ case
			 * 1:world.setBlockAndMetadata(i+nerk[n][0],j+nerk[n
			 * ][1],k+nerk[n][2], zei_Ids.hollow,0);break; case
			 * 2:world.setBlockAndMetadata
			 * (i+nerk[n][0],j+nerk[n][1],k+nerk[n][2], zei_Ids.hollow,1);break;
			 * case
			 * 3:world.setBlockAndMetadata(i+nerk[n][0],j+nerk[n][1],k+nerk[
			 * n][2], zei_Ids.hollow,6);break; case
			 * 4:world.setBlockAndMetadata(i
			 * +nerk[n][0],j+nerk[n][1],k+nerk[n][2], zei_Ids.hollow,2);break;
			 * case
			 * 9:world.setBlockAndMetadata(i+nerk[n][0],j+nerk[n][1],k+nerk[
			 * n][2], zei_Ids.hollow,3);break; case
			 * 11:world.setBlockAndMetadata(
			 * i+nerk[n][0],j+nerk[n][1],k+nerk[n][2], zei_Ids.hollow,4);break;
			 * case
			 * 82:world.setBlockAndMetadata(i+nerk[n][0],j+nerk[n][1],k+nerk
			 * [n][2], zei_Ids.hollow,5);break; case
			 * 12:world.setBlockAndMetadata
			 * (i+nerk[n][0],j+nerk[n][1],k+nerk[n][2], zei_Ids.hollow,7);break;
			 * case
			 * 24:world.setBlockAndMetadata(i+nerk[n][0],j+nerk[n][1],k+nerk
			 * [n][2], zei_Ids.hollow,8);break; case
			 * 13:world.setBlockAndMetadata
			 * (i+nerk[n][0],j+nerk[n][1],k+nerk[n][2], zei_Ids.hollow,9);break;
			 * }
			 */
			int r = H;
			world.markBlockRangeForRenderUpdate(i - r, j - r, k - r, i + r, j + r, k + r);
		}
	}

	public static void sphereGen(int size) {
		int diam = size * 2 + 1;
		ArrayList<ArrayList<Integer>> ar = new ArrayList<ArrayList<Integer>>();
		for (int y = 0; y < diam; y++) {
			for (int x = 0; x < diam; x++) {
				for (int z = 0; z < diam; z++) {
					if (FValue(x - size, y - size, z - size, size) <= 0) {
						ArrayList<Integer> der = new ArrayList<Integer>();
						der.add(x - size);
						der.add(y - size);
						der.add(z - size);
						ar.add(der);
					}
				}
			}
		}
		int j = ar.size();
		nerp = new int[j][3];
		for (int d = 0; d < j; d++) {
			nerp[d][0] = ar.get(d).get(0);
			nerp[d][1] = ar.get(d).get(1);
			nerp[d][2] = ar.get(d).get(2);
		}
	}

	public static void sphereGen2(int size) {
		int diam = size * 2 + 1;
		ArrayList<ArrayList<Integer>> ar = new ArrayList<ArrayList<Integer>>();
		for (int y = 0; y < diam; y++) {
			for (int x = 0; x < diam; x++) {
				for (int z = 0; z < diam; z++) {
					if (FValue(x - size, y - size, z - size, size) <= 0) {
						ArrayList<Integer> der = new ArrayList<Integer>();
						der.add(x - size);
						der.add(y - size);
						der.add(z - size);
						ar.add(der);
					}
				}
			}
		}
		int j = ar.size();
		nerk = new int[j][3];
		for (int d = 0; d < j; d++) {
			nerk[d][0] = ar.get(d).get(0);
			nerk[d][1] = ar.get(d).get(1);
			nerk[d][2] = ar.get(d).get(2);
		}
	}
}
