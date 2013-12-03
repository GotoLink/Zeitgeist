package zeitgeist.common.world.biome;

import java.util.Random;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class zei_BiomeGenAutumn extends BiomeGenBase {
	public zei_BiomeGenAutumn(int par1) {
		super(par1);
		// spawnableCreatureList.add(new
		// SpawnListEntry(net.minecraft.src.EntityWolf.class, 5, 4, 4));
		theBiomeDecorator.treesPerChunk = 6;
		theBiomeDecorator.grassPerChunk = 10;
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random) {
		if (par1Random.nextInt(5) == 0) {
			return worldGeneratorBigTree;
		}
		if (par1Random.nextInt(10) == 0) {
			return worldGeneratorForest;
		} else {
			return worldGeneratorTrees;
		}
	}

	public int getBiomeGrassColor() {
		// double d = MathHelper.clamp_float(getFloatTemperature(), 0.0F, 1.0F);
		// double d1 = MathHelper.clamp_float(getFloatRainfall(), 0.0F, 1.0F);
		return 0xffb033;// ColorizerGrass.getGrassColor(d, d1);
	}

	public int getBiomeFoliageColor() {
		// double d = MathHelper.clamp_float(getFloatTemperature(), 0.0F, 1.0F);
		// double d1 = MathHelper.clamp_float(getFloatRainfall(), 0.0F, 1.0F);
		// 0xeba333
		// 0xab583b
		// 0xee5959
		// 0xeecf34
		float FF = rainfall * getFloatTemperature();
		return (int) (0xffffff * FF);// 0xfbb63c;// 0xffab33;//0xab583b
										// +((int)(0x40*FF ) |(int)(
										// 0x4a*FF));//ColorizerFoliage.getFoliageColor(d,
										// d1);
	}
}
