package openlegacy.logic;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ITEMS")
public class ItemEntity {
	
	/*
	 *  A class that represents and creates the table ITEMS in the DB
	 */
	
	private Integer itemNo;
	private String name;
	private Integer amount;
	private String inventoryCode;
	
	
	public ItemEntity()
	{
		this.itemNo = 0;
		this.name = "name";
		this.amount = 0;
		this.inventoryCode = "inventoryCode";
	}


	public ItemEntity(Integer itemNo, String name, Integer amount, String inventoryCode) {
		super();
		this.itemNo = itemNo;
		this.name = name;
		this.amount = amount;
		this.inventoryCode = inventoryCode;
	}

	@Id
	public Integer getItemNo() {
		return itemNo;
	}


	public void setItemNo(Integer itemNo) {
		this.itemNo = itemNo;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getAmount() {
		return amount;
	}


	public void setAmount(Integer amount) {
		this.amount = amount;
	}


	public String getInventoryCode() {
		return inventoryCode;
	}


	public void setInventoryCode(String inventoryCode) {
		this.inventoryCode = inventoryCode;
	}
	
	
	
	
	

}
