package br.piaba.piabadroid.system;

import android.os.AsyncTask;
import br.piaba.piabadroid.system.world.GenericWorld;

/**
 * Classe que representa um passo de aplicação. Executa como uma {@link AsyncTask} para tornar a execução
 * das ações necessárias assíncrona.
 * @author pedrovitorlima
 * **/
public class SystemControllerStep extends AsyncTask<Void, Void, Void>{

	/**
	 * Instância do mundo da aplicação
	 * **/
	private GenericWorld world;
	
	/**
	 * Setter para o mundo da aplicação.
	 * 
	 * @param world Instância de classe filha de {@link GenericWorld}.
	 * **/
	public void setWorld(GenericWorld world) {
		this.world = world;
	}

	@Override
	protected Void doInBackground(Void... params) {
		for(int i=0; i<world.getWorldData().getCyclesByStep(); i++){
			world.cycle();
		}
		
		return null;
	}

}
