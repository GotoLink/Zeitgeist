package zeitgeist.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class zei_RenderBurden extends RenderLiving {
	public zei_RenderBurden(ModelBase modelbase, float f) {
		super(modelbase, f);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		// TODO Auto-generated method stub
		return null;
	}
	/*
	 * protected void renderEquippedItems(EntityLiving entityliving, float f) {
	 * if(entityliving instanceof zei_EntityBurden){ zei_EntityBurden b =
	 * (zei_EntityBurden)entityliving; //b.storage. } }
	 */
}
