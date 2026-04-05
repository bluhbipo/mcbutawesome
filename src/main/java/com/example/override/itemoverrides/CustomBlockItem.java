package com.example.override.itemoverrides;

import net.minecraft.src.Block;
import net.minecraft.src.ItemBlock;
import net.minecraft.src.ItemStack;

import java.util.List;

public class CustomBlockItem extends ItemBlock
{
	Block source;
	public CustomBlockItem(Block source)
	{
		super(source.blockID-256);
		this.source = source;
	}


	@Override
	public void addInformation(ItemStack itemStack, List list)
	{
		ToolTipRules.getTooltipBlock(source, itemStack, list);
	}
}