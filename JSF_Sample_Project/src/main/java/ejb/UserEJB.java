package ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entity.User;

@LocalBean
@Stateless
public class UserEJB {
	@PersistenceContext
	EntityManager entityManager;
	
	public void saveUser(User user) {
		entityManager.merge(user);
	}
	
	public void deleteUser(User user) {
		entityManager.remove(user);
	}
	
	public List<User> getAll() {
		Query query = entityManager.createQuery("SELECT user from User user");
		
		return (List<User>)query.getResultList();
	}

}