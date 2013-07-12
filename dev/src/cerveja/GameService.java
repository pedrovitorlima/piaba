package cerveja;

import br.piaba.piabadroid.system.SystemController;
import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class GameService extends IntentService{

	public GameService(){
		super("GameService");
	}
	
	public GameService(String name) {
		super(name);
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		
		Log.i(getClass().toString(), "onStart do serviço");
		
		SystemController systemController = ((CervejaApplication) getApplication()).getSystemController();
		Thread thread = new Thread(new GameExecutorThread(systemController));
		thread.start();
		
		
	}

}
