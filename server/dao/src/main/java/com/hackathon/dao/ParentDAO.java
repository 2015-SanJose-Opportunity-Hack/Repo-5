package com.hackathon.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

public class ParentDAO<K, E> extends GenericDAO<K, E> 
{
	private static final Logger log = Logger.getLogger(ParentDAO.class);
	
	@PersistenceContext(unitName = "rs.persist")
	protected EntityManager em;
	

	@Override
	protected EntityManager getEntityManager() 
	{
		return em;
	}
	
	
	@Override
	protected void setEntityManager(EntityManager em) 
	{
		log.info("***EntityManager set");
		this.em = em;
	}
}
