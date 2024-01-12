package user;
public class StandardMember implements Member {

	public double calculate(int loyaltyPoints, int bookingCount) {
		return (loyaltyPoints*0.1);
	}

	@Override
	public String toString() {
		return "Standard Member";
	}
	
}