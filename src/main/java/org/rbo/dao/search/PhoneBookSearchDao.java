package org.rbo.dao.search;

import org.rbo.model.PhoneBook;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created by leo on 15.06.16.
 */
public interface PhoneBookSearchDao extends ElasticsearchRepository<PhoneBook,Integer> {
}
