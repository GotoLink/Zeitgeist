package zeitgeist.common;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import zeitgeist.common.entity.zei_EntityGolem;
import zeitgeist.common.entity.zei_EntityGolemPure;
import zeitgeist.common.entity.zei_EntityMaker;
import zeitgeist.common.entity.zei_EntityNoop;
import zeitgeist.common.entity.zei_EntityRemnant;
import zeitgeist.common.world.gen.zei_WorldGenDwelling;

public class zei_Spells {
	public static void absorbPlant(World world, int i, int j, int k, EntityPlayer entityPlayer) {
		int u = 0;
		int l = 0;
		mod_Zeitgeist.proxy.bomf(world, i, j, k);
		while (u < 90 && l < 300) {
			l++;
			if (derg(world, i, j, k)) {
				u++;
			}
		}
		if (u >= 40) {
			EntityItem ei = new EntityItem(world, i + Math.random() * 2 - 1, j, k + Math.random() * 2 - 1, new ItemStack(zei_Ids.plantMass, 1, 0));
			world.spawnEntityInWorld(ei);
		}
	}

	public static boolean absorbSoul(World world, int i, int j, int k, EntityPlayer entityPlayer) {
		List L = world.getEntitiesWithinAABB(EntityItem.class, AxisAlignedBB.getBoundingBox(i - 2, j - 2, k - 2, i + 2, j + 2, k + 2));
		EntityItem targetItem = null;
		if (!L.isEmpty()) {
			for (int n = 0; n < L.size(); n++) {
				EntityItem e = (EntityItem) L.get(n);
				if (e.getEntityItem().itemID == zei_Ids.soulCore && e.getEntityItem().getItemDamage() == 4) {
					targetItem = e;
					break;
				}
			}
		} else {
			return false;
		}
		if (targetItem == null) {
			return false;
		}
		L = world.getEntitiesWithinAABB(EntityLiving.class, AxisAlignedBB.getBoundingBox(i - 2, j - 2, k - 2, i + 2, j + 2, k + 2));
		boolean complete = false;
		if (!L.isEmpty()) {
			for (int n = 0; n < L.size(); n++) {
				EntityLiving el = (EntityLiving) L.get(n);
				if (!complete) {
					if (el instanceof EntityPlayer || el instanceof zei_IBot || el instanceof EntityTameable || el instanceof EntityDragonBase || el instanceof EntityGolem) {
					} else if (el instanceof EntityAnimal) {
						if (!(el instanceof EntityChicken)) {
							dropA(world, el);
						}
						dropB(world, el);
						mod_Zeitgeist.proxy.gorey(world, i, j, k);
						complete = true;
						targetItem.getEntityItem().stackSize = 1;
						targetItem.getEntityItem().setItemDamage(5);
					} else if (el instanceof EntityMob) {
						if (el instanceof EntityZombie) {
							if (el instanceof EntityPigZombie) {
								dropA(world, el);
							} else {
								dropC(world, el);
							}
							dropB(world, el);
							mod_Zeitgeist.proxy.gorey(world, i, j, k);
						} else if (el instanceof EntitySkeleton) {
							dropC(world, el);
							dropB(world, el);
						} else if (el instanceof EntityEnderman) {
							EntityItem ei = new EntityItem(world, el.posX + Math.random() * 2 - 1, el.posY, el.posZ + Math.random() * 2 - 1, new ItemStack(Item.flint, 1));
							world.spawnEntityInWorld(ei);
						} else {
							dropB(world, el);
							mod_Zeitgeist.proxy.gorey(world, i, j, k);
						}
						complete = true;
						targetItem.getEntityItem().stackSize = 1;
						targetItem.getEntityItem().setItemDamage(6);
					}
					if (complete) {
						el.attackEntityFrom(DamageSource.magic, 999);
					}
				}
				el.attackEntityFrom(DamageSource.magic, 4);
			}
		}
		return complete;
	}

	public static void awful(World world, int i, int j, int k, EntityPlayer entityPlayer) {
		for (int m = 0; m < 6; m++) {
			EntityCreeper ep = new EntityCreeper(world);
			double d = i + 0.5f;
			double d1 = j + 0.5f;
			double d2 = k + 0.5f;
			ep.setPosition(d, d1, d2);
			/*
			 * ep.motionX = 0.0D; ep.motionY = 0.0D; ep.motionZ = 0.0D;
			 * ep.prevPosX = d; ep.prevPosY = d1; ep.prevPosZ = d2;
			 */
			world.spawnEntityInWorld(ep);
		}
	}

