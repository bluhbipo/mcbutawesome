package com.example.override.biomeoverrides.nether;

import net.minecraft.src.Block;
import net.minecraft.src.MathHelper;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

import java.util.Random;

public class WorldGenMinableNether extends WorldGenerator
{
	private int minableBlockId;
	private int minableBlockMeta;
	private int numberOfBlocks;

	public WorldGenMinableNether(int par1, int par2) {
		this.minableBlockMeta = 0;
		this.minableBlockId = par1;
		this.numberOfBlocks = par2;
	}

	public WorldGenMinableNether(int id, int meta, int number) {
		this(id, number);
		this.minableBlockMeta = meta;
	}

	public boolean generate(World world, Random random, int blockX, int blockY, int blockZ) {
		float angle = random.nextFloat() * (float)Math.PI;

		double xEnd = (float)(blockX + 8) + MathHelper.sin(angle) * (float)this.numberOfBlocks / 8.0F;
		double xStart = (float)(blockX + 8) - MathHelper.sin(angle) * (float)this.numberOfBlocks / 8.0F;
		double zEnd = (float)(blockZ + 8) + MathHelper.cos(angle) * (float)this.numberOfBlocks / 8.0F;
		double zStart = (float)(blockZ + 8) - MathHelper.cos(angle) * (float)this.numberOfBlocks / 8.0F;

		double yEnd = blockY + random.nextInt(3) - 2;
		double yStart = blockY + random.nextInt(3) - 2;

		for (int i = 0; i <= this.numberOfBlocks; ++i) {
			double currentX = xEnd + (xStart - xEnd) * (double)i / (double)this.numberOfBlocks;
			double currentY = yEnd + (yStart - yEnd) * (double)i / (double)this.numberOfBlocks;
			double currentZ = zEnd + (zStart - zEnd) * (double)i / (double)this.numberOfBlocks;

			double radiusFactor = random.nextDouble() * (double)this.numberOfBlocks / 16.0;
			double radiusX = (MathHelper.sin((float)i * (float)Math.PI / (float)this.numberOfBlocks) + 1.0F) * radiusFactor + 1.0;
			double radiusY = (MathHelper.sin((float)i * (float)Math.PI / (float)this.numberOfBlocks) + 1.0F) * radiusFactor + 1.0;

			int minX = MathHelper.floor_double(currentX - radiusX / 2.0);
			int minY = MathHelper.floor_double(currentY - radiusY / 2.0);
			int minZ = MathHelper.floor_double(currentZ - radiusX / 2.0);
			int maxX = MathHelper.floor_double(currentX + radiusX / 2.0);
			int maxY = MathHelper.floor_double(currentY + radiusY / 2.0);
			int maxZ = MathHelper.floor_double(currentZ + radiusX / 2.0);

			for (int x = minX; x <= maxX; ++x) {
				double xDist = (x + 0.5 - currentX) / (radiusX / 2.0);
				if (xDist * xDist > 1.0) continue;
				for (int y = minY; y <= maxY; ++y) {
					double yDist = (y + 0.5 - currentY) / (radiusY / 2.0);
					if (xDist * xDist + yDist * yDist > 1.0) continue;
					for (int z = minZ; z <= maxZ; ++z) {
						double zDist = (z + 0.5 - currentZ) / (radiusX / 2.0); // x and z radius are same
						Block currentBlock = Block.blocksList[world.getBlockId(x, y, z)];
						if (xDist * xDist + yDist * yDist + zDist * zDist < 1.0
							&& currentBlock != null
							&& currentBlock.blockID == Block.netherrack.blockID) {
							world.setBlockAndMetadata(x, y, z, this.minableBlockId, this.minableBlockMeta);
						}
					}

				}

			}
		}

		return true;
	}
}
