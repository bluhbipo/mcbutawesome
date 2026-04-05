package com.example.override.itemoverrides;

import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

import java.util.List;

public class ToolTipRules
{
	public static void getTooltip(Item item, ItemStack itemStack, List entries)
	{
		if(item.getItemName().toLowerCase().contains("diamond"))
		{
			if(item.getItemName().equalsIgnoreCase("item.diamond"))
			{
				entries.add("All items made with diamonds");
				entries.add("are saved on death!");

			}
			entries.add("§b§oSOULBOUND");
		}
	}
	public static void getTooltipBlock(Block block, ItemStack itemStack, List entries)
	{
		if(block.blockID == Block.oreDiamond.blockID||
			block.blockID == Block.blockDiamond.blockID ||
			block.blockID == Block.enchantmentTable.blockID)
		{
			entries.add("§b§oSOULBOUND");
		}
	}
}
