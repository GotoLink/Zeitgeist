package zeitgeist.common.world;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.storage.ISaveHandler;

public class zei_PseudoWorld extends World {
	public zei_PseudoWorld(ISaveHandler par1iSaveHandler, String par2Str,
			WorldProvider par3WorldProvider, WorldSettings par4WorldSettings) {
		super(par1iSaveHandler, par2Str, par3WorldProvider, par4WorldSettings,
				theProfiler, worldLogAgent);
	}

	public WorldChunkManager getWorldChunkManager() {
		return this.provider.worldChunkMgr;
	}

	@Override
	public BiomeGenBase getBiomeGenForCoords(int par1, int par2) {
		return BiomeGenBase.forest;
	}

	@Override
	protected IChunkProvider createChunkProvider() {
		return new zei_ChunkProviderPsuedo(this);// var1,
													// this.worldProvider.getChunkProvider());
	}

	protected void generateSpawnPoint() {
	}

	public ChunkCoordinates getEntrancePortalLocation() {
		return new ChunkCoordinates(0, 0, 0);
	}

	@Override
	public void setSpawnLocation() {
	}

	public void spawnPlayerWithLoadedChunks(EntityPlayer par1EntityPlayer) {
	}

	public void saveWorld(boolean par1, IProgressUpdate par2IProgressUpdate) {
	}

	public boolean quickSaveWorld(int par1) {
		return false;
	}

	@Override
	public Entity getEntityByID(int i) {
		// TODO Auto-generated method stub
		return null;
	}
}
