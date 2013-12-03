package zeitgeist.client.render;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import zeitgeist.common.entity.zei_EntityCannonItem;

public class zei_RenderCannonItem extends Render {
	public boolean field_27004_a = true;
	/** Defines the zLevel of rendering of item on GUI. */
	public float zLevel = 0.0F;
	/** The RNG used in RenderItem (for bobbing itemstacks on the ground) */
	private Random random = new Random();
	private RenderBlocks renderBlocks = new RenderBlocks();

	public zei_RenderCannonItem() {
		this.shadowSize = 0.15F;
		this.shadowOpaque = 0.75F;
	}

	/**
	 * Actually renders the given argument. This is a synthetic bridge method,
	 * always casting down its argument and then handing it off to a worker
	 * function which does the actual work. In all probabilty, the class Render
	 * is generic (Render<T extends Entity) and this method has signature public
	 * void doRender(T entity, double d, double d1, double d2, float f, float
	 * f1). But JAD is pre 1.5 so doesn't do that.
	 */
	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
		this.doRenderItem((zei_EntityCannonItem) par1Entity, par2, par4, par6, par8, par9);
	}

	/**
	 * Renders the item
	 */
	public void doRenderItem(zei_EntityCannonItem par1EntityItem, double par2, double par4, double par6, float par8, float par9) {
		this.random.setSeed(187L);
		ItemStack var10 = par1EntityItem.item;
		GL11.glPushMatrix();
		float var11 = MathHelper.sin((par1EntityItem.age + par9) / 10.0F + par1EntityItem.field_804_d) * 0.1F + 0.1F;
		float var12 = ((par1EntityItem.age + par9) / 20.0F + par1EntityItem.field_804_d) * (180F / (float) Math.PI);
		byte var13 = 1;
		if (par1EntityItem.item.stackSize > 1) {
			var13 = 2;
		}
		if (par1EntityItem.item.stackSize > 5) {
			var13 = 3;
		}
		if (par1EntityItem.item.stackSize > 20) {
			var13 = 4;
		}
		GL11.glTranslatef((float) par2, (float) par4 + var11, (float) par6);
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		int var15;
		float var19;
		float var18;
		float var23;
		IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(var10, ENTITY);
		if (customRenderer != null) {
			if (customRenderer.shouldUseRenderHelper(ENTITY, var10, ENTITY_ROTATION)) {
				GL11.glRotatef(var12, 0.0F, 1.0F, 0.0F);
			}
			if (!customRenderer.shouldUseRenderHelper(ENTITY, var10, ENTITY_BOBBING)) {
				GL11.glTranslatef(0.0F, -var11, 0.0F);
			}
			boolean is3D = customRenderer.shouldUseRenderHelper(ENTITY, var10, BLOCK_3D);
			if (var10.itemID < 256 && (is3D || RenderBlocks.renderItemIn3d(Block.blocksList[var10.itemID].getRenderType()))) {
				this.loadTexture(ForgeHooksClient.getTexture("/terrain.png", var10.getItem()));
				float var21 = 0.25F;
				var15 = Block.blocksList[var10.itemID].getRenderType();
				if (var15 == 1 || var15 == 19 || var15 == 12 || var15 == 2) {
					var21 = 0.5F;
				}
				GL11.glScalef(var21, var21, var21);
				for (int j = 0; j < var13; j++) {
					GL11.glPushMatrix();
					if (j > 0) {
						GL11.glTranslatef(((random.nextFloat() * 2.0F - 1.0F) * 0.2F) / 0.5F, ((random.nextFloat() * 2.0F - 1.0F) * 0.2F) / 0.5F, ((random.nextFloat() * 2.0F - 1.0F) * 0.2F) / 0.5F);
					}
					customRenderer.renderItem(ENTITY, var10, renderBlocks, par1EntityItem);
					GL11.glPopMatrix();
				}
			} else {
				this.loadTexture(ForgeHooksClient.getTexture(var10.itemID < 256 ? "/terrain.png" : "/gui/items.png", var10.getItem()));
				GL11.glScalef(0.5F, 0.5F, 0.5F);
				customRenderer.renderItem(ENTITY, var10, renderBlocks, par1EntityItem);
			}
		} else if (var10.itemID < 256 && RenderBlocks.renderItemIn3d(Block.blocksList[var10.itemID].getRenderType())) {
			GL11.glRotatef(var12, 0.0F, 1.0F, 0.0F);
			this.loadTexture(ForgeHooksClient.getTexture("/terrain.png", Block.blocksList[var10.itemID]));
			float var21 = 0.25F;
			var15 = Block.blocksList[var10.itemID].getRenderType();
			if (var15 == 1 || var15 == 19 || var15 == 12 || var15 == 2) {
				var21 = 0.5F;
			}
			GL11.glScalef(var21, var21, var21);
			for (int var22 = 0; var22 < var13; ++var22) {
				GL11.glPushMatrix();
				if (var22 > 0) {
					var23 = (this.random.nextFloat() * 2.0F - 1.0F) * 0.2F / var21;
					var18 = (this.random.nextFloat() * 2.0F - 1.0F) * 0.2F / var21;
					var19 = (this.random.nextFloat() * 2.0F - 1.0F) * 0.2F / var21;
					GL11.glTranslatef(var23, var18, var19);
				}
				var23 = 1.0F;
				this.renderBlocks.renderBlockAsItem(Block.blocksList[var10.itemID], var10.getItemDamage(), var23);
				GL11.glPopMatrix();
			}
		} else {
			int var14;
			float var16;
			if (var10.getItem().func_46058_c()) {
				GL11.glScalef(0.5F, 0.5F, 0.5F);
				this.loadTexture(ForgeHooksClient.getTexture("/gui/items.png", Item.itemsList[var10.itemID]));
				for (var14 = 0; var14 < var10.getItem().getRenderPasses(var10.getItemDamage()); ++var14) {
					this.random.setSeed(187L); // Fixes Vanilla bug where layers
												// would not render aligns
												// properly.
					var15 = var10.getItem().func_46057_a(var10.getItemDamage(), var14);
					var16 = 1.0F;
					if (this.field_27004_a) {
						int var17 = Item.itemsList[var10.itemID].getColorFromDamage(var10.getItemDamage(), var14);
						var18 = (var17 >> 16 & 255) / 255.0F;
						var19 = (var17 >> 8 & 255) / 255.0F;
						float var20 = (var17 & 255) / 255.0F;
						GL11.glColor4f(var18 * var16, var19 * var16, var20 * var16, 1.0F);
					}
					this.func_40267_a(var15, var13);
				}
			} else {
				GL11.glScalef(0.5F, 0.5F, 0.5F);
				var14 = var10.getIconIndex();
				if (var10.itemID < 256) {
					this.loadTexture(ForgeHooksClient.getTexture("/terrain.png", Block.blocksList[var10.itemID]));
				} else {
					this.loadTexture(ForgeHooksClient.getTexture("/gui/items.png", Item.itemsList[var10.itemID]));
				}
				if (this.field_27004_a) {
					var15 = Item.itemsList[var10.itemID].getColorFromDamage(var10.getItemDamage(), 0);
					var16 = (var15 >> 16 & 255) / 255.0F;
					var23 = (var15 >> 8 & 255) / 255.0F;
					var18 = (var15 & 255) / 255.0F;
					var19 = 1.0F;
					GL11.glColor4f(var16 * var19, var23 * var19, var18 * var19, 1.0F);
				}
				this.func_40267_a(var14, var13);
			}
		}
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
	}

	public void drawItemIntoGui(FontRenderer par1FontRenderer, RenderEngine par2RenderEngine, int par3, int par4, int par5, int par6, int par7) {
		int var10;
		float var11;
		float var12;
		float var13;
		if (par3 < 256 && RenderBlocks.renderItemIn3d(Block.blocksList[par3].getRenderType())) {
			par2RenderEngine.bindTexture(par2RenderEngine.getTexture(ForgeHooksClient.getTexture("/terrain.png", Block.blocksList[par3])));
			Block var15 = Block.blocksList[par3];
			GL11.glPushMatrix();
			GL11.glTranslatef(par6 - 2, par7 + 3, -3.0F + this.zLevel);
			GL11.glScalef(10.0F, 10.0F, 10.0F);
			GL11.glTranslatef(1.0F, 0.5F, 1.0F);
			GL11.glScalef(1.0F, 1.0F, -1.0F);
			GL11.glRotatef(210.0F, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
			var10 = Item.itemsList[par3].getColorFromDamage(par4, 0);
			var11 = (var10 >> 16 & 255) / 255.0F;
			var12 = (var10 >> 8 & 255) / 255.0F;
			var13 = (var10 & 255) / 255.0F;
			if (this.field_27004_a) {
				GL11.glColor4f(var11, var12, var13, 1.0F);
			}
			GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
			this.renderBlocks.useInventoryTint = this.field_27004_a;
			this.renderBlocks.renderBlockAsItem(var15, par4, 1.0F);
			this.renderBlocks.useInventoryTint = true;
			GL11.glPopMatrix();
		} else {
			int var8;
			if (Item.itemsList[par3].func_46058_c()) {
				GL11.glDisable(GL11.GL_LIGHTING);
				par2RenderEngine.bindTexture(par2RenderEngine.getTexture(ForgeHooksClient.getTexture("/gui/items.png", Item.itemsList[par3])));
				for (var8 = 0; var8 < Item.itemsList[par3].getRenderPasses(par4); ++var8) {
					int var9 = Item.itemsList[par3].func_46057_a(par4, var8);
					var10 = Item.itemsList[par3].getColorFromDamage(par4, var8);
					var11 = (var10 >> 16 & 255) / 255.0F;
					var12 = (var10 >> 8 & 255) / 255.0F;
					var13 = (var10 & 255) / 255.0F;
					if (this.field_27004_a) {
						GL11.glColor4f(var11, var12, var13, 1.0F);
					}
					this.renderTexturedQuad(par6, par7, var9 % 16 * 16, var9 / 16 * 16, 16, 16);
				}
				GL11.glEnable(GL11.GL_LIGHTING);
			} else if (par5 >= 0) {
				GL11.glDisable(GL11.GL_LIGHTING);
				if (par3 < 256) {
					par2RenderEngine.bindTexture(par2RenderEngine.getTexture(ForgeHooksClient.getTexture("/terrain.png", Block.blocksList[par3])));
				} else {
					par2RenderEngine.bindTexture(par2RenderEngine.getTexture(ForgeHooksClient.getTexture("/gui/items.png", Item.itemsList[par3])));
				}
				var8 = Item.itemsList[par3].getColorFromDamage(par4, 0);
				float var14 = (var8 >> 16 & 255) / 255.0F;
				float var16 = (var8 >> 8 & 255) / 255.0F;
				var11 = (var8 & 255) / 255.0F;
				if (this.field_27004_a) {
					GL11.glColor4f(var14, var16, var11, 1.0F);
				}
				this.renderTexturedQuad(par6, par7, par5 % 16 * 16, par5 / 16 * 16, 16, 16);
				GL11.glEnable(GL11.GL_LIGHTING);
			}
		}
		GL11.glEnable(GL11.GL_CULL_FACE);
	}

	/**
	 * Renders the item's icon or block into the UI at the specified position.
	 */
	public void renderItemIntoGUI(FontRenderer par1FontRenderer, RenderEngine par2RenderEngine, ItemStack par3ItemStack, int par4, int par5) {
		if (par3ItemStack != null) {
			IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(par3ItemStack, INVENTORY);
			if (customRenderer != null) {
				par2RenderEngine.bindTexture(par2RenderEngine.getTexture(ForgeHooksClient.getTexture(par3ItemStack.itemID < 256 ? "/terrain.png" : "/gui/items.png",
						Item.itemsList[par3ItemStack.itemID])));
				if (customRenderer.shouldUseRenderHelper(INVENTORY, par3ItemStack, INVENTORY_BLOCK)) {
					GL11.glPushMatrix();
					GL11.glTranslatef(par4 - 2, par5 + 3, -3.0F + this.zLevel);
					GL11.glScalef(10F, 10F, 10F);
					GL11.glTranslatef(1.0F, 0.5F, 1.0F);
					GL11.glScalef(1.0F, 1.0F, -1F);
					GL11.glRotatef(210F, 1.0F, 0.0F, 0.0F);
					GL11.glRotatef(45F, 0.0F, 1.0F, 0.0F);
					int color = Item.itemsList[par3ItemStack.itemID].getColorFromDamage(par4, 0);
					float r = (color >> 16 & 0xff) / 255F;
					float g = (color >> 8 & 0xff) / 255F;
					float b = (color & 0xff) / 255F;
					if (field_27004_a) {
						GL11.glColor4f(r, g, b, 1.0F);
					}
					GL11.glRotatef(-90F, 0.0F, 1.0F, 0.0F);
					renderBlocks.useInventoryTint = field_27004_a;
					customRenderer.renderItem(INVENTORY, par3ItemStack, renderBlocks);
					renderBlocks.useInventoryTint = true;
					GL11.glPopMatrix();
				} else {
					GL11.glDisable(GL11.GL_LIGHTING);
					GL11.glPushMatrix();
					GL11.glTranslatef((par4), (par5), -3.0F + this.zLevel);
					int color = Item.itemsList[par3ItemStack.itemID].getColorFromDamage(par4, 0);
					float r = (color >> 16 & 255) / 255.0F;
					float g = (color >> 8 & 255) / 255.0F;
					float b = (color & 255) / 255.0F;
					if (this.field_27004_a) {
						GL11.glColor4f(r, g, b, 1.0F);
					}
					customRenderer.renderItem(INVENTORY, par3ItemStack, renderBlocks);
					GL11.glPopMatrix();
					GL11.glEnable(GL11.GL_LIGHTING);
				}
			} else {
				this.drawItemIntoGui(par1FontRenderer, par2RenderEngine, par3ItemStack.itemID, par3ItemStack.getItemDamage(), par3ItemStack.getIconIndex(), par4, par5);
			}
			if (par3ItemStack != null && par3ItemStack.hasEffect()) {
				GL11.glDepthFunc(GL11.GL_GREATER);
				GL11.glDisable(GL11.GL_LIGHTING);
				GL11.glDepthMask(false);
				par2RenderEngine.bindTexture(par2RenderEngine.getTexture("%blur%/misc/glint.png"));
				this.zLevel -= 50.0F;
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glBlendFunc(GL11.GL_DST_COLOR, GL11.GL_DST_COLOR);
				GL11.glColor4f(0.5F, 0.25F, 0.8F, 1.0F);
				this.func_40266_a(par4 * 431278612 + par5 * 32178161, par4 - 2, par5 - 2, 20, 20);
				GL11.glDisable(GL11.GL_BLEND);
				GL11.glDepthMask(true);
				this.zLevel += 50.0F;
				GL11.glEnable(GL11.GL_LIGHTING);
				GL11.glDepthFunc(GL11.GL_LEQUAL);
			}
		}
	}

	public void renderItemOverlayIntoGUI(FontRenderer par1FontRenderer, RenderEngine par2RenderEngine, ItemStack par3ItemStack, int par4, int par5) {
		if (par3ItemStack != null) {
			if (par3ItemStack.stackSize > 1) {
				String var6 = "" + par3ItemStack.stackSize;
				GL11.glDisable(GL11.GL_LIGHTING);
				GL11.glDisable(GL11.GL_DEPTH_TEST);
				par1FontRenderer.drawStringWithShadow(var6, par4 + 19 - 2 - par1FontRenderer.getStringWidth(var6), par5 + 6 + 3, 16777215);
				GL11.glEnable(GL11.GL_LIGHTING);
				GL11.glEnable(GL11.GL_DEPTH_TEST);
			}
			if (par3ItemStack.isItemDamaged()) {
				int var11 = (int) Math.round(13.0D - par3ItemStack.getItemDamageForDisplay() * 13.0D / par3ItemStack.getMaxDamage());
				int var7 = (int) Math.round(255.0D - par3ItemStack.getItemDamageForDisplay() * 255.0D / par3ItemStack.getMaxDamage());
				GL11.glDisable(GL11.GL_LIGHTING);
				GL11.glDisable(GL11.GL_DEPTH_TEST);
				GL11.glDisable(GL11.GL_TEXTURE_2D);
				Tessellator var8 = Tessellator.instance;
				int var9 = 255 - var7 << 16 | var7 << 8;
				int var10 = (255 - var7) / 4 << 16 | 16128;
				this.renderQuad(var8, par4 + 2, par5 + 13, 13, 2, 0);
				this.renderQuad(var8, par4 + 2, par5 + 13, 12, 1, var10);
				this.renderQuad(var8, par4 + 2, par5 + 13, var11, 1, var9);
				GL11.glEnable(GL11.GL_TEXTURE_2D);
				GL11.glEnable(GL11.GL_LIGHTING);
				GL11.glEnable(GL11.GL_DEPTH_TEST);
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			}
		}
	}

	/**
	 * Adds a textured quad to the tesselator at the specified position with the
	 * specified texture coords, width and height. Args: x, y, u, v, width,
	 * height
	 */
	public void renderTexturedQuad(int par1, int par2, int par3, int par4, int par5, int par6) {
		float var7 = 0.00390625F;
		float var8 = 0.00390625F;
		Tessellator var9 = Tessellator.instance;
		var9.startDrawingQuads();
		var9.addVertexWithUV(par1 + 0, par2 + par6, this.zLevel, (par3 + 0) * var7, (par4 + par6) * var8);
		var9.addVertexWithUV(par1 + par5, par2 + par6, this.zLevel, (par3 + par5) * var7, (par4 + par6) * var8);
		var9.addVertexWithUV(par1 + par5, par2 + 0, this.zLevel, (par3 + par5) * var7, (par4 + 0) * var8);
		var9.addVertexWithUV(par1 + 0, par2 + 0, this.zLevel, (par3 + 0) * var7, (par4 + 0) * var8);
		var9.draw();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	private void func_40266_a(int par1, int par2, int par3, int par4, int par5) {
		for (int var6 = 0; var6 < 2; ++var6) {
			if (var6 == 0) {
				GL11.glBlendFunc(GL11.GL_SRC_COLOR, GL11.GL_ONE);
			}
			if (var6 == 1) {
				GL11.glBlendFunc(GL11.GL_SRC_COLOR, GL11.GL_ONE);
			}
			float var7 = 0.00390625F;
			float var8 = 0.00390625F;
			float var9 = System.currentTimeMillis() % (3000 + var6 * 1873) / (3000.0F + var6 * 1873) * 256.0F;
			float var10 = 0.0F;
			Tessellator var11 = Tessellator.instance;
			float var12 = 4.0F;
			if (var6 == 1) {
				var12 = -1.0F;
			}
			var11.startDrawingQuads();
			var11.addVertexWithUV(par2 + 0, par3 + par5, this.zLevel, (var9 + par5 * var12) * var7, (var10 + par5) * var8);
			var11.addVertexWithUV(par2 + par4, par3 + par5, this.zLevel, (var9 + par4 + par5 * var12) * var7, (var10 + par5) * var8);
			var11.addVertexWithUV(par2 + par4, par3 + 0, this.zLevel, (var9 + par4) * var7, (var10 + 0.0F) * var8);
			var11.addVertexWithUV(par2 + 0, par3 + 0, this.zLevel, (var9 + 0.0F) * var7, (var10 + 0.0F) * var8);
			var11.draw();
		}
	}

	private void func_40267_a(int par1, int par2) {
		Tessellator var3 = Tessellator.instance;
		float var4 = (par1 % 16 * 16 + 0) / 256.0F;
		float var5 = (par1 % 16 * 16 + 16) / 256.0F;
		float var6 = (par1 / 16 * 16 + 0) / 256.0F;
		float var7 = (par1 / 16 * 16 + 16) / 256.0F;
		float var8 = 1.0F;
		float var9 = 0.5F;
		float var10 = 0.25F;
		for (int var11 = 0; var11 < par2; ++var11) {
			GL11.glPushMatrix();
			if (var11 > 0) {
				float var12 = (this.random.nextFloat() * 2.0F - 1.0F) * 0.3F;
				float var13 = (this.random.nextFloat() * 2.0F - 1.0F) * 0.3F;
				float var14 = (this.random.nextFloat() * 2.0F - 1.0F) * 0.3F;
				GL11.glTranslatef(var12, var13, var14);
			}
			GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
			var3.startDrawingQuads();
			var3.setNormal(0.0F, 1.0F, 0.0F);
			var3.addVertexWithUV(0.0F - var9, 0.0F - var10, 0.0D, var4, var7);
			var3.addVertexWithUV(var8 - var9, 0.0F - var10, 0.0D, var5, var7);
			var3.addVertexWithUV(var8 - var9, 1.0F - var10, 0.0D, var5, var6);
			var3.addVertexWithUV(0.0F - var9, 1.0F - var10, 0.0D, var4, var6);
			var3.draw();
			GL11.glPopMatrix();
		}
	}

	/**
	 * Adds a quad to the tesselator at the specified position with the set
	 * width and height and color. Args: tessellator, x, y, width, height, color
	 */
	private void renderQuad(Tessellator par1Tessellator, int par2, int par3, int par4, int par5, int par6) {
		par1Tessellator.startDrawingQuads();
		par1Tessellator.setColorOpaque_I(par6);
		par1Tessellator.addVertex(par2 + 0, par3 + 0, 0.0D);
		par1Tessellator.addVertex(par2 + 0, par3 + par5, 0.0D);
		par1Tessellator.addVertex(par2 + par4, par3 + par5, 0.0D);
		par1Tessellator.addVertex(par2 + par4, par3 + 0, 0.0D);
		par1Tessellator.draw();
	}
}
