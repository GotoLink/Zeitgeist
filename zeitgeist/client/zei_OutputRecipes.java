package zeitgeist.client;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public class zei_OutputRecipes {
	static BufferedImage bar[];
	// static BufferedImage tr;
	static BufferedImage it;
	static RenderItem ri;

	public static BufferedImage create(String s, String s2) {
		GL11.glClearColor(0.7f, 0.7f, 0.7f, 1);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_STENCIL_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		// int ii = zei_Items.worker.shiftedIndex;
		ClientProxy.renderengine.bindTexture(s);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		int height = 256;
		int width = 256;
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.setColorOpaque_I(0xffffff);
		tessellator.addVertexWithUV(0.0D, 240, 0.0D, 0.0D, 1);
		tessellator.addVertexWithUV(128, 240, 0.0D, 1, 1);
		tessellator.addVertexWithUV(128, 112, 0.0D, 1, 0);
		tessellator.addVertexWithUV(0.0D, 112, 0.0D, 0.0D, 0);
		tessellator.draw();
		BufferedImage e = new BufferedImage(256, 256, BufferedImage.TYPE_INT_RGB);
		try {
			ByteBuffer buffer = BufferUtils.createByteBuffer(256 * 256 * 3);
			GL11.glReadPixels(0, 0, 256, 256, GL11.GL_RGB, GL11.GL_UNSIGNED_BYTE, buffer);
			for (int x = 0; x < 256; x++)
				for (int y = 0; y < 256; y++) {
					int i = (x + (256 * y)) * 3;
					int r = buffer.get(i) & 0xFF;
					int g = buffer.get(i + 1) & 0xFF;
					int b = buffer.get(i + 2) & 0xFF;
					e.setRGB(x, 256 - (y + 1), (0xFF << 24) | (r << 16) | (g << 8) | b);
				}
			ImageIO.write(e, "png", new File("C:\\Users\\Aninon\\Documents\\fugg" + s2 + ".png"));
		} catch (IOException er) {
			er.printStackTrace();
		}
		return e;
	}

	public static void derp() {
		System.out.println("HERE");
		ri = new RenderItem();
		ri.field_27004_a = false;
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_FOG);
		bar = new BufferedImage[256];
		for (int i = 1; i < 256; i++) {
			if (Block.blocksList[i] != null) {
				GL11.glDisable(GL11.GL_LIGHTING);
				bar[i] = makeBlocks(i);
			}
		}
		GL11.glDisable(GL11.GL_LIGHTING);
		// tr=create("/terrain.png","terra");
		it = create("/gui/items.png", "item");
		// GL11.glEnable(GL11.GL_LIGHTING);
		// recipe(new int[]{271,264,46,50,1,1,1,1,1});
		recipeAll();
	}

	/*
	 * public static void recipe(int[] p){ //BufferedImage w; BufferedImage out
	 * = new BufferedImage(64,64,BufferedImage.TYPE_INT_RGB); Graphics2D g2 =
	 * out.createGraphics(); for(int i=0;i<9;i++){ if(p[i]!=0){ int dx; int dy;
	 * if(p[i]>256){ //w=it; int icon=Item.itemsList[p[i]].iconIndex;
	 * dx=icon%16; dy=icon-dx; dx*=16; int x=(i%3)*17; int y=(i/3)*17;
	 * g2.drawImage(it, x, y, x+16, y+16, dx, dy,dx+ 16, dy+16, null); } } } try
	 * { ImageIO.write(out, "png", new File("C:\\output.png")); } catch
	 * (IOException e) { e.printStackTrace(); } }
	 */
	public static BufferedImage makeBlocks(int ii) {
		GL11.glClearColor(0.7f, 0.7f, 0.7f, 1);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_STENCIL_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		Block bl = Block.blocksList[ii];
		if (bl != null) {
			ri.field_27004_a = !ri.field_27004_a;
			GL11.glColor4f(100.0F, 1.0F, 1.0F, 1.0F);
			for (int j = 0; j < 16; j++) {
				ItemStack is = new ItemStack(ii, 1, j);
				// System.out.println("now doing "+ii+" "+bl.getBlockName());
				ri.renderItemIntoGUI(ClientProxy.fontRenderer, ClientProxy.renderengine, is, j * 16, 224); // 14*16
			}
		} else {
			return null;
		}
		BufferedImage e = new BufferedImage(512, 32, BufferedImage.TYPE_INT_RGB);
		// try {
		ByteBuffer buffer = BufferUtils.createByteBuffer(512 * 32 * 3);
		GL11.glReadPixels(0, 0, GL11.GL_NEVER, 32, GL11.GL_RGB, GL11.GL_UNSIGNED_BYTE, buffer);
		for (int x = 0; x < 512; x++)
			for (int y = 0; y < 32; y++) {
				int i = (x + (512 * y)) * 3;
				int r = buffer.get(i) & 0xFF;
				int g = buffer.get(i + 1) & 0xFF;
				int b = buffer.get(i + 2) & 0xFF;
				e.setRGB(x, 32 - (y + 1), (0xFF << 24) | (r << 16) | (g << 8) | b);
			}
		// ImageIO.write(e, "png", new File("C:\\Mess\\block"+ii+".png"));
		// } catch (IOException er) {
		// er.printStackTrace();
		// }
		return e;
	}

	public static void recipeAll() {
		/*
		 * //int m =p.length; List L =
		 * CraftingManager.getInstance().getRecipeList(); int m = L.size(); for
		 * (int i = 0; i < m; i++) { Object o = L.get(i); if (o instanceof
		 * ShapedRecipes) { ShapedRecipes r = (ShapedRecipes)o; //ItemStack
		 * is[]=r.recipe; int u = r.getRecipeOutput().itemID; //if((u>122 &&
		 * u<256)||(u>743)){ // continue; //} if (r != null &&
		 * r.getRecipeOutput() != null && r.recipeItems != null) {
		 * recipe(r.recipeItems, r.getRecipeOutput(), r.recipeWidth,
		 * r.recipeHeight, false); } } else if (o instanceof ShapelessRecipes) {
		 * ShapelessRecipes r = (ShapelessRecipes)o; List l = r.recipeItems;
		 * ItemStack ar[] = new ItemStack[l.size()]; for (int j = 0; j <
		 * ar.length; j++) { Object ob = l.get(j); ar[j] = (ItemStack)l.get(j);
		 * } recipe(ar, r.getRecipeOutput(), 3, 3, true); } } } public static
		 * void recipe(ItemStack[] p, ItemStack o, int wid, int hei, boolean
		 * boo) { //BufferedImage w; BufferedImage out = new BufferedImage(256,
		 * 128, BufferedImage.TYPE_INT_RGB); Graphics2D g2 =
		 * out.createGraphics(); int m = p.length; int i = 0; for (int y = 0; y
		 * < hei; y++) for (int x = 0; x < wid; x++) { if (i >= m) { break; } if
		 * (p[i] != null) { int dx; int dy; int id = p[i].itemID; int xo = x *
		 * 34; int yo = y * 34; if (id >= 256) { int icon = p[i].getIconIndex();
		 * dx = icon % 16; dy = icon - dx; dx *= 16; g2.drawImage(it, xo, yo, xo
		 * + 32, yo + 32, dx, dy, dx + 16, dy + 16, null); } else { int ico =
		 * p[i].getItemDamage(); if (ico < 0) { ico++; } int icon = (ico) * 32;
		 * //if(bar[id]!=null){ g2.drawImage(bar[id], xo, yo, xo + 32, yo + 32,
		 * icon, 0, icon + 32, 32, null); //} //g2.drawString(""+id, xo, yo); }
		 * } i++; } boolean skip = false; int dx = 0; int dy = 0; int id =
		 * o.itemID; String name = "null"; int d = 24 + (wid * 32); int ky = hei
		 * * 34; if (boo) { if (i < 3) { d = 24 + (i * 32); } ky = (1 + (i / 3))
		 * * 34; } if (id >= 256) { int icon = o.getIconIndex(); dx = icon % 16;
		 * dy = icon - dx; dx *= 16; g2.drawImage(it, d, (ky / 2) - 16, d + 32,
		 * 16 + (ky / 2), dx, dy, dx + 16, dy + 16, null); } else { Block block
		 * = Block.blocksList[id]; if (block == null) { skip = true; } else {
		 * int icon = o.getItemDamage() * 32; g2.drawImage(bar[id], d, (ky / 2)
		 * - 16, d + 32, 16 + (ky / 2), icon, 0, icon + 32, 32, null); } } if
		 * (!skip) { //g2.drawImage(w, d, (ky/2)-8, d+16,8+(ky/2), dx, dy,dx+16,
		 * dy+16, null); name = o.getItem().getItemName(); if (name == null) {
		 * name = "null"; } int size = o.stackSize; if (size > 1) {
		 * g2.drawString("" + size, d + 30, (ky / 2) + 16); } } int max = (d +
		 * 44); int maxy = (ky); BufferedImage doubled = new BufferedImage(max,
		 * maxy, BufferedImage.TYPE_INT_RGB); Graphics2D gg =
		 * doubled.createGraphics(); gg.drawImage(out, 0, 0, 256, 128, null);
		 * int gi = (ky / 2); gg.fillPolygon(new int[] {d - 20, d - 10, d - 10,
		 * d - 2, d - 10, d - 10, d - 20}, new int[] {gi + 4, gi + 4, gi + 8,
		 * gi, gi - 8, gi - 4, gi - 4}, 7); if (boo) {
		 * //gg.drawString("*Shapeless*", 20, ); } try { File f = new
		 * File("C:\\Mess\\output " + name + ".png"); int itr = 0; while
		 * (f.exists()) { f = new File("C:\\Mess\\" + (itr) + "output " + name +
		 * ".png"); itr++; } ImageIO.write(doubled, "png", f); } catch
		 * (IOException e) { e.printStackTrace(); }
		 */
	}
}
