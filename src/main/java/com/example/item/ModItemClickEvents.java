package com.example.item;

import com.example.item.handler.ModItem;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class ModItemClickEvents
{
	@ForgeSubscribe
	public void onPlayerRightClick(PlayerInteractEvent event) {
		if (!(event.action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR ||
			event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK)) return;
		if (event.entityPlayer.getCurrentEquippedItem() == null) return;
		ModifiedItem m = ModItem.getItemByID.get(event.entityPlayer.getCurrentEquippedItem().itemID);
		if(!(m instanceof ModItem)) return;
		if (((ModItem)m).props.rightClick == null ) return;

		((ModItem)m).props.rightClick.logic(event.entityPlayer);


	}
}
