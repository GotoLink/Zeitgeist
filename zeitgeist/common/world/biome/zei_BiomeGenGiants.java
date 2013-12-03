package zeitgeist.common.world.biome;

import java.util.Random;

import zeitgeist.common.entity.zei_EntityMusicMaker;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenHugeTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

public class zei_BiomeGenGiants extends BiomeGenBase {
	public zei_BiomeGenGiants(int par1) {
		super(par1);
		spawnableMonsterList.clear();
		spawnableCreatureList.clear();
		spawnableWaterCreatureList.clear();
		spawnableCreatureList.add(new SpawnListEntry(
				zei_EntityMusicMaker.class, 10, 4, 4));
		// topBlock = (byte)Block.grass.blockID;
		// fillerBlock = (byte)Block.grass.blockID;
		// biomeDecorator = new zei_BiomeGiantsDecorator(this);
		theBiomeDecorator.treesPerChunk = 20;
	}

	public WorldGenerator getRandomWorldGenForTrees(Random par1Random) {
		return new WorldGenHugeTrees(false, 10 + par1Random.nextInt(80), 3, 3);
		// return worldGenBigTree;
	}

	public int getBiomeGrassColor() {
		// double d = MathHelper.clamp_float(getFloatTemperature(), 0.0F, 1.0F);
		// double d1 = MathHelper.clamp_float(getFloatRainfall(), 0.0F, 1.0F);
		return 0x969d3f;// ColorizerGrass.getGrassColor(d, d1);
	}

	public int getBiomeFoliageColor() {
		// double d = MathHelper.clamp_float(getFloatTemperature(), 0.0F, 1.0F);
		// double d1 = MathHelper.clamp_float(getFloatRainfall(), 0.0F, 1.0F);
		return 0x3e6803;// ColorizerFoliage.getFoliageColor(d, d1);
	}
	/**
	 * takes temperature, returns color
	 */
}
