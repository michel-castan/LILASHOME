package demo.sqrt;

import java.awt.Color;

import lilas.Main;

public class Sqrt2 extends Sqrt2_LILAS implements lilas.base.Coroutine
{
	public Sqrt2(String nomDansModuleParent, lilas.base.Module parent,
			lilas.base.Signal<java.lang.Double> x_signal,
			lilas.base.Signal<java.lang.Double> y_signal) throws lilas.base.ExceptionLilas {
		super(nomDansModuleParent, parent,
			x_signal,
			y_signal);
		y_port.propagate = lilas.base.Port.Propagate.PERMANENT;
		tmp_port.propagate = lilas.base.Port.Propagate.PERMANENT;
	}

	@Override
	final protected void activation() throws lilas.base.ExceptionLilas {
		double xnplus1=x; 
		double xn=x/2.0;
		double deuxPuissanceMoins52 = Double.longBitsToDouble(Long.parseLong("3ff0000000000001", 16))-1;
		while (Math.abs(xnplus1-xn)/xn > deuxPuissanceMoins52){
			xn=xnplus1;
			xnplus1=0.5 * (xn+x/xn);
			tmp = xnplus1;
			yieldLilas();
		} 
		y = xnplus1;
	}
}

