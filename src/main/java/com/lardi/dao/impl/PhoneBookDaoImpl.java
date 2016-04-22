package com.lardi.dao.impl;

import com.lardi.dao.PhoneBookFilterDao;
import com.lardi.dto.FilterCriteria;
import com.lardi.model.PhoneBook;

import com.lardi.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author vitalii.levash
 */
@Repository
public class PhoneBookDaoImpl implements PhoneBookFilterDao {
    @PersistenceContext
    private EntityManager em;
    @Override
    public List<PhoneBook> filterPhoneItems(FilterCriteria filterCriteria) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<PhoneBook> query = criteriaBuilder.createQuery(PhoneBook.class);
        Root<PhoneBook> book = query.from(PhoneBook.class);
        Join<PhoneBook,User> ujoin = book.join("user",JoinType.LEFT);
        List<javax.persistence.criteria.Predicate> predicates = new ArrayList<>();

        predicates.add(criteriaBuilder.equal(ujoin.<Integer> get("id"),Integer.valueOf(filterCriteria.getUser_id())));
        if (StringUtils.hasText(filterCriteria.getMobPhone())){
            predicates.add(criteriaBuilder.like(criteriaBuilder.upper(book.<String> get("mobPhone")), "%" + filterCriteria.getMobPhone() + "%"));

        }
        if (StringUtils.hasText(filterCriteria.getPhone())){
            predicates.add(criteriaBuilder.like(criteriaBuilder.upper(book.<String> get("phone")), "%" + filterCriteria.getPhone() + "%"));

        }
        if (StringUtils.hasText(filterCriteria.getLastname())){
            predicates.add(criteriaBuilder.like(criteriaBuilder.upper(book.<String> get("lastname")), "%" + filterCriteria.getLastname() + "%"));

        }
        if (StringUtils.hasText(filterCriteria.getName())){
            predicates.add(criteriaBuilder.like(criteriaBuilder.upper(book.<String> get("name")), "%" + filterCriteria.getName() + "%"));

        }
        if (StringUtils.hasText(filterCriteria.getSurname())){
            predicates.add(criteriaBuilder.like(criteriaBuilder.upper(book.<String> get("surname")), "%" + filterCriteria.getSurname() + "%"));

        }

        if (!predicates.isEmpty()) {
            query.where(predicates.toArray(new Predicate[predicates.size()]));
        }

        return em.createQuery(query).getResultList();

    }
}
