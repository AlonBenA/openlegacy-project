package openlegacy.layout;

import openlegacy.logic.ItemEntity;

public class ItemTO {
	
	Integer itemNo;
	String name;
	Integer amount;
	String inventoryCode;
	 
	 
		public ItemTO()
		{
			this.itemNo = 0;
			this.name = "name";
			this.amount = 0;
			this.inventoryCode = "inventoryCode";
		}


		public ItemTO(Integer itemNo, String name, Integer amount, String inventoryCode) {
			super();
			this.itemNo = itemNo;
			this.name = name;
			this.amount = amount;
			this.inventoryCode = inventoryCode;
		}

		
		public ItemTO(ItemEntity entity)
		{
			this();
			if (entity != null) {
				this.itemNo = entity.getItemNo();
				this.name = entity.getName();
				this.amount = entity.getAmount();
				this.inventoryCode = entity.getInventoryCode();
			}
		}


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
		
		
		public ItemEntity toEntity()
		{
			ItemEntity rv = new ItemEntity();
			rv.setItemNo(this.itemNo);
			rv.setName(this.name);
			rv.setAmount(this.amount);
			rv.setInventoryCode(this.inventoryCode);
			
			return rv;
		}


		@Override
		public String toString() {
			return "ItemTO [itemNo=" + itemNo + ", name=" + name + ", amount=" + amount + ", inventoryCode="
					+ inventoryCode + "]";
		}
		
		
		
}
