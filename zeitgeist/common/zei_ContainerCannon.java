package zeitgeist.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import zeitgeist.common.tile.zei_TileEntityCannon;

public class zei_ContainerCannon extends Container {
	private zei_TileEntityCannon cann;

	public zei_ContainerCannon(InventoryPlayer par1InventoryPlayer, zei_TileEntityCannon ca) {
		this.cann = ca;
		this.addSlotToContainer(new zei_SlotCannon(par1InventoryPlayer.player, ca, 0, 8, 17));
		int var3;
		for (int j = 0; j < 3; j++) {
			for (int i1 = 0; i1 < 4; i1++) {
				addSlotToContainer(new Slot(ca, 1 + i1 + j * 4, 98 + i1 * 18, 17 + j * 18));
			}
		}
		for (var3 = 0; var3 < 3; ++var3) {
			for (int var4 = 0; var4 < 9; ++var4) {
				this.addSlotToContainer(new Slot(par1InventoryPlayer, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
			}
		}
		for (var3 = 0; var3 < 9; ++var3) {
			this.addSlotToContainer(new Slot(par1InventoryPlayer, var3, 8 + var3 * 18, 142));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
		return this.cann.isUseableByPlayer(par1EntityPlayer);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par1) {
		if (par1 == 0) {
			return null;
		}
		ItemStack var2 = null;
		Slot var3 = (Slot) this.inventorySlots.get(par1);
		if (var3 != null && var3.getHasStack()) {
			ItemStack var4 = var3.getStack();
			var2 = var4.copy();
			if (par1 < 12) {
				if (!this.mergeItemStack(var4, 12, this.inventorySlots.size(), true)) {
					return null;
				}
			} else if (!this.mergeItemStack(var4, 0, 12, false)) {
				return null;
			}
			if (var4.stackSize == 0) {
				var3.putStack((ItemStack) null);
			} else {
				var3.onSlotChanged();
			}
		}
		return var2;
	}
}
