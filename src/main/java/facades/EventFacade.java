package facades;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entites.Event;

@Stateless
public class EventFacade extends AbstractFacade<Event> {
	@PersistenceContext(unitName="events")
	private EntityManager em;
	
	public EventFacade() {
		super(Event.class);
	}
	
	@Override
    @RolesAllowed({"admin"})
	public void remove(Event entity) {
		super.remove(entity);
	}
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
}
