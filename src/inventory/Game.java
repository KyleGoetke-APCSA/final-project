package inventory;

public class Game {
	private int id;
	private String title;
	private String developer;
	private int keys;
	private int available;

	public Game(int id, String title, String developer, int keys, int available) {
		super();

		this.id = id;
		this.title = title;
		this.developer = developer;
		this.keys = keys;
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

	public int getKeys() {
		return keys;
	}

	public void setKeys(int keys) {
		this.keys = keys;
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
		if (available < keys) {
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