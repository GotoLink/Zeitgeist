package zeitgeist.common.world.gen;

import net.minecraft.world.WorldType;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerAddIsland;
import net.minecraft.world.gen.layer.GenLayerAddMushroomIsland;
import net.minecraft.world.gen.layer.GenLayerAddSnow;
import net.minecraft.world.gen.layer.GenLayerFuzzyZoom;
import net.minecraft.world.gen.layer.GenLayerHills;
import net.minecraft.world.gen.layer.GenLayerIsland;
import net.minecraft.world.gen.layer.GenLayerRiver;
import net.minecraft.world.gen.layer.GenLayerRiverInit;
import net.minecraft.world.gen.layer.GenLayerRiverMix;
import net.minecraft.world.gen.layer.GenLayerShore;
import net.minecraft.world.gen.layer.GenLayerSmooth;
import net.minecraft.world.gen.layer.GenLayerSwampRivers;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayerZoom;

public class zei_GenLayerZeitgeist extends GenLayer {
	public static GenLayer[] THINGY(long par0, WorldType par2WorldType) {
		GenLayer obj = new GenLayerIsland(1L);
		obj = new GenLayerFuzzyZoom(2000L, ((obj)));
		obj = new GenLayerAddIsland(1L, ((obj)));
		obj = new GenLayerZoom(2001L, ((obj)));
		obj = new GenLayerAddIsland(2L, ((obj)));
		obj = new GenLayerAddSnow(2L, ((obj)));
		obj = new GenLayerZoom(2002L, ((obj)));
		obj = new GenLayerAddIsland(3L, ((obj)));
		obj = new GenLayerZoom(2003L, ((obj)));
		obj = new GenLayerAddIsland(4L, ((obj)));
		obj = new GenLayerAddMushroomIsland(5L, ((obj)));
		byte byte0 = 4;
		GenLayer obj1 = obj;
		obj1 = GenLayerZoom.magnify(1000L, ((obj1)), 0);
		obj1 = new GenLayerRiverInit(100L, ((obj1)));
		obj1 = GenLayerZoom.magnify(1000L, ((obj1)), byte0 + 2);
		obj1 = new GenLayerRiver(1L, ((obj1)));
		obj1 = new GenLayerSmooth(1000L, ((obj1)));
		GenLayer obj2 = obj;
		obj2 = GenLayerZoom.magnify(1000L, ((obj2)), 0);
		obj2 = new zei_GenLayerBiomeZeitgeist(200L, ((obj2)), par2WorldType);
		obj2 = GenLayerZoom.magnify(1000L, ((obj2)), 2);
		obj2 = new GenLayerHills(1000L, ((obj2)));
		for (int i = 0; i < byte0; i++) {
			obj2 = new GenLayerZoom(1000 + i, ((obj2)));
			if (i == 0) {
				obj2 = new GenLayerAddIsland(3L, ((obj2)));
			}
			if (i == 1) {
				obj2 = new GenLayerShore(1000L, ((obj2)));
			}
			if (i == 1) {
				obj2 = new GenLayerSwampRivers(1000L, ((obj2)));
			}
		}
		obj2 = new GenLayerSmooth(1000L, ((obj2)));
		obj2 = new GenLayerRiverMix(100L, ((obj2)), ((obj1)));
		GenLayerRiverMix genlayerrivermix = ((GenLayerRiverMix) (obj2));
		GenLayerVoronoiZoom genlayervoronoizoom = new GenLayerVoronoiZoom(10L, ((obj2)));
		(obj2).initWorldGenSeed(par0);
		genlayervoronoizoom.initWorldGenSeed(par0);
		return (new GenLayer[] { obj2, genlayervoronoizoom, genlayerrivermix });
	}

	private long baseSeed;
	private long chunkSeed;
	protected zei_GenLayerZeitgeist parent;
	private long worldGenSeed;

	public zei_GenLayerZeitgeist(long par1) {
		super(par1);
		/*
		 * baseSeed = par1; baseSeed *= baseSeed * 0x5851f42d4c957f2dL +
		 * 0x14057b7ef767814fL; baseSeed += par1; baseSeed *= baseSeed *
		 * 0x5851f42d4c957f2dL + 0x14057b7ef767814fL; baseSeed += par1; baseSeed
		 * *= baseSeed * 0x5851f42d4c957f2dL + 0x14057b7ef767814fL; baseSeed +=
		 * par1;
		 */
	}

	@Override
	public int[] getInts(int i, int j, int k, int l) {
		// TODO Auto-generated method stub
		return new int[] { 0, 0, 0 };
	}

	@Override
	public void initChunkSeed(long par1, long par3) {
		chunkSeed = worldGenSeed;
		chunkSeed *= chunkSeed * 0x5851f42d4c957f2dL + 0x14057b7ef767814fL;
		chunkSeed += par1;
		chunkSeed *= chunkSeed * 0x5851f42d4c957f2dL + 0x14057b7ef767814fL;
		chunkSeed += par3;
		chunkSeed *= chunkSeed * 0x5851f42d4c957f2dL + 0x14057b7ef767814fL;
		chunkSeed += par1;
		chunkSeed *= chunkSeed * 0x5851f42d4c957f2dL + 0x14057b7ef767814fL;
		chunkSeed += par3;
	}

	@Override
	public void initWorldGenSeed(long par1) {
		worldGenSeed = par1;
		if (parent != null) {
			parent.initWorldGenSeed(par1);
		}
		worldGenSeed *= worldGenSeed * 0x5851f42d4c957f2dL + 0x14057b7ef767814fL;
		worldGenSeed += baseSeed;
		worldGenSeed *= worldGenSeed * 0x5851f42d4c957f2dL + 0x14057b7ef767814fL;
		worldGenSeed += baseSeed;
		worldGenSeed *= worldGenSeed * 0x5851f42d4c957f2dL + 0x14057b7ef767814fL;
		worldGenSeed += baseSeed;
	}

	@Override
	protected int nextInt(int par1) {
		int i = (int) ((chunkSeed >> 24) % par1);
		if (i < 0) {
			i += par1;
		}
		chunkSeed *= chunkSeed * 0x5851f42d4c957f2dL + 0x14057b7ef767814fL;
		chunkSeed += worldGenSeed;
		return i;
	}
}
