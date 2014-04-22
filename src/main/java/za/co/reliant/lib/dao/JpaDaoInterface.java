package za.co.reliant.lib.dao;

import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Kainda Daka on 2014/04/17.
 */
public interface JpaDaoInterface<E, ID extends Serializable> {
    /**
     * Returns the class of the Entity
     * @return the class of the Entity
     */
    public Class<E> getEntityClass();

    /**
     *
     * @param id The primary key of the Entity
     * @return
     */
    public E findById(ID id);

    /**
     * Returns the list of all the entities in the database
     * @return a List of all the entities in the database;
     */
    public List <E> findAll();

    /**
     * Returns the list of all the entities in the database meeting the named query criteria
     * @param jpql The value of the JPQL Named Query
     * @param params The parameters to pass to the named query
     * @return the entities matching the query and the parameters
     */
    public List<E> findByNamedQuery(final String jpql, Object... params);

    /**
     * Returns the list of all the entities in the database meeting the named query criteria
     * @param jpql The value of the Named Query
     * @param params The parameters to pass to the named query
     * @return the entities matching the query and the parameters
     */
    public List<E> findByNamedQuery(final String jpql, Map<String, ? extends Object> params);

    /**
     * Counts all the entities in the database
     * @return count of entities
     */
    public int countAll();

    /**
     * Save an enity to the database. This can be SAVE or an UPDATE
     * @param entity The entity to be saved
     * @return The saved entity.
     */
    public E save(E entity);

    /**
     * Deletes an entity from the database
     * @param entity The entity to be deleted
     * @return 1 or 0 to indicate status. 1 = deleted and 0 = failed to delete
     */
    public void delete(E entity);

    /**
     * Deletes an entity from the database by primary key
     * @param id primary key of the entity to be deleted
     * @return 1 or 0 to indicate status. 1 = deleted and 0 = failed to delete
     */
    public void delete(ID id);

}
