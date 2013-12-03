package zeitgeist.common.world.gen;

import java.util.Random;

import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import zeitgeist.common.zei_Ids;

public class zei_WorldGenBigFakeTree extends WorldGenerator {
	static final byte otherCoordPairs[] = { 2, 0, 0, 1, 2, 1 };
	int basePos[] = { 0, 0, 0 };
	public boolean boo = true;
	double field_872_k;
	double field_873_j;
	double field_874_i;
	double field_875_h;
	int GG = 4; // 17
	int height;
	double heightAttenuation;
	int heightLimit;
	int heightLimitLimit;
	int leafDistanceLimit;
	int leafNodes[][];
	int LL = zei_Ids.crink; // 18
	public int Meto = 0;
	Random rand;
	int trunkSize;
	World worldObj;

	public zei_WorldGenBigFakeTree() {
		rand = new Random();
		heightLimit = 20;
		heightAttenuation = 0.3d; // 0.61799999999999999D;
		field_875_h = 2.0D;
		field_874_i = 0.8100000000000001D;
		field_873_j = 2.0D;
		field_872_k = 2.0D;
		trunkSize = 2;
		heightLimitLimit = 10;
		leafDistanceLimit = 7;
	}

	int checkBlockLine(int ai[], int ai1[]) {
		int ai2[] = { 0, 0, 0 };
		byte byte0 = 0;
		int i = 0;
		for (; byte0 < 3; byte0++) {
			ai2[byte0] = ai1[byte0] - ai[byte0];
			if (Math.abs(ai2[byte0]) > Math.abs(ai2[i])) {
				i = byte0;
			}
		}
		if (ai2[i] == 0) {
			return -1;
		}
		byte byte1 = otherCoordPairs[i];
		byte byte2 = otherCoordPairs[i + 3];
		byte byte3;
		if (ai2[i] > 0) {
			byte3 = 1;
		} else {
			byte3 = -1;
		}
		double d = (double) ai2[byte1] / (double) ai2[i];
		double d1 = (double) ai2[byte2] / (double) ai2[i];
		int ai3[] = { 0, 0, 0 };
		int j = 0;
		int k = ai2[i] + byte3;
		do {
			if (j == k) {
				break;
			}
			ai3[i] = ai[i] + j;
			ai3[byte1] = MathHelper.floor_double(ai[byte1] + j * d);
			ai3[byte2] = MathHelper.floor_double(ai[byte2] + j * d1);
			int l = worldObj.getBlockId(ai3[0], ai3[1], ai3[2]);
			if (l != 0 && l != LL) {
				break;
			}
			j += byte3;
		} while (true);
		if (j == k) {
			return -1;
		} else {
			return Math.abs(j);
		}
	}

	public void func_517_a(double d, double d1, double d2) {
		heightLimitLimit = (int) (d * 12D);
		if (d > 0.5D) {
			leafDistanceLimit = 5;
		}
		field_873_j = d1;
		field_872_k = d2;
	}

	void func_523_a(int i, int j, int k, float f, byte byte0, int l) {
		int i1 = (int) (f + 0.61799999999999999D);
		byte byte1 = otherCoordPairs[byte0];
		byte byte2 = otherCoordPairs[byte0 + 3];
		int ai[] = { i, j, k };
		int ai1[] = { 0, 0, 0 };
		int j1 = -i1;
		int k1 = -i1;
		ai1[byte0] = ai[byte0];
		for (; j1 <= i1; j1++) {
			ai1[byte1] = ai[byte1] + j1;
			for (int l1 = -i1; l1 <= i1;) {
				double d = Math.sqrt(Math.pow(Math.abs(j1) + 0.5D, 2D) + Math.pow(Math.abs(l1) + 0.5D, 2D));
				if (d > f) {
					l1++;
				} else {
					ai1[byte2] = ai[byte2] + l1;
					int i2 = worldObj.getBlockId(ai1[0], ai1[1], ai1[2]);
					if (i2 != 0 && i2 != LL) {
						l1++;
					} else {
						worldObj.setBlock(ai1[0], ai1[1], ai1[2], l, Meto, 3);
						l1++;
					}
				}
			}
		}
	}

