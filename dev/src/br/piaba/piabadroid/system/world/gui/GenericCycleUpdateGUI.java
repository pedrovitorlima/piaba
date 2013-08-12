package br.piaba.piabadroid.system.world.gui;

import android.app.Activity;
import android.view.View;
import br.piaba.piabadroid.system.world.model.WorldData;

public abstract class GenericCycleUpdateGUI implements Runnable{

	private Activity activity;
	private WorldData worldData;

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}	
	
	public WorldData getWorldData() {
		return worldData;
	}

	public void setWorldData(WorldData worldData) {
		this.worldData = worldData;
	}

	public View findViewById(int id){
		return activity.findViewById(id);
	}
}
