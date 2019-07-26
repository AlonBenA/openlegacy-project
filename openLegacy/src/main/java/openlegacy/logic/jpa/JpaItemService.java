package openlegacy.logic.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import openlegacy.jpadal.GeneratedNumber;
import openlegacy.jpadal.ItemsDao;
import openlegacy.jpadal.NumbersDao;
import openlegacy.logic.ItemAlreadyExistsException;
import openlegacy.logic.ItemEntity;
import openlegacy.logic.ItemNotFoundException;
import openlegacy.logic.ItemService;

/**
 * Implementation of the InterFace ItemService with DB
 * 
 *
 */


@Service
public class JpaItemService implements ItemService {
	
	
	private ItemsDao items;
	private NumbersDao numbers;
	
	@Autowired
	public JpaItemService(ItemsDao items, NumbersDao numbers) {
		super();
		this.items = items;
		this.numbers = numbers;
	}

	@Override
	@Transactional(readOnly=true)
	public List<ItemEntity> getAllItems(int size, int page) {
		
		//return all the items in page and size(to enable pagination) Sorted by name 
		return 
		this.items.findAll(PageRequest.of(page, size, Direction.DESC, "name"))
			.getContent();
		
	}

	@Override
	@Transactional(readOnly=true)
	public ItemEntity getItem(Integer itemNo) throws ItemNotFoundException {
		
		return 
				this.items.findById(itemNo)
				.orElseThrow(()->new ItemNotFoundException("No Item for: " + itemNo));
	}

	@Override
	@Transactional
	public void reduceItemAmount(Integer itemNo, int amount) throws ItemNotFoundException {
		
		ItemEntity existing = getItem(itemNo);
		
		Integer newAmount = existing.getAmount() - amount;
		
		if(newAmount < 0 )
		{
			existing.setAmount(0);
		}
		else {
			existing.setAmount(newAmount);

		}
				
		this.items.save(existing);	
	}

	@Override
	@Transactional
	public void increaseItemAmount(Integer itemNo, int amount) throws ItemNotFoundException {

		ItemEntity existing = getItem(itemNo);
		
		if(amount > 0 ) {
			Integer newAmount = existing.getAmount() + amount;
			existing.setAmount(newAmount);
			this.items.save(existing);
		}
				


	}

	@Override
	@Transactional
	public ItemEntity addNewItem(ItemEntity itemEntity) throws ItemAlreadyExistsException {

		// find if the item exists and if not add new item and give it unique Inventory Code 
		if (!this.items.existsById(itemEntity.getItemNo())) {
			long number = this.numbers.save(new GeneratedNumber()).getNextValue();
			this.numbers.deleteById(number);
			itemEntity.setInventoryCode("" + number);
			return this.items.save(itemEntity);
		}else {
			throw new ItemAlreadyExistsException("item exisits with: " + itemEntity.getName()); 
		}
	}

	@Override
	public void deleteItem(Integer itemNo) throws ItemNotFoundException {
		if (this.items.existsById(itemNo)) {
			this.items.deleteById(itemNo);
		}else {
			throw new ItemNotFoundException("no message for name: " + itemNo);
		}
	}

	@Override
	@Transactional
	public void cleanup() {
		//to delete all the db if necessary, good for test
		this.items.deleteAll();
	}

}
