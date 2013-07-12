package br.piaba.piabadroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import br.piaba.piabadroid.system.activity.PiabaActivity;
import cerveja.CervejaApplication;
import cerveja.GameService;

public class PiabadroidActivity extends PiabaActivity implements OnClickListener{
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout);

		CervejaApplication application = (CervejaApplication) getApplication();
		application.setSystemController(systemController);
		application.setStart(true);
		
		Intent intent = new Intent(this, GameService.class);
    	startService(intent);
    }

	@Override
	public void onClick(View v) {
		systemController.step();
	}
    
}