package za.co.reliant.lib.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * Created by Kainda Daka on 2014/04/17.
 */
public abstract class JpaAbstractDao<E, ID extends Serializable>  implements  JpaDaoInterface<E, ID>{

    Class<E> entityClass;

    EntityManager em;

    public JpaAbstractDao(){
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        entityClass = (Class) pt.getActualTypeArguments()[0];
    }

    public JpaAbstractDao(Class<E> entityClass){
        super();
        this.entityClass = entityClass;
    }

    public EntityManager getEntityManager(){
        return em;
    }

    @Override
    public E findById(ID id){
        return em.find(entityClass, id);
    }

    @Override
    public List <E> findAll(){
        Query query = getEntityManager().createQuery(
                "select e from " + entityClass.getSimpleName() + " e");
        return (List<E>) query.getResultList();
    }

    @Override
    public List<E> findByNamedQuery(final String jpql, Object... params){
        Query query = getEntityManager().createQuery(jpql);
        int index = 1;
        for(Object param: params){
            query.setParameter(index, param);
            index++;
        }
        return (List<E>) query.getResultList();
    }

    @Override
    public List<E> findByNamedQuery(final String jpql, Map<String, ? extends Object> params){
        Query query = getEntityManager().createQuery(jpql);
        for(Map.Entry<String, ?> entry: params.entrySet()){
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return (List<E>) query.getResultList();
    }

    @Override
    public int countAll(){
        Query query = getEntityManager().createQuery(
                "select count(e) from " + entityClass.getSimpleName() + " e");
        return (Integer) query.getSingleResult();
    }

    @Override
    public E save(E entity){
        final E savedEntity = getEntityManager().merge(entity);
        return savedEntity;
    }

    @Override
    public void delete(E entity){
        getEntityManager().remove(entity);
    }

    @Override
    public void delete(ID id){
        E entity = findById(id);
        if(entity != null){
            getEntityManager().remove(entity);
        }
    }
}
