package demo.fft;
public class DataReceptorP1 extends DataReceptorP1_LILAS
{
	double[] x;
	double[] y;
	int ctrRecu = 0;
	int ctrSérie = 1;
	boolean[] present;
	int nbrEtage;
	
	public DataReceptorP1(String nameInParentModule, lilas.base.Module parent,
			lilas.base.Signal<java.lang.Integer> fftSize_signal,
			lilas.base.Signal<java.lang.Integer> kin_signal,
			lilas.base.Signal<java.lang.Double> xin_signal,
			lilas.base.Signal<java.lang.Double> yin_signal) throws lilas.base.ExceptionLilas {
		super(nameInParentModule, parent,
			fftSize_signal,
			kin_signal,
			xin_signal,
			yin_signal);
		
		x = new double[fftSize];
		y = new double[fftSize];
		present = new boolean[fftSize];
		for (int i=0; i<present.length; i++) present[i] = false;
		nbrEtage = (int)(Math.log(fftSize) / Math.log(2));
	}

	int bitReverse(int k) {
    	int kBR= 0;
    	for (int i=0; i<nbrEtage; i++) {
    		kBR = kBR*2+k%2;
    		k= k/2;
    	}
    	return kBR;
	}
    
	private long lastReceivedDate;
	
	@Override
	final protected void activation() throws lilas.base.ExceptionLilas {
		if (present==null || kin==null) return;
		if (present[kin]) printMsg("Erreur sur série "+ctrSérie+" pour réceptor, donnée précédente non traitée : "+kin, java.awt.Color.red); 
		// calcul bitReverse de kin
    	int kinBR= bitReverse(kin);
		x[kinBR] = xin;
		y[kinBR] = yin;
		long delay = systemL.getCurrentDate()-lastReceivedDate;
		lastReceivedDate = systemL.getCurrentDate();
		printMsg("DataReceptorP1 Receive series "+ctrSérie+" : "+kin+" => "+kinBR+" at "+systemL.getCurrentDateStr()
				+" ("+lilas.util.Chronometer.toString(delay)+")", java.awt.Color.black);
		ctrRecu++;
		if (ctrRecu==fftSize) {
			// on vérifie les résultats
			printMsg("DataReceptorP1 End of series, verification.", java.awt.Color.green);      
			Complex[] xc = new Complex[fftSize];
			// original data
			for (int i = 0; i < fftSize; i++) {
				xc[i] = new Complex(i, 0);
			}
			Complex[] z = FFT.fft(xc);
			boolean test = Test.cmp(z, x, y);
			//System.out.println("Test égalité série "+ctrSérie+" : "+ test);
			printMsg("Test equality series "+ctrSérie+" : "+ test, (test?java.awt.Color.green:java.awt.Color.red));         
			ctrRecu=0;
			ctrSérie++;
			for (int i=0; i<present.length; i++) present[i] = false;
		}
		//duréeActivation = 10000;
	}
}