	float func_526_b(int i) {
		if (i < 0 || i >= leafDistanceLimit) {
			return -1F;
		}
		return i != 0 && i != leafDistanceLimit - 1 ? 3F : 2.0F;
	}

	float func_528_a(int i) {
		if (i < heightLimit * 0.29999999999999999D) {
			return -1.618F;
		}
		float f = heightLimit / 2.0F;
		float f1 = heightLimit / 2.0F - i;
		float f2;
		if (f1 == 0.0F) {
			f2 = f;
		} else if (Math.abs(f1) >= f) {
			f2 = 0.0F;
		} else {
			f2 = (float) Math.sqrt(Math.pow(Math.abs(f), 2D) - Math.pow(Math.abs(f1), 2D));
		}
		f2 *= 0.5F;
		return f2;
	}

	@Override
	public boolean generate(World world, Random random, int i, int j, int k) {
		Meto = random.nextInt(6);
		worldObj = world;
		long l = random.nextLong();
		rand.setSeed(l);
		basePos[0] = i;
		basePos[1] = j;
		basePos[2] = k;
		if (heightLimit == 0) {
			heightLimit = 5 + rand.nextInt(heightLimitLimit);
		}
		if (!validTreeLocation()) {
			return false;
		} else {
			generateLeafNodeList();
			generateLeaves();
			generateTrunk();
			generateLeafNodeBases();
			if (boo) {
				int W = 7; // M*M;
				i = (i / W) * W;
				k = (k / W) * W;
				int bbb = world.getBlockId(i, zei_Ids.builderLevel, k);
				if (bbb != zei_Ids.tech && bbb != zei_Ids.importantBuildingThingy) {
					world.setBlock(i, zei_Ids.builderLevel, k, zei_Ids.importantBuildingThingy, random.nextInt(2), 3);
				}
			}
			return true;
		}
	}

	void generateLeafNode(int i, int j, int k) {
		int l = j;
		for (int i1 = j + leafDistanceLimit; l < i1; l++) {
			float f = func_526_b(l - j);
			func_523_a(i, l, k, f, (byte) 1, LL);
		}
	}

	void generateLeafNodeBases() {
		int i = 0;
		int j = leafNodes.length;
		int ai[] = { basePos[0], basePos[1], basePos[2] };
		for (; i < j; i++) {
			int ai1[] = leafNodes[i];
			int ai2[] = { ai1[0], ai1[1], ai1[2] };
			ai[1] = ai1[3];
			int k = ai[1] - basePos[1];
			if (leafNodeNeedsBase(k)) {
				placeBlockLine(ai, ai2, GG);
			}
		}
	}

	void generateLeafNodeList() {
		height = (int) (heightLimit * heightAttenuation);
		if (height >= heightLimit) {
			height = heightLimit - 1;
		}
		int i = (int) (1.3819999999999999D + Math.pow((field_872_k * heightLimit) / 13D, 2D));
		if (i < 1) {
			i = 1;
		}
		int ai[][] = new int[i * heightLimit][4];
		int j = (basePos[1] + heightLimit) - leafDistanceLimit;
		int k = 1;
		int l = basePos[1] + height;
		int i1 = j - basePos[1];
		ai[0][0] = basePos[0];
		ai[0][1] = j;
		ai[0][2] = basePos[2];
		ai[0][3] = l;
		j--;
		while (i1 >= 0) {
			int j1 = 0;
			float f = func_528_a(i1);
			if (f < 0.0F) {
				j--;
				i1--;
			} else {
				double d = 0.5D;
				for (; j1 < i; j1++) {
					double d1 = field_873_j * (f * (rand.nextFloat() + 0.32800000000000001D));
					double d2 = rand.nextFloat() * 2D * Math.PI;
					int k1 = MathHelper.floor_double(d1 * Math.sin(d2) + basePos[0] + d);
					int l1 = MathHelper.floor_double(d1 * Math.cos(d2) + basePos[2] + d);
					int ai1[] = { k1, j, l1 };
					int ai2[] = { k1, j + leafDistanceLimit, l1 };
					if (checkBlockLine(ai1, ai2) != -1) {
						continue;
					}
					int ai3[] = { basePos[0], basePos[1], basePos[2] };
					double d3 = Math.sqrt(Math.pow(Math.abs(basePos[0] - ai1[0]), 2D) + Math.pow(Math.abs(basePos[2] - ai1[2]), 2D));
					double d4 = d3 * field_874_i;
					if (ai1[1] - d4 > l) {
						ai3[1] = l;
					} else {
						ai3[1] = (int) (ai1[1] - d4);
					}
					if (checkBlockLine(ai3, ai1) == -1) {
						ai[k][0] = k1;
						ai[k][1] = j;
						ai[k][2] = l1;
						ai[k][3] = ai3[1];
						k++;
					}
				}
				j--;
				i1--;
			}
		}
		leafNodes = new int[k][4];
		System.arraycopy(ai, 0, leafNodes, 0, k);
	}

