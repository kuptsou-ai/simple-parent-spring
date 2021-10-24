package org.sonatype.mavenbook.weather.persist;

import org.hibernate.Query;
import org.hibernate.Session;
import org.sonatype.mavenbook.weather.model.Location;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.ArrayList;
import java.util.List;

public class LocationDAO extends HibernateDaoSupport {

    public LocationDAO() {
    }

    public Location findByCity(final String city) {
        return (Location) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) {
                Query query = getSession().getNamedQuery("Location.uniqueByCity");
                query.setString("city", city);
                return query.uniqueResult();
            }
        });
    }

    @SuppressWarnings("unchecked")
    public List<Location> all() {
        return new ArrayList<Location>(getHibernateTemplate().loadAll(Location.class));
    }

}