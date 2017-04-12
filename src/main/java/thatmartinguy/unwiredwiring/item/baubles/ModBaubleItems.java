package thatmartinguy.unwiredwiring.item.baubles;

import baubles.api.IBauble;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBaubleItems
{
	public static Item baubleRedstoneGlasses;
	
	public static void init()
	{
		baubleRedstoneGlasses = new BaubleRedstoneGlasses("baubleRedstoneGlasses", "BaubleRedstoneGlasses");
		
		registerBaubles();
	}
	
	private static void registerBaubles()
	{
		registerBauble(baubleRedstoneGlasses);
	}
	
	private static void registerBauble(Item bauble)
	{
		GameRegistry.register(bauble);
	}
}
