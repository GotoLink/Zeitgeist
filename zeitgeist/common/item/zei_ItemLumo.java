package zeitgeist.common.item;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import zeitgeist.common.mod_Zeitgeist;

public class zei_ItemLumo extends ItemBlock {
	public static final String dyeColorNames[] = { "sky", "walk", "wallo", "automatonLeg", "rod", "automatonBack", "biterHead", "robo", "greens", "coals", "superMetal", "techo", "lightBlue",
			"magenta", "orange", "white" };

	public zei_ItemLumo(int i) {
		super(i);
		setMaxDamage(0);
		setHasSubtypes(true);
	}

	@Override
	public Icon getIconFromDamage(int i) {
		return mod_Zeitgeist.proxy.blockSky(i);
	}

	public String getItemNameIS(ItemStack itemstack) {
		return (new StringBuilder()).append(super.getUnlocalizedName()).append(".").append(dyeColorNames[itemstack.getItemDamage()]).toString();
	}

	public int getPlacedBlockMetadata(int i) {
		return i;
	}
}
