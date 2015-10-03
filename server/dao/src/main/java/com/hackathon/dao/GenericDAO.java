package com.paypal.psc.rs.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.LockTimeoutException;
import javax.persistence.OptimisticLockException;
import javax.persistence.PessimisticLockException;
import javax.persistence.Query;

import org.apache.log4j.Logger;


public abstract class GenericDAO<K, E> 
{
	protected Class<E> entityClass;
	protected abstract EntityManager getEntityManager();
	protected abstract void setEntityManager(EntityManager em);
	private final static Logger log = Logger.getLogger(GenericDAO.class);

	@SuppressWarnings("unchecked")
	public GenericDAO()
	{
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.entityClass = (Class<E>) genericSuperclass.getActualTypeArguments()[1];
	}
	
	
	
	public final E findById(K id) 
	{
		
		if (id != null) 
		{
			return getEntityManager().find(entityClass, id);
		} else 
		{
			return null;
		}
	}
	
	
	public final E findByIdWithLock(K id, LockModeType lockMode) 
	{
		if (id != null) 
		{
			try
			{
				return getEntityManager().find(entityClass, id, lockMode);
			}
			catch(OptimisticLockException e)
			{
				log.warn("Could not get optimistic lock " + e.getMessage());
			}
			catch(PessimisticLockException e)
			{
				log.warn("Could not get pessimistic lock " + e.getMessage());
			}
			catch(LockTimeoutException e)
			{
				log.warn("Lock timeout " + e.getMessage());
			}
		} 
		return null;
		
	}
		
	
	
	public final E persist(E entity) 
	{
		//getEntityManager().persist(entity);
		if (!getEntityManager().contains(entity))
		{
			getEntityManager().persist(entity);	
			return entity;
		} else 
		{
			E merged = getEntityManager().merge(entity);
			getEntityManager().flush();
			return merged;
		}
	}
	
	
		
	public final void remove(E entity) 
	{
		getEntityManager().remove(entity);
	}
	
	
	
	@SuppressWarnings("unchecked")
	public final List<E> getList() 
	{		
		Query query = getEntityManager().createQuery("SELECT o FROM " + this.entityClass.getSimpleName() + " o");
		
		return query.getResultList();
	}
}
