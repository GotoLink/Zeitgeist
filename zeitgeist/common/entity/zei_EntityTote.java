package zeitgeist.common.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import zeitgeist.common.mod_Zeitgeist;
import zeitgeist.common.zei_IBot;
import zeitgeist.common.zei_Ids;
import zeitgeist.common.zei_ToteInventory;
import zeitgeist.common.entity.ai.zei_EntityAIBotFollowOwner;
import zeitgeist.common.tile.zei_TileEntityLatch;

public class zei_EntityTote extends zei_EntityOwnedBot implements zei_IBot {
	public double ango = 90;
	public double dir;
	public zei_ToteInventory inv;

	public zei_EntityTote(World world) {
		super(world);
		inv = new zei_ToteInventory(this);
		texture = "/zeitgeist/Toter.png";
		setSize(0.4F, 0.5F);
		moveSpeed = 0.2F;
		health = 6;
		getNavigator().setAvoidsWater(true);
		tasks.addTask(5, new zei_EntityAIBotFollowOwner(this, moveSpeed, 4F, 2.0F));
		tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8F));
		tasks.addTask(9, new EntityAILookIdle(this));
	}

	public zei_EntityTote(World world, double d, double d1, double d2, String s) {
		this(world);
		setPosition(d, d1 + yOffset, d2);
		motionX = 0.0D;
		motionY = 0.0D;
		motionZ = 0.0D;
		prevPosX = d;
		prevPosY = d1;
		prevPosZ = d2;
		setBotOwner(s);
	}

	public void dropethod() {
		label9768: for (int i = 0; i < inv.getSizeInventory(); i++) {
			ItemStack itemstack = inv.getStackInSlot(i);
			if (itemstack == null) {
				continue;
			}
			float f = rand.nextFloat() * 0.8F + 0.1F;
			float f1 = rand.nextFloat() * 0.8F + 0.1F;
			float f2 = rand.nextFloat() * 0.8F + 0.1F;
			do {
				if (itemstack.stackSize <= 0) {
					continue label9768;
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
	}

	public boolean even(int i) {
		return i % 2 == 0;
	}

	@Override
	public AxisAlignedBB getBoundingBox() {
		return boundingBox;
	}

	@Override
	public AxisAlignedBB getCollisionBox(Entity par1Entity) {
		return par1Entity.boundingBox;
	}

	@Override
	public float getEyeHeight() {
		return height * 0.8F;
	}

	@Override
	public boolean interact(EntityPlayer entityplayer) {
		if (!worldObj.isRemote) {
			mod_Zeitgeist.proxy.toteGui(entityplayer, this);
		}
		return true;
	}

	@Override
	public boolean isAIEnabled() { //
		return true;
	}

	@Override
	public void onDeath(DamageSource damagesource) {
		super.onDeath(damagesource);
		if (!worldObj.isRemote) {
			Dropper();
		}
		setDead();
	}

	/*
	 * @Override public void onUpdate() { super.onUpdate();
	 * //super.onLivingUpdate(); }
	 * @Override public void onLivingUpdate(){ super.onLivingUpdate(); }
	 */
	@Override
	public void onUpdate() {
		super.onUpdate();
		if (isWet()) {
			if (isEntityAlive()) {
				Dropper();
			}
		}
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		super.readEntityFromNBT(nbttagcompound);
		NBTTagList nbttaglist = nbttagcompound.getTagList("Items");
		inv.cargoItems = new ItemStack[inv.getSizeInventory()];
		for (int i = 0; i < nbttaglist.tagCount(); i++) {
			NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist.tagAt(i);
			int j = nbttagcompound1.getByte("Slot") & 0xff;
			if (j >= 0 && j < inv.cargoItems.length) {
				inv.cargoItems[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}
	}

	@Override
	public void setDead() {
		if (inv != null) {
			inv.reference = null;
			inv = null;
		}
		super.setDead();
	}

	public void toteClose() {
		dir = 2.5;
		// ango=90;
	}

	/*
	 * @Override public int getSizeInventory() { // TODO Auto-generated method
	 * stub return 9; }
	 * @Override public ItemStack getStackInSlot(int i) { return cargoItems[i];
	 * }
	 * @Override public ItemStack decrStackSize(int i, int j) { if
	 * (cargoItems[i] != null) { if (cargoItems[i].stackSize <= j) { ItemStack
	 * itemstack = cargoItems[i]; cargoItems[i] = null; return itemstack; }
	 * ItemStack itemstack1 = cargoItems[i].splitStack(j); if
	 * (cargoItems[i].stackSize == 0) { cargoItems[i] = null; } return
	 * itemstack1; } else { return null; } }
	 * @Override public ItemStack getStackInSlotOnClosing(int i) { return null;
	 * }
	 * @Override public void setInventorySlotContents(int i, ItemStack
	 * itemstack) { cargoItems[i] = itemstack; if (itemstack != null &&
	 * itemstack.stackSize > getInventoryStackLimit()) { itemstack.stackSize =
	 * getInventoryStackLimit(); } }
	 * @Override public String getInvName() { return "Totr"; }
	 * @Override public int getInventoryStackLimit() { return 64; }
	 * @Override public void onInventoryChanged() { }
	 * @Override public boolean isUseableByPlayer(EntityPlayer entityplayer) {
	 * return true; }
	 * @Override public void openChest() { dir=-10; }
	 * @Override public void closeChest() { dir=2.5; }
	 */
	public void toteOpen() {
		dir = -10;
		// ango=20;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		super.writeEntityToNBT(nbttagcompound);
		NBTTagList nbttaglist = new NBTTagList();
		for (int i = 0; i < inv.cargoItems.length; i++) {
			if (inv.cargoItems[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				inv.cargoItems[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}
		nbttagcompound.setTag("Items", nbttaglist);
	}

	@Override
	protected String getDeathSound() {
		return "random.glass";
	}

	@Override
	protected String getHurtSound() {
		return "step.stone";
	}

	@Override
	protected String getLivingSound() {
		return "automatons.beep";
	}

	@Override
	protected float getSoundVolume() {
		return 0.4F;
	}

	void Dropper() {
		for (int j = 0; j < 20; j++) {
			double d = rand.nextGaussian() * 0.02D;
			double d1 = rand.nextGaussian() * 0.02D;
			double d2 = rand.nextGaussian() * 0.02D;
			worldObj.spawnParticle("explode", (posX + rand.nextFloat() * width * 2.0F) - width, posY + rand.nextFloat() * height, (posZ + rand.nextFloat() * width * 2.0F) - width, d, d1, d2);
		}
		if (!worldObj.isRemote) {
			EntityItem entityitem = new EntityItem(worldObj, posX, posY + 1, posZ, new ItemStack(zei_Ids.soulCore, 1, 0));
			entityitem.delayBeforeCanPickup = 10;
			entityitem.addVelocity(Math.random() * 0.25 - 0.125, 0.25, Math.random() * 0.25 - 0.125);
			worldObj.spawnEntityInWorld(entityitem);
			int xx = MathHelper.floor_double(posX);
			int yy = MathHelper.floor_double(posY);
			int zz = MathHelper.floor_double(posZ);
			int id = worldObj.getBlockId(xx, yy, zz);
			if (id == 0 || Block.blocksList[id].getCollisionBoundingBoxFromPool(worldObj, xx, yy, zz) == null) {
				worldObj.setBlock(xx, yy, zz, zei_Ids.toteBlock);
				zei_TileEntityLatch latch = (zei_TileEntityLatch) worldObj.getBlockTileEntity(xx, yy, zz);
				latch.dispenserContents = inv.cargoItems.clone();
				inv.cargoItems = null;
				// inv=null;
			} else {
				entityitem = new EntityItem(worldObj, posX, posY, posZ, new ItemStack(zei_Ids.itemTote, 1, 0));
				worldObj.spawnEntityInWorld(entityitem);
				dropethod();
			}
			setDead();
		}
	}

	void poof2() {
		mod_Zeitgeist.proxy.poof(worldObj, posX, posY, posZ);
	}
}
