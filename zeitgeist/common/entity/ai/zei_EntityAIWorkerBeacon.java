package zeitgeist.common.entity.ai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import zeitgeist.common.zei_BeaconManager;
import zeitgeist.common.entity.zei_EntityWorker;
import zeitgeist.common.tile.zei_TileEntityBeacon;

public class zei_EntityAIWorkerBeacon extends EntityAIBase {
	zei_EntityWorker bot;
	EntityPlayer owner;

	public zei_EntityAIWorkerBeacon(zei_EntityWorker ento) {
		bot = ento;
		// setMutexBits(5);
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	public boolean shouldExecute() {
		if (bot.getHome() == null) {
			owner = bot.reallyGetBotOwner();
			if (owner != null) {
				findBeacon(owner);
			}
			return false;
		}
		return true;
	}

	public boolean continueExecuting() {
		return false;
	}

	public void startExecuting() {
		super.startExecuting();
		beaconCheck();
	}

	void findBeacon(EntityPlayer playah) {
		zei_TileEntityBeacon beacon = zei_BeaconManager.getSelection(playah);
		if (beacon != null) {
			bot.setHome(beacon);
			bot.getLookHelper().setLookPosition(beacon.xCoord, beacon.yCoord,
					beacon.zCoord, 10f, bot.getVerticalFaceSpeed());
		}
	}

	public void beaconCheck() {
		if (bot.getHome().getSiren() == 1) {
			bot.setMode(1);
		} else if (bot.getHome().getSiren() == 2) {
			bot.setPosition(bot.hx, bot.hy, bot.hz);
			bot.Dropper();
		}
	}
}
