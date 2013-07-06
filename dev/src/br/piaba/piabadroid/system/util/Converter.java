package br.piaba.piabadroid.system.util;

public class Converter {

	public static int StrToInt(String strValue) {
		try{
			int value = Integer.parseInt(strValue);
			
			return value;
		}catch(NumberFormatException e){
			System.err.println("Percepção sem valor de inteiro válido. value=" + strValue + "\n");
			e.printStackTrace();
		}
		return 0;
	}

}
