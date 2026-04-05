package com.example.override;

import com.example.override.itemoverrides.CustomBlockItem;
import com.example.override.itemoverrides.CustomItem;
import com.example.override.itemoverrides.CustomItemArmor;
import net.minecraft.src.Block;
import net.minecraft.src.EnumArmorMaterial;
import net.minecraft.src.Item;
import net.minecraft.src.ItemArmor;

import java.util.Collections;
import java.util.List;

public class ItemOverride
{
	public static void inject()
	{
		override(Block.oreDiamond);
		override(Block.blockDiamond);
		override(Block.enchantmentTable);

		for(Item i : itemsNeedingCustomBehaviour)
		{
			giveItemCustomClass(i);
		}

	}

	private static final Item[] itemsNeedingCustomBehaviour = new Item[]
		{
			Item.diamond,

			Item.helmetLeather,
			Item.plateLeather,
			Item.legsLeather,
			Item.bootsLeather,

			Item.helmetChain,
			Item.plateChain,
			Item.legsChain,
			Item.bootsChain,


			Item.helmetSteel,
			Item.plateSteel,
			Item.legsSteel,
			Item.bootsSteel,

			Item.helmetGold,
			Item.plateGold,
			Item.legsGold,
			Item.bootsGold,

			Item.helmetDiamond,
			Item.plateDiamond,
			Item.legsDiamond,
			Item.bootsDiamond,

			Item.pickaxeWood,
			Item.axeWood,
			Item.shovelWood,
			Item.hoeWood,
			Item.swordWood,

			Item.pickaxeStone,
			Item.axeStone,
			Item.shovelStone,
			Item.hoeStone,
			Item.swordStone,

			Item.pickaxeSteel,
			Item.axeSteel,
			Item.shovelSteel,
			Item.hoeSteel,
			Item.swordSteel,

			Item.pickaxeDiamond,
			Item.axeDiamond,
			Item.shovelDiamond,
			Item.hoeDiamond,
			Item.swordDiamond,

			Item.pickaxeGold,
			Item.axeGold,
			Item.shovelGold,
			Item.hoeGold,
			Item.swordGold
		};


	private static void override(Block source)
	{
		Item.itemsList[source.blockID] = null;
		new CustomBlockItem(source);
	}

	private static void giveItemCustomClass(Item source)
	{
		Item.itemsList[source.shiftedIndex] = null;
		if(source instanceof ItemArmor)
		{
			EnumArmorMaterial material = null;
			if(source.getItemName().contains("Cloth")) material = EnumArmorMaterial.CLOTH;
			if(source.getItemName().contains("Chain")) material = EnumArmorMaterial.CHAIN;
			if(source.getItemName().contains("Iron")) material = EnumArmorMaterial.IRON;
			if(source.getItemName().contains("Gold")) material = EnumArmorMaterial.GOLD;
			if(source.getItemName().contains("Diamond")) material = EnumArmorMaterial.DIAMOND;

			CustomItemArmor result = new CustomItemArmor((ItemArmor) source, material);
			Item.itemsList[source.shiftedIndex] = result;
		}else{
			CustomItem result = new CustomItem(source);
			Item.itemsList[source.shiftedIndex] = result;
		}

	}
}
