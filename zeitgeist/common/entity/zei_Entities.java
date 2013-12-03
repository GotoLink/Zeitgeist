package zeitgeist.common.entity;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.WorldType;
import zeitgeist.common.mod_Zeitgeist;
import zeitgeist.common.zei_Ids;
import cpw.mods.fml.common.registry.EntityRegistry;

public class zei_Entities {
	public static void init(mod_Zeitgeist INSTANCE) {
		EntityRegistry.registerModEntity(zei_EntityWatcher.class, "zei_Watcher", zei_Ids.entityWatcher, INSTANCE, 20, 2, true);
		EntityRegistry.registerModEntity(zei_EntityWorker.class, "zei_Worker", zei_Ids.entityWorker, INSTANCE, 20, 2, true);
		EntityRegistry.registerModEntity(zei_EntityBeacon.class, "zei_Beacon", zei_Ids.entityBeacon, INSTANCE, 20, 2, true);
		EntityRegistry.registerModEntity(zei_EntitySentry.class, "zei_Sentry", zei_Ids.entitySentry, INSTANCE, 20, 2, true);
		EntityRegistry.registerModEntity(zei_EntitySlider.class, "zei_Slider", zei_Ids.entitySlider, INSTANCE, 20, 2, true);
		EntityRegistry.registerModEntity(zei_EntityGuard.class, "zei_Guard", zei_Ids.entityGuardTurret, INSTANCE, 20, 2, true);
		EntityRegistry.registerModEntity(zei_EntityHelios.class, "zei_Helios", zei_Ids.entityHelios, INSTANCE, 20, 4, false);
		EntityRegistry.registerModEntity(zei_EntityBobby.class, "zei_Bobby", zei_Ids.entityBobby, INSTANCE, 20, 4, false);
		EntityRegistry.registerModEntity(zei_EntityGolem.class, "zei_Golem", zei_Ids.entityGolem1, INSTANCE, 20, 2, true);
		EntityRegistry.registerModEntity(zei_EntityGolem2.class, "zei_Golem2", zei_Ids.entityGolem2, INSTANCE, 20, 2, true);
		EntityRegistry.registerModEntity(zei_EntityGolemPure.class, "zei_GolemPure", zei_Ids.entityGolemPure, INSTANCE, 20, 4, true);
		EntityRegistry.registerModEntity(zei_EntityOmni.class, "zei_Omni", zei_Ids.entityOmni, INSTANCE, 20, 2, true);
		EntityRegistry.registerModEntity(zei_EntityFactotum.class, "zei_Factotum", zei_Ids.entityFactotum, INSTANCE, 20, 2, true);
		EntityRegistry.registerModEntity(zei_EntityRemnant.class, "zei_Remnant", zei_Ids.entityRemnant, INSTANCE, 20, 2, true);
		EntityRegistry.registerModEntity(zei_EntityGoreMan.class, "zei_GoreMan", zei_Ids.entityGoreMan, INSTANCE, 20, 2, true);
		EntityRegistry.registerModEntity(zei_EntityLimb.class, "zei_Limb", zei_Ids.entityLimb, INSTANCE, 20, 2, true);
		EntityRegistry.registerModEntity(zei_EntityAncient.class, "zei_Ancient", zei_Ids.entityAncient, INSTANCE, 20, 2, true);
		EntityRegistry.registerModEntity(zei_EntityMaker.class, "zei_Maker", zei_Ids.entityMaker, INSTANCE, 20, 2, true);
		EntityRegistry.registerModEntity(zei_EntityTote.class, "zei_ToterBot", zei_Ids.entityTote, INSTANCE, 20, 2, true);
		// ModLoader.registerEntityID(zei_EntityArborist.class,
		// "AM_Arborist",zei_Ids.arborist);
		// ModLoader.registerEntityID(zei_EntityHydra.class,
		// "AM_Hydra",zei_Ids.hydra);
		EntityRegistry.registerModEntity(zei_EntityNoop.class, "zei_Noop", zei_Ids.entityNoop, INSTANCE, 20, 2, true);
		EntityRegistry.registerModEntity(zei_EntityDispBot.class, "zei_DispBot", zei_Ids.entityDispBot, INSTANCE, 20, 2, true);
		EntityRegistry.addSpawn(zei_EntityWatcher.class, 12, 4, 4, EnumCreatureType.monster, WorldType.base12Biomes);
		EntityRegistry.addSpawn(zei_EntitySlider.class, 4, 4, 4, EnumCreatureType.creature, WorldType.base12Biomes);
		EntityRegistry.addSpawn(zei_EntityHelios.class, 14, 4, 4, EnumCreatureType.creature, WorldType.base12Biomes);
		EntityRegistry.addSpawn(zei_EntityBobby.class, 16, 4, 4, EnumCreatureType.creature, WorldType.base12Biomes);
		EntityRegistry.addSpawn(zei_EntityGolem.class, 5, 4, 4, EnumCreatureType.creature, WorldType.base12Biomes);
		EntityRegistry.registerModEntity(zei_EntityCannonItem.class, "zei_CannonItem", zei_Ids.entityCannonBall, INSTANCE, 20, 1, true);
	}
}