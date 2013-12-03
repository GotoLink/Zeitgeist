package zeitgeist.common.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import zeitgeist.common.zei_IBot;
import zeitgeist.common.zei_Ids;

public class zei_EntityOmni extends EntityLiving implements zei_IBot {
	public zei_EntityOmni(World world) {
		super(world);
		setSize(0.3F, 1.2F);
		texture = "/zeitgeist/omni.png";
	}

	public zei_EntityOmni(World world, double d, double d1, double d2) {
		this(world);
		setPosition(d, d1 + yOffset, d2);
		motionX = 0.0D;
		motionY = 0.0D;
		motionZ = 0.0D;
		prevPosX = d;
		prevPosY = d1;
		prevPosZ = d2;
	}

	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}

	@Override
	public float getMaxHealth() {
		return 1;
	}

	// protected String texture;
	@Override
	public boolean interact(EntityPlayer entityplayer) {
		ItemStack itemstack = entityplayer.inventory.getCurrentItem();
		if (itemstack != null) {
			if (itemstack.itemID == Item.porkRaw.itemID || itemstack.itemID == Item.porkCooked.itemID) {
				metamorph(new EntityPig(worldObj));
				return true;
			}
			if (itemstack.itemID == Block.cloth.blockID) {
				metamorph(new EntitySheep(worldObj));
				return true;
			}
			if (itemstack.itemID == Item.egg.itemID || itemstack.itemID == Item.chickenRaw.itemID || itemstack.itemID == Item.feather.itemID) {
				metamorph(new EntityChicken(worldObj));
				return true;
			}
			if (itemstack.itemID == Item.leather.itemID) {
				metamorph(new EntityCow(worldObj));
				return true;
			}
			if (itemstack.itemID == Item.rottenFlesh.itemID) {
				metamorph(new EntityZombie(worldObj));
				return true;
			}
			if (itemstack.itemID == Item.bone.itemID) {
				metamorph(new EntitySkeleton(worldObj));
				return true;
			}
			if (itemstack.itemID == Item.silk.itemID) {
				metamorph(new EntitySpider(worldObj));
				return true;
			}
			if (itemstack.itemID == Block.web.blockID) {
				metamorph(new EntityCaveSpider(worldObj));
				return true;
			}
			if (itemstack.itemID == Item.gunpowder.itemID) {
				metamorph(new EntityCreeper(worldObj));
				return true;
			}
			if (itemstack.itemID == Item.slimeBall.itemID) {
				EntitySlime es = new EntitySlime(worldObj);
				// System.out.s(""+ (1 << 2));
				es.setSlimeSize(1);
				metamorph(es);
				return true;
			}
			if (itemstack.itemID == zei_Ids.soulCore) {
				metamorph(new zei_EntityHelios(worldObj));
				return true;
			}
			if (itemstack.itemID == Item.dyePowder.itemID && itemstack.getItemDamage() == 0) {
				metamorph(new EntitySquid(worldObj));
				return true;
			}
			if (itemstack.itemID == zei_Ids.boing) {
				metamorph(new zei_EntitySlider(worldObj));
				return true;
			}
			/*
			 * if (itemstack.itemID == zei_Ids.crystal) { metamorph(new
			 * zei_EntityWatcher(worldObj)); return true; }
			 */
			if (itemstack.itemID == Item.enderPearl.itemID) {
				metamorph(new EntityEnderman(worldObj));
				return true;
			}
			if (itemstack.itemID == Item.swordGold.itemID) {
				/*
				 * try { Class c = Class.forName("EntityPigZombie"); Constructor
				 * co = c.getConstructor(new Class[]{World.class});
				 * metamorph((Entity)co.newInstance(worldObj)); }catch(Exception
				 * e) { }
				 */
				metamorph(new EntityPigZombie(worldObj));
				return true;
			}
		}
		return false;
	}

	public void metamorph(Entity ep) {
		if (!worldObj.isRemote) {
			double d = posX;
			double d1 = posY;
			double d2 = posZ;
			ep.setPosition(d, d1 + yOffset, d2);
			ep.motionX = 0.0D;
			ep.motionY = 0.0D;
			ep.motionZ = 0.0D;
			ep.prevPosX = d;
			ep.prevPosY = d1;
			ep.prevPosZ = d2;
			worldObj.spawnEntityInWorld(ep);
			setDead();
		}
		// particles?
		// if(!worldObj.multiplayerWorld){
		// if(s=="pig"){
		// Entity ep= new Entity(worldObj);
		// ep2=cl.cast(ep);
		// (cl) ep= new (cl)(worldObj);
		// cl.cast(ep);
		// ep.posX=posX;ep.posY=posY;ep.posZ=posZ;
		// worldObj.entityJoinedWorld(ep);
		// }
		// }
	}

	@Override
	public void onLivingUpdate() {
	}

	protected void a(Boolean b) {
		for (int j = 0; j < 20; j++) {
			double d = rand.nextGaussian() * 0.02D;
			double d1 = rand.nextGaussian() * 0.02D;
			double d2 = rand.nextGaussian() * 0.02D;
			worldObj.spawnParticle("explode", (posX + rand.nextFloat() * width * 2.0F) - width, posY + rand.nextFloat() * height, (posZ + rand.nextFloat() * width * 2.0F) - width, d, d1, d2);
		}
		if (!worldObj.isRemote) {
			// entityDropItem(new ItemStack(zei_Ids.itemOmni, 1,0), 0.0F);
			setDead();
		}
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
