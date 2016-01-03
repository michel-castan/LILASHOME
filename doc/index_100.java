package demo.fft;

import lilas.base.Signal;

public class EtageTabP1 extends EtageTabP1_LILAS
{
	private EtageP1[] table;
	
	public EtageTabP1(String nameInParentModule, lilas.base.Module parent,
			lilas.base.Signal<java.lang.Integer> fftSize_signal,
			lilas.base.Signal<java.lang.Integer> kin_signal,
			lilas.base.Signal<java.lang.Double> xin_signal,
			lilas.base.Signal<java.lang.Double> yin_signal,
			lilas.base.Signal<java.lang.Integer> kout_signal,
			lilas.base.Signal<java.lang.Double> xout_signal,
			lilas.base.Signal<java.lang.Double> yout_signal) throws lilas.base.ExceptionLilas {
		super(nameInParentModule, parent,
			fftSize_signal,
			kin_signal,
			xin_signal,
			yin_signal,
			kout_signal,
			xout_signal,
			yout_signal);
		//kout_port.propagate = lilas.base.Port.Propagate.PERMANENT;
		//xout_port.propagate = lilas.base.Port.Propagate.PERMANENT;
		//yout_port.propagate = lilas.base.Port.Propagate.PERMANENT;
		int nbrEtage = (int)(Math.log(fftSize) / Math.log(2));
		//System.out.println("Etage tab : fftSize="+fftSize+" nbrEtage="+nbrEtage);
		table = new EtageP1[nbrEtage];
		Signal<Integer> kinS = kin_signal;
		Signal<Double> xinS = xin_signal;
		Signal<Double> yinS = yin_signal;
		for (int noEtage=0; noEtage<nbrEtage; noEtage++) {
			System.out.println("Création etage "+noEtage);
			Signal<Integer> noEtageSignal = new Signal<Integer>(this, new Integer(noEtage), false);
			Signal<Integer> koutS = (noEtage==nbrEtage-1)?kout_signal:new Signal<Integer>(this, new Integer(0), false);
			Signal<Double> xoutS = (noEtage==nbrEtage-1)?xout_signal:new Signal<Double>(this, new Double(0.0), false);
			Signal<Double> youtS = (noEtage==nbrEtage-1)?yout_signal:new Signal<Double>(this, new Double(0.0), false);
			table[noEtage] = 
				new EtageP1("etg_"+noEtage, this, noEtageSignal, fftSize_signal, 
					kinS, xinS, yinS, 
					koutS, xoutS, youtS);
			kinS = koutS;
			xinS = xoutS;
			yinS = youtS;
		}
	}

	@Override
	final protected void activation() throws lilas.base.ExceptionLilas {
		avecPostActivation = false;
	}
}

