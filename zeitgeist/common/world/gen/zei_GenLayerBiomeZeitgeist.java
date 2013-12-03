package zeitgeist.common.world.gen;

import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import zeitgeist.common.world.biome.zei_Biomes;

public class zei_GenLayerBiomeZeitgeist extends GenLayer {
	public static BiomeGenBase biomeArray[];
	static {
		biomeArray = (new BiomeGenBase[] { zei_Biomes.autumn, zei_Biomes.giants, zei_Biomes.windFarm, zei_Biomes.city, BiomeGenBase.desert });
	}
	private BiomeGenBase allowedBiomes[];

	public zei_GenLayerBiomeZeitgeist(long par1, GenLayer par3GenLayer, WorldType par4WorldType) {
		super(par1);
		allowedBiomes = biomeArray;
		parent = par3GenLayer;
		if (par4WorldType == WorldType.DEFAULT_1_1) {
			allowedBiomes = (new BiomeGenBase[] { BiomeGenBase.desert, BiomeGenBase.forest, BiomeGenBase.extremeHills, BiomeGenBase.swampland, BiomeGenBase.plains, BiomeGenBase.taiga });
		}
	}

	@Override
	public int[] getInts(int par1, int par2, int par3, int par4) {
		int ai[] = parent.getInts(par1, par2, par3, par4);
		int ai1[] = IntCache.getIntCache(par3 * par4);
		for (int i = 0; i < par4; i++) {
			for (int j = 0; j < par3; j++) {
				initChunkSeed(j + par1, i + par2);
				int k = ai[j + i * par3];
				if (k == 0) {
					ai1[j + i * par3] = 0;
				} else if (k == BiomeGenBase.mushroomIsland.biomeID) {
					ai1[j + i * par3] = k;
				} else if (k == 1) {
					ai1[j + i * par3] = allowedBiomes[nextInt(allowedBiomes.length)].biomeID;
				} else {
					ai1[j + i * par3] = zei_Biomes.giants.biomeID;
				}
			}
		}
		return ai1;
	}
}
