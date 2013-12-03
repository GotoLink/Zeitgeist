package zeitgeist.common.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import zeitgeist.common.mod_Zeitgeist;
import zeitgeist.common.zei_IBot;
import zeitgeist.common.zei_Ids;

public class zei_EntityBeacon extends EntityLiving implements zei_IBot {
	protected int siren;

	public zei_EntityBeacon(World world) {
		super(world);
		setSize(0.3F, 1F);
		health = 1;
		siren = 0;
	}

	public zei_EntityBeacon(World world, double d, double d1, double d2, String s) {
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

	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}

	public String getBotOwner() {
		return dataWatcher.getWatchableObjectString(17);
	}

	@Override
	public float getMaxHealth() {
		return 1;
	}

	public int getSiren() {
		return mod_Zeitgeist.proxy.getInt(dataWatcher, 18);
	}

	public String getTexture() {
		switch (getSiren()) {
		case 1:
			return "/zeitgeist/beac1.png";
		case 2:
			return "/zeitgeist/beac2.png";
			// case 0:return "/zeitgeist/.png";
		default:
			return "/zeitgeist/beaco.png";// super.getTexture();
		}
	}

	@Override
	public boolean interact(EntityPlayer entityplayer) {
		if (entityplayer.username.equalsIgnoreCase(getBotOwner())) {
			ItemStack itemstack = entityplayer.inventory.getCurrentItem();
			if (itemstack != null && itemstack.itemID == Item.stick.itemID) {
				if (!worldObj.isRemote) {
					setSiren(2);
				}
			} else {
				if (!worldObj.isRemote) {
					if (getSiren() != 0) {
						setSiren(0);
					} else {
						setSiren(1);
					}
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public void onDeath(DamageSource damagesource) {
		if (!worldObj.isRemote) {
			Dropper();// a(field_34905_c > 0);
		}
		worldObj.setEntityState(this, (byte) 3);
	}

	@Override
	public void onLivingUpdate() {
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		super.readEntityFromNBT(nbttagcompound);
		String s = nbttagcompound.getString("Owner");
		if (s.length() > 0) {
			setBotOwner(s);
		}
	}

	public void setBotOwner(String s) {
		dataWatcher.updateObject(17, s);
	}

	public void setSiren(int b) {
		siren = b;
		dataWatcher.updateObject(18, b);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		super.writeEntityToNBT(nbttagcompound);
		if (getBotOwner() == null) {
			nbttagcompound.setString("Owner", "");
		} else {
			nbttagcompound.setString("Owner", getBotOwner());
			// System.out.println("owned");
		}
	}

	protected void Dropper() {
		for (int j = 0; j < 20; j++) {
			double d = rand.nextGaussian() * 0.02D;
			double d1 = rand.nextGaussian() * 0.02D;
			double d2 = rand.nextGaussian() * 0.02D;
			worldObj.spawnParticle("explode", (posX + rand.nextFloat() * width * 2.0F) - width, posY + rand.nextFloat() * height, (posZ + rand.nextFloat() * width * 2.0F) - width, d, d1, d2);
		}
		if (!worldObj.isRemote) {
			entityDropItem(new ItemStack(zei_Ids.itemBeacon, 1, 0), 0.0F);
			setDead();
		}
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(17, ""); // owner
		dataWatcher.addObject(18, new Integer(siren)); // health
	}

	@Override
	protected String getDeathSound() {
		return "";
	}

	@Override
	protected String getHurtSound() {
		return "";
	}

	@Override
	protected float getSoundVolume() {
		return 0.4F;
	}
}
