package org.rbo.dao;

import org.rbo.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Authority entity.
 * @author vitalii.levash
 */
public interface AuthorityDao extends JpaRepository<Authority,Long> {
}
