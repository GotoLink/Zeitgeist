package zeitgeist.common.entity.ai;

import java.util.List;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import zeitgeist.common.zei_Ids;
import zeitgeist.common.entity.zei_EntityMaker;

public class zei_EntityAIMakerMake extends EntityAIBase {
	zei_EntityMaker bot;
	PathNavigate botPathfinder;

	public zei_EntityAIMakerMake(zei_EntityMaker ento) {
		bot = ento;
		setMutexBits(3);
		botPathfinder = ento.getNavigator();
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	public boolean shouldExecute() {
		if (!bot.making) {
			int xi = bot.xx;// +bot.rand.nextInt(9)-4;
			int yi = bot.yy - 1;
			int zi = bot.zz;// +bot.rand.nextInt(9)-4;
			int id = bot.worldObj.getBlockId(xi, yi, zi);
			List l = bot.worldObj
					.getEntitiesWithinAABB(TileEntityFurnace.class,
							bot.boundingBox.expand(16, 4D, 16));
			if (!l.isEmpty()) {
				TileEntity t = (TileEntity) l.get(0);
				x = t.xCoord;
				y = t.yCoord;
				z = t.zCoord;
				return true;
			}
			if (id == zei_Ids.sky || id == zei_Ids.tech) {
				x = 8 * (xi / 8) + 4;
				z = 8 * (zi / 8) + 4;
				y = yi + 1;
				return true;
			}
		}
		return false;
	}

	public void updateTask() {
		// /bot.getLookHelper().setLookPosition(bot.dX, bot.dY, bot.dZ, 10F,
		// bot.getVerticalFaceSpeed());
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	public boolean continueExecuting() {
		return !botPathfinder.noPath() && !bot.making;
	}

	int x, y, z;

	public void startExecuting() {
		super.startExecuting();
		PathEntity pe = bot.worldObj.getEntityPathToXYZ(bot, x, y, z, 16F,
				true, true, false, true);
		// System.out.println("hmm");
		botPathfinder.setPath(pe, 0.25f);
		if (pe.getCurrentPathLength() < 4) {
			bot.making = true;
		}
		// bot.build(x,y,z);
		//
		// botPathfinder.
	}
}
