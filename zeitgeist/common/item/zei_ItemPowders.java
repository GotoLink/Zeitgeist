package zeitgeist.common.item;

import net.minecraft.item.Item;

public class zei_ItemPowders extends Item {
	public zei_ItemPowders(int i) {
		super(i);
		setHasSubtypes(true);
		setMaxDamage(0);
		loadSprites();
	}

	int textur[];
	int colo[];
	String names[];

	private void add(int i, int ind, String simple, String Full) {
		textur[i] = ind;// ModLoader.addOverride("/gui/items.png",
						// "/zeitgeist/"+simple+".png");
		names[i] = simple;
		// ModLoader.addName(new ItemStack(zei_Ids.craftSet2, 1, i), Full);
	}

	public void loadSprites() {
		textur = new int[48];
		colo = new int[48];
		names = new String[48];
	}

	public int getColorFromDamage(int par1, int par2) {
		return colo[par1];// var3 != null ? (par2 == 0 ? var3.primaryColor :
							// var3.secondaryColor) : 16777215;
	}
}