	public static boolean copySoul(World world, int i, int j, int k, EntityPlayer entityPlayer) {
		List L = world.getEntitiesWithinAABB(EntityItem.class, AxisAlignedBB.getBoundingBox(i - 2, j - 2, k - 2, i + 2, j + 2, k + 2));
		EntityItem targetItem = null;
		if (L.size() > 1) {
			for (int n = 0; n < L.size(); n++) {
				EntityItem e = (EntityItem) L.get(n);
				if (e.getEntityItem().itemID == zei_Ids.soulCore && e.getEntityItem().getItemDamage() >= 5) {
					targetItem = e;
					break;
				}
			}
		} else {
			return false;
		}
		L = world.getEntitiesWithinAABB(EntityItem.class, AxisAlignedBB.getBoundingBox(i - 2, j - 2, k - 2, i + 2, j + 2, k + 2));
		boolean success = false;
		for (int n = 0; n < L.size(); n++) {
			EntityItem e = (EntityItem) L.get(n);
			if (e.getEntityItem().itemID == zei_Ids.soulCore && e.getEntityItem().getItemDamage() == 4) {
				e.getEntityItem().setItemDamage(0);
				success = true;
			}
		}
		return success;
	}

	public static void DELETESTUFF(World world, int i, int j, int k, EntityPlayer entityPlayer) {
		for (int jj = j; jj > 2; jj--) {
			for (int ii = -3; ii < 3; ii++) {
				for (int kk = -3; kk < 3; kk++) {
					world.setBlock(i + ii, jj, k + kk, 0);
				}
			}
		}
	}

	public static boolean derg(World world, int i, int j, int k) {
		i += world.rand.nextInt(9) - 4;
		j += world.rand.nextInt(6) - 1;
		k += world.rand.nextInt(9) - 4;
		Material m = world.getBlockMaterial(i, j, k);
		int id = world.getBlockId(i, j, k);
		if (m == Material.cactus || m == Material.grass || m == Material.plants || m == Material.leaves || m == Material.vine) {
			if (id == 2) {
				world.setBlock(i, j, k, 3);
			} else {
				world.setBlock(i, j, k, 0);
				EntityItem ei = new EntityItem(world, i, j, k, new ItemStack(Item.stick, 1));
				world.spawnEntityInWorld(ei);
				int f = world.getTopSolidOrLiquidBlock(i, k);
				if (world.getBlockId(i, f, k) == 2) {
					world.setBlock(i, f, k, 3);
				} else {
					world.setBlock(i, f, k, 0);
				}
			}
			return true;
		} else if (id == 17) {
			world.setBlock(i, j, k, 12);
			int n = 1;
			while (world.getBlockId(i, j + n, k) == 17) {
				world.setBlock(i, j + n, k, 12);
				n++;
			}
			return true;
		}
		return false;
	}

	public static boolean dimensionSurface(World world, int i, int j, int k, EntityPlayer entityPlayer) {
		if (entityPlayer.dimension != 0) {
			mod_Zeitgeist.proxy.changeDimension(entityPlayer, 0);
			return true;
		}
		return false;
	}

	/*
	 * public static void makeTimeMachine(World world, int i, int j, int
	 * k,EntityPlayer entityPlayer) { zei_EntityTimeMachine ep= new
	 * zei_EntityTimeMachine(world); double d=i+0.5f; double d1=j+0.5f; double
	 * d2=k+0.5f; ep.setPosition(d, d1, d2); world.entityJoinedWorld(ep); }
	 */
	public static boolean dimensionZei(World world, int i, int j, int k, EntityPlayer entityPlayer) {
		if (entityPlayer.dimension != 99) {
			mod_Zeitgeist.proxy.changeDimension(entityPlayer, 99);
			return true;
		} else if (entityPlayer.dimension == 99) {
			mod_Zeitgeist.proxy.changeDimension(entityPlayer, 0);
			return true;
		}
		return false;
	}

	public static void godamnRain(World world, int i, int j, int k, EntityPlayer entityPlayer) {
		world.getWorldInfo().setRaining(false);
	}

