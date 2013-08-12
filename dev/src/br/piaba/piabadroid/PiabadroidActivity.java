package br.piaba.piabadroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import br.piaba.piabadroid.system.activity.PiabaActivity;
import cerveja.CervejaApplication;
import cerveja.GameService;
import cerveja.interacoes.InteracaoDeEntregarCerveja;

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
    	
    	Button interacaoEntregarCervejaRobo1 = (Button) findViewById(R.id.bt_robo_esquerda);
    	Button interacaoEntregarCervejaRobo2 = (Button) findViewById(R.id.bt_robo_direita);
    	
    	interacaoEntregarCervejaRobo1.setOnClickListener(new InteracaoDeEntregarCerveja(systemController.getWorldData().getAgent("robo_1")));
    	interacaoEntregarCervejaRobo2.setOnClickListener(new InteracaoDeEntregarCerveja(systemController.getWorldData().getAgent("robo_2")));
    	
    }

	@Override
	public void onClick(View v) {
		systemController.step();
	}
    
}