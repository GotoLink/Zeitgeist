package zeitgeist.client;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import zeitgeist.common.entity.zei_EntityBobby;
import zeitgeist.common.entity.zei_EntityHelios;
import zeitgeist.common.entity.zei_EntityRemnant;
import zeitgeist.common.entity.zei_EntityWatcher;

public class zeiOld_BlockDeployer extends Block {
	static Icon[] D;

	protected zeiOld_BlockDeployer(int i) {
		super(i, Material.glass);
		setTickRandomly(true);
	}

	public void deploy(int R, World world, int i, int j, int k) {
		AxisAlignedBB aab = getCollisionBoundingBoxFromPool(world, i, j, k);
		if (world.getClosestPlayer(i, j, k, 50) != null && world.checkIfAABBIsClear(aab.expand(0D, 2D, 0D)) && world.countEntities(EntityLiving.class) < zei_Ids.maxDeployableEntities) {
			int l = world.getBlockMetadata(i, j, k);
			if (l == 0) {
				R = 5;
			} else if (l == 2) {
				R = 3;
			}
			if (R == 1) {
				world.spawnEntityInWorld(new zei_EntityWatcher(world, i + 0.5F, j + 1.5F, k + 0.5F));
			} else if (R == 2) {
				world.spawnEntityInWorld(new zei_EntityBobby(world, i + 0.5F, j + 1.5F, k + 0.5F));
			} else if (R == 3) {
				world.spawnEntityInWorld(new zei_EntityRemnant(world, i + 0.5F, j + 1.5F, k + 0.5F));
				world.spawnEntityInWorld(new zei_EntityRemnant(world, i + 0.5F, j + 1.5F, k + 0.5F));
				world.spawnEntityInWorld(new zei_EntityRemnant(world, i + 0.5F, j + 1.5F, k + 0.5F));
			} else {
				world.spawnEntityInWorld(new zei_EntityHelios(world, i + 0.5F, j + 1.5F, k + 0.5F));
			}
		}
	}

	@Override
	public Icon getBlockTexture(IBlockAccess iblockaccess, int i, int j, int k, int l) {
		// if(TYPE==2){return D[6];}
		// if(j==1){}
		if (l <= 1) {
			return D[1];
		}
		return D[0];
	}

	@Override
	public void onNeighborBlockChange(World world, int i, int j, int k, int l) {
		if (world.isBlockIndirectlyGettingPowered(i, j, k)) {
			deploy(world.rand.nextInt(5), world, i, j, k);
		}
	}

	@Override
	public void updateTick(World world, int i, int j, int k, Random random) {
		// int r1=0;//random.nextInt(6);
		// if(r1==0){
		deploy(random.nextInt(6), world, i, j, k);
		// }
	}

	static void loadSprites() {
		D = new int[2];
		D[0] = 0; // zei_Universal.modOverride("/terrain.png",
					// "/zeitgeist/deployer.png");
		D[1] = 0; // zei_Universal.modOverride("/terrain.png",
					// "/zeitgeist/deployer2.png");
	}
}
