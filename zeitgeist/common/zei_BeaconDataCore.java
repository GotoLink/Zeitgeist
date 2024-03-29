package zeitgeist.common;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldSavedData;

public class zei_BeaconDataCore extends WorldSavedData {
	int cap;

	public zei_BeaconDataCore(String par1Str) {
		super(par1Str);
	}

	public zei_BeaconDataCore(String par1Str, int cap) {
		super(par1Str);
		this.cap = cap;
	}

	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		cap = par1NBTTagCompound.getInteger("cap");
	}

	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		par1NBTTagCompound.setInteger("cap", cap);
	}
}
