package zeitgeist.common.item;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import org.lwjgl.input.Mouse;

public class zei_ItemClimbClaw extends Item {
	protected zei_ItemClimbClaw(int i) {
		super(i);
	}

	public void onUpdate(ItemStack itemstack, World world, Entity entity,
			int i, boolean flag) {
		int x = MathHelper
				.floor_double(ModLoader.getMinecraftInstance().thePlayer.posX);
		int y = MathHelper
				.floor_double(ModLoader.getMinecraftInstance().thePlayer.posY);
		int z = MathHelper
				.floor_double(ModLoader.getMinecraftInstance().thePlayer.posZ);
		if (Mouse.isButtonDown(1)
				&& ModLoader.getMinecraftInstance().thePlayer.motionY < 0
				&& (world.getBlockId(x + 1, y, z) == Block.wood.blockID
						|| world.getBlockId(x - 1, y, z) == Block.wood.blockID
						|| world.getBlockId(x, y, z + 1) == Block.wood.blockID
						|| world.getBlockId(x, y, z - 1) == Block.wood.blockID
						|| world.getBlockId(x + 1, y, z + 1) == Block.wood.blockID
						|| world.getBlockId(x - 1, y, z + 1) == Block.wood.blockID
						|| world.getBlockId(x + 1, y, z - 1) == Block.wood.blockID
						|| world.getBlockId(x - 1, y, z - 1) == Block.wood.blockID
						|| world.getBlockId(x, y + 1, z) == Block.wood.blockID
						|| world.getBlockId(x + 1, y + 1, z) == Block.wood.blockID
						|| world.getBlockId(x - 1, y + 1, z) == Block.wood.blockID
						|| world.getBlockId(x, y + 1, z + 1) == Block.wood.blockID
						|| world.getBlockId(x, y + 1, z - 1) == Block.wood.blockID
						|| world.getBlockId(x + 1, y + 1, z + 1) == Block.wood.blockID
						|| world.getBlockId(x - 1, y + 1, z + 1) == Block.wood.blockID
						|| world.getBlockId(x + 1, y + 1, z - 1) == Block.wood.blockID
						|| world.getBlockId(x, y + 2, z) == Block.wood.blockID
						|| world.getBlockId(x - 1, y, z) == Block.leaves.blockID
						|| world.getBlockId(x, y, z + 1) == Block.leaves.blockID
						|| world.getBlockId(x, y, z - 1) == Block.leaves.blockID
						|| world.getBlockId(x + 1, y, z + 1) == Block.leaves.blockID
						|| world.getBlockId(x - 1, y, z + 1) == Block.leaves.blockID
						|| world.getBlockId(x + 1, y, z - 1) == Block.leaves.blockID
						|| world.getBlockId(x - 1, y, z - 1) == Block.leaves.blockID
						|| world.getBlockId(x, y + 1, z) == Block.leaves.blockID
						|| world.getBlockId(x + 1, y + 1, z) == Block.leaves.blockID
						|| world.getBlockId(x - 1, y + 1, z) == Block.leaves.blockID
						|| world.getBlockId(x, y + 1, z + 1) == Block.leaves.blockID
						|| world.getBlockId(x, y + 1, z - 1) == Block.leaves.blockID
						|| world.getBlockId(x + 1, y + 1, z + 1) == Block.leaves.blockID
						|| world.getBlockId(x - 1, y + 1, z + 1) == Block.leaves.blockID
						|| world.getBlockId(x + 1, y + 1, z - 1) == Block.leaves.blockID
						|| world.getBlockId(x, y + 2, z) == Block.leaves.blockID
						|| world.getBlockId(x - 1, y - 1, z) == Block.leaves.blockID
						|| world.getBlockId(x, y - 1, z + 1) == Block.leaves.blockID
						|| world.getBlockId(x, y, z - 1) == Block.leaves.blockID
						|| world.getBlockId(x + 1, y - 1, z + 1) == Block.leaves.blockID
						|| world.getBlockId(x - 1, y - 1, z + 1) == Block.leaves.blockID
						|| world.getBlockId(x + 1, y - 1, z - 1) == Block.leaves.blockID
						|| world.getBlockId(x - 1, y - 1, z - 1) == Block.leaves.blockID
						|| world.getBlockId(x, y + 1, z) == Block.leaves.blockID
						|| world.getBlockId(x + 1, y - 1, z) == Block.wood.blockID
						|| world.getBlockId(x - 1, y - 1, z) == Block.wood.blockID
						|| world.getBlockId(x, y - 1, z + 1) == Block.wood.blockID
						|| world.getBlockId(x, y - 1, z - 1) == Block.wood.blockID
						|| world.getBlockId(x + 1, y - 1, z + 1) == Block.wood.blockID
						|| world.getBlockId(x - 1, y - 1, z + 1) == Block.wood.blockID || world
						.getBlockId(x + 1, y - 1, z - 1) == Block.wood.blockID)) {
			ModLoader.getMinecraftInstance().thePlayer.motionY = 0;
			ModLoader.getMinecraftInstance().thePlayer.fallDistance = 0;
			ModLoader.getMinecraftInstance().thePlayer.onGround = true;
		}
	}
}
