package zeitgeist.common;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.world.WorldProvider;

//attempting to create a new dimension structures
public enum zei_EnumDimension {
	// wasteland("Wasteland", 1, GurWorldProviderWasteland.class,
	// GurTeleporterWasteland.class, "param"),
	// bots("Old Bot Test",9,AM_WorldProviderBot.class,AM_Teleporter.class,"idk?"),
	// hell("The nether but... nothing is different. LOL",-1,WorldProviderHell.class,Teleporter.class,"idk?"),
	// map1("Map1?",12,zei_WorldProviderMap.class,AM_Teleporter.class,""),
	// eupraxia("Eupraxia",57,zei_EupraxiaWorldProvider.class,zei_EupraxiaTeleporter.class,"");
	eupraxia("Eupraxia", 57, null, null, "");
	public static Map<Integer, Class> worldList = new HashMap<Integer, Class>();

	public static WorldProvider getWorld(int i) {
		WorldProvider w = null;
		Class c = worldList.get(i);
		if (c != null) {
			try {
				w = (WorldProvider) c.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		if (w == null) {
			if (i == -1) {
				return WorldProvider.getProviderForDimension(-1);
			}
			return WorldProvider.getProviderForDimension(0);
		}
		return w;
	}

	static void init() {
		zei_EnumDimension A[] = zei_EnumDimension.values();
		for (int i = 0; i < A.length; i++) {
			worldList.put(A[i].id, A[i].worldProvider);
		}
	}

	public final int id;
	public final String name;
	public final String param;
	public final Class teleporter;
	public final Class worldProvider;

	private zei_EnumDimension(String name, int id, Class worldProvider, Class teleporter, String param) {
		this.name = name;
		this.id = id;
		this.worldProvider = worldProvider;
		this.teleporter = teleporter;
		this.param = param;
	}
}
