package test.lilas.awt;

import javax.swing.JComponent;

public class ComponentAWT extends ComponentAWT_LILAS
{
	public ComponentAWT(String nameInParentModule, lilas.base.Module parent) throws lilas.base.ExceptionLilas {
		super(nameInParentModule, parent);
	}

	@Override
	final protected void activation() throws lilas.base.ExceptionLilas {

	}

	@Override
	public JComponent myComponent() {
		JComponent comp = new javax.swing.JSlider(javax.swing.JSlider.HORIZONTAL, 0, 100, 50);;
		return comp;
	}
}