	public static void kittens(World world, int i, int j, int k, EntityPlayer entityPlayer) {
		EntityOcelot o;
		for (int h = 0; h < 7; h++) {
			o = new EntityOcelot(world);
			world.spawnEntityInWorld(o);
			o.setPosition(i, j, k);
			o.setTamed(true);
			// o.func_48147_c(1 + world.rand.nextInt(3));
			o.setOwner(entityPlayer.username);
			// o.func_48142_a(true);
			// o.aiSit.func_48407_a(true);
			// o.worldObj.setEntityState(o, (byte)7);
		}
	}

	/*
	 * public static void easyTele(World world, int i, int j, int k,EntityPlayer
	 * entityPlayer) { zei_Dimensions.usePortal(zei_EnumDimension.eupraxia); }
	 */
	public static void light(World world, int i, int j, int k, EntityPlayer entityPlayer) {
		world.setLightValue(EnumSkyBlock.Sky, i, j, k, 15);
		// world.scheduleLightingUpdate(EnumSkyBlock.Sky, i-2, j-2, k-2, i+2,
		// j+2, k+2);
		// world.markBlocksDirty(i-2, j-2, k-2, i+2, j+2, k+2);
	}

	public static void makeBigExplosion(World world, int i, int j, int k, EntityPlayer entityPlayer) {
		world.createExplosion(entityPlayer, i, j, k, 30f, true);
	}

	public static void makeDay(World world, int i, int j, int k, EntityPlayer entityPlayer) {
		world.setWorldTime(0);
	}

	public static void makeExplosion(World world, int i, int j, int k, EntityPlayer entityPlayer) {
		world.createExplosion(entityPlayer, i, j, k, 5f, true);
	}

	public static boolean makeMimic(World world, int i, int j, int k, EntityPlayer entityPlayer) {
		List L = world.getEntitiesWithinAABB(EntityItem.class, AxisAlignedBB.getBoundingBox(i - 2, j - 1, k - 2, i + 2, j + 1, k + 2));
		EntityItem targetItem = null;
		if (!L.isEmpty()) {
			for (int n = 0; n < L.size(); n++) {
				EntityItem e = (EntityItem) L.get(n);
				// System.out.println("id "+e.item.itemID);
				if (e.getEntityItem().itemID == 1) {
					if (e.getEntityItem().stackSize > 1) {
						e.getEntityItem().stackSize--;
					} else {
						e.setDead();
					}
					EntityItem e2 = new EntityItem(world, i + 0.5f, j + 0.5f, k + 0.5f, new ItemStack(zei_Ids.stoneFigureItem, 1, 0));
					world.spawnEntityInWorld(e2);
					return true;
				}
			}
		}
		return false;
	}

	public static void makeNight(World world, int i, int j, int k, EntityPlayer entityPlayer) {
		world.setWorldTime(13500);
	}

	/*
	 * public static void MusicMaker(World world,int i,int j,int k,EntityPlayer
	 * entityPlayer) { zei_EntityMusicMaker ep= new zei_EntityMusicMaker(world,
	 * "leader"); ep.setPosition(i+2.5f, j+0.5f, k+0.5f);
	 * world.entityJoinedWorld(ep); ep = new zei_EntityMusicMaker(world,
	 * "gatherer"); ep.setPosition(i+0.5f, j+0.5f, k-2.5f);
	 * world.entityJoinedWorld(ep); ep = new zei_EntityMusicMaker(world,
	 * "guardian"); ep.setPosition(i+2.5f, j+0.5f, k+2.5f);
	 * world.entityJoinedWorld(ep); }
	 */
	/*
	 * public static void vendor(World world,int i,int j,int k,EntityPlayer
	 * entityPlayer) { GurEntityVendingMachine ep= new
	 * GurEntityVendingMachine(world); ep.setPosition(i+0.5f, j+0.5f, k+0.5f);
	 * world.entityJoinedWorld(ep); }
	 */
	public static boolean makeTree(World world, int i, int j, int k, EntityPlayer entityPlayer) {
		world.setBlock(i, j - 1, k, 2);
		// world.setBlock(i, j, k, 0);
		WorldGenerator obj = new WorldGenBigTree(true);
		return obj.generate(world, world.rand, i, j, k);
	}

