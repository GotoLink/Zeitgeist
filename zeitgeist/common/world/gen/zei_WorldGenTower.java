package zeitgeist.common.world.gen;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import zeitgeist.common.zei_Ids;

public class zei_WorldGenTower extends WorldGenerator {
	public zei_WorldGenTower() {
	}

	@Override
	public boolean generate(World world, Random random, int i, int j, int k) {
		int bb = world.getBlockId(i, j - 1, k);
		if (bb == 2 || bb == 12 || bb == zei_Ids.arch) {
			int h = random.nextInt(6) + 7;
			for (int n = 0; n < h; n++) {
				world.setBlock(i, j + n, k, 1);
			}
			// world.setBlockWithNotify(i,j+h,k,zei_Ids.glowy);
		}
		return true;
	}
}
