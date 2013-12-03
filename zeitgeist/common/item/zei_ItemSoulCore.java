package zeitgeist.common.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import zeitgeist.common.entity.zei_EntityGolem;
import zeitgeist.common.entity.zei_EntityGolem2;
import zeitgeist.common.entity.zei_EntityGolemPure;
import zeitgeist.common.entity.zei_EntitySentry;
import zeitgeist.common.entity.zei_EntityTote;
import zeitgeist.common.entity.zei_EntityWorker;
import zeitgeist.common.tile.zei_TileEntityLatch;

public class zei_ItemSoulCore extends Item {
	public static final String names[] = { "soulSynth", "stoneRounded", "stoneBall", "stoneIncomplete", "stoneEmpty", "soulPure", "soulEvil", "", "", "", "", "", "", "", "", "" };
	static boolean derr[] = new boolean[256];
	int textur[];

	public zei_ItemSoulCore(int i) {
		super(i);
		setHasSubtypes(true);
		setMaxDamage(0);
		loadSprites();
	}

	@Override
	public Icon getIconFromDamage(int i) {
		return textur[i];
	}

	public String getItemNameIS(ItemStack itemstack) {
		return (new StringBuilder()).append(super.getUnlocalizedName()).append(".").append(names[itemstack.getItemDamage()]).toString();
	}

	public void loadSprites() {
		textur = new int[16];
		textur[0] =
				"/zeitgeist/soulSynth.png");
		textur[1] =
				"/zeitgeist/stoneRounded.png");
		textur[2] =
				"/zeitgeist/stoneBall.png");
		textur[3] =
				"/zeitgeist/stoneIncomplete.png");
		textur[4] =
				"/zeitgeist/stoneEmpty.png");
		textur[5] =
				"/zeitgeist/soulPure.png");
		textur[6] =
				"/zeitgeist/soulEvil.png");
		// textur[7] = ModLoader.addOverride("/gui/items.png",
		// "/zeitgeist/soulEvil.png"); ancients?
	}

	public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l) {
		if (zei_ItemSoulCore.touch(entityplayer, itemstack.getItemDamage(), world, i, j, k)) {
			itemstack.stackSize--;
			return true;
		}
		return false;
	}

	public static void mrChecker() {
		for (int ii = 0; ii < 256; ii++) {
			if (ii == zei_Ids.arch || (ii > 0 && ii <= 5) || (ii >= 12 && ii <= 17) || ii == 24 || ii == 35 || (ii >= 41 && ii <= 45) || ii == 48 || ii == 49 || ii == 61 || (ii >= 79 && ii <= 82)
					|| (ii >= 86 && ii <= 88) || (ii >= 98 && ii <= 100)) {
				derr[ii] = true;
			} else {
				derr[ii] = false;
			}
		}
		derr[zei_Ids.workerBlock] = true;
		derr[zei_Ids.sentryBlock] = true;
		derr[zei_Ids.toteBlock] = true;
		derr[zei_Ids.sulfOre] = true;
		derr[zei_Ids.saltOre] = true;
		// derr[zei_Ids.stoneFigure] = true;
	}

	public static boolean touch(EntityPlayer entityplaye, int dama, World world, int i, int j, int k) {
		int ii = world.getBlockId(i, j, k);
		if (derr[ii]) {
			if (world.isAirBlock(i, j, k)) {
				return false;
			}
			if (world.isRaining() && world.canLightningStrikeAt(i, j + 1, k)) {
				return false;
			}
			if (!world.isRemote) {
				if (ii == zei_Ids.workerBlock) {
					int m = world.getBlockMetadata(i, j, k);
					if (dama == 0) {
						world.spawnEntityInWorld(new zei_EntityWorker(world, i + 0.5F, j, k + 0.5F, m, entityplaye == null ? "huh" : entityplaye.username));
						world.setBlock(i, j, k, 0);
					} else if (dama == 5) {
						world.spawnEntityInWorld(new zei_EntityWorker(world, i + 0.5F, j, k + 0.5F, m, ""));
						world.setBlock(i, j, k, 0);
					} else if (dama == 6) {
						zei_EntityWorker wor = new zei_EntityWorker(world, i + 0.5F, j, k + 0.5F, m, "Tenebrae");
						wor.setMode(5);
						world.spawnEntityInWorld(wor);
						world.setBlock(i, j, k, 0);
					} else {
						return false;
					}
				} else if (ii == zei_Ids.sentryBlock) {
					if (dama != 0) {
						return false;
					}
					int m = world.getBlockMetadata(i, j, k);
					world.spawnEntityInWorld(new zei_EntitySentry(world, i + 0.5F, j, k + 0.5F, m, entityplaye == null ? "huh" : entityplaye.username));
					world.setBlock(i, j, k, 0);
				} else if (ii == zei_Ids.toteBlock) {
					if (dama != 0) {
						return false;
					}
					zei_EntityTote toter = new zei_EntityTote(world, i + 0.5F, j, k + 0.5F, entityplaye == null ? "huh" : entityplaye.username);
					world.spawnEntityInWorld(toter);
					zei_TileEntityLatch tot = (zei_TileEntityLatch) world.getBlockTileEntity(i, j, k);
					toter.inv.cargoItems = tot.dispenserContents.clone();
					tot.dispenserContents = new ItemStack[9];
					world.setBlockToAir(i, j, k);
				} else if (ii != 88) {
					Block bb = Block.blocksList[ii];
					int dam = world.getBlockMetadata(i, j, k);
					if (ii == zei_Ids.arch) {
						dam = 0;
					}
					if (dama == 0) {
						int he = 1 + MathHelper.floor_double(bb.getHardness() * 3);
						world.spawnEntityInWorld(new zei_EntityGolem(world, i + 0.5F, j, k + 0.5F, ii, he, dam));
					} else if (dama == 5) {
						int he = 1 + MathHelper.floor_double(bb.getHardness() * 8);
						world.spawnEntityInWorld(new zei_EntityGolemPure(world, i + 0.5F, j, k + 0.5F, ii, he, dam));
					} else {
						return false;
					}
					world.setBlockToAir(i, j, k);
				} else {
					world.spawnEntityInWorld(new zei_EntityGolem2(world, i + 0.5F, j, k + 0.5F));
					world.setBlockToAir(i, j, k);
				}
			}
			return true;
		}
		return false;
	}
}
