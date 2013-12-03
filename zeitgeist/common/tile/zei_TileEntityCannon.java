package zeitgeist.common.tile;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import zeitgeist.common.entity.zei_EntityCannonItem;

public class zei_TileEntityCannon extends TileEntity implements IInventory {
	public static ItemStack item = new ItemStack(Block.cobblestone, 1);
	public short cool;
	public boolean Firetype;
	public short gun;
	public short Mode;
	public short Pitch;
	public short Rotation;
	public boolean SafetyOff;
	private ItemStack[] slots = new ItemStack[13];
	AxisAlignedBB bb;
	short delay = 0;
	EntityLiving targ;

	public zei_TileEntityCannon() {
		// System.out.println("CANNON!!!!");
	}

	public void adjust() {
		Pitch += 5;
		if (Pitch > 90) {
			Pitch = 0;
		}
	}

	public void adjust(int d) {
		Pitch += d;
		if (Pitch > 90) {
			Pitch = 90;
		}
		if (Pitch < 0) {
			Pitch = 0;
		}
		worldObj.addBlockEvent(xCoord, yCoord, zCoord, 1, Pitch);
	}

	public void alert() {
		worldObj.addBlockEvent(xCoord, yCoord, zCoord, 2, Firetype ? 1 : 0);
		worldObj.addBlockEvent(xCoord, yCoord, zCoord, 3, SafetyOff ? 1 : 0);
		worldObj.addBlockEvent(xCoord, yCoord, zCoord, 4, gun / 3);
		worldObj.addBlockEvent(xCoord, yCoord, zCoord, 5, cool / 4);
	}

	@Override
	public void closeChest() {
	}

	@Override
	public ItemStack decrStackSize(int par1, int par2) {
		if (this.slots[par1] != null) {
			ItemStack var3;
			if (this.slots[par1].stackSize <= par2) {
				var3 = this.slots[par1];
				this.slots[par1] = null;
				return var3;
			} else {
				var3 = this.slots[par1].splitStack(par2);
				if (this.slots[par1].stackSize == 0) {
					this.slots[par1] = null;
				}
				return var3;
			}
		} else {
			return null;
		}
	}

	public void Fire() {
		int safe = SafetyOff ? 2000 : 500;
		if (cool <= safe) {
			if (cool > 1000) {
				worldObj.createExplosion(null, xCoord, yCoord, zCoord, 5, true);
			} else {
				if (gun > 0) {
					int i = 1;
					while (getStackInSlot(i) == null) {
						i++;
						if (i > slots.length - 1) {
							i = 1;
							break;
						}
					}
					ItemStack is = this.decrStackSize(i, 1);
					if (is != null) {
						fire2(is);
						gun -= 3;
					} else {
						// this.worldObj.playSoundEffect(xCoord, yCoord, zCoord,
						// "random.drr", 0.6F, 1f);
						worldObj.addBlockEvent(xCoord, yCoord, zCoord, 9, 2);
					}
				} else {
					if (slots[0] != null && slots[0].itemID == 289) {
						this.decrStackSize(0, 1);
						gun = 60;
						// this.worldObj.playSoundEffect(xCoord, yCoord, zCoord,
						// "step.gravel", 0.6F, 1f);
						worldObj.addBlockEvent(xCoord, yCoord, zCoord, 9, 0);
					} else {
						// this.worldObj.playSoundEffect(xCoord, yCoord, zCoord,
						// "random.drr", 0.6F, 1f);
						worldObj.addBlockEvent(xCoord, yCoord, zCoord, 9, 2);
					}
					// fire2();
				}
			}
		}
	}

