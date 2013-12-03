package zeitgeist.common;

import net.minecraft.entity.Entity;

public class zei_Quest {
	public zei_Quest(String questName, int trust, Entity target, int numKills,
			int collectID, int needed, String destination, Entity guideName,
			String saveGameName, Entity secondReceiver, int rewardxp) {
		name = questName;
		trustRequired = trust;
		killTarget = target;
		numKillsReq = numKills;
		itemToCollect = collectID;
		itemsNeeded = needed;
		courrier = destination;
		guide = guideName;
		saveName = saveGameName;
		separateReceiver = secondReceiver;
		rewardExp = rewardxp;
	}

	public int rewardExp;
	public String name;
	public int trustRequired;
	public Entity killTarget;
	public int numKillsReq;
	public int itemToCollect;
	public int itemsNeeded;
	public String courrier;
	public Entity guide;
	public String saveName;
	public Entity separateReceiver;
}
