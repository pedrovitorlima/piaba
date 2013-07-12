package cerveja;

import android.app.Application;
import br.piaba.piabadroid.system.SystemController;

public class CervejaApplication extends Application {

	private SystemController systemController;
	private boolean start;

	public SystemController getSystemController() {
		return systemController;
	}

	public void setSystemController(SystemController systemController) {
		this.systemController = systemController;
	}
	
	public void setStart(boolean start){
		this.start = start;
	}
	
}
