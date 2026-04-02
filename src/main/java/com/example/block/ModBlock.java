package com.example.block;

import com.example.ItemOrBlock;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import net.minecraft.src.Block;
import net.minecraft.src.EntityFallingSand;
import net.minecraft.src.Material;
import net.minecraft.src.World;

import java.util.Random;

public class ModBlock extends Block implements ModifiedBlock, ItemOrBlock
{
	public ISimpleBlockRenderingHandler renderer;
	public final ModBlockBuilder props;
	public ModBlock(ModBlockBuilder struct) {

		super(ModBlockDefaults.id, struct.material);
		this.props = struct;
		System.out.println("here");
		ModBlockDefaults.id++;
		if(struct.renderer!=null)
		{
			renderer = struct.renderer;
		}
		ModBlockDefaults.init(this, struct);
	}

	@SideOnly(Side.CLIENT)
	public String getTextureFile()
	{
		return "/mods/themod/textures/blocks.png";
	}

	public int getRenderType()
	{
		if(renderer == null) return 0;
		return renderer.getRenderId();
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return renderer==null;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return renderer==null;
	}

	@Override
	public void onBlockAdded(World world, int par2, int par3, int par4) {
		if(!props.hasGravity) return;
		world.scheduleBlockUpdate(par2, par3, par4, this.blockID, this.tickRate());
	}

	@Override
	public void onNeighborBlockChange(World world, int par2, int par3, int par4, int par5) {
		if(!props.hasGravity) return;
		world.scheduleBlockUpdate(par2, par3, par4, this.blockID, this.tickRate());
	}

	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
		if (!par1World.isRemote && this.props.hasGravity) {
			this.tryToFall(par1World, par2, par3, par4);
		}

	}

	private void tryToFall(World par1World, int x, int y, int z) {
		if (canFallBelow(par1World, x, y - 1, z) && y >= 0) {
			byte var8 = 32;
			if (par1World.checkChunksExist(x - var8, y - var8, z - var8, x + var8, y + var8, z + var8)) {
				if (!par1World.isRemote) {
					EntityFallingSand var9 = new EntityFallingSand(par1World, (double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), this.blockID);
					par1World.spawnEntityInWorld(var9);
				}
			} else {
				par1World.setBlockWithNotify(x, y, z, 0);

				while(canFallBelow(par1World, x, y - 1, z) && y > 0) {
					--y;
				}

				if (y > 0) {
					par1World.setBlockWithNotify(x, y, z, this.blockID);
				}
			}
		}

	}

	public int tickRate() {
		return 3;
	}

	public static boolean canFallBelow(World par0World, int x, int y, int z) {
		int blockIdBelow = par0World.getBlockId(x, y, z);
		if (blockIdBelow == 0) {
			return true;
		} else if (blockIdBelow == Block.fire.blockID) {
			return true;
		} else {
			Material blockMaterialBelow = Block.blocksList[blockIdBelow].blockMaterial;
			if (blockMaterialBelow == Material.water) {
				return true;
			} else {
				return blockMaterialBelow == Material.lava;
			}
		}
	}
}
