package zeitgeist.common.world.gen;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import zeitgeist.common.zei_Ids;

public class zei_WorldGenDerk extends WorldGenerator {
	public zei_WorldGenDerk() {
	}

	@Override
	public boolean generate(World world, Random random, int i, int j, int k) {
		int bb = world.getBlockId(i, j - 1, k);
		if (bb == zei_Ids.arch) {
			int h = random.nextInt(3) + 1;
			for (int n = 0; n < h; n++) {
				// world.setBlockAndMetadataWithNotify(i, j+n, k,
				// zei_Ids.grower,5);
			}
			world.setBlock(i, j + h, k, zei_Ids.crink, (7 + h - 1), 3);
		}
		return true;
	}
}
