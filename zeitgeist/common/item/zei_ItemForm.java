package zeitgeist.common.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class zei_ItemForm extends Item {
	public zei_ItemForm(int i) {
		super(i);
		setHasSubtypes(true);
		setMaxDamage(0);
		loadSprites();
	}

	int textur[];

	public void loadSprites() {
		textur = new int[16];
		textur[0] = "/zeitgeist/soulSynth.png");
		textur[1] = "/zeitgeist/stoneRounded.png");
		textur[2] = "/zeitgeist/stoneBall.png");
		textur[3] = "/zeitgeist/stoneIncomplete.png");
		textur[4] = "/zeitgeist/stoneEmpty.png");
		textur[5] = "/zeitgeist/soulPure.png");
		textur[6] = "/zeitgeist/soulEvil.png");
		// textur[7] = "/zeitgeist/soulEvil.png"); ancients?
	}

	public Icon getIconFromDamage(int i) {
		return textur[i];
	}

	public String getItemNameIS(ItemStack itemstack) {
		return (new StringBuilder()).append(super.getName()).append(".").append(names[itemstack.getItemDamage()]).toString();
	}

	public static final String names[] = { "formMan", "formOct", "formSmall", "formBiped", "formQuad", "formFlight" };

	public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l) {
		itemstack.stackSize--;
		return false;
	}
}
