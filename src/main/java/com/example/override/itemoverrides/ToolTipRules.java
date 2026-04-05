package com.example.override.itemoverrides;

import com.example.util.ChatColour;
import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

import java.util.List;
import java.util.Queue;

public class ToolTipRules
{
	public static void getTooltip(Item item, ItemStack itemStack, List entries)
	{
		if(itemStack.getMaxDamage()>0)
		{
			entries.add(ChatColour.WHITE+"Durability: "+(itemStack.getMaxDamage()-itemStack.getItemDamage())+" / "+itemStack.getMaxDamage());
		}


		if(item.getItemName().toLowerCase().contains("diamond"))
		{
			if(item.getItemName().equalsIgnoreCase("item.diamond"))
			{
				entries.add("All items made with diamonds");
				entries.add("are saved on death!");

			}
			entries.add(ChatColour.AQUA + "" + ChatColour.ITALIC + "SOULBOUND");
		}
		//the list contains the item name... maybe useful later
		if (entries.size() != 1)
		{
			entries.add(1, " ");
		}
	}
	public static void getTooltipBlock(Block block, ItemStack itemStack, List entries)
	{
		if(block.blockID == Block.oreDiamond.blockID||
			block.blockID == Block.blockDiamond.blockID ||
			block.blockID == Block.enchantmentTable.blockID)
		{
			entries.add(ChatColour.AQUA + "" + ChatColour.ITALIC + "SOULBOUND");
		}
	}
}