	public static void oneGuy(World world, int i, int j, int k, int m) {
		world.setBlock(i, j - 1, k, 8);
		// world.setBlock(i-1, j-1, k, 1);
		// world.setBlock(i+1, j-1, k, 1);
		// world.setBlock(i, j-1, k-1, 1);
		// world.setBlock(i, j-1, k+1, 1);
		world.setBlock(i, j, k, zei_Ids.stoneFigure, m, 3);
		world.setBlock(i, j + 1, k, zei_Ids.stoneFigure, m, 3);
		world.setBlock(i, j + 2, k, zei_Ids.stoneFigure, m, 3);
	}

	public static void sentries(World world, int i, int j, int k, EntityPlayer entityPlayer) {
		// WHYD I DO THSI BY HAND LOL
		oneGuy(world, i - 5, j, k - 4, 1);
		oneGuy(world, i - 6, j, k - 3, 1);
		oneGuy(world, i - 6, j, k - 2, 1);
		oneGuy(world, i - 7, j, k - 1, 1);
		oneGuy(world, i - 7, j, k, 1);
		oneGuy(world, i - 7, j, k + 1, 1);
		oneGuy(world, i - 6, j, k + 2, 1);
		oneGuy(world, i - 6, j, k + 3, 1);
		oneGuy(world, i - 5, j, k + 4, 1);
		oneGuy(world, i - 4, j, k + 5, 2);
		oneGuy(world, i - 3, j, k + 6, 2);
		oneGuy(world, i - 2, j, k + 6, 2);
		oneGuy(world, i - 1, j, k + 7, 2);
		oneGuy(world, i, j, k + 7, 2);
		oneGuy(world, i + 1, j, k + 7, 2);
		oneGuy(world, i + 2, j, k + 6, 2);
		oneGuy(world, i + 3, j, k + 6, 2);
		oneGuy(world, i + 4, j, k + 5, 2);
		oneGuy(world, i + 5, j, k + 4, 0);
		oneGuy(world, i + 6, j, k + 3, 0);
		oneGuy(world, i + 6, j, k + 2, 0);
		oneGuy(world, i + 7, j, k + 1, 0);
		oneGuy(world, i + 7, j, k, 0);
		oneGuy(world, i + 7, j, k - 1, 0);
		oneGuy(world, i + 6, j, k - 2, 0);
		oneGuy(world, i + 6, j, k - 3, 0);
		oneGuy(world, i + 5, j, k - 4, 0);
		oneGuy(world, i + 4, j, k - 5, 3);
		oneGuy(world, i + 3, j, k - 6, 3);
		oneGuy(world, i + 2, j, k - 6, 3);
		oneGuy(world, i + 1, j, k - 7, 3);
		oneGuy(world, i, j, k - 7, 3);
		oneGuy(world, i - 1, j, k - 7, 3);
		oneGuy(world, i - 2, j, k - 6, 3);
		oneGuy(world, i - 3, j, k - 6, 3);
		oneGuy(world, i - 4, j, k - 5, 3);
		// 0
	}

	public static void spawnBuilding(World world, int i, int j, int k, EntityPlayer entityPlayer) {
		zei_WorldGenDwelling gen = new zei_WorldGenDwelling();
		gen.generate(world, world.rand, i, j, k);
	}

	public static void spawnEvil(World world, int i, int j, int k, EntityPlayer entityPlayer) {
		zei_EntityGolemPure ep = new zei_EntityGolemPure(world, i + 0.5f, j + 1, k + 0.5f, zei_Ids.arch, 10, 5);
		world.spawnEntityInWorld(ep);
		zei_EntityGolem eg = new zei_EntityGolem(world, i + 0.5f, j + 1, k + 0.5f, zei_Ids.arch, 10, 5);
		world.spawnEntityInWorld(eg);
	}

	public static void spawnEvilThingy(World world, int i, int j, int k, EntityPlayer entityPlayer) {
		zei_EntityRemnant ep = new zei_EntityRemnant(world);
		ep.setPosition(i + 0.5f, j + 0.5f, k + 0.5f);
		world.spawnEntityInWorld(ep);
	}

