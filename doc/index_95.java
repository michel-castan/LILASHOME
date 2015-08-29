package demo.fft;
public class DataGeneratorP1 extends DataGeneratorP1_LILAS implements lilas.base.Coroutine
{
	int[] indice;
	boolean onlyOneRun = true;
	boolean firstRunFinished = false;
	long cycleTimeGenerator;
	long nextDateGenerator;
	
	public DataGeneratorP1(String nameInParentModule, lilas.base.Module parent,
			lilas.base.Signal<java.lang.Double> generatorFrequency_signal,
			lilas.base.Signal<java.lang.Integer> fftSize_signal,
			lilas.base.Signal<java.lang.Boolean> start_signal,
			lilas.base.Signal<java.lang.Integer> kout_signal,
			lilas.base.Signal<java.lang.Double> xout_signal,
			lilas.base.Signal<java.lang.Double> yout_signal) throws lilas.base.ExceptionLilas {
		super(nameInParentModule, parent,
			generatorFrequency_signal,
			fftSize_signal,
			start_signal,
			kout_signal,
			xout_signal,
			yout_signal);
		//kout_port.propagate = lilas.base.Port.Propagate.PERMANENT;
		//xout_port.propagate = lilas.base.Port.Propagate.PERMANENT;
		//yout_port.propagate = lilas.base.Port.Propagate.PERMANENT;
		
		// cycleTimeGenerator in picoSeconds from generator frequency
		cycleTimeGenerator = (long) (1000000000000.0 / generatorFrequency);
		indice = new int[fftSize];
		System.out.println("Taille "+fftSize);
		for (int i=0; i<fftSize; i++) indice[i] = i;
		System.out.println("init ok");
		// bit reverse sur les indices
		int j = 0; 
		for (int i=1; i < fftSize- 1; i++) {
			int n1 = fftSize/2;
			while ( j >= n1 ) {
				j = j - n1;
				n1 = n1/2;
			}
			j = j + n1;

			if (i < j) {
				int tmp = indice[i];
				indice[i] = indice[j];
				indice[j] = tmp;
			}
		}
	}

	@Override
	final protected void activation() throws lilas.base.ExceptionLilas {
		if (start==null || !start || (firstRunFinished && onlyOneRun)) return;
		nextDateGenerator = systemL.getCurrentDate()+cycleTimeGenerator;
		for (int seriesCtr=0; seriesCtr<1; seriesCtr++) {
			printMsg("Start send series "+seriesCtr+" at "+systemL.getCurrentDateStr(), java.awt.Color.magenta);
			for (int i=0; i<fftSize; i++) {
				waitDate(nextDateGenerator, true, false);
				nextDateGenerator = nextDateGenerator+cycleTimeGenerator;
				propagateForceOnce(kout_port, i);
				propagateOnce(xout_port, (double) i);
				propagateOnce(yout_port, 0.0);
				printMsg("DataGeneratorP1 Send series "+seriesCtr+" : "+i+" at "+systemL.getCurrentDateStr(), java.awt.Color.black);
				if (i==fftSize-1) printMsg("End series "+seriesCtr, java.awt.Color.magenta); 
				yieldLilas();
			}
		}
		removeSensibilityDate();
		firstRunFinished = true;
	}
}

