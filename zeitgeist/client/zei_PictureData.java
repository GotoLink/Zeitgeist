package zeitgeist.client;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldSavedData;

import org.lwjgl.opengl.GL11;

public class zei_PictureData extends WorldSavedData {
	public static HashMap<Integer, Boolean> picRenew;
	static HashMap<Integer, Integer> picIDs;
	public byte colors[];
	String name;

	public zei_PictureData(String par1Str) {
		super(par1Str);
		colors = new byte[256];
	}

	static {
		picIDs = new HashMap<Integer, Integer>();
		picRenew = new HashMap<Integer, Boolean>();
	}

	public void bindPicture(int ID, boolean block) {
		int m = colors.length;
		int[] arr = new int[65536];
		int mm = 16 * 256;
		int x = 0;
		int y = 0;
		for (int i = 0; i < mm; i++) {
			if (x < 32 && x >= 16) {
				arr[i] = convert(colors[x + 16 * y - 16] + 128, block);
			} else {
				if (x >= 256) {
					x = 0;
					y++;
					arr[i] = convert(colors[16 * y] + 128, block);// colors[x+16*y]*256*256*256*64;
				}
			}
			x++;
		}
		ClientProxy.mc.renderEngine.createTextureFromBytes(arr, 256, 256, ID);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, ID);// zei_Universal.mc.renderEngine.getTexture("/zeitgeist/gridz.png"));
	}

	public void bindPictures(int ID, boolean block) {
		Boolean obj = picRenew.get(ID);
		int imgID = 0;
		if (obj == null || obj.booleanValue()) {
			if (obj == null || picIDs.get(ID) == null) {
				imgID = ClientProxy.mc.renderEngine.allocateAndSetupTexture(new BufferedImage(256, 256, BufferedImage.TYPE_INT_RGB));
				picIDs.put(ID, imgID);
			} else {
				imgID = picIDs.get(ID);
			}
			int m = colors.length;
			int[] arr = new int[65536];
			int mm = 16 * 256;
			int x = 0;
			int y = 0;
			for (int i = 0; i < mm; i++) {
				if (x < 32 && x >= 16) {
					arr[i] = convert(colors[x + 16 * y - 16] + 128, block);
				} else {
					if (x >= 256) {
						x = 0;
						y++;
						arr[i] = convert(colors[16 * y] + 128, block);// colors[x+16*y]*256*256*256*64;
					}
				}
				x++;
			}
			ClientProxy.mc.renderEngine.createTextureFromBytes(arr, 256, 256, imgID);
			picRenew.put(ID, false);
		} else {
			imgID = picIDs.get(ID);
		}
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, imgID);// zei_Universal.mc.renderEngine.getTexture("/zeitgeist/gridz.png"));
	}

	public int convert(int u, boolean block) {
		int a = (u & 255) >> 6;
		int r = (u & 63) >> 4;
		int g = (u & 15) >> 2;
		int b = (u & 3);
		r *= 64;
		g *= 64;
		b *= 64;
		a *= 64;
		if (block)
			a += 63;
		return ((a * 256 + r) * 256 + g) * 256 + b;
	}

	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		colors = par1NBTTagCompound.getByteArray("colors");
		name = par1NBTTagCompound.getString("name");
	}

	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		par1NBTTagCompound.setByteArray("colors", colors);
		par1NBTTagCompound.setString("name", name);
	}
}
