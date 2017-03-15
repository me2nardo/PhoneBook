package org.rbo.dao;

import org.rbo.model.PersistentToken;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the PersistentToken entity.
 * @author vitalii.levash
 */
public interface PersistentTokenDao extends JpaRepository<PersistentToken,String>{
}
