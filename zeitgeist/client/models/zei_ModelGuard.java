package zeitgeist.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class zei_ModelGuard extends ModelBase {
	public zei_ModelGuard() {
		rod = new ModelRenderer(this, 0, 0);
		rod.addBox(-1F, 0F, -1F, 2, 2, 2, 0F);
		rod.setRotationPoint(0F, 20F, 0F);
		rod.rotateAngleX = 0F;
		rod.rotateAngleY = (float) Math.PI;
		rod.rotateAngleZ = 0F;
		rod.mirror = false;
		head = new ModelRenderer(this, 13, 0);
		head.addBox(-2.5F, -2.5F, -9F, 5, 5, 12, 0F);
		head.setRotationPoint(0F, 18F, 0F);
		head.rotateAngleX = 0F;
		head.rotateAngleY = 0F;
		head.rotateAngleZ = 0F;
		head.mirror = false;
		thing = new ModelRenderer(this, 5, 19);
		thing.addBox(-1.5F, -1.5F, -2F, 3, 3, 7, 0F);
		thing.setRotationPoint(0F, 18F, 0F);
		thing.rotateAngleX = 0F;
		thing.rotateAngleY = 0F;
		thing.rotateAngleZ = 0F;
		thing.mirror = false;
		stand2 = new ModelRenderer(this, 26, 19);
		stand2.addBox(-4F, 0F, -4F, 8, 2, 8, 0F);
		stand2.setRotationPoint(0F, 22F, 0F);
		stand2.rotateAngleX = 0F;
		stand2.rotateAngleY = 0F;
		stand2.rotateAngleZ = 0F;
		stand2.mirror = false;
	}

	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		rod.render(f5);
		head.render(f5);
		thing.render(f5);
		stand2.render(f5);
	}

	public void setRotationAngles(float f, float f1, float f2, float f3,
			float f4, float f5, Entity ent) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, ent);
		head.rotateAngleX = f4 / (180F / (float) Math.PI);
		head.rotateAngleY = f3 / (180F / (float) Math.PI);
		thing.rotateAngleX = head.rotateAngleX;
		thing.rotateAngleY = head.rotateAngleY;
	}

	// fields
	public ModelRenderer rod;
	public ModelRenderer head;
	public ModelRenderer thing;
	public ModelRenderer stand2;
}
