package openlegacy.jpadal;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import openlegacy.logic.ItemEntity;

@RepositoryRestResource
public interface ItemsDao extends PagingAndSortingRepository<ItemEntity, Integer>{

}
