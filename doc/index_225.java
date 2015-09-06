package demo.hello;
public class Hello1 extends Hello1_LILAS
{
	public Hello1(String nomDansModuleParent, lilas.base.Module parent,
			lilas.base.Signal<java.lang.String> sender_signal) throws lilas.base.ExceptionLilas {
		super(nomDansModuleParent, parent,
			sender_signal);
	}

	@Override
	final protected void activation() throws lilas.base.ExceptionLilas {
		System.out.println("Hello world from "+sender+" !");
	}
}

