package facades;

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
	protected EntityManager getEntityManager() {
		return em;
	}
}