	void generateLeaves() {
		int i = 0;
		for (int j = leafNodes.length; i < j; i++) {
			int k = leafNodes[i][0];
			int l = leafNodes[i][1];
			int i1 = leafNodes[i][2];
			generateLeafNode(k, l, i1);
		}
	}

	void generateTrunk() {
		int i = basePos[0];
		int j = basePos[1];
		int k = basePos[1] + height;
		int l = basePos[2];
		int ai[] = { i, j, l };
		int ai1[] = { i, k, l };
		placeBlockLine(ai, ai1, GG);
		if (trunkSize == 2) {
			ai[0]++;
			ai1[0]++;
			placeBlockLine(ai, ai1, GG);
			ai[2]++;
			ai1[2]++;
			placeBlockLine(ai, ai1, GG);
			ai[0]--;
			ai1[0]--;
			placeBlockLine(ai, ai1, GG);
			worldObj.setBlock(i, j - 1, l, zei_Ids.arch, 1, 3);
			worldObj.setBlock(i + 1, j - 1, l, zei_Ids.arch, 1, 3);
			worldObj.setBlock(i + 1, j - 1, l + 1, zei_Ids.arch, 1, 3);
			worldObj.setBlock(i, j - 1, l + 1, zei_Ids.arch, 1, 3);
		}
	}

	boolean leafNodeNeedsBase(int i) {
		return i >= heightLimit * 0.20000000000000001D;
	}

	void placeBlockLine(int ai[], int ai1[], int i) {
		int ai2[] = { 0, 0, 0 };
		byte byte0 = 0;
		int j = 0;
		for (; byte0 < 3; byte0++) {
			ai2[byte0] = ai1[byte0] - ai[byte0];
			if (Math.abs(ai2[byte0]) > Math.abs(ai2[j])) {
				j = byte0;
			}
		}
		if (ai2[j] == 0) {
			return;
		}
		byte byte1 = otherCoordPairs[j];
		byte byte2 = otherCoordPairs[j + 3];
		byte byte3;
		if (ai2[j] > 0) {
			byte3 = 1;
		} else {
			byte3 = -1;
		}
		double d = (double) ai2[byte1] / (double) ai2[j];
		double d1 = (double) ai2[byte2] / (double) ai2[j];
		int ai3[] = { 0, 0, 0 };
		int k = 0;
		for (int l = ai2[j] + byte3; k != l; k += byte3) {
			ai3[j] = MathHelper.floor_double(ai[j] + k + 0.5D);
			ai3[byte1] = MathHelper.floor_double(ai[byte1] + k * d + 0.5D);
			ai3[byte2] = MathHelper.floor_double(ai[byte2] + k * d1 + 0.5D);
			worldObj.setBlock(ai3[0], ai3[1], ai3[2], zei_Ids.tech, 1, 3);
		}
	}

	boolean validTreeLocation() {
		int ai[] = { basePos[0], basePos[1], basePos[2] };
		int ai1[] = { basePos[0], (basePos[1] + heightLimit) - 1, basePos[2] };
		int i = worldObj.getBlockId(basePos[0], basePos[1] - 1, basePos[2]);
		if (i != zei_Ids.arch && i != zei_Ids.arch2) {
			return false;
		}
		int j = checkBlockLine(ai, ai1);
		if (j == -1) {
			return true;
		}
		if (j < 6) {
			return false;
		} else {
			heightLimit = j;
			return true;
		}
	}
}
