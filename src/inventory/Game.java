package inventory;

public class Game {
	private int id;
	private String title;
	private String developer;
	private int copies;
	private int available;

	public Game(int id, String title, String developer, int copies, int available) {
		super();

		this.id = id;
		this.title = title;
		this.developer = developer;
		this.copies = copies;
		this.available = available;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDeveloper() {
		return developer;
	}

	public int getCopies() {
		return copies;
	}

	public void setCopies(int copies) {
		this.copies = copies;
	}

	public int getAvailable() {
		return available;
	}

	public void rentMe() {
		if (available > 0) {
			available--;
		}
	}

	public void returnMe() {
		if (available < copies) {
			available++;
		}
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDeveloper(String developer) {
		this.developer = developer;
	}

	public void setAvailable(int available) {
		this.available = available;
	}
}