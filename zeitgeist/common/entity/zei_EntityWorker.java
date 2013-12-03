package zeitgeist.common.entity;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import zeitgeist.common.mod_Zeitgeist;
import zeitgeist.common.zei_IBot;
import zeitgeist.common.zei_Ids;
import zeitgeist.common.entity.ai.zei_EntityAIWorkerBeacon;
import zeitgeist.common.entity.ai.zei_EntityAIWorkerCollect;
import zeitgeist.common.entity.ai.zei_EntityAIWorkerDig;
import zeitgeist.common.entity.ai.zei_EntityAIWorkerFollow;
import zeitgeist.common.entity.ai.zei_EntityAIWorkerWander;
import zeitgeist.common.tile.zei_TileEntityBeacon;

public class zei_EntityWorker extends zei_EntityOwnedBot implements zei_IBot {
	public int dX;
	public int dY;
	public int dZ;
	public int hx, hy, hz;
	public Entity itemGet;
	private boolean boop = false;
	private int dig;
	private int invDamage;
	private int invNum;
	private int invType;
	private int mode;
	private boolean renew;
	private int state;
	private int trigger;
	int F = 0;
	zei_TileEntityBeacon home;
	int Op = 0;
	int R = 0;

	public zei_EntityWorker(World world) {
		super(world);
		texture = "/zeitgeist/golem1.png";
		setSize(0.6F, 0.8F);
		moveSpeed = 0.25F;
		health = 6;
		mode = 0;
		state = 0;
		dig = 0;
		invNum = 0;
		invType = 0;
		invDamage = 0;
		hy = -1;
		getNavigator().setAvoidsWater(true);
		// tasks.addTask(1, new EntityAISwimming(this));
		// tasks.addTask(2, aiSit);
		// tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F)); //0.4f
		// tasks.addTask(4, new EntityAIAttackOnCollide(this, moveSpeed, true));
		tasks.addTask(5, new zei_EntityAIWorkerFollow(this, moveSpeed, 7F, 2.0F));
		tasks.addTask(5, new zei_EntityAIWorkerDig(this));
		tasks.addTask(5, new zei_EntityAIWorkerCollect(this));
		// tasks.addTask(6, new EntityAIMate(this, moveSpeed));
		tasks.addTask(7, new zei_EntityAIWorkerWander(this, moveSpeed));
		// tasks.addTask(8, new EntityAIBeg(this, 8F));
		tasks.addTask(200, new zei_EntityAIWorkerBeacon(this));
		tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8F));
		tasks.addTask(9, new EntityAILookIdle(this));
	}

	public zei_EntityWorker(World world, double d, double d1, double d2, int turn, String s) {
		this(world);
		setPosition(d, d1 + yOffset, d2);
		prevPosX = d;
		prevPosY = d1;
		prevPosZ = d2;
		zei_Universal.rotateEntity(this, turn * 90);
		if (s == "") {
			setMode(3);
			tasks.addTask(1, new EntityAIPanic(this, 0.38F));
		}
		setBotOwner(s);
	}

	@Override
	public boolean attackEntityFrom(DamageSource damagesource, int i) {
		if (getMode() == 3) {
			fleeingTick = 60;
		}
		return super.attackEntityFrom(damagesource, i);
	}

	public void Dropper() {
		for (int j = 0; j < 20; j++) {
			double d = rand.nextGaussian() * 0.02D;
			double d1 = rand.nextGaussian() * 0.02D;
			double d2 = rand.nextGaussian() * 0.02D;
			worldObj.spawnParticle("explode", (posX + rand.nextFloat() * width * 2.0F) - width, posY + rand.nextFloat() * height, (posZ + rand.nextFloat() * width * 2.0F) - width, d, d1, d2);
		}
		if (!worldObj.isRemote) {
			// poof();
			int ii = getMode();
			EntityItem entityitem = new EntityItem(worldObj, posX, posY + 1, posZ, new ItemStack(zei_Ids.soulCore, 1, ii != 3 ? ii != 5 ? 0 : 6 : 5));
			entityitem.delayBeforeCanPickup = 10;
			entityitem.addVelocity(Math.random() * 0.25 - 0.125, 0.25, Math.random() * 0.25 - 0.125);
			worldObj.spawnEntityInWorld(entityitem);
			dropInventory();
			int xx = MathHelper.floor_double(posX);
			int yy = MathHelper.floor_double(posY);
			int zz = MathHelper.floor_double(posZ);
			int id = worldObj.getBlockId(xx, yy, zz);
			if (id == 0 || Block.blocksList[id].getCollisionBoundingBoxFromPool(worldObj, xx, yy, zz) == null) {
				int meta = MathHelper.floor_double(rotationYawHead * 4.0F / 360.0F + 0.5D) & 3;
				worldObj.setBlock(xx, yy, zz, zei_Ids.workerBlock, meta, 3);
			} else {
				entityitem = new EntityItem(worldObj, posX, posY, posZ, new ItemStack(zei_Ids.itemWorker, 1, 0));
				worldObj.spawnEntityInWorld(entityitem);
			}
			setDead();
		}
	}

	public String getD() {
		return dataWatcher.getWatchableObjectString(20);
	}

	public int getDig() {
		return dig;// dataWatcher.getWatchableObjectInt(19);
	}

	@Override
	public float getEyeHeight() {
		return height * 0.8F;
	}

	public zei_TileEntityBeacon getHome() {
		if (hy == -1) {
			return null;
		} else {
			if (worldObj.getBlockId(hx, hy, hz) != zei_Ids.beaconBlock) {
				home = null;
				hy = -1;
				return home;
			} else {
				if (home == null) {
					home = null;
					zei_TileEntityBeacon beacon = (zei_TileEntityBeacon) worldObj.getBlockTileEntity(hx, hy, hz);
					if (beacon != null) {
						home = beacon;
					}
				}
				if (home == null) {
					hy = -1;
				}
			}
			return home;
		}
	}

	public int getInventoryDamage() {
		return invDamage;
	}

	public int getInventoryNum() {
		return invNum;
	}

	public int getInventoryType() {
		return mod_Zeitgeist.proxy.getInt(dataWatcher, 18);
	}

	@Override
	public float getMaxHealth() {
		return 6;
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 8;
	}

	public int getMode() {
		return mod_Zeitgeist.proxy.getInt(dataWatcher, 16);
	}

	public int getState() {
		return state;// dataWatcher.getWatchableObjectInt(18); //state;//
	}

	public int getT() {
		return zei_Universal.getInt(dataWatcher, 19);
	}

	public String getTexture() {
		switch (getMode()) {
		case 0:
			return "/zeitgeist/golem1.png"; // idle/white
		case 1:
			return "/zeitgeist/golem2.png"; // follow/blue
		case 2:
			return "/zeitgeist/golem3.png"; // dig/yellow
		case 3:
			return "/zeitgeist/golem4.png"; // wander/green
		case 4:
			return "/zeitgeist/golem5.png"; // inventory/magenta
		case 5:
			return "/zeitgeist/golem6.png"; // purple?
		default:
			return super.getTexture();
		}
	}

	// public PathEntity getEntityPathToXYZ(Entity entity, int i, int j, int k,
	// float f)
	public void gotoSpot(int x, int y, int z, float f) {
		PathEntity pathentity = worldObj.getEntityPathToXYZ(this, x, y, z, 16F, true, true, false, true);
		this.getNavigator().setPath(pathentity, moveSpeed);
	}

	@Override
	public boolean interact(EntityPlayer entityplayer) {
		if (entityplayer.username.equalsIgnoreCase(getBotOwner())) {
			ItemStack itemstack = entityplayer.inventory.getCurrentItem();
			if (itemstack != null) {
				if ((itemstack.itemID >= 1 && itemstack.itemID <= 4) || (itemstack.itemID >= 12 && itemstack.itemID <= 22)) {
					if (!worldObj.isRemote) {
						setMode(2);
						setInventoryType(itemstack.itemID);// itemstack.itemID);
					}
					modeSwap();
					return true;
				} else if (itemstack.itemID == Item.shovelStone.itemID) {
					if (!worldObj.isRemote) {
						setMode(3);
					}
					modeSwap();
					return true;
				} else if (itemstack.itemID == 54) // Item.stick.shiftedIndex
				{
					if (!worldObj.isRemote) {
						setInventoryType(54);
						setMode(4);
					}
					modeSwap();
					return true;
				} else if (itemstack.itemID == Item.redstone.itemID) {
					if (!worldObj.isRemote) {
						setMode(2);
						setInventoryType(Block.oreRedstone.blockID);
					}
					modeSwap();
					return true;
				} else if (itemstack.itemID == Item.coal.itemID) {
					if (!worldObj.isRemote) {
						setMode(2);
						setInventoryType(Block.oreCoal.blockID);
					}
					modeSwap();
					return true;
				}
				if (!worldObj.isRemote) {
					if (getMode() != 0) {
						setMode(0);
					} else {
						setMode(1);
					}
				}
				modeSwap();
				return true;
			}
			if (!worldObj.isRemote) {
				if (getMode() != 0) {
					setMode(0);
				} else {
					setMode(1);
				}
			}
			modeSwap();
			return true;
		}
		return false;
	}

	@Override
	public boolean isAIEnabled() {
		return true;
	}

	public void modeCollection(EntityLivingBase owner) // pickup
	{
		if (getState() == 0) {
			int xo;
			int yo;
			int zo;
			if (getHome() == null) {
				xo = MathHelper.floor_double(owner.posX) + rand.nextInt(3) - 1;
				yo = MathHelper.floor_double(owner.posY) + rand.nextInt(3) - 1;
				zo = MathHelper.floor_double(owner.posZ) + rand.nextInt(3) - 1;
			} else {
				xo = hx + rand.nextInt(5) - 2;
				yo = hy + rand.nextInt(3) - 1;
				zo = hz + rand.nextInt(5) - 2;
			}
			if (worldObj.getBlockId(xo, yo, zo) == 54) {
				dX = xo;
				dY = yo;
				dZ = zo;
				setState(1);
			}
		} else if (!hasPath()) {
			if (getState() == 1) {
				if (worldObj.getBlockId(dX, dY, dZ) == 54) {
					int num = getInventoryNum();
					if (itemGet == null || itemGet.isDead) {
						itemGet = findStuff();
						if (itemGet != null && num < 64) {
							getPathy(itemGet);
						} else if (num > 0) {
							setState(2);
						}
					} else {
						if (getDistanceToEntity(itemGet) < 2f) {
							pickup();
						} else {
							itemGet = null;
						}
					}
				} else {
					setState(0);
				}
			} else if (getState() == 2) {
				if (worldObj.getBlockId(dX, dY, dZ) == 54) {
					if (getDistance(dX, dY, dZ) < 2) {
						setState(3);
					} else {
						gotoSpot(dX, dY, dZ, 16F);
					}
				} else {
					setState(0);
				}
			} else if (getState() == 3) {
				if (worldObj.getBlockId(dX, dY, dZ) == 54) {
					TileEntityChest tc = ((TileEntityChest) worldObj.getBlockTileEntity(dX, dY, dZ));
					int sl = 0;
					while (sl <= 26) {
						ItemStack is = tc.getStackInSlot(sl);
						if (is == null) {
							is = new ItemStack(getInventoryType(), getInventoryNum(), getInventoryDamage());
							tc.setInventorySlotContents(sl, is);
							setInventoryNum(0);
							setInventoryType(0);
							setInventoryDamage(0);
							setState(1);
							break;
						} else if ((is.itemID == getInventoryType() && is.getItemDamage() == getInventoryDamage() && is.stackSize < 64)) {
							if (is.stackSize + getInventoryNum() <= 64) {
								is.stackSize += getInventoryNum();
								tc.setInventorySlotContents(sl, is);
								setInventoryNum(0);
								setInventoryType(0);
								setInventoryDamage(0);
								setState(1);
								break;
							} else {
								int ii = getInventoryNum() - (64 - is.stackSize);
								setInventoryNum(ii);
								is.stackSize += ii;
								tc.setInventorySlotContents(sl, is);
							}
						} else {
							sl++;
						}
					}
					if (sl >= 26) {
						setState(0);
					}
				} else {
					setState(0);
				}
			}
		}
	}

	@Override
	public void onDeath(DamageSource damagesource) {
		super.onDeath(damagesource);
		Dropper();
		setDead();
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (getT() == 1) {
			if (worldObj.isRemote && renew) {
				renew = false;
				String s = getD();
				if (s != "") {
					int ii = s.indexOf(",");
					int xo = Integer.parseInt(s.substring(0, ii));
					s = s.substring(ii + 1, s.length());
					int jj = s.indexOf(",");
					int yo = Integer.parseInt(s.substring(0, jj));
					s = s.substring(jj + 1, s.length());
					int zo = Integer.parseInt(s);
					dX = xo;
					dY = yo;
					dZ = zo;
				}
			}
			// System.out.println("digg??: "+dX
			// +","+dY+","+dZ+"  :  "+MathHelper.floor_double(posX)+","+MathHelper.floor_double(posY-1)+","+MathHelper.floor_double(posZ));
			dig(dX, dY, dZ);
		} else if (worldObj.isRemote && getT() == 2) {
			if (renew) {
				renew = false;
				worldObj.playSoundAtEntity(this, "random.pop", 0.2F, ((rand.nextFloat() - rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
			}
		} else {
			renew = true;
		}
	}

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
		setMode(nbttagcompound.getInteger("mode"));
		setState(nbttagcompound.getInteger("state"));
		NBTTagList nbttaglist = nbttagcompound.getTagList("Dest");
		dX = ((NBTTagInt) nbttaglist.tagAt(0)).data;
		dY = ((NBTTagInt) nbttaglist.tagAt(1)).data;
		dZ = ((NBTTagInt) nbttaglist.tagAt(2)).data;
		NBTTagList nbttaglist2 = nbttagcompound.getTagList("Home");
		hx = ((NBTTagInt) nbttaglist.tagAt(0)).data;
		hy = ((NBTTagInt) nbttaglist.tagAt(1)).data;
		hz = ((NBTTagInt) nbttaglist.tagAt(2)).data;
		if (getMode() == 3) {
			tasks.addTask(1, new EntityAIPanic(this, 0.38F));
		}
	}

	public void setD(String s) {
		dataWatcher.updateObject(20, s);
	}

	public void setDig(int i) {
		dig = i;
		// dataWatcher.updateObject(19, Integer.valueOf(i));
	}

	public void setHome(zei_TileEntityBeacon i) {
		// System.out.println("home found :> "+i.entityId);
		home = i;
		// if (hy == -1)
		// {
		hx = home.xCoord;
		hy = home.yCoord;
		hz = home.zCoord;
		// }
		// dataWatcher.updateObject(19, Integer.valueOf(i));
	}

	public void setInventoryDamage(int i) {
		invDamage = i;
	}

	public void setInventoryNum(int i) {
		invNum = i;
	}

	/*
	 * public boolean isBotSitting() { return getMode() == 0; }
	 */
	public void setInventoryType(int i) {
		invType = i;
		dataWatcher.updateObject(18, Integer.valueOf(i));
	}

	public void setMode(int i) {
		mode = i;
		dataWatcher.updateObject(16, Integer.valueOf(mode));
		dropInventory();
		setT(0);
		setState(0);
	}

	public void setState(int i) {
		state = i;
		// dataWatcher.updateObject(18, Integer.valueOf(i));
	}

	public void setT(int i) {
		trigger = i;
		dataWatcher.updateObject(19, Integer.valueOf(i));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		super.writeEntityToNBT(nbttagcompound);
		nbttagcompound.setInteger("mode", getMode());
		nbttagcompound.setInteger("state", getState());
		nbttagcompound.setTag("Dest", newIntNBTList(new int[] { dX, dY, dZ }));
		nbttagcompound.setTag("Home", newIntNBTList(new int[] { hx, hy, hz }));
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(16, new Integer(mode));// mode
		dataWatcher.addObject(18, new Integer(invType));// type
		dataWatcher.addObject(19, new Integer(trigger));// state
		dataWatcher.addObject(20, ""); // dstring
	}

	protected Entity findStuff() {
		List list = worldObj.getEntitiesWithinAABB(EntityItem.class, boundingBox.expand(16D, 3D, 16D));
		List<Entity> list2 = new ArrayList<Entity>();
		if (!list.isEmpty()) {
			for (int j = 0; j < list.size(); j++) {
				Entity entity = (Entity) list.get(j);
				if (!entity.isWet()) {
					if (getInventoryType() != 0) {
						ItemStack is = ((EntityItem) entity).getEntityItem();
						if (is.itemID == getInventoryType() && is.getItemDamage() == getInventoryDamage()) {
							list2.add(entity);
						}
					} else {
						list2.add(entity);
					}
				}
			}
		}
		if (list2.isEmpty()) {
			setT(0);
			return list2.get(rand.nextInt(list2.size()));
		}
		if (getInventoryNum() > 0) {
			setState(2);
		}
		return null;
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

	// }
	protected void modeSwap() {
		poof();
		isJumping = false;
		setPathToEntity(null);
	}

	protected NBTTagList newIntNBTList(int ad[]) {
		NBTTagList nbttaglist = new NBTTagList();
		int ad1[] = ad;
		int i = ad1.length;
		for (int j = 0; j < i; j++) {
			int d = ad1[j];
			nbttaglist.appendTag(new NBTTagInt(null, d));
		}
		return nbttaglist;
	}

	private void dig(int x, int y, int z) {
		mod_Zeitgeist.proxy.dig(worldObj, x, y, z);
	}

	private void getPathy(Entity entity) {
		PathEntity pathentity = worldObj.getPathEntityToEntity(this, entity, 16F, true, true, false, true);
		this.getNavigator().setPath(pathentity, moveSpeed);
	}

	// protected void attackEntity(Entity entity, float f)
	// {
	private void pickup() {
		EntityItem ent = (EntityItem) itemGet;
		setInventoryType(ent.getEntityItem().itemID);
		setInventoryDamage(ent.getEntityItem().getItemDamage());
		setInventoryNum(getInventoryNum() + ent.getEntityItem().stackSize);
		// System.out.println("num:"+getInventoryNum());
		// worldObj.playSoundAtEntity(this, "random.pop", 0.2F,
		// ((rand.nextFloat() - rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
		setT(2);
		ent.setDead();
		itemGet = null;
	}

	void dropInventory() {
		if (getInventoryNum() > 0) {
			entityDropItem(new ItemStack(getInventoryType(), getInventoryNum(), getInventoryDamage()), 0.0F);
			setInventoryNum(0);
			setInventoryDamage(0);
		}
		setInventoryType(0);
	}

	void poof() {
		mod_Zeitgeist.proxy.poof(worldObj, posX, posY, posZ);
	}
}
