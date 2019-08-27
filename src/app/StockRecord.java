package app;

public class StockRecord {
    long timestamp;
    double price;

    public StockRecord(long time, double price) {
        this.timestamp = time;
        this.price = price;
    }

	public static StockRecord generateRandom(int timestamp, double maxPrice) {
		return new StockRecord(timestamp, Math.random() * maxPrice);
    }
    
    @Override
    public String toString() {
        return new String("{" + this.timestamp + String.format(", $%.2f", this.price) + "}");
    }
}