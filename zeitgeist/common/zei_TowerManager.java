package zeitgeist.common;

import net.minecraft.world.World;

public class zei_TowerManager {
	public static void addTower(World world, int i, int j, int k, int floors) {
		int iii = world.getUniqueDataId("tower");
		String s = "tower_" + iii;
		zei_TowerData data = new zei_TowerData(s);
		data.floors = floors;
		data.x = i;
		data.y = j;
		data.z = k;
		data.id = iii;
		data.markDirty();
	}

	public static void compose() {
	}
}
