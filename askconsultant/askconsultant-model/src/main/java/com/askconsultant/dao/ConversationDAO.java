package com.askconsultant.dao;

import java.sql.Timestamp;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import com.askconsultant.model.Conversation;
import com.askconsultant.model.Message;

@Stateless
public class ConversationDAO {

	@PersistenceContext
	EntityManager em;

	/**
	 * Add the conversation to the database
	 * 
	 * @param conversation
	 *            The conversation object
	 * @return added conversation object
	 */
	public Conversation addConversation(Conversation conversation) {
		em.persist(conversation);
		return conversation;
	}

	/**
	 * Returns all the active conversations created by a user
	 * 
	 * @param userid
	 *            UserID of the user/employee
	 * @return List of all active conversations
	 */
	public List<Conversation> listActiveConversationsByUser(String userid) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Conversation> criteriaQuery = criteriaBuilder.createQuery(Conversation.class);
		Root<Conversation> root = criteriaQuery.from(Conversation.class);
		criteriaQuery.select(root);

		ParameterExpression<String> useridparam = criteriaBuilder.parameter(String.class);

		ParameterExpression<String> statusparam = criteriaBuilder.parameter(String.class);
		// add all the criteria
		criteriaQuery.where(criteriaBuilder.equal(root.get("status"), statusparam),
				criteriaBuilder.equal(root.get("owner"), useridparam));

		TypedQuery<Conversation> query = em.createQuery(criteriaQuery);
		query.setParameter(useridparam, userid);
		query.setParameter(statusparam, Constants.CONVERSATION_STATUS_ACTIVE);

		List<Conversation> queryResult = query.getResultList();
		return queryResult;
	}

	/**
	 * Retrieves the conversation by conversation identifier
	 * 
	 * @param id
	 *            the conversation id
	 * @return Conversation object
	 */
	public Conversation getConversationByID(long id) {
		return em.find(Conversation.class, id);
	}

	/**
	 * Archives the conversation
	 * 
	 * @param id
	 *            Id of the conversation
	 * @param userid
	 *            userid of the user archiving the conversation
	 */
	public void archiveConversation(long id, String userid) {
		Conversation conversation = em.find(Conversation.class, id);
		conversation.setStatus(Constants.CONVERSATION_STATUS_ARCHIVED);
		conversation.setArchivinguser(userid);
		em.merge(conversation);
	}

	/**
	 * Lists all Active conversations
	 * 
	 * @return
	 */
	public List<Conversation> listAllActiveConversations() {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Conversation> criteriaQuery = criteriaBuilder.createQuery(Conversation.class);
		Root<Conversation> root = criteriaQuery.from(Conversation.class);
		criteriaQuery.select(root);

		ParameterExpression<String> statusparam = criteriaBuilder.parameter(String.class);
		// add all the criteria
		criteriaQuery.where(criteriaBuilder.equal(root.get("status"), statusparam));

		TypedQuery<Conversation> query = em.createQuery(criteriaQuery);
		query.setParameter(statusparam, Constants.CONVERSATION_STATUS_ACTIVE);

		List<Conversation> queryResult = query.getResultList();
		return queryResult;
	}

	/**
	 * Lists all Active and Archived Conversations
	 * 
	 * @return
	 */
	public List<Conversation> listAllConversations() {
		@SuppressWarnings("unchecked")
		List<Conversation> resultList = em.createQuery("Select c from Conversation c").getResultList();
		return resultList;
	}

}
