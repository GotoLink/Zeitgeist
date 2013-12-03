package zeitgeist.common.item;

import net.minecraft.item.ItemBlock;
import net.minecraft.util.Icon;
import zeitgeist.common.mod_Zeitgeist;

public class zei_ItemArch extends ItemBlock {
	public zei_ItemArch(int i) {
		super(i);
		// setMaxDamage(0);
		setHasSubtypes(true);
	}

	public int getColorFromDamage(int par1, int par2) {
		// this.get
		return 0xff0000;
	}

	@Override
	public Icon getIconFromDamage(int i) {
		return mod_Zeitgeist.proxy.blockArch(i);
	}

	@Override
	public int getMetadata(int par1) {
		return par1;
	}

	public int getPlacedBlockMetadata(int i) {
		return i;
	}
}
