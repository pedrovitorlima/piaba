package cerveja;

import android.util.Log;
import br.piaba.piabadroid.system.SystemController;

public class GameExecutorThread implements Runnable {

	private SystemController systemController;
	
	public GameExecutorThread(SystemController systemController){
		this.systemController = systemController;
	}
	
	@Override
	public void run() {
		while(true){
			systemController.step();
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				Log.e(this.getClass().toString(), "Houve um erro na thread do serviço que executa o SystemController. => " + e.getMessage());
			}
		}
	}

}
