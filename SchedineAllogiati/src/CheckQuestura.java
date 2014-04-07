import java.util.ArrayList;
import java.util.List;


public class CheckQuestura {
	

	/*
	 * Variabili statiche per la modalità del check
	 */
	public static int STRICT = 0; 		//controllo integrità sottogruppi e che data arrivo sia nelle 48 ore precedenti (per invio immediato a questura)
	public static int SEMI_STRICT = 1;	//controllo integrità sottogruppi e data arrivo non differisca più di 48 h da un record all'altro
	public static int PERMISSIVE = 2;	//controllo integrità sottogruppi

	
	/*
	 * Utilità varie
	 */
	public static boolean check(List<Record> records, int mod){

		return false;

	}

	private static boolean checkSubGroups(List<Record> records){
		boolean isSingle, isFamily, isGroup;
		isSingle=isGroup=isFamily=false;

		/*
		 * RULES:
		 * - ogni sottogruppo deve cominciare con un osite singolo, un capogruppo o u capo famiglia
		 * - ogni sottogruppo ospite singolo è composto solo da un osite
		 * - ogni sottogruppo gruppo deve contenere almeno un capogruppo e un membro
		 * - ogni sottogruppo famiglia deve avere almeno un capofamiglia e un membro famiglia
		 */
		for(Record temp : records){

			//trovo di che sottogruppo si tratta
			if(temp.getTipoAlloggiato().equals(Alloggiato.CAPO_FAMIGLIA)){
				isFamily = true;
				isGroup = false;
				isSingle=false;

			}else if(temp.getTipoAlloggiato().equals(Alloggiato.CAPO_GRUPPO)){
				isGroup = true;
				isFamily=false;
				isSingle=false;

			}else if(temp.getTipoAlloggiato().equals(Alloggiato.OSPITE_SINGOLO)){
				isSingle = true;
				isFamily = false;
				isGroup = false;

			}else{
				/*
				 * Entro nel controllo dei membri gruppo/famiglia
				 */
				if(temp.getTipoAlloggiato().equals(Alloggiato.MEMBRO_GRUPPO) && isGroup==true){
					//OK
				}else if(temp.getTipoAlloggiato().equals(Alloggiato.MEMBRO_FAMIGLIA) && isFamily==true){
					//OK
				}else{
					//PROBLEM DETECTED!
					return false;

				}

			}


		}

		return true;
	}

	private static List<FormatException> checkDateInterval(List<Record> records, int mod){

		List<FormatException> dateExceptions = new ArrayList<FormatException>();

		if(mod==PERMISSIVE){
			return null;
		}else if(mod==SEMI_STRICT){
			for(Record temp : records){
				// controlli per la modalità semi-strict 
			}

		}else if(mod==STRICT){
			for(Record temp : records){
				// controlli per la modalità strict 
			}
		}else{
			// parametro errato
		}

		return null;
	}


}
