package zeitgeist.common;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet;

public class zei_Packet233Quests extends Packet {
	public zei_Packet233Quests() {
	}

	public zei_Packet233Quests(int i, String s) {
		if (s.length() > 119) {
			s = s.substring(0, 119);
		}
		entityID = i;
		message = s;
	}

	public void readPacketData(DataInput datainputstream) throws IOException {
		entityID = datainputstream.readInt();
		message = readString(datainputstream, 119);
	}

	public void writePacketData(DataOutput dataoutputstream) throws IOException {
		dataoutputstream.writeInt(entityID);
		writeString(message, dataoutputstream);
	}

	public void processPacket(NetHandler nethandler) {
		// TODO QUESTS.
		// nethandler.handleQuests(this);
	}

	public int getPacketSize() {
		return 4 + message.length();
	}

	public String message;
	public int entityID;
}
