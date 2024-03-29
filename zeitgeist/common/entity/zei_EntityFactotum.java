package zeitgeist.common.entity;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.src.ModLoader;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import zeitgeist.common.mod_Zeitgeist;
import zeitgeist.common.zei_IBot;
import zeitgeist.common.zei_Ids;

public class zei_EntityFactotum extends zei_EntityOwnedBot implements IInventory, zei_IBot {
	public int currentItemBurnTime;
	public int furnaceBurnTime;
	public int furnaceCookTime;
	private int availableSlot2 = 6;
	private ItemStack cargoItems[];
	private ItemStack furnaceItemStacks[];
	private int who2Burn = 3;
	private int who2Cook = 0;

	public zei_EntityFactotum(World world) {
		super(world);
		cargoItems = new ItemStack[21];
		texture = "/zeitgeist/factotum1.png"; // bit
		setSize(2F, 2F);
		moveSpeed = 0.7F;
		health = 10;
		isImmuneToFire = true;
		furnaceBurnTime = 0;
		currentItemBurnTime = 0;
		furnaceCookTime = 0;
	}

	public zei_EntityFactotum(World world, double d, double e, double f, String username) {
		super(world);
		cargoItems = new ItemStack[21];
		texture = "/zeitgeist/factotum1.png"; // bit
		setSize(2F, 2F);
		moveSpeed = 0.7F;
		health = 10;
		isImmuneToFire = true;
		furnaceBurnTime = 0;
		currentItemBurnTime = 0;
		furnaceCookTime = 0;
		setPosition(d, e + yOffset, f);
		motionX = 0.0D;
		motionY = 0.0D;
		motionZ = 0.0D;
		prevPosX = d;
		prevPosY = e;
		prevPosZ = f;
		setPathToEntity(null);
		setBotOwner(username);
	}

	@Override
	public void closeChest() {
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		if (cargoItems[i] != null) {
			if (cargoItems[i].stackSize <= j) {
				ItemStack itemstack = cargoItems[i];
				cargoItems[i] = null;
				return itemstack;
			}
			ItemStack itemstack1 = cargoItems[i].splitStack(j);
			if (cargoItems[i].stackSize == 0) {
				cargoItems[i] = null;
			}
			return itemstack1;
		} else {
			return null;
		}
	}

	public int getBurnTimeRemainingSc(int i) {
		if (currentItemBurnTime == 0) {
			currentItemBurnTime = 200;
		}
		return (furnaceBurnTime * i) / currentItemBurnTime;
	}

