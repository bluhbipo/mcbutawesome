package com.example.item.creation;

import com.example.ItemOrBlock;
import com.example.block.ModBlockList;
import com.example.item.ModifiedItem;
import com.example.util.Map2;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.src.Item;
import net.minecraft.src.ItemArmor;

import java.util.HashMap;
import java.util.Map;

public enum Material
{
	NETHERITE(0, 25, 45, Item.blazeRod),
	ENDERITE(1, 30, 60, Item.blazeRod),
	EMERALD(2, 15, 12, Item.stick)
	;
	final int textureIndex;
	final int enchantability;
	final int durabilityFactor;
	final int armourRenderIndex;
	final Item toolStick;
	ItemArmor e;
	Material(int t, int e, int d, Item s)
	{
		textureIndex = t;
		enchantability = e;
		durabilityFactor = d;
		toolStick = s;
		armourRenderIndex = RenderingRegistry.addNewArmourRendererPrefix(this.toString().toLowerCase());
	}
	public ItemOrBlock getOre()
	{
		switch (this)
		{
			case NETHERITE: return (ItemOrBlock) ModBlockList.netheriteOre;
			default: return null;
		}
	}

	public static Map2<Material, AutogenMaterialItem, ModifiedItem> modItemMap = new Map2<>();



	public ModifiedItem getFromAutogen(AutogenMaterialItem a)
	{


		return null;
	}

}
