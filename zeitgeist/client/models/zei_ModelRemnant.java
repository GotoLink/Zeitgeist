package zeitgeist.client.models;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class zei_ModelRemnant extends ModelBiped {
	public zei_ModelRemnant() {
		float f = 0.0F;
		RightArm = new ModelRenderer(this, 56, 18);
		RightArm.addBox(-1F, -2F, -1F, 2, 12, 2, f);
		RightArm.setRotationPoint(-5F, 2.0F, 0.0F);
		LeftArm = new ModelRenderer(this, 56, 18);
		LeftArm.mirror = true;
		LeftArm.addBox(-1F, -2F, -1F, 2, 12, 2, f);
		LeftArm.setRotationPoint(5F, 2.0F, 0.0F);
		RightLeg = new ModelRenderer(this, 56, 18);
		RightLeg.addBox(-1F, 0.0F, -1F, 2, 12, 2, f);
		RightLeg.setRotationPoint(-2F, 12F, 0.0F);
		LeftLeg = new ModelRenderer(this, 56, 18);
		LeftLeg.mirror = true;
		LeftLeg.addBox(-1F, 0.0F, -1F, 2, 12, 2, f);
		LeftLeg.setRotationPoint(2.0F, 12F, 0.0F);
		bipedBody.rotationPointZ -= 3f;
		bipedHead.rotationPointZ -= 5f;
		bipedHeadwear.rotationPointZ -= 5f;
		RightArm.rotationPointZ -= 5f;
		bipedRightArm.rotationPointZ -= 5f;
		LeftArm.rotationPointZ -= 5f;
		bipedLeftArm.rotationPointZ -= 5f;
	}

	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		RightArm.render(f5);
		LeftArm.render(f5);
		LeftLeg.render(f5);
		RightLeg.render(f5);
	}

	public void setRotationAngles(float f, float f1, float f2, float f3,
			float f4, float f5, Entity ent) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, ent);
		bipedRightArm.rotationPointZ -= 5f;
		bipedLeftArm.rotationPointZ -= 5f;
		bipedHead.rotateAngleX += 0.5f;
		bipedHeadwear.rotateAngleX += 0.5f;
		bipedBody.rotateAngleX += 0.3f;
		bipedRightArm.rotateAngleX -= 0.3f;
		bipedLeftArm.rotateAngleX -= 0.3f;
		RightArm.rotateAngleX = bipedRightArm.rotateAngleX;
		RightArm.rotateAngleY = bipedRightArm.rotateAngleY;
		RightArm.rotateAngleZ = bipedRightArm.rotateAngleZ;
		LeftArm.rotateAngleX = bipedLeftArm.rotateAngleX;
		LeftArm.rotateAngleY = bipedLeftArm.rotateAngleY;
		LeftArm.rotateAngleZ = bipedLeftArm.rotateAngleZ;
		LeftLeg.rotateAngleX = bipedLeftLeg.rotateAngleX;
		LeftLeg.rotateAngleY = bipedLeftLeg.rotateAngleY;
		LeftLeg.rotateAngleZ = bipedLeftLeg.rotateAngleZ;
		RightLeg.rotateAngleX = bipedRightLeg.rotateAngleX;
		RightLeg.rotateAngleY = bipedRightLeg.rotateAngleY;
		RightLeg.rotateAngleZ = bipedRightLeg.rotateAngleZ;
	}

	public ModelRenderer RightLeg;
	public ModelRenderer LeftLeg;
	public ModelRenderer LeftArm;
	public ModelRenderer RightArm;
}
