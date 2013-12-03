package zeitgeist.common.world.biome;

import java.util.Random;

import zeitgeist.common.entity.zei_EntityAncient;
import zeitgeist.common.entity.zei_EntityHelios;
import zeitgeist.common.entity.zei_EntitySlider;
import zeitgeist.common.entity.zei_EntityWatcher;
import zeitgeist.common.world.gen.zei_WorldGenDwelling;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenerator;

public class zei_BiomeGenCity extends BiomeGenBase {
	protected zei_BiomeGenCity(int par1) {
		super(par1);
		theBiomeDecorator.treesPerChunk = 2;
		theBiomeDecorator.flowersPerChunk = 1;
		theBiomeDecorator.grassPerChunk = 2;
		spawnableMonsterList.clear();
		spawnableCreatureList.clear();
		spawnableWaterCreatureList.clear();
		spawnableMonsterList.add(new SpawnListEntry(zei_EntitySlider.class, 50,
				1, 6));
		spawnableCreatureList.add(new SpawnListEntry(zei_EntityHelios.class,
				100, 1, 8));
		spawnableMonsterList.add(new SpawnListEntry(zei_EntityAncient.class, 1,
				1, 1));
		spawnableMonsterList.add(new SpawnListEntry(zei_EntityWatcher.class,
				50, 1, 3));
	}

	public WorldGenerator getRandomWorldGenForTrees(Random par1Random) {
		return new zei_WorldGenDwelling(); // WindMill
		// return worldGenBigTree;
	}
}
