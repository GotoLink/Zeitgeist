package zeitgeist.common.item;

import zeitgeist.common.zei_Ids;
import zeitgeist.common.block.zei_Blocks;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class zei_ItemChalk extends Item {
	public zei_ItemChalk(int i) {
		super(i);
	}

	public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer,
			World world, int i, int j, int k, int l) {
		int id = world.getBlockId(i, j, k);
		if (id == zei_Ids.chalk) {
			int meta = world.getBlockMetadata(i, j, k);
			if (meta < 6) {
				world.setBlockMetadataWithNotify(i, j, k, meta + 1, 3); // zei_Ids.chalk,
				world.markBlockForUpdate(i, j, k);
				itemstack.stackSize--;
				return true;
			}
		}
		if (id != Block.snow.blockID) {
			if (l == 0) {
				j--;
			}
			if (l == 1) {
				j++;
			}
			if (l == 2) {
				k--;
			}
			if (l == 3) {
				k++;
			}
			if (l == 4) {
				i--;
			}
			if (l == 5) {
				i++;
			}
			if (!world.isAirBlock(i, j, k)) {
				return false;
			}
		}
		if (!entityplayer.canPlayerEdit(i, j, k, l, itemstack)) {
			return false;
		}
		if (zei_Blocks.chalk.canPlaceBlockAt(world, i, j, k)) {
			// itemstack.stackSize--;
			itemstack.damageItem(1, entityplayer);
			world.setBlock(i, j, k, zei_Ids.chalk, 1, 3);
		}
		return true;
	}
}
