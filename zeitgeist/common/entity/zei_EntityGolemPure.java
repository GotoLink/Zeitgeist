package zeitgeist.common.entity;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import zeitgeist.common.mod_Zeitgeist;
import zeitgeist.common.zei_IBot;
import zeitgeist.common.zei_Ids;

public class zei_EntityGolemPure extends zei_EntityGolem implements zei_IBot {
	public int form = 0;
	public String texture;

	public zei_EntityGolemPure(int u, World world, double d, double d1, double d2, int I, int h, int dam) {
		super(world, d, d1, d2, I, h, dam);
		setForm(u);
	}

	public zei_EntityGolemPure(World world) {
		super(world);
		texture = "/zeitgeist/golemPure.png";
		setSize(1.2F, 0.8F);
		health = 10;
		maxHealth = 10;
		init();
	}

	public zei_EntityGolemPure(World world, double d, double d1, double d2, int I, int h, int dam) {
		super(world, d, d1, d2, I, h, dam);
		setForm(worldObj.rand.nextInt(3));
	}

	@Override
	public void derk() {
	}

	public int getForm() {
		return mod_Zeitgeist.proxy.getInt(dataWatcher, 18);
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 3;
	}

	@Override
	public void init() {
		this.getNavigator().setAvoidsWater(true);
		float var2 = 0.25F;
		this.tasks.addTask(1, new EntityAIPanic(this, 0.38F));
		this.tasks.addTask(5, new EntityAIWander(this, var2));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(7, new EntityAILookIdle(this));
	}

	@Override
	public boolean isAIEnabled() {
		return true;
	}

	@Override
	public boolean isOnLadder() {
		return isCollidedHorizontally && getForm() == 0;
	}

	@Override
	public ItemStack item() {
		return new ItemStack(zei_Ids.soulCore, 1, 5);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		super.readEntityFromNBT(nbttagcompound);
		setForm(nbttagcompound.getInteger("form"));
	}

	public void set(float x, float y) {
		setSize(x, y);
		float var7 = x / 2f;
		float var8 = y;
		this.boundingBox.setBounds(posX - var7, posY - this.yOffset + this.ySize, posZ - var7, posX + var7, posY - this.yOffset + this.ySize + var8, posZ + var7);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		super.writeEntityToNBT(nbttagcompound);
		nbttagcompound.setInteger("form", getForm());
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(18, Integer.valueOf(form));
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
		list1 = worldObj.getEntitiesWithinAABB(zei_EntityGolem.class, boundingBox.expand(f, f, f));
		for (int j = 0; j < list1.size(); j++) {
			zei_EntityGolem e = (zei_EntityGolem) list1.get(j);
			if (!(e instanceof zei_EntityGolemPure) && e.getType() == T) {
				return e;
			}
		}
		return null;
	}

	protected void setForm(int i) {
		form = i;
		// System.out.println("form up "+form);
		dataWatcher.updateObject(18, Integer.valueOf(i));
		float var7;
		float var8;
		switch (i) {
		case 1:
			texture = "/zeitgeist/golemPureLong.png";
			set(0.2F, 1.5F);
			break;
		case 2:
			texture = "/zeitgeist/golemPureBi.png";
			set(0.25f, 1.5f);
			break;
		default:
			texture = "/zeitgeist/golemPure.png";
			set(1.2f, 0.8f);
		}
	}

	@Override
	protected void setType(int i) {
		this.tasks.addTask(1, new EntityAITempt(this, 0.25F, i, false));
		super.setType(i);
	}
}
