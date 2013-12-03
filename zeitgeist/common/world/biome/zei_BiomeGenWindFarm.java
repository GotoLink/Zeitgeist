package zeitgeist.common.world.biome;

import java.util.Random;

import zeitgeist.common.world.gen.zei_WorldGenWindMill;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class zei_BiomeGenWindFarm extends BiomeGenBase {
	protected zei_BiomeGenWindFarm(int par1) {
		super(par1);
		theBiomeDecorator.treesPerChunk = 4;
		theBiomeDecorator.flowersPerChunk = 1;
		theBiomeDecorator.grassPerChunk = 10;
	}

	public WorldGenerator getRandomWorldGenForTrees(Random par1Random) {
		return new zei_WorldGenWindMill();
		// return worldGenBigTree;
	}
}
