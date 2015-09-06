package demo.hello;
public class Hello2 extends Hello2_LILAS
{
	public Hello2(String nomDansModuleParent, lilas.base.Module parent,
			lilas.base.Signal<java.lang.String> sender_signal) throws lilas.base.ExceptionLilas {
		super(nomDansModuleParent, parent,
			sender_signal);
		msg_port.propagate=lilas.base.Port.Propagate.PERMANENT;
	}

	@Override
	final protected void activation() throws lilas.base.ExceptionLilas {
		msg = "Hello"+((sender==null)?"":" from "+sender);
	}
}

