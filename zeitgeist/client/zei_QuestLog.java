package zeitgeist.client;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.src.BaseMod;
import net.minecraft.world.World;
import zeitgeist.common.zei_Quest;

public class zei_QuestLog extends Entity {
	public Map<String, String> QuestMap = new HashMap<String, String>(32);
	public String[] questName = new String[32];
	private BaseMod engiCraftTools;
	private boolean hasChecked = false;

	public zei_QuestLog(World world) {
		super(world);
		if (engiCraftTools != null) {
			// ((mod_EngiCraftTools)
			// engiCraftTools).setHasQuestLogBeenMade(true);
		}
	}

	public void addQuest(String questName, Entity questGiver) {
		Minecraft mc = Minecraft.getMinecraft();
		GuiIngame gui = mc.ingameGUI;
		gui.addChatMessage(questName);
		int i = 0;
		boolean foundEmptySpot = false;
		if (QuestMap.containsKey(i) && i < 32) {
			i++;
		} else if (QuestMap.containsKey(32)) {
			Minecraft.getMinecraft().ingameGUI.addChatMessage("Quest log full... GO COMPLETE SOME QUESTS FIRST!");
			return;
		} else {
			foundEmptySpot = true;
		}
		if (!QuestMap.containsValue(questName) && foundEmptySpot) {
			Entity e = new EntityPig(worldObj);
			zei_Quest quest = new zei_Quest("LOLDONGS", 0, e, 0, Item.bone.itemID, 5, "uh?", e, "save?", e, 10);
			quest.guide = questGiver;
			QuestMap.put(Integer.toString(i), quest.name);
			this.questName[i] = questName;
			gui.addChatMessage(quest.name);
		} else {
			Minecraft.getMinecraft().ingameGUI.addChatMessage("You've already got that quest.  Go do it first, then come get it again.");
			return;
		}
	}

	public String getQuestNameBySlot(int i) {
		String s = "";
		Object obj;
		String s1 = Integer.toString(i);
		s = QuestMap.get(s1);
		return s == null ? "Place Holder" : s;
	}

	@Override
	public boolean handleWaterMovement() {
		return false;
	}

	@Override
	public void onEntityUpdate() {
	}

	@Override
	public void onUpdate() {
		if (!hasChecked) {
			Minecraft.getMinecraft().ingameGUI.addChatMessage("The quest log does indeed exist.");
			hasChecked = true;
		}
	}

	@Override
	protected void entityInit() {
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		for (int i = 0; i < 32; i++) {
			nbttagcompound.getString("Quest_" + Integer.toString(i));
		}
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		for (int i = 0; i < 32; i++) {
			if (questName[i] == null || questName[i].equals("")) {
				continue;
			}
			nbttagcompound.setString("Quest_" + Integer.toString(i), "questName[i]");
		}
	}

	private boolean hasAnyQuests() {
		return !QuestMap.isEmpty();
	}
}