	public int getCookProgressSc(int i) {
		return (furnaceCookTime * i) / 200;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public String getInvName() {
		return "Factotum";
	}

	@Override
	public int getSizeInventory() {
		return 21;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return cargoItems[i];
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		return null;
	}

	public String getTexture() {
		if (furnaceBurnTime > 0) {
			if ((furnaceBurnTime % 2) == 1) {
				return "/zeitgeist/factotum2.png";
			} else {
				return "/zeitgeist/factotum3.png";
			}
		}
		return "/zeitgeist/factotum1.png";
	}

	@Override
	public boolean interact(EntityPlayer entityplayer) {
		if (!worldObj.isRemote) {
			mod_Zeitgeist.proxy.factotumGui(entityplayer, this);
		}
		return true;
	}

	public boolean isBurningStuff() {
		return furnaceBurnTime > 0;
	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return true;
	}

	@Override
	public void onDeath(DamageSource damagesource) {
		super.onDeath(damagesource);
		if (!worldObj.isRemote) {
			Dropper();// a(field_34905_c > 0);
		}
		// worldObj.setEntityState(this, (byte)3);
	}

	@Override
	public void onInventoryChanged() {
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		updateBurnin();
	}

	@Override
	public void openChest() {
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		super.readEntityFromNBT(nbttagcompound);
		NBTTagList nbttaglist = nbttagcompound.getTagList("Items");
		cargoItems = new ItemStack[getSizeInventory()];
		for (int i = 0; i < nbttaglist.tagCount(); i++) {
			NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist.tagAt(i);
			int j = nbttagcompound1.getByte("Slot") & 0xff;
			if (j >= 0 && j < cargoItems.length) {
				cargoItems[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}
		furnaceBurnTime = nbttagcompound.getShort("BurnTime");
		furnaceCookTime = nbttagcompound.getShort("CookTime");
		currentItemBurnTime = checkBurn2();
	}

	@Override
	public void setDead() {
		label98: for (int i = 0; i < getSizeInventory(); i++) {
			ItemStack itemstack = getStackInSlot(i);
			if (itemstack == null) {
				continue;
			}
			float f = rand.nextFloat() * 0.8F + 0.1F;
			float f1 = rand.nextFloat() * 0.8F + 0.1F;
			float f2 = rand.nextFloat() * 0.8F + 0.1F;
			do {
				if (itemstack.stackSize <= 0) {
					continue label98;
				}
				int j = rand.nextInt(21) + 10;
				if (j > itemstack.stackSize) {
					j = itemstack.stackSize;
				}
				itemstack.stackSize -= j;
				EntityItem entityitem = new EntityItem(worldObj, posX + f, posY + f1, posZ + f2, new ItemStack(itemstack.itemID, j, itemstack.getItemDamage()));
				float f3 = 0.05F;
				entityitem.motionX = (float) rand.nextGaussian() * f3;
				entityitem.motionY = (float) rand.nextGaussian() * f3 + 0.2F;
				entityitem.motionZ = (float) rand.nextGaussian() * f3;
				worldObj.spawnEntityInWorld(entityitem);
			} while (true);
		}
		super.setDead();
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		cargoItems[i] = itemstack;
		if (itemstack != null && itemstack.stackSize > getInventoryStackLimit()) {
			itemstack.stackSize = getInventoryStackLimit();
		}
	}

	public void smeltItem2() {
		if (!icanSmelt()) {
			return;
		}
		if (cargoItems[who2Cook] == null) {
			return;
		}
		ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(cargoItems[who2Cook].getItem().itemID);
		if (cargoItems[availableSlot2] == null) {
			cargoItems[availableSlot2] = itemstack.copy();
		} else if (cargoItems[availableSlot2].itemID == itemstack.itemID) {
			cargoItems[availableSlot2].stackSize += itemstack.stackSize;
		}
		if (cargoItems[who2Cook].getItem().hasContainerItem()) {
			cargoItems[who2Cook] = new ItemStack(cargoItems[who2Cook].getItem().getContainerItem());
		} else {
			cargoItems[who2Cook].stackSize--;
		}
		if (cargoItems[who2Cook].stackSize <= 0) {
			cargoItems[who2Cook] = null;
		}
	}

	public void updateBurnin() {
		boolean flag = furnaceBurnTime > 0;
		boolean flag1 = false;
		if (furnaceBurnTime > 0) {
			furnaceBurnTime -= 3;
		}
		if (!worldObj.isRemote) {
			if (furnaceBurnTime <= 0 && icanSmelt()) {
				furnaceBurnTime = checkBurn2();
				currentItemBurnTime = furnaceBurnTime;
				if (furnaceBurnTime > 0) {
					flag1 = true;
					if (cargoItems[who2Burn] != null) {
						if (cargoItems[who2Burn].getItem().hasContainerItem()) {
							cargoItems[who2Burn] = new ItemStack(cargoItems[who2Burn].getItem().getContainerItem());
						} else {
							cargoItems[who2Burn].stackSize--;
						}
						if (cargoItems[who2Burn].stackSize == 0) {
							cargoItems[who2Burn] = null;
						}
					}
				}
			}
			if (isBurningStuff() && icanSmelt()) {
				furnaceCookTime += 3;
				if (furnaceCookTime >= 200) {
					furnaceCookTime = 0;
					smeltItem2();
					flag1 = true;
				}
			} else {
				furnaceCookTime = 0;
			}
			if (flag != (furnaceBurnTime > 0)) {
				flag1 = true;
				setIsSmelting2(furnaceBurnTime > 0);
			}
		}
		if (flag1) {
			onInventoryChanged();
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		super.writeEntityToNBT(nbttagcompound);
		nbttagcompound.setShort("BurnTime", (short) furnaceBurnTime);
		nbttagcompound.setShort("CookTime", (short) furnaceCookTime);
		NBTTagList nbttaglist = new NBTTagList();
		for (int i = 0; i < cargoItems.length; i++) {
			if (cargoItems[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				cargoItems[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}
		nbttagcompound.setTag("Items", nbttaglist);
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}

	@Override
	protected String getDeathSound() {
		return "random.glass";
	}

	@Override
	protected String getHurtSound() {
		return "automatons.thunk";
	}

	@Override
	protected String getLivingSound() {
		return "automatons.techy";
	}

	@Override
	protected float getSoundVolume() {
		return 0.4F;
	}

	@Override
	protected void updateWanderPath() // kills wandering!
	{
	}

	private int checkBurn2() {
		who2Burn = 3;
		int temp = 0;
		while (who2Burn <= 5) {
			temp = getItemBurnTime2(cargoItems[who2Burn]);
			if (temp > 0) {
				return temp;
			}
			who2Burn++;
		}
		return 0;
	}

	private boolean icanSmelt() {
		availableSlot2 = 6;
		who2Cook = 0;
		if (cargoItems[0] == null && cargoItems[1] == null && cargoItems[2] == null) {
			return false;
		}
		ItemStack itemstack1 = null;
		ItemStack itemstack2 = null;
		ItemStack itemstack3 = null;
		if (cargoItems[0] != null) {
			itemstack1 = FurnaceRecipes.smelting().getSmeltingResult(cargoItems[0]);
		}
		if (cargoItems[1] != null) {
			itemstack2 = FurnaceRecipes.smelting().getSmeltingResult(cargoItems[1]);
		}
		if (cargoItems[2] != null) {
			itemstack3 = FurnaceRecipes.smelting().getSmeltingResult(cargoItems[2]);
		}
		ItemStack itemstack;
		if (itemstack1 != null) {
			who2Cook = 0;
			itemstack = itemstack1;
		} else if (itemstack2 != null) {
			who2Cook = 1;
			itemstack = itemstack2;
		} else if (itemstack3 != null) {
			who2Cook = 2;
			itemstack = itemstack3;
		} else {
			return false;
		}
		while (availableSlot2 < 21) {
			if (cargoItems[availableSlot2] == null) {
				return true;
			}
			if (cargoItems[availableSlot2].isItemEqual(itemstack)) {
				if (cargoItems[availableSlot2].stackSize < getInventoryStackLimit() && cargoItems[availableSlot2].stackSize < cargoItems[availableSlot2].getMaxStackSize()) {
					return true;
				}
			}
			availableSlot2++;
		}
		return false;
	}

	private void setIsSmelting2(boolean b) {
	}

	void Dropper() {
		for (int j = 0; j < 20; j++) {
			double d = rand.nextGaussian() * 0.02D;
			double d1 = rand.nextGaussian() * 0.02D;
			double d2 = rand.nextGaussian() * 0.02D;
			worldObj.spawnParticle("explode", (posX + rand.nextFloat() * width * 2.0F) - width, posY + rand.nextFloat() * height, (posZ + rand.nextFloat() * width * 2.0F) - width, d, d1, d2);
		}
		if (!worldObj.isRemote) {
			entityDropItem(new ItemStack(zei_Ids.itemFactotum, 1, 0), 0.0F);
			setDead();
		}
	}

	public static int getItemBurnTime2(ItemStack par1ItemStack) {
		if (par1ItemStack == null) {
			return 0;
		}
		int i = par1ItemStack.getItem().itemID;
		if (i < 256 && Block.blocksList[i].blockMaterial == Material.wood) {
			return 300;
		}
		if (i == Item.stick.itemID) {
			return 100;
		}
		if (i == Item.coal.itemID) {
			return 1600;
		}
		if (i == Item.bucketLava.itemID) {
			return 20000;
		}
		if (i == Block.sapling.blockID) {
			return 100;
		}
		if (i == Item.blazeRod.itemID) {
			return 2400;
		} else {
			return ModLoader.addAllFuel(par1ItemStack.itemID, par1ItemStack.getItemDamage());
		}
	}
}
