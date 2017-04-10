package thatmartinguy.unwiredwiring.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.world.World;
import thatmartinguy.unwiredwiring.client.HideManager;

public class TileEntityWire extends TileEntity implements ITickable
{
	int powerLevel;
	
	public TileEntityWire(World world)
	{
		this.setWorld(world);
	}
	
	public int getPowerLevel()
	{
		return powerLevel;
	}

	public void setPowerLevel(int powerLevel)
	{
		this.setPowerLevel(powerLevel, true);
	}
	
	public void setPowerLevel(int powerLevel, boolean markDirty)
	{
		if(!this.world.isRemote)
		{
			if(this.powerLevel == powerLevel) return;
			
			if(powerLevel < 0)
				this.powerLevel = 0;
			else if(powerLevel > 15)
				this.powerLevel = 15;
			else
				this.powerLevel = powerLevel;
			System.out.println(this.powerLevel);
			
			if(markDirty)
				this.markDirty();
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		compound.setInteger("powerLevel", powerLevel);
		return compound;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.setPowerLevel(compound.getInteger("powerLevel"), false);
	}
	
	//Update whether the wire is hidden
	@Override
	public void update()
	{
		if(world.isRemote)
		{
			HideManager.refresh(world, pos);
		}
	}
}
