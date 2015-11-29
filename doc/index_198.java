package demo.eq2d;
public class Discriminant extends Discriminant_LILAS
{
	public Discriminant(String nameInParentModule, lilas.base.Module parent,
			lilas.base.Signal<java.lang.Double> va_signal,
			lilas.base.Signal<java.lang.Double> vb_signal,
			lilas.base.Signal<java.lang.Double> vc_signal,
			lilas.base.Signal<java.lang.Double> vdelta_signal) throws lilas.base.ExceptionLilas {
		super(nameInParentModule, parent,
			va_signal,
			vb_signal,
			vc_signal,
			vdelta_signal);
		vdelta_port.propagate = lilas.base.Port.Propagate.PERMANENT;
	}

	@Override
	final protected void activation() throws lilas.base.ExceptionLilas {
		vdelta = vb*vb-4.0*va*vc;
	}
}

