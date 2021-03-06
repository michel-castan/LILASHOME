package demo.sqrt;
public class Sqrt1 extends Sqrt1_LILAS
{
	public Sqrt1(String nomDansModuleParent, lilas.base.Module parent,
			lilas.base.Signal<java.lang.Double> x_signal,
			lilas.base.Signal<java.lang.Double> y_signal) throws lilas.base.ExceptionLilas {
		super(nomDansModuleParent, parent,
			x_signal,
			y_signal);
		y_port.propagate = lilas.base.Port.Propagate.PERMANENT;
	}

	@Override
	final protected void activation() throws lilas.base.ExceptionLilas {
		double xnplus1=x; 
		double xn=x/2.0;
		double deuxPuissanceMoins52 = Double.longBitsToDouble(Long.parseLong("3ff0000000000001", 16))-1;

		while (Math.abs(xnplus1-xn)/xn > deuxPuissanceMoins52){
			xn=xnplus1;
			xnplus1=0.5 * (xn+x/xn);
		}
		y = xnplus1;

	}
}

