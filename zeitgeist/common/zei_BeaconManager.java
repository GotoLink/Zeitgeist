package zeitgeist.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import zeitgeist.common.tile.zei_TileEntityBeacon;

public class zei_BeaconManager {
	public static int addBeacon(World world, int x, int y, int z) {
		int ii = core(1, world);
		String s = "beacon_" + ii;
		zei_BeaconData data = new zei_BeaconData(s, x, y, z);
		data.markDirty();
		world.setItemData(s, data);
		return ii;
	}

	public static void alertPlayerOnBots(World world, EntityPlayer player) {
		int count = beaconSize(world);
		for (int i = 1; i <= count; i++) {
			zei_BeaconData data = (zei_BeaconData) world.loadItemData(zei_BeaconData.class, "beacon_" + i);
			int id = world.getBlockId(data.x, data.y, data.z);
			System.out.println("t" + i);
			if (id == zei_Ids.workerBlock) {
				player.addChatMessage("Worker at: " + data.x + "," + data.y + "," + data.z);
			} else if (id == zei_Ids.sentryBlock) {
				player.addChatMessage("Sentry at: " + data.x + "," + data.y + "," + data.z);
			} else if (id == zei_Ids.toteBlock) {
				player.addChatMessage("Toter at: " + data.x + "," + data.y + "," + data.z);
			} else if (id != zei_Ids.beaconBlock) {
				removeBeacon(world, i);
				count--;
			}
		}
	}

	public static int beaconSize(World world) {
		zei_BeaconDataCore core = (zei_BeaconDataCore) world.loadItemData(zei_BeaconDataCore.class, "BeaconCore");
		if (core != null) {
			return core.cap;
		} else {
			return 0;
		}
	}

	public static void confirm(World world) {
		int count = core(0, world);
		for (int i = 1; i <= count; i++) {
			zei_BeaconData data = (zei_BeaconData) world.loadItemData(zei_BeaconData.class, "beacon_" + i);
			int id = world.getBlockId(data.x, data.y, data.z);
			if (!okay(id)) {
				removeBeacon(world, i);
				count--;
			}
		}
	}

	public static int core(int n, World world) {
		zei_BeaconDataCore core = (zei_BeaconDataCore) world.loadItemData(zei_BeaconDataCore.class, "BeaconCore");
		int out = 1;
		if (core == null) {
			int nn = 1;
			if (n > 1) {
				nn = n;
				out = n;
			}
			core = new zei_BeaconDataCore("BeaconCore", nn);
		} else {
			core.cap += n;
			if (core.cap < 0)
				core.cap = 0;
			out = core.cap;
		}
		core.markDirty();
		world.setItemData("BeaconCore", core);
		return out;
	}

	public static zei_TileEntityBeacon getSelection(EntityPlayer ep) {
		String s = "SBeacon_" + ep.username;
		zei_BeaconDataCore core = (zei_BeaconDataCore) ep.worldObj.loadItemData(zei_BeaconDataCore.class, s);
		if (core == null)
			return null;
		zei_BeaconData beacon = (zei_BeaconData) ep.worldObj.loadItemData(zei_BeaconData.class, "beacon_" + core.cap);
		if (beacon == null)
			return null;
		zei_TileEntityBeacon tile = (zei_TileEntityBeacon) ep.worldObj.getBlockTileEntity(beacon.x, beacon.y, beacon.z);
		return tile;
	}

	public static void guessSelect(EntityPlayer ep) {
		int y = nearest(ep.worldObj, ep.posX, ep.posY, ep.posZ, 16);
		if (y != 0) {
			String s = "SBeacon_" + ep.username;
			zei_BeaconDataCore select = new zei_BeaconDataCore(s, y);
			select.markDirty();
			ep.worldObj.setItemData(s, select);
		}
	}

	public static int nearest(World world, double x, double y, double z, double distance) {
		return nearest(world, (int) x, (int) y, (int) z, distance);
	}

	public static int nearest(World world, int x, int y, int z, double distance) {
		int count = core(0, world);
		int id = 0;
		for (int i = 1; i <= count; i++) {
			zei_BeaconData data = (zei_BeaconData) world.loadItemData(zei_BeaconData.class, "beacon_" + i);
			int dx = x - data.x;
			int dy = y - data.y;
			int dz = z - data.z;
			double dis = Math.sqrt(dx * dx + dy * dy + dz * dz);
			if (dis < distance) {
				distance = dis;
				id = i;
			}
		}
		return id;
	}

	public static boolean okay(int i) {
		return i == zei_Ids.workerBlock || i == zei_Ids.beaconBlock || i == zei_Ids.sentryBlock || i == zei_Ids.toteBlock;
	}

	public static void printAll(World world) {
		int count = core(0, world);
		for (int i = 1; i <= count; i++) {
			zei_BeaconData data = (zei_BeaconData) world.loadItemData(zei_BeaconData.class, "beacon_" + i);
			System.out.println(data.toString());
		}
	}

	public static void removeBeacon(World world, int id) {
		int top = core(-1, world) + 1;
		if (top != id) {
			zei_BeaconData data = (zei_BeaconData) world.loadItemData(zei_BeaconData.class, "beacon_" + top);
			zei_BeaconData newer = new zei_BeaconData("beacon_" + id, data.x, data.y, data.z);
			newer.markDirty();
			world.setItemData("beacon_" + id, newer);
		}
	}

	public static void select(EntityPlayer ep, int i) {
		String s = "SBeacon_" + ep.username;
		zei_BeaconDataCore select = new zei_BeaconDataCore(s, i);
		select.markDirty();
		ep.worldObj.setItemData(s, select);
	}
}
