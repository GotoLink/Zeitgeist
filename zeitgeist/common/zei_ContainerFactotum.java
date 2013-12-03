package zeitgeist.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import zeitgeist.common.entity.zei_EntityFactotum;

public class zei_ContainerFactotum extends Container {
	private int burnTime;
	private int cookTime;
	private zei_EntityFactotum furnace;
	private int itemBurnTime;

	public zei_ContainerFactotum(InventoryPlayer inventoryplayer, zei_EntityFactotum tileentityfurnace) {
		cookTime = 0;
		burnTime = 0;
		itemBurnTime = 0;
		furnace = tileentityfurnace;
		// addSlot(new Slot(tileentityfurnace, 0, 56, 17));
		// addSlot(new Slot(tileentityfurnace, 1, 56, 53));
		// addSlot(new SlotFurnace(inventoryplayer.player, tileentityfurnace, 2,
		// 116, 35));
		addSlotToContainer(new Slot(tileentityfurnace, 0, 8, 17));
		addSlotToContainer(new Slot(tileentityfurnace, 1, 26, 17));
		addSlotToContainer(new Slot(tileentityfurnace, 2, 44, 17));
		addSlotToContainer(new Slot(tileentityfurnace, 3, 8, 53));
		addSlotToContainer(new Slot(tileentityfurnace, 4, 26, 53));
		addSlotToContainer(new Slot(tileentityfurnace, 5, 44, 53));
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 5; y++) {
				addSlotToContainer(new Slot(tileentityfurnace, 6 + y + x * 5, 80 + y * 18, 17 + x * 18));
			}
		}
		// addSlot(new Slot(tileentityfurnace, 0, 80, 17));
		for (int i = 0; i < 3; i++) {
			for (int k = 0; k < 9; k++) {
				addSlotToContainer(new Slot(inventoryplayer, k + i * 9 + 9, 8 + k * 18, 84 + i * 18));
			}
		}
		for (int j = 0; j < 9; j++) {
			addSlotToContainer(new Slot(inventoryplayer, j, 8 + j * 18, 142));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return furnace.isUseableByPlayer(entityplayer);
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		for (int i = 0; i < crafters.size(); i++) {
			ICrafting icrafting = (ICrafting) crafters.get(i);
			if (cookTime != furnace.furnaceCookTime) {
				icrafting.sendProgressBarUpdate(this, 0, furnace.furnaceCookTime);
			}
			if (burnTime != furnace.furnaceBurnTime) {
				icrafting.sendProgressBarUpdate(this, 1, furnace.furnaceBurnTime);
			}
			if (itemBurnTime != furnace.currentItemBurnTime) {
				icrafting.sendProgressBarUpdate(this, 2, furnace.currentItemBurnTime);
			}
		}
		cookTime = furnace.furnaceCookTime;
		burnTime = furnace.furnaceBurnTime;
		itemBurnTime = furnace.currentItemBurnTime;
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int i) {
		ItemStack itemstack = null;
		Slot slot = (Slot) inventorySlots.get(i);
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			if (i == 2) {
				mergeItemStack(itemstack1, 3, 39, true);
			} else if (i >= 3 && i < 30) {
				mergeItemStack(itemstack1, 30, 39, false);
			} else if (i >= 30 && i < 39) {
				mergeItemStack(itemstack1, 3, 30, false);
			} else {
				mergeItemStack(itemstack1, 3, 39, false);
			}
			if (itemstack1.stackSize == 0) {
				slot.putStack(null);
			} else {
				slot.onSlotChanged();
			}
			if (itemstack1.stackSize != itemstack.stackSize) {
				slot.onPickupFromSlot(player, itemstack1);
			} else {
				return null;
			}
		}
		return itemstack;
	}

	@Override
	public void updateProgressBar(int i, int j) {
		if (i == 0) {
			furnace.furnaceCookTime = j;
		}
		if (i == 1) {
			furnace.furnaceBurnTime = j;
		}
		if (i == 2) {
			furnace.currentItemBurnTime = j;
		}
	}
}
