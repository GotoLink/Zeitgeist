package zeitgeist.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.PositionTextureVertex;
import net.minecraft.client.model.TexturedQuad;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.Tessellator;

import org.lwjgl.opengl.GL11;

public class zei_FlatRenderer {
	public zei_FlatRenderer(ModelBase modelbase, int i, int j) {
		textureWidth = 64F;
		textureHeight = 32F;
		compiled = false;
		displayList = 0;
		mirror = false;
		showModel = true;
		isHidden = false;
		textureOffsetX = i;
		textureOffsetY = j;
		modelbase.boxList.add(this);
	}

	public void addBox(float f, float f1, float f2, int i, int j, int k) {
		addBox(f, f1, f2, i, j, k, 0.0F);
	}

	public void addBox(float f, float f1, float f2, int i, int j, int k,
			float f3) {
		field_35977_i = f;
		field_35975_j = f1;
		field_35976_k = f2;
		field_35973_l = f + (float) i;
		field_35974_m = f1 + (float) j;
		field_35972_n = f2 + (float) k;
		// field_35978_r = new PositionTextureVertex[8];
		// faces = new TexturedQuad();
		float f4 = f + (float) i;
		float f5 = f1 + (float) j;
		float f6 = f2 + (float) k;
		f -= f3;
		f1 -= f3;
		f2 -= f3;
		f4 += f3;
		f5 += f3;
		f6 += f3;
		if (mirror) {
			float f7 = f4;
			f4 = f;
			f = f7;
		}
		PositionTextureVertex positiontexturevertex = new PositionTextureVertex(
				f, f1, f2, 0.0F, 0.0F);
		PositionTextureVertex positiontexturevertex1 = new PositionTextureVertex(
				f4, f1, f2, 0.0F, 8F);
		PositionTextureVertex positiontexturevertex2 = new PositionTextureVertex(
				f4, f5, f2, 8F, 8F);
		PositionTextureVertex positiontexturevertex3 = new PositionTextureVertex(
				f, f5, f2, 8F, 0.0F);
		/*
		 * PositionTextureVertex positiontexturevertex4 = new
		 * PositionTextureVertex(f, f1, f6, 0.0F, 0.0F); //
		 * PositionTextureVertex positiontexturevertex5 = new
		 * PositionTextureVertex(f4, f1, f6, 0.0F, 8F); PositionTextureVertex
		 * positiontexturevertex6 = new PositionTextureVertex(f4, f5, f6, 8F,
		 * 8F); PositionTextureVertex positiontexturevertex7 = new
		 * PositionTextureVertex(f, f5, f6, 8F, 0.0F);//
		 */
		/*
		 * field_35978_r[0] = positiontexturevertex; field_35978_r[1] =
		 * positiontexturevertex1; field_35978_r[2] = positiontexturevertex2;
		 * field_35978_r[3] = positiontexturevertex3; field_35978_r[4] =
		 * positiontexturevertex4; field_35978_r[5] = positiontexturevertex5;
		 * field_35978_r[6] = positiontexturevertex6; field_35978_r[7] =
		 * positiontexturevertex7;
		 */
		faces = new TexturedQuad(
				new PositionTextureVertex[] { positiontexturevertex,
						positiontexturevertex1, positiontexturevertex2,
						positiontexturevertex3 // 4 7 -> 1 2
				}, textureOffsetX + k + i, textureOffsetY + k, textureOffsetX
						+ k + i + k, textureOffsetY + k + j, textureWidth,
				textureHeight);
		if (mirror) {
			faces.flipFace();
		}
	}

	public void setRotationPoint(float f, float f1, float f2) {
		rotationPointX = f;
		rotationPointY = f1;
		rotationPointZ = f2;
	}

	public void render(float f) {
		if (isHidden) {
			return;
		}
		if (!showModel) {
			return;
		}
		if (!compiled) {
			compileDisplayList(f);
		}
		if (rotateAngleX != 0.0F || rotateAngleY != 0.0F
				|| rotateAngleZ != 0.0F) {
			GL11.glPushMatrix();
			GL11.glTranslatef(rotationPointX * f, rotationPointY * f,
					rotationPointZ * f);
			if (rotateAngleZ != 0.0F) {
				GL11.glRotatef(rotateAngleZ * (180F / (float) Math.PI), 0.0F,
						0.0F, 1.0F);
			}
			if (rotateAngleY != 0.0F) {
				GL11.glRotatef(rotateAngleY * (180F / (float) Math.PI), 0.0F,
						1.0F, 0.0F);
			}
			if (rotateAngleX != 0.0F) {
				GL11.glRotatef(rotateAngleX * (180F / (float) Math.PI), 1.0F,
						0.0F, 0.0F);
			}
			GL11.glCallList(displayList);
			GL11.glPopMatrix();
		} else if (rotationPointX != 0.0F || rotationPointY != 0.0F
				|| rotationPointZ != 0.0F) {
			GL11.glTranslatef(rotationPointX * f, rotationPointY * f,
					rotationPointZ * f);
			GL11.glCallList(displayList);
			GL11.glTranslatef(-rotationPointX * f, -rotationPointY * f,
					-rotationPointZ * f);
		} else {
			GL11.glCallList(displayList);
		}
	}

