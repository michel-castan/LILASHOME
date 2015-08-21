package lilas.bib.awt;

import lilas.util.Chronometer;

public class BoutonAvance extends BoutonAvance_LILAS
{
 
	public BoutonAvance(String nomDansModuleParent, lilas.base.Module parent,
			lilas.base.Signal<java.lang.String> delaiInit_signal) throws lilas.base.ExceptionLilas {
		super(nomDansModuleParent, parent,
			delaiInit_signal);

		delai_port.signal.setInitialValue(delaiInit);

	}
  
	boolean isRunning = false;  
	public static String[] attente_paramsNames = { };
	
	public void attente() throws lilas.base.ExceptionLilas {
		long delaiVal= delai.isEmpty()?0L:Chronometer.toDélai(delai);
  		if (!isRunning) {
  			isRunning = true;
  			waitDélai(delaiVal);
  		}
		isRunning = false;
	}  


}
