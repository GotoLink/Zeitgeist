package zeitgeist.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class zei_ModelGolemPure extends ModelBase {
	public ModelRenderer Head;
	ModelRenderer Body;
	ModelRenderer Eye1;
	ModelRenderer Eye2;
	ModelRenderer Leg1;
	ModelRenderer Leg2;
	ModelRenderer Leg3;
	ModelRenderer Leg4;
	ModelRenderer Leg5;
	ModelRenderer Leg6;
	ModelRenderer Leg7;
	ModelRenderer Leg8;
	ModelRenderer Legu1;
	ModelRenderer Legu2;

	public zei_ModelGolemPure() {
		textureWidth = 64;
		textureHeight = 32;
		Head = new ModelRenderer(this, 32, 4);
		Head.addBox(-1F, -1F, -1F, 2, 2, 2);
		Head.setRotationPoint(0F, 16F, -4F);
		Head.setTextureSize(64, 32);
		Head.mirror = true;
		setRotation(Head, 0F, 0F, 0F);
		Eye1 = new ModelRenderer(this, 0, 16);
		Eye1.addBox(-1F, -1F, -1F, 2, 2, 2);
		Eye1.setRotationPoint(-3F, 0F, -6F);
		Eye1.setTextureSize(64, 32);
		Eye1.mirror = true;
		setRotation(Eye1, 0F, 0F, 0F);
		Leg8 = new ModelRenderer(this, 18, 0);
		Leg8.addBox(-1F, -1F, -1F, 17, 2, 2);
		Leg8.setRotationPoint(4F, 16F, -1F);
		Leg8.setTextureSize(64, 32);
		Leg8.mirror = true;
		setRotation(Leg8, 0F, 0.6874945F, -0.7003005F);
		Leg6 = new ModelRenderer(this, 18, 0);
		Leg6.addBox(-1F, -1F, -1F, 16, 2, 2);
		Leg6.setRotationPoint(4F, 17F, 0F);
		Leg6.setTextureSize(64, 32);
		Leg6.mirror = true;
		setRotation(Leg6, 0F, 0.2792527F, -0.6259432F);
		Leg4 = new ModelRenderer(this, 18, 0);
		Leg4.addBox(-1F, -1F, -1F, 16, 2, 2);
		Leg4.setRotationPoint(4F, 17F, 1F);
		Leg4.setTextureSize(64, 32);
		Leg4.mirror = true;
		setRotation(Leg4, 0F, -0.2792527F, -0.7746577F);
		Leg2 = new ModelRenderer(this, 18, 0);
		Leg2.addBox(-1F, -1F, -1F, 16, 2, 2);
		Leg2.setRotationPoint(4F, 17F, 2F);
		Leg2.setTextureSize(64, 32);
		Leg2.mirror = true;
		setRotation(Leg2, 0F, -0.5759587F, -0.6631218F);
		Leg5 = new ModelRenderer(this, 18, 0);
		Leg5.addBox(-15F, -1F, -1F, 16, 2, 2);
		Leg5.setRotationPoint(-4F, 17F, 0F);
		Leg5.setTextureSize(64, 32);
		Leg5.mirror = true;
		setRotation(Leg5, 0F, -0.2792527F, 0.6259432F);
		Leg3 = new ModelRenderer(this, 18, 0);
		Leg3.addBox(-15F, -1F, -1F, 16, 2, 2);
		Leg3.setRotationPoint(-4F, 17F, 1F);
		Leg3.setTextureSize(64, 32);
		Leg3.mirror = true;
		setRotation(Leg3, 0F, 0.2792527F, 0.4028716F);
		Leg1 = new ModelRenderer(this, 18, 0);
		Leg1.addBox(-15F, -1F, -1F, 16, 2, 2);
		Leg1.setRotationPoint(-4F, 17F, 2F);
		Leg1.setTextureSize(64, 32);
		Leg1.mirror = true;
		setRotation(Leg1, 0F, 0.5759587F, 0.5144074F);
		Leg7 = new ModelRenderer(this, 18, 0);
		Leg7.addBox(-15F, -1F, -1F, 16, 2, 2);
		Leg7.setRotationPoint(-4F, 17F, -1F); // 20
		Leg7.setTextureSize(64, 32);
		Leg7.mirror = true;
		setRotation(Leg7, 0F, -0.6874945F, 0);
		Body = new ModelRenderer(this, 0, 0);
		Body.addBox(-3F, -3F, -3F, 6, 6, 6);
		Body.setRotationPoint(0F, 20F, 0F);
		Body.setTextureSize(64, 32);
		Body.mirror = true;
		setRotation(Body, 0F, 0F, 0F);
		Eye2 = new ModelRenderer(this, 0, 16);
		Eye2.addBox(-1F, -1F, -1F, 2, 2, 2);
		Eye2.setRotationPoint(3F, 0F, -6F);
		Eye2.setTextureSize(64, 32);
		Eye2.mirror = true;
		setRotation(Eye2, 0F, 0F, 0F);
		Legu1 = new ModelRenderer(this, 0, 13);
		Legu1.addBox(-15F, -1F, -1F, 16, 1, 1);
		Legu1.setRotationPoint(-16F, 16f, 0F); // 20
		Legu1.setTextureSize(64, 32);
		Legu1.mirror = true;
		setRotation(Legu1, 0F, 0, 1.57f);
		Legu2 = new ModelRenderer(this, 0, 13);
		Legu2.addBox(-15F, -1F, -1F, 16, 1, 1);
		Legu2.setRotationPoint(16F, 16f, 0F); // 20
		Legu2.setTextureSize(64, 32);
		Legu2.mirror = true;
		setRotation(Legu2, 0F, 0, 1.57f);
		Leg8.addChild(Legu2);
		Leg6.addChild(Legu2);
		Leg4.addChild(Legu2);
		Leg2.addChild(Legu2);
		Leg7.addChild(Legu1);
		Leg5.addChild(Legu1);
		Leg3.addChild(Legu1);
		Leg1.addChild(Legu1);
		Head.addChild(Eye1);
		Head.addChild(Eye2);
	}

	public void offset(int v1, int v2) {
		// Leg8.cubeList.get(0).setTextureOffset(v1, v2);
		Leg1.setTextureOffset(v1, v2);
		Leg2.setTextureOffset(v1, v2);
		Leg3.setTextureOffset(v1, v2);
		Leg4.setTextureOffset(v1, v2);
		Leg5.setTextureOffset(v1, v2);
		Leg6.setTextureOffset(v1, v2);
		Leg7.setTextureOffset(v1, v2);
		((ModelRenderer) Leg7.childModels.get(0)).setTextureOffset(v1, v2);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		// Head.render(f5);
		// Eye1.render(f5);
		Leg8.render(f5);
		Leg6.render(f5);
		Leg4.render(f5);
		Leg2.render(f5);
		// Legu1.render(f5);
		Leg5.render(f5);
		Leg3.render(f5);
		Leg1.render(f5);
		Leg7.render(f5);
		// Body.render(f5);
		// Eye2.render(f5);
		Head.render(f5);
	}

	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity ent) {
		Head.rotateAngleY = par4 / (180F / (float) Math.PI);
		Head.rotateAngleX = par5 / (180F / (float) Math.PI);
		float f = -((float) Math.PI / 4F);
		Leg1.rotateAngleZ = -f;
		Leg2.rotateAngleZ = f;
		Leg3.rotateAngleZ = -f * 0.74F;
		Leg4.rotateAngleZ = f * 0.74F;
		Leg5.rotateAngleZ = -f * 0.74F;
		Leg6.rotateAngleZ = f * 0.74F;
		Leg7.rotateAngleZ = -f;
		Leg8.rotateAngleZ = f;
		float f1 = -0F;
		float f2 = 0.3926991F;
		Leg1.rotateAngleY = f2 * 2.0F + f1;
		Leg2.rotateAngleY = -f2 * 2.0F - f1;
		Leg3.rotateAngleY = f2 * 1.0F + f1;
		Leg4.rotateAngleY = -f2 * 1.0F - f1;
		Leg5.rotateAngleY = -f2 * 1.0F + f1;
		Leg6.rotateAngleY = f2 * 1.0F - f1;
		Leg7.rotateAngleY = -f2 * 2.0F + f1;
		Leg8.rotateAngleY = f2 * 2.0F - f1;
		float f3 = -(MathHelper.cos(par1 * 0.6662F * 2.0F + 0.0F) * 0.4F) * par2;
		float f4 = -(MathHelper.cos(par1 * 0.6662F * 2.0F + (float) Math.PI) * 0.4F) * par2;
		float f5 = -(MathHelper.cos(par1 * 0.6662F * 2.0F + ((float) Math.PI / 2F)) * 0.4F) * par2;
		float f6 = -(MathHelper.cos(par1 * 0.6662F * 2.0F + ((float) Math.PI * 3F / 2F)) * 0.4F) * par2;
		float f7 = Math.abs(MathHelper.sin(par1 * 0.6662F + 0.0F) * 0.4F) * par2;
		float f8 = Math.abs(MathHelper.sin(par1 * 0.6662F + (float) Math.PI) * 0.4F) * par2;
		float f9 = Math.abs(MathHelper.sin(par1 * 0.6662F + ((float) Math.PI / 2F)) * 0.4F) * par2;
		float f10 = Math.abs(MathHelper.sin(par1 * 0.6662F + ((float) Math.PI * 3F / 2F)) * 0.4F) * par2;
		Leg1.rotateAngleY += f3;
		Leg2.rotateAngleY += -f3;
		Leg3.rotateAngleY += f4;
		Leg4.rotateAngleY += -f4;
		Leg5.rotateAngleY += f5;
		Leg6.rotateAngleY += -f5;
		Leg7.rotateAngleY += f6;
		Leg8.rotateAngleY += -f6;
		Leg1.rotateAngleZ += f7;
		Leg2.rotateAngleZ += -f7;
		Leg3.rotateAngleZ += f8;
		Leg4.rotateAngleZ += -f8;
		Leg5.rotateAngleZ += f9;
		Leg6.rotateAngleZ += -f9;
		Leg7.rotateAngleZ += f10;
		Leg8.rotateAngleZ += -f10;
		Leg1.rotateAngleX = -f;
		Leg2.rotateAngleX = -f;
		Leg7.rotateAngleX = f;
		Leg8.rotateAngleX = f;
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
