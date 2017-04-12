package thatmartinguy.unwiredwiring.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.world.World;
import thatmartinguy.unwiredwiring.client.HideManager;

public class TileEntityHideable extends TileEntity implements ITickable
{
	//Update whether the block is hidden
	@Override
	public void update()
	{
		if(world.isRemote)
		{
			HideManager.refresh(world, pos);
		}
	}
}
