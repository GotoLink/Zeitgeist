package zeitgeist.common;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet;

public class zei_Packet133UpdatePicture extends Packet {
	public byte itemData[];
	public short itemID;
	/**
	 * Contains a unique ID for the item that this packet will be populating.
	 */
	public String name;

	public zei_Packet133UpdatePicture() {
		itemData = new byte[256];
		isChunkDataPacket = true;
	}

	public zei_Packet133UpdatePicture(String n, byte[] ar) {
		name = n;
		itemData = ar;
		isChunkDataPacket = true;
	}

	/**
	 * Abstract. Return the size of the packet (not counting the header).
	 */
	@Override
	public int getPacketSize() {
		return 4 + itemData.length; // +name.length()
	}

	/**
	 * Passes this Packet on to the NetHandler for processing.
	 */
	@Override
	public void processPacket(NetHandler par1NetHandler) {
		zei_PictureData data = (zei_PictureData) mod_Zeitgeist.proxy.getAWorld().loadItemData(zei_PictureData.class, "texture_" + itemID);
		data.colors = itemData;
	}

	/**
	 * Abstract. Reads the raw packet data from the data stream.
	 */
	@Override
	public void readPacketData(DataInput par1DataInputStream) throws IOException {
		itemID = par1DataInputStream.readShort();
		// name=readString(par1DataInputStream, 15);
		itemData = new byte[par1DataInputStream.readByte() & 0xff];
		par1DataInputStream.readFully(itemData);
	}

	/**
	 * Abstract. Writes the raw packet data to the data stream.
	 */
	@Override
	public void writePacketData(DataOutput par1DataOutputStream) throws IOException {
		par1DataOutputStream.writeShort(itemID);
		// writeString(name, par1DataOutputStream);
		par1DataOutputStream.writeByte(itemData.length);
		par1DataOutputStream.write(itemData);
	}
}
