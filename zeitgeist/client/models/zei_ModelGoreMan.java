package zeitgeist.client.models;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import zeitgeist.common.entity.zei_EntityGoreMan;

public class zei_ModelGoreMan extends ModelBiped {
	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		int u = ((zei_EntityGoreMan) entity).getSever();
		if (u > 4) {
			bipedRightArm.render(f5);
		}
		if (u > 3) {
			bipedLeftArm.render(f5);
		}
		if (u > 2) {
			bipedHead.render(f5);
			bipedHeadwear.render(f5);
		}
		bipedBody.render(f5);
		bipedRightLeg.render(f5);
		bipedLeftLeg.render(f5);
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3,
			float f4, float f5, Entity ent) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, ent);
		float f6 = MathHelper.sin(onGround * (float) Math.PI);
		float f7 = MathHelper
				.sin((1.0F - (1.0F - onGround) * (1.0F - onGround))
						* (float) Math.PI);
		bipedRightArm.rotateAngleZ = 0.0F;
		bipedLeftArm.rotateAngleZ = 0.0F;
		bipedRightArm.rotateAngleY = -(0.1F - f6 * 0.6F);
		bipedLeftArm.rotateAngleY = 0.1F - f6 * 0.6F;
		bipedRightArm.rotateAngleX = -((float) Math.PI / 2F);
		bipedLeftArm.rotateAngleX = -((float) Math.PI / 2F);
		bipedRightArm.rotateAngleX -= f6 * 1.2F - f7 * 0.4F;
		bipedLeftArm.rotateAngleX -= f6 * 1.2F - f7 * 0.4F;
		bipedRightArm.rotateAngleZ += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
		bipedLeftArm.rotateAngleZ -= MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
		bipedRightArm.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.05F;
		bipedLeftArm.rotateAngleX -= MathHelper.sin(f2 * 0.067F) * 0.05F;
	}
}
