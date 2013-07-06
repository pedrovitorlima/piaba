package br.piaba.piabadroid.system;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import android.app.Activity;
import br.piaba.piabadroid.system.agent.GenericAgent;
import br.piaba.piabadroid.system.agent.InitAgents;
import br.piaba.piabadroid.system.world.GenericWorld;
import br.piaba.piabadroid.system.world.InitWorld;
import br.piaba.piabadroid.system.world.action.InitMios;
import br.piaba.piabadroid.system.world.action.impl.MioAction;
import cerveja.CervejaWorld;

/**
 * Classe responsável por controlar as execuções de passos ({@link #step()} do sistema.
 * @author pedrovitorlima
 * **/
public class SystemController{
	private GenericWorld world; 
	private Activity activity;	
	
	/**
	 * Construtor da classe. Recebe uma {@link Activity} como parâmetro.
	 * Ela é utilizada para ler os xml de modelagem da aplicação através dos Assets,
	 * além de passá-la para o mundo com intuito de permitir acesso aos elementos gráficos da interface gráfica
	 * para atualização após cada ciclo.
	 * 
	 * @param activity Activity Android
	 * **/
	public SystemController(Activity activity){
		this.activity = activity;
	}
	
	/**
	 * Inicializa aplicação. Carrega as configurações via xml e instancia os objetos necessários para
	 * execução da aplicação. 
	 * **/
	public void startApplication() {
		
		InputStream agentsStream = null;
        InputStream worldStream = null;
        InputStream mioStream = null;
        try {
        	agentsStream = activity.getAssets().open("MASAgents.xml");
        	worldStream = activity.getAssets().open("MASWorld.xml");
        	mioStream = activity.getAssets().open("MASMios.xml");
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
        
        List<GenericAgent> agents = InitAgents.readAgents(agentsStream);
        List<MioAction> mios = InitMios.readMios(mioStream);
        
        world = InitWorld.readWorld(worldStream);
        world.getWorldData().setAgents(agents); 
        world.getWorldData().getExecutor().setMios(mios);
        
        
        ((CervejaWorld) world).setAndroidLayout(activity);
		
		world.initWorld();
	}
	
	/**
	 * Realiza um passo de aplicação, representado pela execução de um {@link SystemControllerStep}.
	 * Utiliza a abordagem de AssyncTask para tornar essa execução assíncrona.
	 * **/
	public void step(){
		SystemControllerStep scs = new SystemControllerStep();
		scs.setWorld(world);
		
		scs.execute();
	}
}
