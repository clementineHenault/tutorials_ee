package bean;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ApplicationScoped // Until it gets redeployed
@ManagedBean
public class ApplicationNumberBean {
	private int number = 1;
	
	public int getNumber() {
		return number;
	}
	
	public void increase() {
		number++;
	}
}