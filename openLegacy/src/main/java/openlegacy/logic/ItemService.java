package openlegacy.logic;

import java.util.List;

public interface ItemService {
	/**
	 * interface with all functions required to handle item web UI 
	 * 
	 * 
	 */
	
	public List<ItemEntity> getAllItems(int size, int page);
	
	public ItemEntity getItem(Integer itemNo) throws ItemNotFoundException;
	
	public void reduceItemAmount(Integer itemNo,int amount) throws ItemNotFoundException;
	
	public void increaseItemAmount(Integer itemNo,int amount) throws ItemNotFoundException;
	
	public ItemEntity addNewItem(ItemEntity itemEntity) throws ItemAlreadyExistsException;
	
	public void deleteItem(Integer itemNo) throws ItemNotFoundException;
	
	public void cleanup();
	

}
