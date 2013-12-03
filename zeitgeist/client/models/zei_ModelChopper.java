package zeitgeist.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class zei_ModelChopper extends ModelBase {
	public zei_ModelChopper() {
		hed = new ModelRenderer(this, 0, 3);
		hed.addBox(-3F, 0F, -5F, 6, 4, 3, 0F);
		hed.setRotationPoint(0F, 16F, 0F);
		hed.rotateAngleX = 0F;
		hed.rotateAngleY = 0F;
		hed.rotateAngleZ = 0F;
		hed.mirror = false;
		bod = new ModelRenderer(this, 20, 6);
		bod.addBox(-2F, 0F, -2F, 4, 8, 4, 0F);
		bod.setRotationPoint(0F, 16F, 0F);
		bod.rotateAngleX = 0F;
		bod.rotateAngleY = 0F;
		bod.rotateAngleZ = 0F;
		bod.mirror = false;
		blade1 = new ModelRenderer(this, 0, 0);
		blade1.addBox(-8F, -0.5F, -0.5F, 16, 1, 1, 0F);
		blade1.setRotationPoint(0F, 15F, 0F);
		blade1.rotateAngleX = 0F;
		blade1.rotateAngleY = 0F;
		blade1.rotateAngleZ = 0F;
		blade1.mirror = false;
		blade2 = new ModelRenderer(this, 0, 0);
		blade2.addBox(-8F, -0.5F, -0.5F, 16, 1, 1, 0F);
		blade2.setRotationPoint(0F, 15F, 0F);
		blade2.rotateAngleX = 0F;
		blade2.rotateAngleY = ((float) Math.PI / 2F);
		blade2.rotateAngleZ = 0F;
		blade2.mirror = false;
	}

	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		hed.render(f5);
		bod.render(f5);
		blade1.render(f5);
		blade2.render(f5);
	}

	public void setRotationAngles(float f, float f1, float f2, float f3,
			float f4, float f5, Entity ent) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, ent);
		blade1.rotateAngleY = f * f2 * 3.14F;
		blade2.rotateAngleY = ((float) Math.PI / 2F) + blade1.rotateAngleY;
		hed.rotateAngleX = f4 / (180F / (float) Math.PI);
		hed.rotateAngleY = f3 / (180F / (float) Math.PI);
	}

	// fields
	public ModelRenderer hed;
	public ModelRenderer bod;
	public ModelRenderer blade1;
	public ModelRenderer blade2;
}
