package br.piaba.piabadroid;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import br.piaba.piabadroid.system.activity.PiabaActivity;

public class PiabadroidActivity extends PiabaActivity implements OnClickListener{
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);
        
        Button button = (Button)findViewById(R.id.btLigar);
        button.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		systemController.step();
	}
    
}