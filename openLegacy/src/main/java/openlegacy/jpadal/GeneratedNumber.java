package openlegacy.jpadal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class GeneratedNumber {
	private Long nextValue;
	
	/*
	 * a class to produce a number that increases each time to it call 
	 * to enter as new inventoryCode
	 */
	public GeneratedNumber() {
	}

	@Id
	@GeneratedValue
	public Long getNextValue() {
		return nextValue;
	}

	public void setNextValue(Long nextValue) {
		this.nextValue = nextValue;
	}
	
	
}
