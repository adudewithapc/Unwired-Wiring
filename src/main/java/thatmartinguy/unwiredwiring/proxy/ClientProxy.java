package thatmartinguy.unwiredwiring.proxy;

import net.minecraftforge.common.MinecraftForge;
import thatmartinguy.unwiredwiring.client.HideManager;
import thatmartinguy.unwiredwiring.client.ModModelManager;

public class ClientProxy extends CommonProxy
{
	public void preInit()
	{
		ModModelManager.registerAllModels();
	}
	
	public void init()
	{
		MinecraftForge.EVENT_BUS.register(new HideManager());
	}
	
	public void postInit()
	{
		
	}
}
