package thatmartinguy.unwiredwiring;

import baubles.common.Baubles;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import thatmartinguy.unwiredwiring.block.ModBlocks;
import thatmartinguy.unwiredwiring.client.HideManager;
import thatmartinguy.unwiredwiring.event.CommonEventHandler;
import thatmartinguy.unwiredwiring.item.ModItems;
import thatmartinguy.unwiredwiring.item.baubles.ModBaubleItems;
import thatmartinguy.unwiredwiring.proxy.IProxy;
import thatmartinguy.unwiredwiring.util.Reference;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION, acceptedMinecraftVersions = Reference.ACCEPTED_VERSIONS)
public class UnwiredWiring
{
	public static final CreativeTabs tabUnwiredWiring = new TabUnwiredWiring("tabUnwiredWiring");
	
	public static boolean isBaublesLoaded;
	
	@Instance
	public static UnwiredWiring instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_LOCATION, serverSide = Reference.SERVER_PROXY_LOCATION)
	public static IProxy proxy;
	
	@EventHandler
	public static void preInit(FMLPreInitializationEvent event)
	{
		isBaublesLoaded = Loader.isModLoaded("baubles");
		
		ModBlocks.init();
		ModItems.init();
		
		if(isBaublesLoaded)
		{
			ModBaubleItems.init();
		}
	}
	
	@EventHandler
	public static void init(FMLInitializationEvent event)
	{
		ModCrafting.init();
		
		MinecraftForge.EVENT_BUS.register(new CommonEventHandler());
		
		proxy.init();
	}
	
	@EventHandler
	public static void postInit(FMLPostInitializationEvent event)
	{
		
	}
}