	public void renderWithRotation(float f) {
		if (isHidden) {
			return;
		}
		if (!showModel) {
			return;
		}
		if (!compiled) {
			compileDisplayList(f);
		}
		GL11.glPushMatrix();
		GL11.glTranslatef(rotationPointX * f, rotationPointY * f,
				rotationPointZ * f);
		if (rotateAngleY != 0.0F) {
			GL11.glRotatef(rotateAngleY * (180F / (float) Math.PI), 0.0F, 1.0F,
					0.0F);
		}
		if (rotateAngleX != 0.0F) {
			GL11.glRotatef(rotateAngleX * (180F / (float) Math.PI), 1.0F, 0.0F,
					0.0F);
		}
		if (rotateAngleZ != 0.0F) {
			GL11.glRotatef(rotateAngleZ * (180F / (float) Math.PI), 0.0F, 0.0F,
					1.0F);
		}
		GL11.glCallList(displayList);
		GL11.glPopMatrix();
	}

	public void postRender(float f) {
		if (isHidden) {
			return;
		}
		if (!showModel) {
			return;
		}
		if (!compiled) {
			compileDisplayList(f);
		}
		if (rotateAngleX != 0.0F || rotateAngleY != 0.0F
				|| rotateAngleZ != 0.0F) {
			GL11.glTranslatef(rotationPointX * f, rotationPointY * f,
					rotationPointZ * f);
			if (rotateAngleZ != 0.0F) {
				GL11.glRotatef(rotateAngleZ * (180F / (float) Math.PI), 0.0F,
						0.0F, 1.0F);
			}
			if (rotateAngleY != 0.0F) {
				GL11.glRotatef(rotateAngleY * (180F / (float) Math.PI), 0.0F,
						1.0F, 0.0F);
			}
			if (rotateAngleX != 0.0F) {
				GL11.glRotatef(rotateAngleX * (180F / (float) Math.PI), 1.0F,
						0.0F, 0.0F);
			}
		} else if (rotationPointX != 0.0F || rotationPointY != 0.0F
				|| rotationPointZ != 0.0F) {
			GL11.glTranslatef(rotationPointX * f, rotationPointY * f,
					rotationPointZ * f);
		}
	}

	private void compileDisplayList(float f) {
		displayList = GLAllocation.generateDisplayLists(1);
		GL11.glNewList(displayList, GL11.GL_COMPILE);
		Tessellator tessellator = Tessellator.instance;
		faces.draw(tessellator, f);
		GL11.glEndList();
		compiled = true;
	}

	public zei_FlatRenderer setTextureSize(int i, int j) {
		textureWidth = i;
		textureHeight = j;
		return this;
	}

	public void setToModel(zei_FlatRenderer modelrenderer) {
		rotationPointX = modelrenderer.rotationPointX;
		rotationPointY = modelrenderer.rotationPointY;
		rotationPointZ = modelrenderer.rotationPointZ;
		rotateAngleX = modelrenderer.rotateAngleX;
		rotateAngleY = modelrenderer.rotateAngleY;
		rotateAngleZ = modelrenderer.rotateAngleZ;
	}

	public float textureWidth;
	public float textureHeight;
	// private PositionTextureVertex field_35978_r[];
	private TexturedQuad faces;
	private int textureOffsetX;
	private int textureOffsetY;
	public float rotationPointX;
	public float rotationPointY;
	public float rotationPointZ;
	public float rotateAngleX;
	public float rotateAngleY;
	public float rotateAngleZ;
	public float field_35977_i;
	public float field_35975_j;
	public float field_35976_k;
	public float field_35973_l;
	public float field_35974_m;
	public float field_35972_n;
	private boolean compiled;
	private int displayList;
	public boolean mirror;
	public boolean showModel;
	public boolean isHidden;
}
