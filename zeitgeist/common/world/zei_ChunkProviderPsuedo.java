package zeitgeist.common.world;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.structure.MapGenVillage;

public class zei_ChunkProviderPsuedo implements IChunkProvider {
	private World worldObj;
	private Random random;
	private MapGenVillage villageGen = new MapGenVillage();

	public zei_ChunkProviderPsuedo(World par1World) {
		this.worldObj = par1World;
		this.random = new Random();
	}

	private void generate(byte[] par1ArrayOfByte) {
		int var2 = par1ArrayOfByte.length / 256;
		for (int var3 = 0; var3 < 16; ++var3) {
			for (int var4 = 0; var4 < 16; ++var4) {
				for (int var5 = 0; var5 < var2; ++var5) {
					int var6 = 0;
					if (var5 == 0) {
						var6 = Block.bedrock.blockID;
					} else if (var5 <= 2) {
						var6 = Block.dirt.blockID;
					} else if (var5 == 3) {
						var6 = Block.grass.blockID;
					}
					par1ArrayOfByte[var3 << 11 | var4 << 7 | var5] = (byte) var6;
				}
			}
		}
	}

	/**
	 * loads or generates the chunk at the chunk location specified
	 */
	public Chunk loadChunk(int par1, int par2) {
		return this.provideChunk(par1, par2);
	}

	/**
	 * Will return back a chunk, if it doesn't exist and its not a MP client it
	 * will generates all the blocks for the specified chunk from the map seed
	 * and chunk seed
	 */
	public Chunk provideChunk(int par1, int par2) {
		byte[] var3 = new byte[32768];
		this.generate(var3);
		Chunk var4 = new Chunk(this.worldObj, var3, par1, par2);
		BiomeGenBase[] var5 = this.worldObj.getWorldChunkManager()
				.loadBlockGeneratorData((BiomeGenBase[]) null, par1 * 16,
						par2 * 16, 16, 16);
		byte[] var6 = var4.getBiomeArray();
		for (int var7 = 0; var7 < var6.length; ++var7) {
			var6[var7] = (byte) var5[var7].biomeID;
		}
		var4.generateSkylightMap();
		return var4;
	}

	/**
	 * Checks to see if a chunk exists at x, y
	 */
	public boolean chunkExists(int par1, int par2) {
		return true;
	}

	/**
	 * Populates chunk with ores etc etc
	 */
	public void populate(IChunkProvider par1IChunkProvider, int par2, int par3) {
		this.random.setSeed(this.worldObj.getSeed());
		long var4 = this.random.nextLong() / 2L * 2L + 1L;
		long var6 = this.random.nextLong() / 2L * 2L + 1L;
		this.random.setSeed((long) par2 * var4 + (long) par3 * var6
				^ this.worldObj.getSeed());
	}

	/**
	 * Two modes of operation: if passed true, save all Chunks in one go. If
	 * passed false, save up to two chunks. Return true if all chunks have been
	 * saved.
	 */
	public boolean saveChunks(boolean par1, IProgressUpdate par2IProgressUpdate) {
		return true;
	}

	/**
	 * Unloads the 100 oldest chunks from memory, due to a bug with
	 * chunkSet.add() never being called it thinks the list is always empty and
	 * will not remove any chunks.
	 */
	public boolean unload100OldestChunks() {
		return false;
	}

	/**
	 * Returns if the IChunkProvider supports saving.
	 */
	public boolean canSave() {
		return true;
	}

	/**
	 * Converts the instance data to a readable string.
	 */
	public String makeString() {
		return "FlatLevelSource";
	}

	/**
	 * Returns a list of creatures of the specified type that can spawn at the
	 * given location.
	 */
	public List getPossibleCreatures(EnumCreatureType par1EnumCreatureType,
			int par2, int par3, int par4) {
		BiomeGenBase var5 = this.worldObj.getBiomeGenForCoords(par2, par4);
		return var5 == null ? null : var5
				.getSpawnableList(par1EnumCreatureType);
	}

	/**
	 * Returns the location of the closest structure of the specified type. If
	 * not found returns null.
	 */
	public ChunkPosition findClosestStructure(World par1World, String par2Str,
			int par3, int par4, int par5) {
		return null;
	}

	@Override
	public boolean unloadQueuedChunks() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getLoadedChunkCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void recreateStructures(int i, int j) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveExtraData() {
		// TODO Auto-generated method stub
		
	}
}
