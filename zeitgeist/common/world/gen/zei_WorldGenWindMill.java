package zeitgeist.common.world.gen;

import java.util.Random;

import zeitgeist.common.zei_Ids;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class zei_WorldGenWindMill extends WorldGenerator {
	int MAX = 12;

	public boolean generate(World world, Random random, int i, int j, int k) {
		// world.setBlockAndMetadataWithNotify(i, j, k, zei_Ids.grower, 1);
		path(0, world, random, i, j, k);
		return true;
	}

	public void path(int f, World world, Random random, int i, int j, int k) {
		if (f >= MAX) {
			return;
		}
		int size = random.nextInt(MAX - f) + 3;
		int n = 0;
		for (n = 0; n < size - 1; n++) {
			if (n < size - 2) {
				world.setBlock(i - 1, j + n, k, 4);
				world.setBlock(i + 1, j + n, k, 4);
				world.setBlock(i, j + n, k - 1, 4);
				world.setBlock(i, j + n, k + 1, 4);
			}
			world.setBlock(i, j + n, k, zei_Ids.turnBlock, 8, 3);
			world.setBlock(i, j + n, k, 4);
		}
		world.setBlock(i, j + n - 1, k, zei_Ids.turnBlock, 8, 3);
		world.setBlock(i, j + n, k, zei_Ids.windmill, 2, 3);
	}
}
