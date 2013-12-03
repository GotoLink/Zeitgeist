package zeitgeist.client.models;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import zeitgeist.common.entity.zei_EntityRagdoll;

public class zei_ModelRLimb extends ModelBiped {
	public zei_ModelRLimb() {
		this(0.0F);
	}

	public zei_ModelRLimb(float f) {
		this(f, 0.0F);
	}

	public zei_ModelRLimb(float f, float f1) {
		bipedHead = new ModelRenderer(this, 0, 0);
		bipedHead.addBox(-4F, -8F, -4F, 8, 8, 8, f);
		bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F); // + f1
		bipedRightArm = new ModelRenderer(this, 40, 16);
		bipedRightArm.addBox(-3F, -2F, -2F, 4, 12, 4, f);
		bipedRightArm.setRotationPoint(-5F, -2f, -2f); // 2.0F + f1
		bipedLeftArm = new ModelRenderer(this, 40, 16);
		bipedLeftArm.mirror = true;
		bipedLeftArm.addBox(-2F, 0f, -2F, 4, 12, 4, f);
		bipedLeftArm.setRotationPoint(0F, 0f, 0f);// 2.0F + f1
		bipedHead.rotateAngleZ = 3.14f;
		bipedRightArm.rotateAngleZ = 0.0F;
		bipedLeftArm.rotateAngleZ = 0.0F;
		bipedRightArm.rotateAngleY = -(0.1F);
		bipedLeftArm.rotateAngleY = 0.1F;
		bipedRightArm.rotateAngleX = -((float) Math.PI / 2F);
		bipedLeftArm.rotateAngleX = -((float) Math.PI / 2F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		int u = ((zei_EntityRagdoll) entity).type;
		bipedRightArm.rotateAngleY = entity.rotationYaw;
		bipedLeftArm.rotateAngleY = -(float) (entity.rotationYaw);
		bipedLeftArm.rotateAngleX = (float) (entity.rotationPitch - 3.14);
		// GL11.glRotatef(, 0, 1,0);
		// GL11.glPushMatrix();
		// GL11.glRotatef(entity.rotationPitch, 1, 0,0);
		if (u == 2) {
			bipedRightArm.render(f5);
		}
		if (u == 1) {
			bipedLeftArm.render(f5);
		}
		if (u == 0) {
			bipedHead.render(f5);
			bipedHeadwear.render(f5);
		}
		// GL11.glPopMatrix();
	}
}
