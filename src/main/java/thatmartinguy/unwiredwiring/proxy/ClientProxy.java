package thatmartinguy.unwiredwiring.proxy;

import net.minecraftforge.common.MinecraftForge;
import thatmartinguy.unwiredwiring.client.HideManager;

public class ClientProxy extends CommonProxy
{
	public void preInit()
	{
		
	}
	
	public void init()
	{
		MinecraftForge.EVENT_BUS.register(new HideManager());
	}
	
	public void postInit()
	{
		
	}
}
