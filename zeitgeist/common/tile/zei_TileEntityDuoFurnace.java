package zeitgeist.common.tile;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;
import zeitgeist.common.block.zei_BlockDuoFurnace;

public class zei_TileEntityDuoFurnace extends TileEntityFurnace {
	/** The number of ticks that the furnace will keep burning */
	public float BurnTime;
	/**
	 * The number of ticks that a fresh copy of the currently-burning item would
	 * keep the furnace burning for
	 */
	// public int currentItemBurnTime;
	/** The number of ticks that the current item has been cooking for */
	public float CookTime;
	private ItemStack furnaceItemStacks[];

	public zei_TileEntityDuoFurnace() {
		furnaceItemStacks = new ItemStack[3];
		BurnTime = 0;
		currentItemBurnTime = 0;
		CookTime = 0;
	}

	public float getBurnVar(int i) {// smaller
		return 1 + i * 0.5f;
	}

	public float getCookVar(int i) {// larger
		return 1.5f + i * i * 0.25f;
	}

	public int neighbors() {
		int ii[] = zei_BlockDuoFurnace.neighborDuos(worldObj, xCoord, yCoord, zCoord);
		return 1 + ii[0];
	}

	@Override
	public void smeltItem() {
		if (!canSmelt()) {
			return;
		}
		ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(furnaceItemStacks[0]);
		if (furnaceItemStacks[2] == null) {
			furnaceItemStacks[2] = itemstack.copy();
		} else if (furnaceItemStacks[2].itemID == itemstack.itemID) {
			furnaceItemStacks[2].stackSize += itemstack.stackSize;
		}
		if (furnaceItemStacks[0].getItem().func_46003_i()) {
			furnaceItemStacks[0] = new ItemStack(furnaceItemStacks[0].getItem().setFull3D());
		} else {
			furnaceItemStacks[0].stackSize--;
		}
		if (furnaceItemStacks[0].stackSize <= 0) {
			furnaceItemStacks[0] = null;
		}
	}

	@Override
	public void updateEntity() {
		boolean flag = furnaceBurnTime > 0;
		boolean flag1 = false;
		int iii = 0;
		if (furnaceBurnTime > 0) {
			iii = neighbors();
			BurnTime -= getBurnVar(iii);
			furnaceBurnTime = (int) BurnTime;
		}
		if (!worldObj.isRemote) {
			if (furnaceBurnTime <= 0 && canSmelt()) {
				currentItemBurnTime = furnaceBurnTime = getItemBurnTime(furnaceItemStacks[1]);
				BurnTime = furnaceBurnTime;
				if (furnaceBurnTime > 0) {
					flag1 = true;
					if (furnaceItemStacks[1] != null) {
						if (furnaceItemStacks[1].getItem().func_46003_i()) {
							furnaceItemStacks[1] = new ItemStack(furnaceItemStacks[1].getItem().setFull3D());
						} else {
							furnaceItemStacks[1].stackSize--;
						}
						if (furnaceItemStacks[1].stackSize == 0) {
							furnaceItemStacks[1] = null;
						}
					}
				}
			}
			if (isBurning() && canSmelt()) {
				if (iii == 0) {
					iii = neighbors();
				}
				CookTime += getCookVar(iii);
				furnaceCookTime = (int) CookTime;
				if (furnaceCookTime >= 200) {
					furnaceCookTime = 0;
					CookTime = furnaceCookTime;
					smeltItem();
					flag1 = true;
				}
			} else {
				furnaceCookTime = 0;
				CookTime = furnaceCookTime;
			}
			if (flag != (furnaceBurnTime > 0)) {
				flag1 = true;
				zei_BlockDuoFurnace.updateFurnaceBlockState(furnaceBurnTime > 0, worldObj, xCoord, yCoord, zCoord);
			}
		}
		if (flag1) {
			onInventoryChanged();
		}
	}
}
