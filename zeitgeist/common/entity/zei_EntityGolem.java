package zeitgeist.common.entity;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import zeitgeist.common.mod_Zeitgeist;
import zeitgeist.common.zei_IBot;
import zeitgeist.common.zei_Ids;

public class zei_EntityGolem extends zei_EntityAniBot implements zei_IBot {
	public int colo = 0;
	/*
	 * public String getEntityTexture(){ if(getType()==2){ return
	 * "/automatons/agol1.png"; }else{ return "/automatons/agol2.png"; } }
	 */
	public int type = 0;
	int maxHealth = 5;

	public zei_EntityGolem(World world) {
		super(world);
		texture = "/zeitgeist/agol2.png";
		setSize(0.9F, 0.99F);
		health = 5;
		maxHealth = 5;
		if (getType() == 0) {
			switch (world.rand.nextInt(2)) {
			case 1:
				setType(2);
			case 2:
				setType(zei_Ids.arch);
				setColo(5);
				break;
			case 3:
				setType(4);
				break;
			case 4:
				setType(1);
				break;
			default:
				setType(82);
				break;
			}
		}
		init();
	}

	public zei_EntityGolem(World world, double d, double d1, double d2, int I, int h, int dam) {
		this(world);
		setPosition(d, d1 + yOffset, d2);
		motionX = 0.0D;
		motionY = 0.0D;
		motionZ = 0.0D;
		prevPosX = d;
		prevPosY = d1;
		prevPosZ = d2;
		setPathToEntity(null);
		setType(I);
		setColo(dam);
		health = h;
		maxHealth = h;
	}

	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}

	public void derk() {
		fleeingTick = 0;
	}

	@Override
	public AxisAlignedBB getBoundingBox() {
		return boundingBox;
	}

	@Override
	public boolean getCanSpawnHere() {
		return true;
	}

	@Override
	public AxisAlignedBB getCollisionBox(Entity par1Entity) {
		return par1Entity.boundingBox;
	}

	public int getColo() {
		return mod_Zeitgeist.proxy.getInt(dataWatcher, 17);
	}

	@Override
	public float getMaxHealth() {
		return maxHealth;
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 20;
	}

	public int getType() {
		return mod_Zeitgeist.proxy.getInt(dataWatcher, 16);
	}

	public void init() {
	}

	public ItemStack item() {
		return new ItemStack(zei_Ids.soulCore, 1, 0);
	}

	@Override
	public void onDeath(DamageSource damagesource) {
		super.onDeath(damagesource);
		if (!worldObj.isRemote) {
			Dropper();// a(field_34905_c > 0);
		}
		setDead();
	}

	@Override
	public void onLivingUpdate() {
		derk();
		if (isWet()) {
			Dropper();
		}
		super.onLivingUpdate();
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		super.readEntityFromNBT(nbttagcompound);
		setType(nbttagcompound.getInteger("type"));
		setColo(nbttagcompound.getInteger("colo"));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		super.writeEntityToNBT(nbttagcompound);
		nbttagcompound.setInteger("type", getType());
		nbttagcompound.setInteger("colo", getColo());
	}

	@Override
	protected void attackEntity(Entity en, float par2) {
		if (en instanceof zei_EntityGolem) {
			if (attackTime <= 0 && par2 < 2.0F && en.boundingBox.maxY > boundingBox.minY && en.boundingBox.minY < boundingBox.maxY) {
				attackTime = 10;
				en.attackEntityFrom(DamageSource.causeMobDamage(this), 1);
			}
		} else {
			entityToAttack = null;
		}
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(16, Integer.valueOf(type));
		dataWatcher.addObject(17, Integer.valueOf(colo));
	}

	@Override
	protected Entity findPlayerToAttack() {
		if (fleeingTick > 0) {
			return null;
		}
		float f = 8F;
		List list1 = worldObj.getEntitiesWithinAABB(EntityPlayer.class, boundingBox.expand(f, f, f));
		int T = getType();
		for (int j = 0; j < list1.size(); j++) {
			EntityPlayer entityplayer = (EntityPlayer) list1.get(j);
			if (entityplayer.getCurrentEquippedItem() != null && entityplayer.getCurrentEquippedItem().itemID == T) {
				return entityplayer;
			}
		}
		return null;
	}

	@Override
	protected String getDeathSound() {
		return "step.gravel";
	}

	@Override
	protected int getDropItemId() {
		return 0;
	}

	@Override
	protected String getHurtSound() {
		return "step.stone";
	}

	@Override
	protected String getLivingSound() {
		return "";
	}

	protected void setColo(int i) {
		colo = i;
		dataWatcher.updateObject(17, Integer.valueOf(i));
	}

	protected void setType(int i) {
		type = i;
		dataWatcher.updateObject(16, Integer.valueOf(i));
	}

	void Dropper() {
		for (int j = 0; j < 20; j++) {
			double d = rand.nextGaussian() * 0.02D;
			double d1 = rand.nextGaussian() * 0.02D;
			double d2 = rand.nextGaussian() * 0.02D;
			worldObj.spawnParticle("explode", (posX + rand.nextFloat() * width * 2.0F) - width, posY + rand.nextFloat() * height, (posZ + rand.nextFloat() * width * 2.0F) - width, d, d1, d2);
		}
		if (!worldObj.isRemote) {
			entityDropItem(item(), 0);
			int xx = MathHelper.floor_double(posX);
			int yy = MathHelper.floor_double(posY);
			int zz = MathHelper.floor_double(posZ);
			Material mat = worldObj.getBlockMaterial(xx, yy, zz);
			int iii = getType();
			if (Block.blocksList[iii].canPlaceBlockAt(worldObj, xx, yy, zz)) {
				worldObj.setBlock(xx, yy, zz, iii, getColo(), 3);
			}
			setDead();
		}
	}
}
