package zeitgeist.common.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class zei_TileEntityTexBlock2 extends TileEntity {
	private int id1 = -1;
	private int id2 = -1;
	private int id3 = -1;
	private int id4 = -1;
	private int id5 = -1;
	private int id6 = -1;
	private boolean unused = true;

	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);
		id1 = par1NBTTagCompound.getInteger("ID1");
		id2 = par1NBTTagCompound.getInteger("ID2");
		id3 = par1NBTTagCompound.getInteger("ID3");
		id4 = par1NBTTagCompound.getInteger("ID4");
		id5 = par1NBTTagCompound.getInteger("ID5");
		id6 = par1NBTTagCompound.getInteger("ID6");
		unused = id1 == -1 && id2 == -1 && id3 == -1 && id4 == -1 && id5 == -1
				&& id6 == -1;
	}

	/**
	 * Writes a tile entity to NBT.
	 */
	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setInteger("ID1", id1);
		par1NBTTagCompound.setInteger("ID2", id2);
		par1NBTTagCompound.setInteger("ID3", id3);
		par1NBTTagCompound.setInteger("ID4", id4);
		par1NBTTagCompound.setInteger("ID5", id5);
		par1NBTTagCompound.setInteger("ID6", id6);
	}

	public int getId1() {
		return id1;
	}

	public void setId1(int id1) {
		unused = false;
		this.id1 = id1;
	}

	public int getId2() {
		return id2;
	}

	public void setId2(int id2) {
		unused = false;
		this.id2 = id2;
	}

	public int getId3() {
		return id3;
	}

	public void setId3(int id3) {
		unused = false;
		this.id3 = id3;
	}

	public int getId4() {
		return id4;
	}

	public void setId4(int id4) {
		unused = false;
		this.id4 = id4;
	}

	public int getId5() {
		return id5;
	}

	public void setId5(int id5) {
		unused = false;
		this.id5 = id5;
	}

	public int getId6() {
		return id6;
	}

	public void setId6(int id6) {
		unused = false;
		this.id6 = id6;
	}

	public zei_TileEntityTexBlock2() {
	}

	public boolean isUnused() {
		return unused;
	}
}
