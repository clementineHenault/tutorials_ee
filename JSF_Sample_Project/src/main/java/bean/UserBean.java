package bean;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import ejb.UserEJB;
import entity.User;

@SuppressWarnings("deprecation")
@ApplicationScoped
@ManagedBean
public class UserBean {
	@Inject
	UserEJB userEJB;
	
	public List<User> getAllUsers() {
		return userEJB.getAll();
	}
}