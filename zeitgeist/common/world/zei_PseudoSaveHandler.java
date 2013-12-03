package zeitgeist.common.world;

import java.io.File;
import java.util.List;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.storage.IChunkLoader;
import net.minecraft.world.storage.IPlayerFileData;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.WorldInfo;

public class zei_PseudoSaveHandler implements ISaveHandler {
	@Override
	public WorldInfo loadWorldInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void checkSessionLock() {
		// TODO Auto-generated method stub
	}

	@Override
	public IChunkLoader getChunkLoader(WorldProvider var1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveWorldInfo(WorldInfo var1) {
		// TODO Auto-generated method stub
	}

	@Override
	public File getMapFileFromName(String var1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveWorldInfoWithPlayer(WorldInfo worldinfo,
			NBTTagCompound nbttagcompound) {
		// TODO Auto-generated method stub
	}

	@Override
	public IPlayerFileData getSaveHandler() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
	}

	@Override
	public String getWorldDirectoryName() {
		// TODO Auto-generated method stub
		return null;
	}
}
