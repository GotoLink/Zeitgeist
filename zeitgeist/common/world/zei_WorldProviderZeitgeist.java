package zeitgeist.common.world;

import net.minecraft.block.Block;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import zeitgeist.common.world.biome.zei_BiomeGenAutumn;
import zeitgeist.common.world.biome.zei_WorldChunkManagerZeitgeist;

public class zei_WorldProviderZeitgeist extends WorldProvider {
	public static final BiomeGenBase giants = (new zei_BiomeGenAutumn(42)).setColor(0xff0000).setBiomeName("Zei");

	public zei_WorldProviderZeitgeist() {
	}

	/**
	 * Calculates the angle of sun and moon in the sky relative to a specified
	 * time (usually worldTime)
	 */
	@Override
	public float calculateCelestialAngle(long par1, float par3) {
		return 2.0F;
	}

	/**
	 * Will check if the x, z position specified is alright to be set as the map
	 * spawn point
	 */
	@Override
	public boolean canCoordinateBeSpawn(int par1, int par2) {
		int i = worldObj.getFirstUncoveredBlock(par1, par2);
		if (i == 0) {
			return false;
		} else {
			return Block.blocksList[i].blockMaterial.blocksMovement();
		}
	}

	/**
	 * True if the player can respawn in this dimension (true = overworld, false
	 * = nether).
	 */
	@Override
	public boolean canRespawnHere() {
		return true;
	}

	@Override
	public int getAverageGroundLevel() {
		return 50;
	}

	/**
	 * Returns the chunk provider back for the world provider
	 */
	public IChunkProvider getChunkProvider() {
		return new zei_ChunkProviderZeitgeist(worldObj, worldObj.getSeed(), true);
	}

	/*
	 * public boolean func_48217_e() { return false; }
	 */
	/**
	 * the y level at which clouds are rendered.
	 */
	@Override
	public float getCloudHeight() {
		return 99F;
	}

	@Override
	public String getDepartMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDimensionName() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Gets the hard-coded portal location to use when entering this dimension
	 */
	@Override
	public ChunkCoordinates getEntrancePortalLocation() {
		return new ChunkCoordinates(100, 50, 0);
	}

	@Override
	public String getSaveFolder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getWelcomeMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Returns array with sunrise/sunset colors
	 */
	/*
	 * public float[] calcSunriseSunsetColors(float par1, float par2) { return
	 * null; }
	 */
	/**
	 * Return Vec3D with biome specific fog color
	 */
	/*
	 * public Vec3D getFogColor(float par1, float par2) { int i = 0x8080a0;
	 * float f = MathHelper.cos(par1 * (float)Math.PI * 2.0F) * 2.0F + 0.5F; if
	 * (f < 0.0F) { f = 0.0F; } if (f > 1.0F) { f = 1.0F; float f1 = 0.7529412F;
	 * float f2 = 0.8470588F; float f3 = 1.0F; return Vec3D.createVector(f1, f2,
	 * f3); }
	 */
	@Override
	public boolean isSkyColored() {
		return true;
	}

	/**
	 * creates a new world chunk manager for WorldProvider
	 */
	@Override
	public void registerWorldChunkManager() {
		worldChunkMgr = new zei_WorldChunkManagerZeitgeist(giants, worldObj);
		worldType = 99;
		hasNoSky = false;
	}
}
