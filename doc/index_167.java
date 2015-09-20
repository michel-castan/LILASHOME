package lilas.bib.logic.clock;

import lilas.Parameter;
import lilas.base.ExceptionLilas;
import lilas.base.Module;
import lilas.base.Signal;
import lilas.type.StdLogic;

public class DCM_SP extends DCM_SP_LILAS
{
	
	public DCM_SP(String nomDansModuleParent, Module parent,
			Signal<Integer> CLKFX_MULTIPLY_signal,
			Signal<Integer> CLKFX_DIVIDE_signal,
			Signal<Float> CLKIN_PERIOD_signal, 
		    Signal<java.lang.Boolean> printDelay_signal,
		    Signal<StdLogic> clkIn_signal,
			Signal<StdLogic> clkOut_signal) throws ExceptionLilas {
		super(nomDansModuleParent, parent, CLKFX_MULTIPLY_signal, CLKFX_DIVIDE_signal,
				CLKIN_PERIOD_signal, printDelay_signal, clkIn_signal, clkOut_signal);
		//clkOut_port.propagate = lilas.base.Port.Propagate.PERMANENT;
		Parameter.simulatedTimeMode = true;
	}

	@Override
	final protected void activation() throws lilas.base.ExceptionLilas {
		long demiPeriode = (CLKIN_PERIOD.longValue()*1000*CLKFX_DIVIDE)/(2*CLKFX_MULTIPLY);// *1000 (ps) /2* (demiPeriode)
		long freqIn = (long) (1000000000000L / (2*demiPeriode));
		System.out.println(""+this.clkOut_port.signal.longVarName()+" : "+(freqIn/1000000)+" Mhz");
		//System.out.println("demiPeriode="+demiPeriode+" ps");
		//System.out.println("freqIn="+(freqIn/1000000)+" Mhz");
		long dateProchainChangement = demiPeriode;
		while (true) {
			//printMsg("Wait for "+dateProchainChangement+" à "+systemL.getCurrentDate()+" "+this, java.awt.Color.orange);
			waitDate(dateProchainChangement, true, true);
  			long retard = systemL.getCurrentDate()-dateProchainChangement;
  			if (retard!=0L) {
  				if (printDelay && lilas.Parameter.simulatedTimeMode) printMsg(""+clkOut_port.signal.varName()+"="+clkOut+" en retard de "+retard+" à "+systemL.getCurrentDate(), java.awt.Color.orange);
  				waitStable();
  				dateProchainChangement=systemL.getCurrentDate();
  			}
  			propagateOnce("clkOut", lilas.type.StdLogic.not(clkOut));
			dateProchainChangement = dateProchainChangement + demiPeriode;
			if (!lilas.Parameter.simulatedTimeMode) dateProchainChangement+=retard;
		}
	}
}

