package thatmartinguy.unwiredwiring.event;

import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thatmartinguy.unwiredwiring.item.ItemWrench;

public class CommonEventHandler
{
	//Re- or unload the wrench if right clicked in the air
	@SubscribeEvent
	public void loadWrench(PlayerInteractEvent.RightClickItem event)
	{
		if(event.getItemStack().getItem() instanceof ItemWrench && event.getWorld().isAirBlock(event.getPos()))
		{
			ItemWrench.processLoading(event.getEntityPlayer(), event.getItemStack());
		}
	}
}
