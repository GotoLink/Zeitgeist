package zeitgeist.common.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;

public class zei_TileEntityStatue extends TileEntity {
	public int blok;
	public int turn;

	public zei_TileEntityStatue() {
		// this.turn=14;
	}

	@Override
	public Packet getDescriptionPacket() {
		Packet132TileEntityData pak = new Packet132TileEntityData(xCoord, yCoord, zCoord, 5, turn);
		pak.customParam2 = blok;
		return pak;
	}

	public void onDataPacket(NetworkManager net, Packet132TileEntityData pkt) {
		setTurn2(pkt.customParam1);
		setBlok2(pkt.customParam2);
	}

	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);
		turn = par1NBTTagCompound.getInteger("Turn");
		blok = par1NBTTagCompound.getInteger("Blok");
	}

	@Override
	public boolean receiveClientEvent(int id, int ar) {
		if (id == 1) {
			setBlok2(ar);
		} else if (id == 0) {
			setTurn2(ar);
		} else if (id == 5) {
		} else {
			worldObj.addBlockEvent(xCoord, yCoord, zCoord, 1, blok);
			worldObj.addBlockEvent(xCoord, yCoord, zCoord, 0, turn);
		}
	}

	public void setBlok2(int t) {
		this.blok = t;
	}

	public void setBlokSafe(int t) {
		setBlok2(t);
		worldObj.addBlockEvent(xCoord, yCoord, zCoord, 1, blok);
	}

	public void setTurn2(int t) {
		// System.out.println("o hai");
		this.turn = t;
	}

	public void setTurnSafe(int t) {
		// System.out.println("o hai");
		setTurn2(t);
		worldObj.addBlockEvent(xCoord, yCoord, zCoord, 0, turn);
	}

	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setInteger("Turn", turn);
		par1NBTTagCompound.setInteger("Blok", blok);
	}
}
