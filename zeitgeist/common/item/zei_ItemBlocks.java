package zeitgeist.common.item;

import zeitgeist.common.zei_Ids;
import zeitgeist.common.tile.zei_TileEntityStatue;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class zei_ItemBlocks extends Item {
	int id;

	public zei_ItemBlocks(int i, int id) {
		super(i);
		this.id = id;
	}

	public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer,
			World world, int i, int j, int k, int l) {
		if (world.getBlockId(i, j, k) != Block.snow.blockID) {
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
		if (id == 0) {
			if (world.isBlockOpaqueCube(i, j - 1, k)) {
				world.setBlock(i, j, k, zei_Ids.plague);
			} else {
				return false;
			}
		} else if (id == 1) {
			int iiii = world.getBlockId(i, j - 1, k);
			if (iiii != 0) {
				int ll = MathHelper
						.floor_double((double) ((entityplayer.rotationYaw * 4F) / 360F) + 0.5D) & 3;
				world.setBlock(i, j, k,
						zei_Ids.stoneFigure,
						world.getBlockMetadata(i, j - 1, k), 3);
				zei_TileEntityStatue tile = (zei_TileEntityStatue) world
						.getBlockTileEntity(i, j, k);
				if (ll == 0) {
					tile.setTurnSafe(14);
				}
				if (ll == 1) {
					tile.setTurnSafe(13);
				}
				if (ll == 2) {
					tile.setTurnSafe(15);
				}
				if (ll == 3) {
					tile.setTurnSafe(12);
				}
				if (iiii == zei_Ids.stoneFigure) {
					zei_TileEntityStatue tile2 = (zei_TileEntityStatue) world
							.getBlockTileEntity(i, j - 1, k);
					iiii = tile2.blok;
				}
				tile.setBlokSafe(iiii);
			} else {
				return false;
			}
		} else {
			world.setBlock(i, j, k, zei_Ids.sentryBlock);
		}
		itemstack.stackSize--;
		return true;
	}
}
