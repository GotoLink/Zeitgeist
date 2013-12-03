package zeitgeist.client.render;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import zeitgeist.client.models.zei_ModelBi;
import zeitgeist.client.models.zei_ModelGolemPure;
import zeitgeist.client.models.zei_ModelLong;
import zeitgeist.common.entity.zei_EntityGolemPure;

public class zei_RenderGolemPure extends RenderLiving {
	zei_ModelBi bip;
	zei_ModelLong longy;
	ModelBase models[];
	zei_ModelGolemPure spider;

	public zei_RenderGolemPure() {
		super(new zei_ModelGolemPure(), 1.1f);
		spider = new zei_ModelGolemPure();
		longy = new zei_ModelLong();
		bip = new zei_ModelBi();
		models = new ModelBase[4];
		models[0] = spider;
		models[1] = longy;
		models[2] = bip;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void preRenderCallback(EntityLivingBase el, float par2) {
		zei_EntityGolemPure en = (zei_EntityGolemPure) el;
		int ii = en.getForm();
		ModelBase needed = models[ii];
		float var7;
		float var8;
		switch (ii) {
		case 1:
			if (en.texture != "/zeitgeist/golemPureLong.png") {
				en.texture = "/zeitgeist/golemPureLong.png";
				en.set(0.2F, 1.5F);
			}
			break;
		case 2:
			if (en.texture != "/zeitgeist/golemPureBi.png") {
				en.texture = "/zeitgeist/golemPureBi.png";
				en.set(0.25f, 1.5f);
			}
			break;
		default:
			if (en.texture != "/zeitgeist/golemPure.png") {
				en.texture = "/zeitgeist/golemPure.png";
				en.set(1.2f, 0.8f);
			}
		}
		switch (ii) {
		case 1:
			mainModel = models[1];
			shadowSize = 0.6f;
			break;
		case 2:
			mainModel = models[2];
			shadowSize = 0.4f;
			break;
		default:
			mainModel = models[0];
			shadowSize = 1.1f;
			float f = 0.85f;
			GL11.glScalef(f, f, f);
		}
	}

	@Override
	protected void renderEquippedItems(EntityLivingBase entityliving, float f) {
		if (entityliving instanceof zei_EntityGolemPure) {
			zei_EntityGolemPure ea = ((zei_EntityGolemPure) entityliving);
			int ii = ea.getType();
			int dam = ea.getColo();
			if (ii > 0) {
				ItemStack itemstack = new ItemStack(ii, 1, dam);
				Block block = Block.blocksList[ii];
				GL11.glPushMatrix();
				int form = ea.getForm();
				if (ii == 81) {
					GL11.glScalef(1.2f, 1, 1.2f);
				}
				if (form == 1) {
					GL11.glPushMatrix();
					longy.head.postRender(0.0625F);
					GL11.glScalef(1, -1, 1);
					block.setBlockBounds(0.25f, 0.75f, 0.25f, 0.75f, 1, 0.75f);
					GL11.glTranslatef(0F, -0.25f, 0);
					renderManager.itemRenderer.renderItem(entityliving, itemstack, 0);
					block.setBlockBounds(0.4375f, 0.9375f, 0.1875f, 0.5625f, 1, 0.8125f);
					GL11.glRotatef(-85, 1, 0, 0);
					GL11.glTranslatef(-0.125F, -0.5f, 0.75f);
					renderManager.itemRenderer.renderItem(entityliving, itemstack, 0);
					GL11.glTranslatef(0.25F, 0, 0);
					renderManager.itemRenderer.renderItem(entityliving, itemstack, 0);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					longy.body.postRender(0.0625F);
					GL11.glScalef(1, -1, 1);
					// GL11.glTranslatef(0F, -0.55f, 0);
					block.setBlockBounds(0.3125f, 0, 0.375f, 0.6875f, 0.5f, 0.625f);
					renderManager.itemRenderer.renderItem(entityliving, itemstack, 0);
					GL11.glPopMatrix();
					block.setBlockBounds(0, 0, 0, 1, 1, 1);
				} else if (form == 2) {
					GL11.glPushMatrix();
					bip.body.postRender(0.0625F);
					GL11.glRotatef(90, 1.0F, 0.0F, 0.0F);
					block.setBlockBounds(0.25f, 0.5f, 0.25f, 0.75f, 1, 0.75f);
					GL11.glTranslatef(0F, -0.125f, 0);
					renderManager.itemRenderer.renderItem(entityliving, itemstack, 0);
					GL11.glTranslatef(0F, 0, -0.625f);
					renderManager.itemRenderer.renderItem(entityliving, itemstack, 0);
					GL11.glPopMatrix();
					block.setBlockBounds(0.375f, 0.75f, 0.25f, 0.625f, 1, 0.75f);
					GL11.glPushMatrix();
					bip.leftleg.postRender(0.0625F);
					GL11.glScalef(1, -1, 1);
					GL11.glRotatef(-90, 1, 0.0F, 0.0F);
					GL11.glTranslatef(0, -0.375f, -0.25f);
					renderManager.itemRenderer.renderItem(entityliving, itemstack, 0);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					bip.rightleg.postRender(0.0625F);
					GL11.glScalef(1, -1, 1);
					GL11.glRotatef(-90, 1, 0.0F, 0.0F);
					GL11.glTranslatef(0, -0.375f, -0.25f);
					renderManager.itemRenderer.renderItem(entityliving, itemstack, 0);
					GL11.glPopMatrix();
					block.setBlockBounds(0.3125f, 0.5f, 0.3125f, 0.6875f, 1, 0.6875f);
					bip.head.postRender(0.0625F);
					float ff = 0.55f;
					// GL11.glScalef(0.4f, -ff, 0.4f);
					GL11.glScalef(1, -1, 1);
					GL11.glTranslatef(0F, -0.3125f, -0.1875F);
					renderManager.itemRenderer.renderItem(entityliving, itemstack, 0);
					block.setBlockBounds(0, 0, 0, 1, 1, 1);
					//
				} else {
					GL11.glPushMatrix();
					GL11.glTranslatef(0F, 1.0F, 0.75F);
					GL11.glRotatef(150F, 1.0F, 0.0F, 0.0F);
					GL11.glTranslatef(0F, 0, -0.2F);
					renderManager.itemRenderer.renderItem(entityliving, itemstack, 0);
					GL11.glRotatef(40F, 1.0F, 0, 0.0F);
					GL11.glTranslatef(0F, 0.4f, 0.65F);
					// GL11.glScalef(0.6f, 0.6f, 0.6f);
					block.setBlockBounds(0.1875f, 0.5f, 0.1875f, 0.8125f, 1, 0.8125f);
					renderManager.itemRenderer.renderItem(entityliving, itemstack, 0);
					GL11.glTranslatef(0F, -1.2f, 0.625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					spider.Head.postRender(0.0625F);
					float ff = 1f;
					GL11.glScalef(ff, -ff, ff);
					GL11.glTranslatef(0F, -0.125f, -0.125F);
					block.setBlockBounds(0.25f, 0.5f, 0.25f, 0.75f, 1, 0.75f);
					renderManager.itemRenderer.renderItem(entityliving, itemstack, 0);
					GL11.glPopMatrix();
					block.setBlockBounds(0, 0, 0, 1, 1, 1);
				}
				GL11.glPopMatrix();
			}
		}
	}
}
