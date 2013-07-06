package br.piaba.piabadroid.system.activity;

import android.app.Activity;
import android.os.Bundle;
import br.piaba.piabadroid.system.SystemController;

public class PiabaActivity extends Activity{
	protected SystemController systemController;
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	       
	        systemController = new SystemController(this);
	    	systemController.startApplication();
	    }
	
}
