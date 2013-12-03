package zeitgeist.common.tile;

import java.util.Random;

import net.minecraft.tileentity.TileEntity;
import zeitgeist.common.zei_Ids;
import zeitgeist.common.block.zei_BlockArch;
import zeitgeist.common.block.zei_BlockArchBend;
import zeitgeist.common.block.zei_Blocks;

public class zei_TileEntityArchitect extends TileEntity {
	public float lid;
	public float Pitch;
	public float prevRotation;
	public float rnx;
	public float rny;
	public float Rotation;
	int delay = 0;
	float lid2;
	float pitt;
	float ppr;
	float rott;
	int tick = 0;
	int tock;

	public zei_TileEntityArchitect() {
		Pitch = 1.57f;
		Rotation = (float) (Math.random() * Math.PI * 2);
		lid = 0.3f;
		lid2 = 1f;
		// System.out.println("Architect!");
	}

	public void plague() {
		Random rand = worldObj.rand;
		if (focus) {
			// System.out.println("focus!");
			if (worldObj.getBlockId(focx, focy, focz) == zei_Ids.arch2) {
				focy++;
			} else {
				((zei_BlockArchBend) zei_Blocks.archBend).grow(worldObj, focx, focy - 1, focz, rand);
				focus = false;
				// System.out.println("peaked!");
			}
		} else {
			int xi = rand.nextInt(7) - 3;
			int yi = rand.nextInt(9) - 2;
			int zi = rand.nextInt(7) - 3;
			int xx = xCoord + xi;
			int yy = yCoord + yi;
			int zz = zCoord + zi;
			if (xi == 0 && yi == 0 && zi == 0) {
				return;
			}
			if ((zi == 2 && (xi == 2 || xi == -2)) || (zi == -2 && (xi == 2 || xi == -2))) {
				worldObj.setBlock(xx, yy, zz, zei_Ids.arch2, 2, 3);
			} else if (Math.abs(zi) <= 1 && Math.abs(xi) <= 1 && yi > -1 && yi < 5) {
				if (yi == 4) {
					worldObj.setBlock(xx, yy, zz, 49);
				} else {
					if (yi == 0 && ((xi == 0 && Math.abs(zi) == 1) || (zi == 0 && Math.abs(xi) == 1))) {
						worldObj.setBlock(xx, yy, zz, zei_Ids.arch2, 4, 3);
					} else {
						worldObj.setBlock(xx, yy, zz, 0);
					}
				}
			} else if (worldObj.isBlockOpaqueCube(xx, yy, zz)) {
				zei_BlockArch.place(worldObj, xx, yy, zz);
			}
		}
	}

	@Override
	public void updateEntity() {
		/*
		 * EntityPlayer ep =
		 * this.worldObj.getClosestPlayer((double)((float)this.xCoord + 0.5F),
		 * (double)((float)this.yCoord + 0.5F), (double)((float)this.zCoord +
		 * 0.5F), 3.0D); delay++; if(delay%10==0){ rnx=(float)
		 * (Math.random()-0.5f)*0.5f; rny=(float) (Math.random()-0.5f)*0.5f; }
		 * if(delay%40==0){ tick=10; } prevRotation=ppr; ppr=Rotation; if (ep !=
		 * null){ double xx = ep.posX - (double)((float)this.xCoord + 0.5F);
		 * double zz = ep.posZ - (double)((float)this.zCoord + 0.5F); double yy
		 * = ep.posY - (double)((float)this.yCoord + 0.5F); this.Rotation =
		 * (float)Math.atan2(xx, zz); this.Pitch =
		 * (float)Math.atan2(Math.sqrt(xx*xx+zz*zz), yy); lid2=(float)
		 * ((Math.sqrt(xx*xx + yy*yy + zz*zz)/3f)); } if(tick>0){ tick--; }
		 * float factor=Math.abs(tick-5)/5f; lid=0.3f*lid2*factor; if(delay>80){
		 * delay=0; tock=20; if(ep==null){ rott=(float) (Math.random()-0.5f)/2f;
		 * pitt=(float) (Math.random()-0.5f)/10f; } } if(tock>0){ tock--;
		 * Rotation+=rott; Pitch+=pitt; if(Pitch>1.57f){ Pitch=1.57f; }else
		 * if(Pitch<0.2f){ Pitch=0.2f; } }
		 */
		if (!worldObj.isRemote)
			plague();
	}
}
