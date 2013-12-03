package zeitgeist.common.item;

import zeitgeist.common.zei_Ids;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class zei_ItemChisel extends Item {
	public zei_ItemChisel(int i) {
		super(i);
		setMaxDamage(63);
	}

	public boolean isDamageable() {
		return false;
	}

	public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer,
			World world, int i, int j, int k, int l) {
		if (l != 0) {
			int id = world.getBlockId(i, j, k);
			if (id == 1) {
				if (!world.isAirBlock(i, j + 1, k)) {
					world.setBlock(i, j, k, 4);
					itemstack.damageItem(4, entityplayer);
				} else {
					world.setBlock(i, j, k,
							zei_Ids.slabBlock, 1, 3);
					if (!world.isRemote)
						world.spawnEntityInWorld(new EntityItem(world,
								i + 0.5f, j + 1, k + 0.5f, new ItemStack(
										zei_Ids.craftSet1, 1, 9)));
					itemstack.damageItem(1, entityplayer);
				}
				return true;
			} else if (id == zei_Ids.slabBlock) {
				int meta = world.getBlockMetadata(i, j, k);
				if (meta < 15) {
					world.setBlock(i, j, k,
							zei_Ids.slabBlock, meta + 1, 3); // zei_Ids.chalk,
				} else {
					world.setBlock(i, j, k, 0);
				}
				if (!world.isRemote)
					world.spawnEntityInWorld(new EntityItem(world, i + 0.5f,
							j + 1, k + 0.5f, new ItemStack(zei_Ids.craftSet1,
									1, 9)));
				itemstack.damageItem(1, entityplayer);
				return true;
			}
		}
		return false;
	}

	public boolean doesContainerItemLeaveCraftingGrid(ItemStack itemstack) {
		return false;
	}
}
