package com.odn.entities;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentityGenerator;
import org.hibernate.HibernateException;

import javax.ws.rs.ext.Provider;
import java.io.Serializable;

@Provider
public class UseIdOrGenerate extends IdentityGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        Serializable id = session.getEntityPersister(null, object).getClassMetadata().getIdentifier(object, session);
        return id != null ? id : super.generate(session, object);
        /*if (object == null) throw new HibernateException(new NullPointerException()) ;

        if ((((InboundRequestEntity) object).getId()) == null) {
            Serializable id = super.generate(session, object) ;
            return id;
        } else {
            return ((InboundRequestEntity) object).getId();
        }*/
    }
}