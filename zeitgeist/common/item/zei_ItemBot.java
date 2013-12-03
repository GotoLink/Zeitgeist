package zeitgeist.common.item;

import zeitgeist.common.zei_Ids;
import zeitgeist.common.entity.zei_EntityFactotum;
import zeitgeist.common.entity.zei_EntityGuard;
import zeitgeist.common.entity.zei_EntityOmni;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class zei_ItemBot extends Item {
	public int Numo;

	public zei_ItemBot(int i, int j) {
		super(i);
		// maxStackSize = 32;
		Numo = j;
		if (Numo == 3) {
			maxStackSize = 8;
		}
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
			if (!world.isAirBlock(i, j, k)
					|| !world.getBlockMaterial(i, j - 1, k).isSolid()) {
				return false;
			}
		}
		if (!entityplayer.canPlayerEdit(i, j, k, l, itemstack)) {
			return false;
		}
		int meta = MathHelper
				.floor_double((double) (entityplayer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		System.out.println("meta: " + meta);
		if (Numo == 0) {
			world.setBlock(i, j, k, zei_Ids.workerBlock, meta, 3);
		} else if (Numo == 1) {
			world.setBlock(i, j, k, zei_Ids.sentryBlock, meta, 3);
		} else if (Numo == 2) {
			world.setBlock(i, j, k, zei_Ids.beaconBlock);
		} else if (Numo == 3) {
			world.setBlock(i, j, k, zei_Ids.toteBlock);
		} else if (Numo == 4) {
			world.spawnEntityInWorld(new zei_EntityFactotum(world,
					(double) i + 0.5, (double) j + 0.5, (double) k + 0.5,
					entityplayer.username));
			// world.entityJoinedWorld(new EntityAHydra(world, (float)i + 0.5F,
			// (float)j+0.5F, (float)k + 0.5F));
		} else if (Numo == 5) {
			world.spawnEntityInWorld(new zei_EntityOmni(world,
					(float) i + 0.5F, (float) j, (float) k + 0.5F));
		} else if (Numo == 6) {
			world.spawnEntityInWorld(new zei_EntityGuard(world,
					(float) i + 0.5F, (float) j, (float) k + 0.5F));
		}
		//
		// world.entityJoinedWorld(new EntityGuard(world, (float)i + 0.5F,
		// (float)j + 0.5F, (float)k + 0.5F));
		itemstack.stackSize--;
		return true;
	}
}