	@Override
	public Packet getDescriptionPacket() {
		Packet132TileEntityData pak = new Packet132TileEntityData(xCoord, yCoord, zCoord, 5, Rotation);
		pak.customParam2 = Pitch;
		pak.customParam3 = Mode;
		return pak;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public String getInvName() {
		return "container.furnace";
	}

	@Override
	public int getSizeInventory() {
		return slots.length;
	}

	@Override
	public ItemStack getStackInSlot(int par1) {
		return this.slots[par1];
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int par1) {
		if (this.slots[par1] != null) {
			ItemStack var2 = this.slots[par1];
			this.slots[par1] = null;
			return var2;
		} else {
			return null;
		}
	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
		return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false
				: par1EntityPlayer.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D;
	}

	public void onDataPacket(NetworkManager net, Packet132TileEntityData pkt) {
		if (pkt.actionType == 5) {
			Rotation = (short) pkt.customParam1;
			Pitch = (short) pkt.customParam2;
			Mode = (short) pkt.customParam3;
		}
		if (pkt.actionType == 6) {
			System.out.println("fire " + pkt.customParam3);
			Firetype = pkt.customParam3 == 1;
		} else if (pkt.actionType == 7) {
			SafetyOff = pkt.customParam3 == 1;
		}
	}

	@Override
	public void openChest() {
	}

	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);
		this.Rotation = par1NBTTagCompound.getShort("Rotation");
		this.Pitch = par1NBTTagCompound.getShort("Pitch");
		this.Mode = par1NBTTagCompound.getShort("Mode");
		this.Firetype = par1NBTTagCompound.getBoolean("Firetype");
		this.SafetyOff = par1NBTTagCompound.getBoolean("SafetyOff");
		this.gun = par1NBTTagCompound.getShort("Gun");
		this.cool = par1NBTTagCompound.getShort("Cool");
		NBTTagList var2 = par1NBTTagCompound.getTagList("Items");
		this.slots = new ItemStack[this.getSizeInventory()];
		for (int var3 = 0; var3 < var2.tagCount(); ++var3) {
			NBTTagCompound var4 = (NBTTagCompound) var2.tagAt(var3);
			byte var5 = var4.getByte("Slot");
			if (var5 >= 0 && var5 < this.slots.length) {
				this.slots[var5] = ItemStack.loadItemStackFromNBT(var4);
			}
		}
	}

	@Override
	public boolean receiveClientEvent(int id, int ar) {
		return true;
		// System.out.println(id+" ,uh "+ar);
		/*
		 * switch(id){ case 0:Rotation=(short)
		 * (90+(ar*1.4));break;//zei_Universal
		 * .mc.thePlayer.addChatMessage("rot: "+ar);break; case 1:Pitch=(short)
		 * ar;break; case 2:Firetype=ar==1;break; case 3:SafetyOff=ar==1;break;
		 * case 4:gun=(short) (ar*3);break; case 5:cool=(short) (ar*4);break;
		 * case 6:Mode=(short) (ar);break; case 9: if(ar==1){ Foof(); }else
		 * if(ar==0){ this.worldObj.playSoundEffect(xCoord, yCoord, zCoord,
		 * "step.gravel", 0.6F, 1f); }else if(ar==2){
		 * this.worldObj.playSoundEffect(xCoord, yCoord, zCoord, "random.drr",
		 * 0.6F, 1f); } break; }
		 */
	}

	public boolean rotateYaw(EntityPlayer player) {
		boolean boo = false;
		short rot = 0;
		int rtt = 0;
		if (player != null) {
			double xx = player.posX - (this.xCoord + 0.5F);
			double yy = player.posZ - (this.zCoord + 0.5F);
			rtt = (int) (270 - (180 * Math.atan2(xx, yy) / Math.PI));
			rot = (short) (rtt);
			boo = this.Rotation - rot == 0;
			this.Rotation = rot;
		}
		if (!boo) {
			// System.out.println("server: "+(rtt-90));
			worldObj.addBlockEvent(xCoord, yCoord, zCoord, 0, (int) Math.floor((rtt - 90) / 1.4));
		}
		return boo;
	}

	@Override
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {
		this.slots[par1] = par2ItemStack;
		if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit()) {
			par2ItemStack.stackSize = this.getInventoryStackLimit();
		}
	}

	public boolean setMode(short i) {
		if (i == 1 && Mode == 0) {
			Mode = 1;
			worldObj.addBlockEvent(xCoord, yCoord, zCoord, 6, 1);
			return true;
		} else if (i > 1 && Mode == 1) {
			Mode = i;
			worldObj.addBlockEvent(xCoord, yCoord, zCoord, 6, i);
			return true;
		}
		return false;
	}

	public void toggleFiretype() {
		Firetype = !Firetype;
	}

	public void toggleSafety() {
		SafetyOff = !SafetyOff;
	}

	@Override
	public void updateEntity() {
		super.updateEntity();
		if (cool > 0) {
			cool--;
		}
		if (Mode > 1) {
			if (bb == null) {
				bb = AxisAlignedBB.getBoundingBox(xCoord - 16, yCoord - 16, zCoord - 16, xCoord + 16, yCoord + 16, zCoord + 16); // System.out.println("CANNON!!!!");
			}
			List l;
			if (Mode == 3) {
				l = worldObj.getEntitiesWithinAABB(EntityLiving.class, bb);
			} else if (Mode == 4) {
				l = worldObj.getEntitiesWithinAABB(EntityPlayer.class, bb);
			} else {
				l = worldObj.getEntitiesWithinAABB(EntityMob.class, bb);
			}
			if (!l.isEmpty()) {
				double dis = 900;
				EntityLiving el = null;
				for (int i = 0; i < l.size(); i++) {
					EntityLiving e = (EntityLiving) l.get(i);
					double dd = e.getDistance(xCoord, yCoord, zCoord);
					if (dd < dis && worldObj.rayTraceBlocks(Vec3.createVectorHelper(e.posX, e.posY, e.posZ), Vec3D.createVector(xCoord, yCoord, zCoord)) == null) {
						dis = dd;
						el = e;
					}
				}
				if (el != null) {
					targ = el;
				}
			}
			if (delay > 0) {
				delay--;
			}
			if (targ != null) {
				double xx = targ.posX - (xCoord + 0.5);
				double yy = (targ.getEyeHeight() * 0.75 + targ.posY) - (yCoord + 0.5);
				double zz = targ.posZ - (zCoord + 0.5);
				if ((xx * xx + yy * yy + zz * zz) > 576) {
					targ = null;
				} else {
					double rr = Math.atan2(zz, xx);
					double r2 = Math.atan2(yy, Math.sqrt(xx * xx + zz * zz));
					Rotation = (short) (180.0 * rr / Math.PI);
					Pitch = (short) ((180.0 * r2 / Math.PI));
				}
				if (Mode != 3
						&& delay <= 0
						&& worldObj.rayTraceBlocks_do_do(Vec3.createVectorHelper(targ.posX, targ.posY, targ.posZ), Vec3.createVectorHelper(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5), true, false) == null) {
					Fire();
					delay += 30;
				}
				if (targ != null && targ.isDead) {
					targ = null;
				}
			} else {
				Rotation++;
				if (Rotation > 360) {
					Rotation = 0;
				}
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setShort("Rotation", this.Rotation);
		par1NBTTagCompound.setShort("Pitch", this.Pitch);
		par1NBTTagCompound.setShort("Mode", this.Mode);
		par1NBTTagCompound.setBoolean("FireType", this.Firetype);
		par1NBTTagCompound.setBoolean("SafetyOff", this.SafetyOff);
		par1NBTTagCompound.setShort("Gun", this.gun);
		par1NBTTagCompound.setShort("Cool", this.cool);
		NBTTagList var2 = new NBTTagList();
		for (int var3 = 0; var3 < this.slots.length; ++var3) {
			if (this.slots[var3] != null) {
				NBTTagCompound var4 = new NBTTagCompound();
				var4.setByte("Slot", (byte) var3);
				this.slots[var3].writeToNBT(var4);
				var2.appendTag(var4);
			}
		}
		par1NBTTagCompound.setTag("Items", var2);
	}

	private void fire2(ItemStack itm) {
		// this.worldObj.playSoundEffect(xCoord, yCoord, zCoord,
		// "random.explode", 0.6F, 0.05f);
		worldObj.addBlockEvent(xCoord, yCoord, zCoord, 9, 1);
		float xx = 5 * MathHelper.cos((float) Math.PI * this.Rotation / 180);
		float zz = 5 * MathHelper.sin((float) Math.PI * this.Rotation / 180);
		xx *= MathHelper.cos((float) Math.PI * this.Pitch / 180);
		zz *= MathHelper.cos((float) Math.PI * this.Pitch / 180);
		float yy = MathHelper.sin((float) Math.PI * this.Pitch / 180);
		zei_EntityCannonItem fire = new zei_EntityCannonItem(Firetype, 1, true, itm, worldObj, xx / 3f + xCoord + 0.5f, yy / 3f + yCoord + 0.8f, zz / 3f + zCoord + 0.5f, xx, yy * 2f, zz);
		worldObj.spawnEntityInWorld(fire);
		cool += 50;
	}

	private void Foof() {
		this.worldObj.playSoundEffect(xCoord, yCoord, zCoord, "random.explode", 0.6F, 0.05f);
		double r = Math.PI * Rotation / 180.0;
		double r2 = Math.PI * Pitch / 180.0;
		double xx = Math.cos(r) * Math.cos(r2) * 1.2;
		double zz = Math.sin(r) * Math.cos(r2) * 1.2;
		double yy = Math.sin(r2) * 1.2;
		for (int d = 0; d < 10; d++)
			worldObj.spawnParticle("explode", xCoord + 0.5 + xx, yCoord + 0.8 + yy, zCoord + 0.5 + zz, (Math.random() - 0.5) * 0.2, (Math.random() - 0.5) * 0.2, (Math.random() - 0.5) * 0.2);
	}
}
