package openlegacy.layout;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import openlegacy.logic.ItemAlreadyExistsException;
import openlegacy.logic.ItemEntity;
import openlegacy.logic.ItemNotFoundException;
import openlegacy.logic.ItemService;

@RestController
public class ItemWebUI {
	private ItemService items;
	
	
	@Autowired
	public void setMessages(ItemService items) {
		this.items = items;
	}
	
	
	@RequestMapping(
			method=RequestMethod.GET,
			path="/items",
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ItemTO[] getAllItems (
			@RequestParam(name="size", required=false, defaultValue="10") int size, 
			@RequestParam(name="page", required=false, defaultValue="0") int page) {
		return 
		this.items.getAllItems(size, page) // list of entities
			.stream() // stream of entities
			.map(ItemTO::new) // stream of boundaries
			.collect(Collectors.toList()) // list of boundaries
			.toArray(new ItemTO[0]); // boundaries array
	}
	
	@RequestMapping(
			method=RequestMethod.GET,
			path="/items/{ItemNo}",
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ItemTO getCustomMessage (@PathVariable("ItemNo") Integer ItemNo) throws ItemNotFoundException {
		ItemEntity rv = this.items.getItem(ItemNo);

		
		return new ItemTO(rv);
	}
	
	
	@RequestMapping(
			method=RequestMethod.PUT,
			path="/increaseItemAmount/{ItemNo}/{amount}",
			consumes=MediaType.APPLICATION_JSON_VALUE)
	public void increaseItemAmount (
			@PathVariable("ItemNo") Integer ItemNo,
			@PathVariable("amount") Integer amount) throws Exception {
		this.items.increaseItemAmount(ItemNo, amount);
	}
	
	@RequestMapping(
			method=RequestMethod.PUT,
			path="/reduceItemAmount/{ItemNo}/{amount}",
			consumes=MediaType.APPLICATION_JSON_VALUE)
	public void reduceItemAmount (
			@PathVariable("ItemNo") Integer ItemNo,
			@PathVariable("amount") Integer amount) throws Exception {
		this.items.reduceItemAmount(ItemNo, amount);
	}
	
	
	@RequestMapping(
			method=RequestMethod.POST,
			path="/items",
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE)
	public ItemTO createNewItem (@RequestBody ItemTO newItem) throws ItemAlreadyExistsException {
		return new ItemTO(
				this.items.addNewItem(newItem.toEntity()));
	}
	
	
	@RequestMapping(
			method=RequestMethod.DELETE,
			path="/items/{ItemNo}")
	public void deleteItem (@PathVariable("ItemNo") Integer ItemNo) throws Exception {
		this.items.deleteItem(ItemNo);
	}
	
	
	
	@ExceptionHandler//(ItemNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorMessage handleException (ItemNotFoundException e) {
		String message = e.getMessage();
		if (message == null) {
			message = "There is no relevant Item";
		}
		return new ErrorMessage(message);
	}
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorMessage handleException (ItemAlreadyExistsException e) {
		String message = e.getMessage();
		if (message == null) {
			message = "Item Already Exists";
		}
		return new ErrorMessage(message);
	}

}
