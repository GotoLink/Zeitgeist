package zeitgeist.client;

import java.io.File;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiFurnace;
import net.minecraft.client.particle.EntityDiggingFX;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.src.ModLoader;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldProviderEnd;
import net.minecraft.world.WorldProviderHell;
import net.minecraft.world.WorldProviderSurface;
import zeitgeist.client.fx.zei_EntityFwooshFX;
import zeitgeist.client.fx.zei_EntityGoreFX;
import zeitgeist.client.gui.zei_GuiCannon;
import zeitgeist.client.gui.zei_GuiEditPicture;
import zeitgeist.client.gui.zei_GuiFactotum;
import zeitgeist.client.gui.zei_GuiTote;
import zeitgeist.client.models.zei_ModelBeacon;
import zeitgeist.client.models.zei_ModelBobby;
import zeitgeist.client.models.zei_ModelFactotum;
import zeitgeist.client.models.zei_ModelGolem;
import zeitgeist.client.models.zei_ModelGoreMan;
import zeitgeist.client.models.zei_ModelHelios;
import zeitgeist.client.models.zei_ModelLimb;
import zeitgeist.client.models.zei_ModelMusic;
import zeitgeist.client.models.zei_ModelOmni;
import zeitgeist.client.models.zei_ModelRLimb;
import zeitgeist.client.models.zei_ModelRemnant;
import zeitgeist.client.models.zei_ModelSentry;
import zeitgeist.client.models.zei_ModelSlider;
import zeitgeist.client.models.zei_ModelToter;
import zeitgeist.client.models.zei_ModelWatcher;
import zeitgeist.client.models.zei_ModelWorker;
import zeitgeist.client.render.zei_RenderAncient;
import zeitgeist.client.render.zei_RenderBlendBlock;
import zeitgeist.client.render.zei_RenderBot;
import zeitgeist.client.render.zei_RenderCannonItem;
import zeitgeist.client.render.zei_RenderChalk;
import zeitgeist.client.render.zei_RenderFactotum;
import zeitgeist.client.render.zei_RenderGolem;
import zeitgeist.client.render.zei_RenderGolemPure;
import zeitgeist.client.render.zei_RenderGoreBiped;
import zeitgeist.client.render.zei_RenderLaser;
import zeitgeist.client.render.zei_RenderLimb;
import zeitgeist.client.render.zei_RenderLiquids2;
import zeitgeist.client.render.zei_RenderNoop;
import zeitgeist.client.render.zei_RenderPlague;
import zeitgeist.client.render.zei_RenderSentryBlock;
import zeitgeist.client.render.zei_RenderStoneFigure;
import zeitgeist.client.render.zei_RenderToteBlock;
import zeitgeist.client.render.zei_RenderToter;
import zeitgeist.client.render.zei_RenderWatcher;
import zeitgeist.client.render.zei_RenderWorker;
import zeitgeist.client.render.zei_RenderWorkerBlock;
import zeitgeist.client.render.zei_TileEntityArchitectRenderer;
import zeitgeist.client.render.zei_TileEntityCannonRenderer;
import zeitgeist.client.render.zei_TileEntityTexBlockRenderer;
import zeitgeist.client.render.zei_TileEntityTexBlockRenderer2;
import zeitgeist.client.render.zei_TileEntityTurnRenderer;
import zeitgeist.client.render.zei_TileEntityWindmillRenderer;
import zeitgeist.common.CommonProxy;
import zeitgeist.common.zei_Ids;
import zeitgeist.common.block.zei_Blocks;
import zeitgeist.common.entity.zei_EntityAncient;
import zeitgeist.common.entity.zei_EntityBeacon;
import zeitgeist.common.entity.zei_EntityBobby;
import zeitgeist.common.entity.zei_EntityBurden;
import zeitgeist.common.entity.zei_EntityCannonItem;
import zeitgeist.common.entity.zei_EntityFactotum;
import zeitgeist.common.entity.zei_EntityGolem;
import zeitgeist.common.entity.zei_EntityGolemPure;
import zeitgeist.common.entity.zei_EntityGoreMan;
import zeitgeist.common.entity.zei_EntityHelios;
import zeitgeist.common.entity.zei_EntityLaser;
import zeitgeist.common.entity.zei_EntityLimb;
import zeitgeist.common.entity.zei_EntityMaker;
import zeitgeist.common.entity.zei_EntityMusicMaker;
import zeitgeist.common.entity.zei_EntityNoop;
import zeitgeist.common.entity.zei_EntityOmni;
import zeitgeist.common.entity.zei_EntityRagdoll;
import zeitgeist.common.entity.zei_EntityRemnant;
import zeitgeist.common.entity.zei_EntitySentry;
import zeitgeist.common.entity.zei_EntitySlider;
import zeitgeist.common.entity.zei_EntityTote;
import zeitgeist.common.entity.zei_EntityWatcher;
import zeitgeist.common.entity.zei_EntityWorker;
import zeitgeist.common.tile.zei_TileEntityArchitect;
import zeitgeist.common.tile.zei_TileEntityCannon;
import zeitgeist.common.tile.zei_TileEntityDuoFurnace;
import zeitgeist.common.tile.zei_TileEntityTexBlock;
import zeitgeist.common.tile.zei_TileEntityTexBlock2;
import zeitgeist.common.tile.zei_TileEntityTurn;
import zeitgeist.common.tile.zei_TileEntityWindmill;
import zeitgeist.common.world.zei_WorldProviderZeitgeist;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy implements ISimpleBlockRenderingHandler {
	public final static ResourceLocation CRYPTIC = new ResourceLocation("zeitgeist", "patterns.png");
	public static FontRenderer fontRenderer;
	public final static ResourceLocation FWOOSH = new ResourceLocation("zeitgeist", "fwoosh.png");
	public final static ResourceLocation GOLEM = new ResourceLocation("zeitgeist", "golem1.png");
	public final static ResourceLocation GORE = new ResourceLocation("zeitgeist", "gore.png");
	public static Minecraft mc;
	public static TextureManager renderengine;
	public final static ResourceLocation SENTRY = new ResourceLocation("zeitgeist", "sentryBlock.png");
	public final static ResourceLocation TOTER = new ResourceLocation("zeitgeist", "Toter.png");

	// public static int doofID;
	public ClientProxy() {
		zei_Ids.chalkRendererId = RenderingRegistry.getNextAvailableRenderId();
		zei_Ids.stoneFigureRenderId = RenderingRegistry.getNextAvailableRenderId();
		zei_Ids.runeRenderId = RenderingRegistry.getNextAvailableRenderId();
		zei_Ids.plagueRenderId = RenderingRegistry.getNextAvailableRenderId();
		zei_Ids.blenderRenderId = RenderingRegistry.getNextAvailableRenderId();
		zei_Ids.workerBlockRenderId = RenderingRegistry.getNextAvailableRenderId();
		zei_Ids.sentryBlockRenderId = RenderingRegistry.getNextAvailableRenderId();
		zei_Ids.turnBlockRenderId = RenderingRegistry.getNextAvailableRenderId();
		zei_Ids.liquidRenderId = RenderingRegistry.getNextAvailableRenderId();
		zei_Ids.toteBlockRenderId = RenderingRegistry.getNextAvailableRenderId();
		renderengine = Minecraft.getMinecraft().renderEngine;
		fontRenderer = Minecraft.getMinecraft().fontRenderer;
		// doofID =
		// Minecraft.getMinecraft().renderEngine.getTexture("/zeitgeist/doof.png");
	}

	public void addRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(zei_EntityWorker.class, new zei_RenderWorker(new zei_ModelWorker(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(zei_EntityBeacon.class, new zei_RenderBot(new zei_ModelBeacon(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(zei_EntitySentry.class, new zei_RenderBot(new zei_ModelSentry(), 0.75F));
		RenderingRegistry.registerEntityRenderingHandler(zei_EntitySlider.class, new zei_RenderBot(new zei_ModelSlider(), 1F));
		RenderingRegistry.registerEntityRenderingHandler(zei_EntityWatcher.class, new zei_RenderWatcher(new zei_ModelWatcher(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(zei_EntityHelios.class, new zei_RenderBot(new zei_ModelHelios(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(zei_EntityBobby.class, new zei_RenderBot(new zei_ModelBobby(), 0.25F));
		RenderingRegistry.registerEntityRenderingHandler(zei_EntityOmni.class, new zei_RenderBot(new zei_ModelOmni(), 0.25F));
		RenderingRegistry.registerEntityRenderingHandler(zei_EntityGolem.class, new zei_RenderGolem(new zei_ModelGolem(), 0.25F));
		RenderingRegistry.registerEntityRenderingHandler(zei_EntityGolemPure.class, new zei_RenderGolemPure());
		RenderingRegistry.registerEntityRenderingHandler(zei_EntityFactotum.class, new zei_RenderFactotum(new zei_ModelFactotum(), 1.5F));
		// map.put(zei_EntityHydra.class, new zei_RenderHydra(new
		// zei_ModelHydra(), 5F));
		RenderingRegistry.registerEntityRenderingHandler(zei_EntityLaser.class, new zei_RenderLaser());
		RenderingRegistry.registerEntityRenderingHandler(zei_EntityRemnant.class, new zei_RenderBot(new zei_ModelRemnant(), 0.25F));
		RenderingRegistry.registerEntityRenderingHandler(zei_EntityGoreMan.class, new zei_RenderGoreBiped(new zei_ModelGoreMan(), 0.25F));
		RenderingRegistry.registerEntityRenderingHandler(zei_EntityLimb.class, new zei_RenderLimb(new zei_ModelLimb(), 0.25F));
		RenderingRegistry.registerEntityRenderingHandler(zei_EntityRagdoll.class, new zei_RenderLimb(new zei_ModelRLimb(), 0.25F));
		RenderingRegistry.registerEntityRenderingHandler(zei_EntityGoreFX.class, new zei_RenderLaser());
		// map.put(zei_EntityTimeMachine.class, new zei_RenderTimeMachine(new
		// zei_ModelTimeMachine(), 0.25F));
		RenderingRegistry.registerEntityRenderingHandler(zei_EntityMusicMaker.class, new RenderBiped(new zei_ModelMusic(), 0.25F));
		RenderingRegistry.registerEntityRenderingHandler(zei_EntityNoop.class, new zei_RenderNoop(0.25F));
		RenderingRegistry.registerEntityRenderingHandler(zei_EntityMaker.class, new zei_RenderBot(new zei_ModelWorker(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(zei_EntityAncient.class, new zei_RenderAncient());
		RenderingRegistry.registerEntityRenderingHandler(zei_EntityTote.class, new zei_RenderToter(new zei_ModelToter(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(zei_EntityCannonItem.class, new zei_RenderCannonItem());
		RenderingRegistry.registerEntityRenderingHandler(zei_EntityBurden.class, new zei_RenderBot(new zei_ModelBobby(), 0.25F));
		ClientRegistry.bindTileEntitySpecialRenderer(zei_TileEntityArchitect.class, new zei_TileEntityArchitectRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(zei_TileEntityCannon.class, new zei_TileEntityCannonRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(zei_TileEntityTurn.class, new zei_TileEntityTurnRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(zei_TileEntityWindmill.class, new zei_TileEntityWindmillRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(zei_TileEntityTexBlock.class, new zei_TileEntityTexBlockRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(zei_TileEntityTexBlock2.class, new zei_TileEntityTexBlockRenderer2());
	}

	@Override
	public Icon blockArch(int i) {
		return zei_Blocks.archBlock.getIcon(2, i);
	}

	@Override
	public Icon blockSky(int i) {
		return zei_Blocks.sky.getIcon(2, i);
	}

	@Override
	public Icon blockTech(int i) {
		return zei_Blocks.tech.getIcon(2, i);
	}

	@Override
	public void bomf(World world, int posX, int posY, int posZ) {
		Random rand = world.rand;
		double sz = 10f;
		for (int i = 0; i < 200; i++) {
			float r = (float) (rand.nextFloat() * Math.PI);
			float r2 = (float) (rand.nextFloat() * Math.PI);
			float rr = MathHelper.cos(r2);
			double nx = sz * MathHelper.cos(r) * rr;
			double ny = sz * MathHelper.sin(r2);
			double nz = sz * MathHelper.sin(r) * rr;
			mc.effectRenderer.addEffect(new zei_EntityFwooshFX(world, (posX + nx), posY + ny, posZ + nz, -nx * 0.04, -ny * 0.04, -nz * 0.04, 20f, false));
		}
	}

	@Override
	public void changeDimension(EntityPlayer ep, int dim) {
		/*
		 * if(dim==-1 ||dim==0||dim==1){ mc.usePortal(dim); }else{
		 * usePortal(dim); }
		 */
		usePortal(dim);
	}

	@Override
	public void dig(World world, int x, int y, int z) {
		Random rand = world.rand;
		int ii = world.getBlockId(x, y, z);
		if (ii > 0) {
			String s = "type" + ii; // smoke
			int type = ii;
			for (int i = 0; i < 3; i++) {
				double d = (x + (double) (rand.nextFloat()));
				double d1 = y + 0.5D + (rand.nextFloat());// rand.nextGaussian()
															// * 0.02D;
				double d2 = (z + (double) (rand.nextFloat())); // rand.nextGaussian()
																// * 0.02D;
				mc.effectRenderer.addEffect(new EntityDiggingFX(world, d, d1, d2, 0.0D, 0.0D, 0.0D, Block.blocksList[type], 0, 0)); // 0=l,0=metadata
			}
		}
	}

	@Override
	public void displayGUICannon(EntityPlayer ep, zei_TileEntityCannon cannon) {
		if (!ep.worldObj.isRemote)
			ModLoader.openGUI(ep, new zei_GuiCannon(ep.inventory, cannon));
	}

	@Override
	public void duoGui(EntityPlayer ep, zei_TileEntityDuoFurnace furnace) {
		ModLoader.openGUI(ep, new GuiFurnace(ep.inventory, furnace));
	}

	@Override
	public void factotumGui(EntityPlayer entityplayer, zei_EntityFactotum F) {
		ModLoader.openGUI(entityplayer, new zei_GuiFactotum(entityplayer.inventory, F));
	}

	@Override
	public void fwoo(World world, int posX, int posY, int posZ) {
		Random rand = world.rand;
		for (int i = 0; i < 10; i++) {
			double nx = rand.nextFloat() - 0.5f;
			double ny = rand.nextFloat() - 0.5f;
			double nz = rand.nextFloat() - 0.5f;
			mc.effectRenderer.addEffect(new zei_EntityFwooshFX(world, (posX + nx), posY + ny, posZ + nz, nx * .2f, ny * 1.2f, nz * .2f, 10f, true));
		}
	}

	@Override
	public World getAWorld() {
		return mc.theWorld;
	}

	@Override
	public WorldProvider getDimension(int dim) {
		switch (dim) {
		case -1:
			return new WorldProviderHell();
		case 0:
			return new WorldProviderSurface();
		case 1:
			return new WorldProviderEnd();
		case 99:
			return new zei_WorldProviderZeitgeist();
		default:
			return null;
		}
		// ((WorldProvider)(par0 != -1 ? par0 != 0 ? par0 != 1 ? null : new
		// WorldProviderEnd() : new WorldProviderSurface() : new
		// WorldProviderHell()));
	}

	@Override
	public int getRenderId() {
		return -1;
	}

	@Override
	public void gore(World world, double posX, double posY, double posZ, double vx, double vy, double vz, int v) {
		gore(world, posX, posY, posZ, vx, vy, vz, v, 2f);
	}

	@Override
	public void gore(World world, double posX, double posY, double posZ, double vx, double vy, double vz, int v, float size) {
		Random rand = world.rand;
		for (int i = 0; i < v; i++) {
			// double d =( rand.nextGaussian() * 0.2D) -0.1d;
			// double d1 = rand.nextGaussian() *0.6d;
			// double d2 = (rand.nextGaussian() * 0.2D)-0.1d;
			double nx = (rand.nextFloat() - 0.5f) * 0.1f;
			double ny = (rand.nextFloat() - 0.5f) * 0.1f;
			double nz = (rand.nextFloat() - 0.5f) * 0.1f;
			double vxn;
			double vyn;
			if (vx != 0 && vy != 0) {
				vxn = rand.nextFloat() * vx;
				vyn = rand.nextFloat() * vy;
			} else {
				vxn = nx * 2f;
				vyn = nx * 2f;
			}
			mc.effectRenderer.addEffect(new zei_EntityGoreFX(world, (posX + nx), posY + ny, posZ + nz, vxn, vz + (ny * 1.5f), vyn, size));
			// world.spawnParticle(s, (posX + rand.nextFloat() * 1.6F - 0.8f),
			// posY + (rand.nextFloat() * 1.6f), (posZ +rand.nextFloat() * 1.6F)
			// - 0.8f, d, d1, d2);
		}
	}

	@Override
	public void gorey(World world, double posX, double posY, double posZ) {
		Random rand = world.rand;
		for (int i = 0; i < 100; i++) {
			// double d =( rand.nextGaussian() * 0.2D) -0.1d;
			// double d1 = rand.nextGaussian() *0.6d;
			// double d2 = (rand.nextGaussian() * 0.2D)-0.1d;
			double nx = rand.nextFloat() - 0.5f;
			double ny = rand.nextFloat() - 0.5f;
			double nz = rand.nextFloat() - 0.5f;
			mc.effectRenderer.addEffect(new zei_EntityGoreFX(world, (posX + nx), posY + ny, posZ + nz, nx * 1.2f, ny * 1.2f, nz * 1.2f, 2f));
			// world.spawnParticle(s, (posX + rand.nextFloat() * 1.6F - 0.8f),
			// posY + (rand.nextFloat() * 1.6f), (posZ +rand.nextFloat() * 1.6F)
			// - 0.8f, d, d1, d2);
		}
	}

	@Override
	public void init() {
		mc = Minecraft.getMinecraft();
		// mc.session.username="Aninon";
		// effectRenderer=ModLoader.getMinecraftInstance().effectRenderer;
		String path = mc.mcDataDir.getAbsolutePath() + File.pathSeparator + "resources" + File.pathSeparator + "mod" + File.pathSeparator + "sound" + File.pathSeparator + "automatons";
		File jar = new File(path);
		if (jar.exists()) {
			File files[] = jar.listFiles();
			if (files != null && files.length > 0) {
				for (int i = 0; i < files.length; i++) {
					mc.sndManager.addSound("automatons/" + files[i].getName());
				}
			}
		}
		// mc.sndManager.addSound(par1Str, par2File)
	}

	@Override
	public void poof(World world, double posX, double posY, double posZ) {
		String s = "explode";
		Random rand = world.rand;
		for (int i = 0; i < 7; i++) {
			double d = rand.nextGaussian() * 0.02D;
			double d1 = rand.nextGaussian() * 0.02D;
			double d2 = rand.nextGaussian() * 0.02D;
			world.spawnParticle(s, (posX + rand.nextFloat() * 1.6F - 0.8f), posY + 0.5f + (rand.nextFloat() * 0.2f), (posZ + rand.nextFloat() * 1.6F) - 0.8f, d, d1, d2);
		}
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		if (block.blockID == zei_Ids.plague) {
			renderer.renderBlockAsItem(Block.blocksList[83], metadata, modelID);
		} else {
			renderer.renderBlockAsItem(Block.blocksList[1], metadata, modelID);
		}
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		boolean bool = false;
		if (modelId == zei_Ids.chalkRendererId) {
			Tessellator.instance.draw();
			Tessellator.instance.startDrawingQuads();
			renderengine.bindTexture(CRYPTIC);
			bool = zei_RenderChalk.chalkPatterns(block, x, y, z, world);
			Tessellator.instance.draw();
			Tessellator.instance.startDrawingQuads();
			renderengine.bindTexture(TextureMap.locationBlocksTexture);
		} else if (modelId == zei_Ids.liquidRenderId) {
			// renderengine.bindTexture(renderengine.getTexture("/zeitgeist/stoneFigure.png"));
			bool = zei_RenderLiquids2.render(renderer, world, block, x, y, z);
		} else if (modelId == zei_Ids.stoneFigureRenderId) {
			bool = zei_RenderStoneFigure.render(renderer, world, block, x, y, z);
		} else if (modelId == zei_Ids.workerBlockRenderId) {
			Tessellator.instance.draw();
			Tessellator.instance.startDrawingQuads();
			renderengine.bindTexture(GOLEM);
			bool = zei_RenderWorkerBlock.render(renderer, world, block, x, y, z);
			Tessellator.instance.draw();
			Tessellator.instance.startDrawingQuads();
			renderengine.bindTexture(TextureMap.locationBlocksTexture);
		} else if (modelId == zei_Ids.toteBlockRenderId) {
			Tessellator.instance.draw();
			Tessellator.instance.startDrawingQuads();
			renderengine.bindTexture(TOTER);
			bool = zei_RenderToteBlock.render(renderer, world, block, x, y, z);
			Tessellator.instance.draw();
			Tessellator.instance.startDrawingQuads();
			renderengine.bindTexture(TextureMap.locationBlocksTexture);
		} else if (modelId == zei_Ids.sentryBlockRenderId) {
			Tessellator.instance.draw();
			Tessellator.instance.startDrawingQuads();
			renderengine.bindTexture(SENTRY);
			bool = zei_RenderSentryBlock.render(renderer, world, block, x, y, z);
			Tessellator.instance.draw();
			Tessellator.instance.startDrawingQuads();
			renderengine.bindTexture(TextureMap.locationBlocksTexture);
		} else if (modelId == zei_Ids.blenderRenderId) {
			bool = zei_RenderBlendBlock.render(renderer, world, block, x, y, z);
		} else if (modelId == zei_Ids.plagueRenderId) {
			Tessellator.instance.draw();
			Tessellator.instance.startDrawingQuads();
			renderengine.bindTexture(CRYPTIC);
			bool = zei_RenderPlague.render(renderer, world, block, x, y, z);
			Tessellator.instance.draw();
			Tessellator.instance.startDrawingQuads();
			renderengine.bindTexture(TextureMap.locationBlocksTexture);
		}
		return bool;
	}

	@Override
	public boolean shouldRender3DInInventory() {
		return false;
	}

	public void toteGui(EntityPlayer entityplayer, IInventory ent) {
		ModLoader.openGUI(entityplayer, new zei_GuiTote(entityplayer.inventory, ent));
	}

	public void usePortal(int dimy) {
		// EntityPlayerSP thePlayer = mc.thePlayer;
		// World theWorld = mc.theWorld;
		int i = mc.thePlayer.dimension;
		mc.thePlayer.dimension = dimy;
		mc.theWorld.removeEntity(mc.thePlayer);
		mc.thePlayer.isDead = false;
		double d = mc.thePlayer.posX;
		double d1 = mc.thePlayer.posZ;
		double d2 = 1.0D;
		/*
		 * if (i > -1 && mc.thePlayer.dimension == -1) { d2 = 0.125D; } else if
		 * (i == -1 && mc.thePlayer.dimension > -1) { d2 = 8D; }
		 */
		// d *= d2;
		// d1 *= d2;
		/*
		 * if (thePlayer.dimension == -1) { thePlayer.setLocationAndAngles(d,
		 * thePlayer.posY, d1, thePlayer.rotationYaw, thePlayer.rotationPitch);
		 * if (thePlayer.isEntityAlive()) {
		 * theWorld.updateEntityWithOptionalForce(thePlayer, false); } World
		 * world = null; world = new World(theWorld,
		 * WorldProvider.getProviderForDimension(thePlayer.dimension));
		 * mc.changeWorld(world, "Entering the Nether", thePlayer); } else
		 */
		if (mc.thePlayer.dimension == 0) {
			if (mc.thePlayer.isEntityAlive()) {
				mc.thePlayer.setLocationAndAngles(d, mc.thePlayer.posY, d1, mc.thePlayer.rotationYaw, mc.thePlayer.rotationPitch);
				mc.theWorld.updateEntityWithOptionalForce(mc.thePlayer, false);
			}
			World world1 = null;
			world1 = new World(mc.theWorld, getDimension(mc.thePlayer.dimension));
			if (i == -1) {
				mc.changeWorld(world1, "Leaving the Nether", mc.thePlayer);
			} else if (i == 99) {
				mc.changeWorld(world1, "Leaving Eupraxia", mc.thePlayer);
			} else {
				mc.changeWorld(world1, "Leaving the End", mc.thePlayer);
			}
		} else {
			World world2 = new World(mc.theWorld, getDimension(mc.thePlayer.dimension));
			// ChunkCoordinates chunkcoordinates =
			// world2.getEntrancePortalLocation();
			// d = chunkcoordinates.posX;
			// thePlayer.posY = chunkcoordinates.posY;
			// d1 = chunkcoordinates.posZ;
			mc.thePlayer.setLocationAndAngles(d, mc.thePlayer.posY, d1, mc.thePlayer.rotationYaw, mc.thePlayer.rotationPitch);// 90F,
																																// 0.0F);
			if (mc.thePlayer.isEntityAlive()) {
				world2.updateEntityWithOptionalForce(mc.thePlayer, false);
			}
			String message = "???";
			// if(i==99){
			message = "Entering Eupraxia";
			// }
			mc.changeWorld(world2, message, mc.thePlayer);
		}
		mc.thePlayer.worldObj = mc.theWorld;
		System.out.println("Teleported to " + mc.theWorld.provider.worldType);
		if (mc.thePlayer.isEntityAlive() && i != 1) {
			mc.thePlayer.setLocationAndAngles(d, mc.thePlayer.posY, d1, mc.thePlayer.rotationYaw, mc.thePlayer.rotationPitch);
			mc.theWorld.updateEntityWithOptionalForce(mc.thePlayer, false);
			(new Teleporter()).placeInPortal(mc.theWorld, mc.thePlayer);
		}
	}

	public static boolean angrywolf(EntityWolf ew) {
		return ew.isAngry();
	}

	public static double anotherAxisFunc(AxisAlignedBB aa) {
		return aa.getAverageEdgeLength() * 4D;
	}

	public static void makeParticle(EntityFX e) {
		mc.effectRenderer.addEffect(e);
	}

	public static void pictureGui(EntityPlayer ep, zei_PictureData data, int dam, int imgID) {
		// if(!ep.worldObj.isRemote)
		ModLoader.openGUI(ep, new zei_GuiEditPicture(ep.username, data, dam, imgID));
	}/*
	 * public static void pictureGui(EntityPlayer ep,zei_PictureData data,int
	 * dam,int imgID){ //EntityPlayerMP mp = (EntityPlayerMP) ep
	 * ep.getNextWidowId(); playerNetServerHandler.sendPacket(new
	 * Packet100OpenWindow(currentWindowId, 0, par1IInventory.getInvName(),
	 * par1IInventory.getSizeInventory())); craftingInventory = new
	 * ContainerChest(inventory, par1IInventory); craftingInventory.windowId =
	 * currentWindowId; craftingInventory.onCraftGuiOpened(this); }
	 */
}
