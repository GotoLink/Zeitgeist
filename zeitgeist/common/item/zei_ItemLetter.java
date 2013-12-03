package zeitgeist.common.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemMapBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class zei_ItemLetter extends ItemMapBase {
	protected zei_ItemLetter(int par1) {
		super(par1);
		setMaxStackSize(1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack par1ItemStack, EntityPlayer player, List list, boolean par4) {
		zei_LetterData data = this.getMapData(par1ItemStack, player.worldObj);
		if (data != null)
			list.add("letter: " + data.header);
	}

	public zei_LetterData getMapData(ItemStack par1ItemStack, World par2World) {
		String s = "letter_" + par1ItemStack.getItemDamage();
		zei_LetterData mapdata = (zei_LetterData) par2World.loadItemData(zei_LetterData.class, s);
		if (mapdata == null && !par2World.isRemote) {
			par1ItemStack.setItemDamage(par2World.getUniqueDataId("letter"));
			String s1 = "letter_" + par1ItemStack.getItemDamage();
			mapdata = new zei_LetterData(s1);
			mapdata.from = "faggot";
			mapdata.to = "asshole";
			mapdata.header = "to a dickwad";
			mapdata.message = "why are you so gay";
			mapdata.markDirty();
			par2World.setItemData(s1, mapdata);
		}
		return mapdata;
	}

	@Override
	public void onCreated(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		par1ItemStack.setItemDamage(par2World.getUniqueDataId("letter"));
		String s = "letter_" + par1ItemStack.getItemDamage();
		zei_LetterData mapdata = new zei_LetterData(s);
		mapdata.from = "sender";
		mapdata.to = "Aninon";
		mapdata.header = "to some fag";
		mapdata.message = "hey guess what your letters are working!";
		mapdata.markDirty();
		par2World.setItemData(s, mapdata);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World world, EntityPlayer ep) {
		zei_LetterData mapdata = (zei_LetterData) world.loadItemData(zei_LetterData.class, "letter_" + par1ItemStack.getItemDamage());
		ep.addChatMessage("To: " + mapdata.to + " From: " + mapdata.from + "\n" + mapdata.message);
		return par1ItemStack;
	}

	@SideOnly(Side.CLIENT)
	public static zei_LetterData getMPMapData(short par0, World par1World) {
		String s = "letter_" + par0;
		zei_LetterData mapdata = (zei_LetterData) par1World.loadItemData(zei_LetterData.class, s);
		if (mapdata == null) {
			mapdata = new zei_LetterData(s);
			par1World.setItemData(s, mapdata);
		}
		return mapdata;
	}
}
