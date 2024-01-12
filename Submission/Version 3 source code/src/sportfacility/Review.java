package sportfacility;

public class Review {

    private String comment;
    private int rate; // Assuming this is a rating on a certain scale, e.g., 1-5

    public Review(String comment, int rate) {
        this.comment = comment;
        this.setRate(rate); // Set rate using the method to enforce any rating constraints
    }

    public void setRate(int rate) {
        if (rate < 1 || rate > 5) {
            System.out.println("Rating must be between 1 and 5.");
            return;
        } else {
            this.rate = rate;
        }

    }

    public String getComment() {
        return comment;
    }

    public int getRate() {
        return rate;
    }

    @Override
    public String toString() {
        return "Review{" + "comment='" + getComment() + '\'' + ", rate=" + getRate() + '}';
    }
}
