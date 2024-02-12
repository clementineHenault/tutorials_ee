package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SessionScoped // As long as the user's session is valid
@ManagedBean
public class SessionNumberBean {
	private int number = 1;
	
	public int getNumber() {
		return number;
	}
	
	public void increase() {
		number++;
	}
}