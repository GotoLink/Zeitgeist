package zeitgeist.common.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class zei_TileEntityTexBlock extends TileEntity {
	public int indo = -1;
	public int meto = -1;

	public zei_TileEntityTexBlock() {
	}

	public int findMeta() {
		meto = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
		return meto;
	}

	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);
		indo = par1NBTTagCompound.getInteger("Num");
	}

	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setInteger("Num", indo);
	}
}
