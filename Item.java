
public class Item {

	private float price;
	private String name;

	
	public Item( String name,float price) {
		this.name= name;
		this.price = price;

	}

	public float getPrice() {
		return price;
	}


	public String getName() {
		return name;
	}

	@Override
	public String toString() {
	return name +","+price;
	}



}
