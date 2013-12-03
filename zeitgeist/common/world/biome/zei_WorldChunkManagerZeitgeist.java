package zeitgeist.common.world.biome;

import java.util.List;
import java.util.Random;

import zeitgeist.common.world.gen.zei_GenLayerZeitgeist;

import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class zei_WorldChunkManagerZeitgeist extends WorldChunkManager {
	/** The biome generator object. */
	private BiomeGenBase biomeGenerator;
	// private float hellTemperature;
	/** The rainfall in the world */
	// private float rainfall;
	private GenLayer genBiomes;
	private BiomeCache biomeCache;
	/** A GenLayer containing the indices into BiomeGenBase.biomeList[] */
	private GenLayer biomeIndexLayer;

	// BiomeGenBase[] biomes;
	/*
	 * ocean=0 plains=1 desert=2 extremeHills=3 forest=4 taiga=5 swampland=6
	 * river=7 hell=8 sky=9 frozenOcean=10 frozenRiver=11 icePlains=12
	 * iceMountains=13 mushroomIsland=14 mushroomIslandShore=15 beach=16
	 * desertHills = 17
	 * 
	 * forestHills =18 taigaHills = 19 extremeHillsEdge 20 jungle =21
	 * jungleHills = 22
	 */
	public zei_WorldChunkManagerZeitgeist(BiomeGenBase par1BiomeGenBase,
			long seed, WorldType type) {
		biomeGenerator = par1BiomeGenBase;
		/*
		 * biomes=new BiomeGenBase[23]; int k=0; for(int i=0;i<=22;i++){
		 * if(k==0){ biomes[i]=zei_Biomes.giants; k++; }else if(k==1){
		 * biomes[i]=BiomeGenBase.desert; k++; }else{
		 * biomes[i]=zei_Biomes.autumn; k=0; }
		 * 
		 * }
		 */
		biomeCache = new BiomeCache(this);
		GenLayer agenlayer[] = zei_GenLayerZeitgeist.THINGY(seed, type);
		genBiomes = agenlayer[0];
		biomeIndexLayer = agenlayer[1];
	}

	public zei_WorldChunkManagerZeitgeist(BiomeGenBase par1BiomeGenBase,
			World par1World) {
		this(par1BiomeGenBase, par1World.getSeed(), par1World.getWorldInfo()
				.getTerrainType());
	}

	/**
	 * Returns the BiomeGenBase related to the x, z position on the world.
	 */
	public BiomeGenBase getBiomeGenAt(int par1, int par2) {
		return biomeCache.getBiomeGenAt(par1, par2);
	}

	/**
	 * Returns an array of biomes for the location input.
	 */
	public BiomeGenBase[] getBiomesForGeneration(
			BiomeGenBase par1ArrayOfBiomeGenBase[], int par2, int par3,
			int par4, int par5) {
		if (par1ArrayOfBiomeGenBase == null
				|| par1ArrayOfBiomeGenBase.length < par4 * par5) {
			par1ArrayOfBiomeGenBase = new BiomeGenBase[par4 * par5];
		}
		int ai[] = genBiomes.getInts(par2, par3, par4, par5);
		for (int i = 0; i < par4 * par5; i++) {
			par1ArrayOfBiomeGenBase[i] = BiomeGenBase.biomeList[ai[i]];
		}
		return par1ArrayOfBiomeGenBase;
	}

	/**
	 * Returns a list of temperatures to use for the specified blocks. Args:
	 * listToReuse, x, y, width, length
	 */
	/*
	 * public float[] getTemperatures(float par1ArrayOfFloat[], int par2, int
	 * par3, int par4, int par5) { if (par1ArrayOfFloat == null ||
	 * par1ArrayOfFloat.length < par4 * par5) { par1ArrayOfFloat = new
	 * float[par4 * par5]; }
	 * 
	 * Arrays.fill(par1ArrayOfFloat, 0, par4 * par5, hellTemperature); return
	 * par1ArrayOfFloat; }
	 */
	public float[] getTemperatures(float par1ArrayOfFloat[], int par2,
			int par3, int par4, int par5) {
		IntCache.resetIntCache();
		if (par1ArrayOfFloat == null || par1ArrayOfFloat.length < par4 * par5) {
			par1ArrayOfFloat = new float[par4 * par5];
		}
		int ai[] = biomeIndexLayer.getInts(par2, par3, par4, par5);
		for (int i = 0; i < par4 * par5; i++) {
			System.out.println("temp " + ai[i]);
			float f = (float) BiomeGenBase.biomeList[ai[i]].getIntTemperature() / 65536F;
			if (f > 1.0F) {
				f = 1.0F;
			}
			par1ArrayOfFloat[i] = f;
		}
		return par1ArrayOfFloat;
	}

	/**
	 * Returns a list of rainfall values for the specified blocks. Args:
	 * listToReuse, x, z, width, length.
	 */
	/*
	 * public float[] getRainfall(float par1ArrayOfFloat[], int par2, int par3,
	 * int par4, int par5) { if (par1ArrayOfFloat == null ||
	 * par1ArrayOfFloat.length < par4 * par5) { par1ArrayOfFloat = new
	 * float[par4 * par5]; }
	 * 
	 * Arrays.fill(par1ArrayOfFloat, 0, par4 * par5, rainfall); return
	 * par1ArrayOfFloat; }
	 */
	public float[] getRainfall(float par1ArrayOfFloat[], int par2, int par3,
			int par4, int par5) {
		IntCache.resetIntCache();
		if (par1ArrayOfFloat == null || par1ArrayOfFloat.length < par4 * par5) {
			par1ArrayOfFloat = new float[par4 * par5];
		}
		int ai[] = biomeIndexLayer.getInts(par2, par3, par4, par5);
		for (int i = 0; i < par4 * par5; i++) {
			float f = (float) BiomeGenBase.biomeList[ai[i]].getIntRainfall() / 65536F;
			if (f > 1.0F) {
				f = 1.0F;
			}
			par1ArrayOfFloat[i] = f;
		}
		return par1ArrayOfFloat;
	}

	/**
	 * Returns biomes to use for the blocks and loads the other data like
	 * temperature and humidity onto the WorldChunkManager Args: oldBiomeList,
	 * x, z, width, depth
	 */
	/*
	 * public BiomeGenBase[] loadBlockGeneratorData(BiomeGenBase
	 * par1ArrayOfBiomeGenBase[], int par2, int par3, int par4, int par5) { if
	 * (par1ArrayOfBiomeGenBase == null || par1ArrayOfBiomeGenBase.length < par4
	 * * par5) { par1ArrayOfBiomeGenBase = new BiomeGenBase[par4 * par5]; }
	 * 
	 * Arrays.fill(par1ArrayOfBiomeGenBase, 0, par4 * par5, biomeGenerator);
	 * return par1ArrayOfBiomeGenBase; }
	 */
	public BiomeGenBase[] loadBlockGeneratorData(
			BiomeGenBase par1ArrayOfBiomeGenBase[], int par2, int par3,
			int par4, int par5) {
		return getBiomeGenAt(par1ArrayOfBiomeGenBase, par2, par3, par4, par5,
				true);
	}

	/**
	 * Finds a valid position within a range, that is once of the listed biomes.
	 */
	/*
	 * public ChunkPosition findBiomePosition(int par1, int par2, int par3, List
	 * par4List, Random par5Random) { if (par4List.contains(biomeGenerator)) {
	 * return new ChunkPosition((par1 - par3) + par5Random.nextInt(par3 * 2 +
	 * 1), 0, (par2 - par3) + par5Random.nextInt(par3 * 2 + 1)); } else { return
	 * null; } }
	 */
	public ChunkPosition findBiomePosition(int par1, int par2, int par3,
			List par4List, Random par5Random) {
		int i = par1 - par3 >> 2;
		int j = par2 - par3 >> 2;
		int k = par1 + par3 >> 2;
		int l = par2 + par3 >> 2;
		int i1 = (k - i) + 1;
		int j1 = (l - j) + 1;
		int ai[] = genBiomes.getInts(i, j, i1, j1);
		ChunkPosition chunkposition = null;
		int k1 = 0;
		for (int l1 = 0; l1 < ai.length; l1++) {
			int i2 = i + l1 % i1 << 2;
			int j2 = j + l1 / i1 << 2;
			BiomeGenBase biomegenbase = BiomeGenBase.biomeList[ai[l1]];
			if (par4List.contains(biomegenbase)
					&& (chunkposition == null || par5Random.nextInt(k1 + 1) == 0)) {
				chunkposition = new ChunkPosition(i2, 0, j2);
				k1++;
			}
		}
		return chunkposition;
	}

	/**
	 * checks given Chunk's Biomes against List of allowed ones
	 */
	/*
	 * public boolean areBiomesViable(int par1, int par2, int par3, List
	 * par4List) { return par4List.contains(biomeGenerator); }
	 */
	public boolean areBiomesViable(int par1, int par2, int par3, List par4List) {
		int i = par1 - par3 >> 2;
		int j = par2 - par3 >> 2;
		int k = par1 + par3 >> 2;
		int l = par2 + par3 >> 2;
		int i1 = (k - i) + 1;
		int j1 = (l - j) + 1;
		int ai[] = genBiomes.getInts(i, j, i1, j1);
		for (int k1 = 0; k1 < i1 * j1; k1++) {
			BiomeGenBase biomegenbase = BiomeGenBase.biomeList[ai[k1]]; //
			if (!par4List.contains(biomegenbase)) {
				return false;
			}
		}
		return true;
	}

	/*
	 * public BiomeGenBase[] getBiomeGenAt(BiomeGenBase
	 * par1ArrayOfBiomeGenBase[], int par2, int par3, int par4, int par5,
	 * boolean par6) { return loadBlockGeneratorData(par1ArrayOfBiomeGenBase,
	 * par2, par3, par4, par5); }
	 */
	/**
	 * Return a list of biomes for the specified blocks. Args: listToReuse, x,
	 * y, width, length, cacheFlag (if false, don't check biomeCache to avoid
	 * infinite loop in BiomeCacheBlock)
	 */
	public BiomeGenBase[] getBiomeGenAt(BiomeGenBase par1ArrayOfBiomeGenBase[],
			int par2, int par3, int par4, int par5, boolean par6) {
		IntCache.resetIntCache();
		if (par1ArrayOfBiomeGenBase == null
				|| par1ArrayOfBiomeGenBase.length < par4 * par5) {
			par1ArrayOfBiomeGenBase = new BiomeGenBase[par4 * par5];
		}
		if (par6 && par4 == 16 && par5 == 16 && (par2 & 0xf) == 0
				&& (par3 & 0xf) == 0) {
			BiomeGenBase abiomegenbase[] = biomeCache.getCachedBiomes(par2,
					par3);
			System.arraycopy(abiomegenbase, 0, par1ArrayOfBiomeGenBase, 0, par4
					* par5);
			return par1ArrayOfBiomeGenBase;
		}
		int ai[] = biomeIndexLayer.getInts(par2, par3, par4, par5);
		for (int i = 0; i < par4 * par5; i++) {
			par1ArrayOfBiomeGenBase[i] = BiomeGenBase.biomeList[ai[i]];
		}
		return par1ArrayOfBiomeGenBase;
	}

	public void cleanupCache() {
		biomeCache.cleanupCache();
	}
}
