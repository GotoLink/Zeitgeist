package zeitgeist.common.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class zei_TileEntityBeacon extends TileEntity {
	public int numeral = 0;
	public int mode = 1;

	// 0 nothing
	// 1 alert workers to follow owner
	// 2 teleport workers
	// 7 toter deactivation locator
	// 8 sentry deactivation locator
	// 9 worker deactivation locator
	public zei_TileEntityBeacon() {
		System.out.println("NEW BEACON" + numeral);
	}

	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);
		numeral = par1NBTTagCompound.getInteger("numeral");
		mode = par1NBTTagCompound.getInteger("mode");
	}

	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setInteger("numeral", numeral);
		par1NBTTagCompound.setInteger("mode", mode);
	}

	public int getSiren() {
		// TODO Auto-generated method stub
		return mode;
	}
}
