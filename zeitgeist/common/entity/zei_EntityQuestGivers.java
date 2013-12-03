package zeitgeist.common.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import zeitgeist.client.zei_QuestHandler;
import zeitgeist.common.zei_Quest;

public class zei_EntityQuestGivers extends EntityLiving {
	public zei_EntityQuestGivers(World world) {
		super(world);
		isQuestGiver = false;
		hasCollectObj = false;
		questActive = false;
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (zei_QuestHandler.questMap.containsKey(name)) {
			isQuestGiver = true;
			quest = zei_QuestHandler.questMap.get(name);
		}
		if (isQuestGiver && !hasCollectObj) {
			quest = zei_QuestHandler.getQuest(name);
			isQuestGiver = true;
			if (quest.itemToCollect > 0) {
				questItemID = quest.itemToCollect;
				numItemNeeded = quest.itemsNeeded;
				hasCollectObj = true;
			}
		}
	}

	public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		super.writeEntityToNBT(nbttagcompound);
		nbttagcompound.setString("NameRole", name);
		nbttagcompound.setBoolean("active", questActive);
		nbttagcompound.setBoolean("Introduced", hasSpokenWithPlayer);
		nbttagcompound.setString("MyRole", role);
	}

	public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		super.readEntityFromNBT(nbttagcompound);
		name = nbttagcompound.getString("NameRole");
		questActive = nbttagcompound.getBoolean("active");
		hasSpokenWithPlayer = nbttagcompound.getBoolean("Introduced");
		role = nbttagcompound.getString("MyRole");
	}

	public boolean interact(EntityPlayer entityplayer) {
		System.out.println("Before the quest xp reward, the player's XP was: "
				+ Integer.toString(entityplayer.experienceTotal));
		System.out.println(role);
		if (!hasSpokenWithPlayer) {
			entityplayer.addChatMessage("Hello, I am a " + faction + " " + role
					+ ".");
			hasSpokenWithPlayer = true;
		}
		if (isQuestGiver) {
			if (hasCollectObj) {
				System.out.println("It has the collection objective.");
				System.out.println("Required Item ID is: "
						+ Integer.toString(questItemID));
				ItemStack itemstack = entityplayer.inventory.getCurrentItem();
				if (itemstack != null) {
					System.out.println("Current held item is: "
							+ Integer.toString(itemstack.itemID));
				}
				System.out.println("Current number of that item is: "
						+ Integer.toString(numItemNeeded));
				if (questActive) {
					if (itemstack != null && itemstack.itemID == questItemID
							&& itemstack.stackSize >= numItemNeeded) {
						entityplayer.addChatMessage(questCompleteText);
						questActive = false;
						int i = itemstack.stackSize - numItemNeeded;
						EntityXPOrb xporb = new EntityXPOrb(worldObj,
								this.posX, this.posY, this.posZ,
								quest.rewardExp);
						System.out.println(Integer
								.toString(entityplayer.experienceTotal));
						worldObj.spawnEntityInWorld(xporb);
						if (i > 0) {
							entityplayer.inventory.setInventorySlotContents(
									entityplayer.inventory.currentItem,
									new ItemStack(Item.itemsList[questItemID],
											i));
							return true;
						} else {
							entityplayer.inventory.setInventorySlotContents(
									entityplayer.inventory.currentItem, null);
							return true;
						}
					} else {
						entityplayer.addChatMessage(questIncompleteText);
						return false;
					}
				} else {
					entityplayer.addChatMessage(itemColRequestpt1
							+ Item.itemsList[questItemID].getStatName()
							+ itemColRequestpt2);
					questActive = true;
				}
			}
			return true;
		} else {
			entityplayer.addChatMessage(getNoQuestDialogue());
			return false;
		}
	}

	public String getNoQuestDialogue() {
		return null;
	}

	private boolean hasSpokenWithPlayer = false;
	private boolean questActive;
	public String itemColRequestpt1 = "";
	public String itemColRequestpt2 = "";
	public String questIncompleteText = "";
	public String questCompleteText = "";
	public String role;
	public String faction = "";
	public String name = "";
	public boolean isQuestGiver;
	public boolean hasCollectObj;
	public int questItemID;
	public int numItemNeeded;
	protected zei_Quest quest;

	@Override
	public float getMaxHealth() {
		return 20;
	}
}