	public static void spawnLoot(World world, int i, int j, int k, EntityPlayer entityPlayer) {
		EntityItem ei = new EntityItem(world, i, j, k, new ItemStack(Item.pickaxeDiamond, 1));
		world.spawnEntityInWorld(ei);
		ei = new EntityItem(world, i, j, k, new ItemStack(Block.cloth, 64));
		world.spawnEntityInWorld(ei);
		ei = new EntityItem(world, i, j, k, new ItemStack(zei_Ids.soulCore, 64, 0));
		world.spawnEntityInWorld(ei);
		ei = new EntityItem(world, i, j, k, new ItemStack(zei_Ids.soulCore, 64, 5));
		world.spawnEntityInWorld(ei);
		ei = new EntityItem(world, i, j, k, new ItemStack(42, 64, 0));
		world.spawnEntityInWorld(ei);
		ei = new EntityItem(world, i, j, k, new ItemStack(1, 64, 0));
		world.spawnEntityInWorld(ei);
		ei = new EntityItem(world, i, j, k, new ItemStack(zei_Ids.windmill, 64, 0));
		world.spawnEntityInWorld(ei);
		ei = new EntityItem(world, i, j, k, new ItemStack(Block.wood, 64));
		world.spawnEntityInWorld(ei);
	}

	public static void spawnNoop(World world, int i, int j, int k, EntityPlayer entityPlayer) {
		zei_EntityNoop ep = new zei_EntityNoop(world);
		ep.setPosition(i + 0.5f, j + 0.5f, k + 0.5f);
		world.spawnEntityInWorld(ep);
	}

	public static void spawnPig(World world, int i, int j, int k, EntityPlayer entityPlayer) {
		EntityPig ep = new EntityPig(world);
		ep.setPosition(i + 0.5f, j + 0.5f, k + 0.5f);
		world.spawnEntityInWorld(ep);
	}

	public static void spawnThing(World world, int i, int j, int k, EntityPlayer entityPlayer) {
		zei_EntityMaker ep = new zei_EntityMaker(world);
		ep.setPosition(i + 0.5f, j + 0.5f, k + 0.5f);
		world.spawnEntityInWorld(ep);
	}

	public static void spawnZombie(World world, int i, int j, int k, EntityPlayer entityPlayer) {
		for (int u = 0; u < 5; u++) {
			EntityZombie ep = new EntityZombie(world);
			ep.setPosition(i + 0.5f, j + 0.5f, k + 0.5f);
			world.spawnEntityInWorld(ep);
		}
	}

	public static void unlight(World world, int i, int j, int k, EntityPlayer entityPlayer) {
		world.setLightValue(EnumSkyBlock.Sky, i, j, k, 0);
		// world.scheduleLightingUpdate(EnumSkyBlock.Sky, i-2, j-2, k-2, i+2,
		// j+2, k+2);
		// world.markBlocksDirty(i-2, j-2, k-2, i+2, j+2, k+2);
	}

	public static void uppy(World world, int i, int j, int k, EntityPlayer entityPlayer) {
		EntityItem ei = new EntityItem(world, i, j, k, new ItemStack(Item.pickaxeDiamond, 1));
		world.spawnEntityInWorld(ei);
		ei = new EntityItem(world, i, j, k, new ItemStack(Block.brick, 64));
		world.spawnEntityInWorld(ei);
		int jj = j;
		while (jj < 126) {
			world.setBlock(i, jj, k, 0);
			jj++;
		}
	}

	private static void dropA(World world, Entity e) {
		EntityItem ei = new EntityItem(world, e.posX + Math.random() * 2 - 1, e.posY, e.posZ + Math.random() * 2 - 1, new ItemStack(zei_Ids.skullA, 1, 0));
		world.spawnEntityInWorld(ei);
	}

	private static void dropB(World world, Entity e) {
		EntityItem ei;
		for (int m = 0; m < 5; m++) {
			ei = new EntityItem(world, e.posX + Math.random() * 2 - 1, e.posY, e.posZ + Math.random() * 2 - 1, new ItemStack(Item.bone, 1));
			world.spawnEntityInWorld(ei);
		}
	}

	private static void dropC(World world, Entity e) {
		EntityItem ei = new EntityItem(world, e.posX + Math.random() * 2 - 1, e.posY, e.posZ + Math.random() * 2 - 1, new ItemStack(zei_Ids.skull, 1, 0));
		world.spawnEntityInWorld(ei);
	}
}
