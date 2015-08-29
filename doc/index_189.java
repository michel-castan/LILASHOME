package demo.eq2d;
public class Solution extends Solution_LILAS
{
	public Solution(String nameInParentModule, lilas.base.Module parent,
			lilas.base.Signal<java.lang.Double> a_signal,
			lilas.base.Signal<java.lang.Double> b_signal,
			lilas.base.Signal<java.lang.Double> delta_signal,
			lilas.base.Signal<java.lang.Integer> nbrSol_signal,
			lilas.base.Signal<java.lang.Double> sol1_signal,
			lilas.base.Signal<java.lang.Double> sol2_signal) throws lilas.base.ExceptionLilas {
		super(nameInParentModule, parent,
			a_signal,
			b_signal,
			delta_signal,
			nbrSol_signal,
			sol1_signal,
			sol2_signal);
		nbrSol_port.propagate = lilas.base.Port.Propagate.PERMANENT;
		sol1_port.propagate = lilas.base.Port.Propagate.PERMANENT;
		sol2_port.propagate = lilas.base.Port.Propagate.PERMANENT;
	}

	@Override
	final protected void activation() throws lilas.base.ExceptionLilas {
		if (delta==0.0) {
			double res = -b / (2.0*a);
			nbrSol = 1;
			sol1 = res;
			sol2 = res;
		} else if (delta>0.0 && !Double.isNaN(delta)) {
			double sqrtDelta = Math.sqrt(delta);
			nbrSol = 2;
			sol1=((-b+sqrtDelta) / (2.0*a));
			sol2=((-b-sqrtDelta) / (2.0*a));
		} else {
			nbrSol = 0;
			sol1 = Double.NaN;
			sol2 = Double.NaN;
		}

	}
}

