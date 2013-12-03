package zeitgeist.common.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemMapBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import zeitgeist.client.ClientProxy;
import zeitgeist.common.zei_Ids;
import zeitgeist.common.zei_PictureData;
import zeitgeist.common.block.zei_Blocks;
import zeitgeist.common.tile.zei_TileEntityTexBlock;
import zeitgeist.common.tile.zei_TileEntityTexBlock2;

public class zei_ItemTexture extends ItemMapBase {
	protected zei_ItemTexture(int par1) {
		super(par1);
		setMaxStackSize(1);
	}

	public void addInformation(ItemStack s, List list) {
		zei_PictureData data = (zei_PictureData) ClientProxy.mc.theWorld.loadItemData(zei_PictureData.class, "texture_" + par1);
		if (data == null) {
			return 1;
		}
		data.bindPicture(imgID, false);
	}

	@Override
	public Icon getIconFromDamage(int par1) {
		return 1;
	}

	@Override
	public String getItemDisplayName(ItemStack par1ItemStack) {
		// zei_LetterData mapdata =
		// (zei_LetterData)zei_Universal.mc.theWorld.loadItemData(zei_LetterData.class,
		// "letter_"+par1ItemStack.getItemDamage());
		// String s = (new
		// StringBuilder()).append("").append(StringTranslate.getInstance().translateNamedKey(getLocalItemName(par1ItemStack))).toString().trim();
		return "Texture #" + par1ItemStack.getItemDamage(); // "letter: "+mapdata.header
	}

	public zei_LetterData getMapData(ItemStack par1ItemStack, World par2World) {
		String s = "letter_" + par1ItemStack.getItemDamage();
		zei_LetterData mapdata = (zei_LetterData) par2World.loadItemData(zei_LetterData.class, "texture_" + par1ItemStack.getItemDamage());
		if (mapdata == null) {
			par1ItemStack.setItemDamage(par2World.getUniqueDataId("texture"));
			String s1 = "texture_" + par1ItemStack.getItemDamage();
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

	/**
	 * Called when item is crafted/smelted. Used only by maps so far.
	 */
	@Override
	public void onCreated(ItemStack par1ItemStack, World par2World, EntityPlayer entityPlayer) {
		par1ItemStack.setItemDamage(par2World.getUniqueDataId("texture"));
		String s = "texture_" + par1ItemStack.getItemDamage();
		zei_PictureData mapdata = new zei_PictureData(s);
		par2World.setItemData(s, mapdata);
		mapdata.name = entityPlayer.username;
		byte[] colo = new byte[256];
		for (int i = 0; i < 256; i++) {
			colo[i] = 127;
		}
		mapdata.colors = colo;
		mapdata.markDirty();
		//
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World world, EntityPlayer ep) {
		zei_PictureData data = (zei_PictureData) world.loadItemData(zei_PictureData.class, "texture_" + par1ItemStack.getItemDamage());
		if (data != null) {
			if (data.name == "" || data.name.equals(ep.username)) {
				data.name = ep.username;
				// ModLoader.openGUI(ep, new
				// zei_GuiEditPicture(data,par1ItemStack.getItemDamage(),imgID));
			} else {
				ep.addChatMessage("Sorry" + ep.username + ",but only the creator" + data.name + " can edit this texture!");
			}
		}
		return par1ItemStack;
	}

	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer ep, World world, int par4, int par5, int par6, int par7) {
		int id = world.getBlockId(par4, par5, par6);
		if (id == zei_Ids.texBlock2 || id == 5) {
			if (id == 5) {
				world.setBlock(par4, par5, par6, zei_Ids.texBlock2);
			}
			zei_TileEntityTexBlock2 te = (zei_TileEntityTexBlock2) world.getBlockTileEntity(par4, par5, par6);
			int dam = par1ItemStack.getItemDamage();
			System.out.println(par7);
			if (par7 == 1) {
			}
			switch (par7) {
			case 1:
				te.setId6(te.getId6() == dam ? -1 : dam);
				break;//
			case 2:
				te.setId3(te.getId3() == dam ? -1 : dam);
				break;
			case 3:
				te.setId1(te.getId1() == dam ? -1 : dam);
				break;
			case 4:
				te.setId4(te.getId4() == dam ? -1 : dam);
				break;
			case 5:
				te.setId2(te.getId2() == dam ? -1 : dam);
				break; //
			case 0:
				te.setId5(te.getId5() == dam ? -1 : dam);
				break;
			}
			return true;
		} else if (id == zei_Ids.texBlock) {
			zei_TileEntityTexBlock te = (zei_TileEntityTexBlock) world.getBlockTileEntity(par4, par5, par6);
			te.indo = par1ItemStack.getItemDamage();
		} else {
			if (ep.inventory.hasItem(Block.cloth.blockID)) {
				if (par7 == 1) {
					par5++;
				}
				if (par7 == 0) {
					par5--;
				}
				if (par7 == 2) {
					par6--;
				}
				if (par7 == 3) {
					par6++;
				}
				if (par7 == 4) {
					par4--;
				}
				if (par7 == 5) {
					par4++;
				}
				if (!ep.canPlayerEdit(par4, par5, par6, par7, par1ItemStack)) {
					return false;
				}
				if (zei_Blocks.texBlock.canPlaceBlockAt(world, par4, par5, par6)) {
					world.setBlock(par4, par5, par6, zei_Ids.texBlock, par7, 3);
					zei_TileEntityTexBlock te = (zei_TileEntityTexBlock) world.getBlockTileEntity(par4, par5, par6);
					te.indo = par1ItemStack.getItemDamage();
					ep.inventory.consumeInventoryItem(Block.cloth.blockID);
				}
			} else {
				return true;
			}
		}
		return true;
	}

	public static zei_LetterData getMPMapData(short par0, World par1World) {
		String s = "texture_" + par0;
		zei_LetterData mapdata = (zei_LetterData) par1World.loadItemData(zei_LetterData.class, "texture_" + par0);
		if (mapdata == null) {
			int i = par1World.getUniqueDataId("texture");
			String s1 = "texture_" + i;
			mapdata = new zei_LetterData(s1);
			par1World.setItemData(s1, mapdata);
		}
		return mapdata;
	}
}
