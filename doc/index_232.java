package demo.hello;
public class Hello extends Hello_LILAS
{
	public Hello(String nomDansModuleParent, lilas.base.Module parent) throws lilas.base.ExceptionLilas {
		super(nomDansModuleParent, parent);
	}

	@Override
	final protected void activation() throws lilas.base.ExceptionLilas {
		System.out.println("Hello world !");
	}
}

