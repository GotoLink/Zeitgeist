package zeitgeist.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class zei_ModelBi extends ModelBase {
	public ModelRenderer body;
	// fields
	public ModelRenderer head;
	public ModelRenderer leftleg;
	public ModelRenderer rightleg;
	ModelRenderer eye1;
	ModelRenderer eye2;
	ModelRenderer leftleg2;
	ModelRenderer rightleg2;

	public zei_ModelBi() {
		textureWidth = 64;
		textureHeight = 32;
		head = new ModelRenderer(this, 0, 0);
		head.addBox(-3F, -3F, -6F, 6, 8, 6);
		head.setRotationPoint(0F, 2F, -4F);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0F, 0F, 0F);
		body = new ModelRenderer(this, 16, 16);
		body.addBox(-3F, 0F, -2F, 6, 10, 4);
		body.setRotationPoint(0F, 0F, -3F);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.4461433F, 0F, 0F);
		rightleg = new ModelRenderer(this, 0, 16);
		rightleg.addBox(-1.5F, 0F, -1.5F, 3, 10, 3);
		rightleg.setRotationPoint(-2F, 9F, 0F);
		rightleg.setTextureSize(64, 32);
		rightleg.mirror = true;
		setRotation(rightleg, -0.7853982F, 0F, 0F);
		leftleg = new ModelRenderer(this, 0, 16);
		leftleg.addBox(-1.5F, 0F, -1.5F, 3, 10, 3);
		leftleg.setRotationPoint(2F, 9F, 0F);
		leftleg.setTextureSize(64, 32);
		leftleg.mirror = true;
		setRotation(leftleg, -0.7853982F, 0F, 0F);
		rightleg2 = new ModelRenderer(this, 36, 16);
		rightleg2.addBox(-1F, 0F, -1F, 2, 10, 2);
		rightleg2.setRotationPoint(0F, 8F, -1F);
		rightleg2.setTextureSize(64, 32);
		rightleg2.mirror = true;
		setRotation(rightleg2, 1.202105F, 0F, 0F);
		leftleg2 = new ModelRenderer(this, 36, 16);
		leftleg2.addBox(-1F, 0F, -1F, 2, 10, 2);
		leftleg2.setRotationPoint(0F, 8F, -1F);
		leftleg2.setTextureSize(64, 32);
		leftleg2.mirror = true;
		setRotation(leftleg2, 1.202105F, 0F, 0F);
		leftleg.addChild(leftleg2);
		rightleg.addChild(rightleg2);
		eye1 = new ModelRenderer(this, 0, 0);
		eye1.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		eye1.setRotationPoint(2F, 2F, -6F);
		eye1.setTextureSize(64, 32);
		eye1.mirror = true;
		eye2 = new ModelRenderer(this, 0, 0);
		eye2.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		eye2.setRotationPoint(-2F, 3F, -6F);
		eye2.setTextureSize(64, 32);
		eye2.mirror = true;
		head.addChild(eye1);
		head.addChild(eye2);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		head.render(f5);
		body.render(f5);
		rightleg.render(f5);
		leftleg.render(f5);
		// rightleg2.render(f5);
		// leftleg2.render(f5);
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity ent) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, ent);
		this.rightleg.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
		this.leftleg.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1;
		((ModelRenderer) rightleg.childModels.get(0)).rotateAngleX = rightleg.rotateAngleX + 1.2f;
		((ModelRenderer) leftleg.childModels.get(0)).rotateAngleX = leftleg.rotateAngleX + 1.2f;
		this.rightleg.rotateAngleX -= 0.6f;
		this.leftleg.rotateAngleX -= 0.6f;
		this.rightleg.rotateAngleY = 0.3F;
		this.leftleg.rotateAngleY = -0.3F;
		this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
