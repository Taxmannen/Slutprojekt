package facades;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entites.Member;

@Stateless
@DeclareRoles({"admin", "user"})
public class MemberFacade extends AbstractFacade<Member> {
	@PersistenceContext(unitName="events")
	private EntityManager em;
	
	public MemberFacade() {
		super(Member.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	@Override
    @RolesAllowed({"admin"})
	public void remove(Member entity) {
		super.remove(entity);
	}
}

