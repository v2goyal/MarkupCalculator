

public class MarkupCalculator {
	//Declaring constants for different markup rates
	private static final double FLAT_MARKUP_RATE = 0.05; //5%
	
	private static final double PHARMACEUTICAL_MARKUP_RATE = 0.075; //7.5%
	private static final double FOOD_MARKUP_RATE = 0.13; //13%
	private static final double ELECTRONICS_MARKUP_RATE = 0.02; //2%
	private static final double DEFAULT_MARKUP_RATE = 0.0; //0%
	
	private static final double MARKUP_RATE_PER_PERSON = 0.012; //1.2%
	
	public enum Category {
		PHARMACEUTICAL, FOOD, ELECTRONICS, OTHERS
	}
	
	public static double get_category_rate(Category category){
		// Returns the markup rate based on the category of the product
		switch (category) {
			case PHARMACEUTICAL:
				return PHARMACEUTICAL_MARKUP_RATE;
			case FOOD:
				return FOOD_MARKUP_RATE;
			case ELECTRONICS:
				return ELECTRONICS_MARKUP_RATE;
			default:
				return DEFAULT_MARKUP_RATE;
		}
	}
	
	public static double get_people_markup(int numOfPeople){
		if(numOfPeople < 0){
			IllegalArgumentException e = new IllegalArgumentException();
			throw e;
		}
		
		//Returns the total markup for number of people
		return numOfPeople * MARKUP_RATE_PER_PERSON;
	}
	
	
	public static double calculateFinalPrice(double initialPrice, Category category, int numOfPeople){
		// If original price of the item is 0, than return the original price as no calculations can be performed
		if(initialPrice == 0){
			return initialPrice;
		}
		
		// If initial price or numOfPeople are negative, invalid argument/s has been passed to the function
		// Throw an exception to show the same
		if(initialPrice < 0 || numOfPeople < 0){
			IllegalArgumentException e = new IllegalArgumentException();
			throw e;
		}
		
		// Calculate the initial flat markup of 5%
		double markedUpPrice = initialPrice * (1 + FLAT_MARKUP_RATE);
		
		//Calculate the final marked up price by getting markup based on product category
		//And markup for number of people involved
		markedUpPrice = markedUpPrice * (1 + get_category_rate(category) + get_people_markup(numOfPeople));
		
		return markedUpPrice;
	}
}
