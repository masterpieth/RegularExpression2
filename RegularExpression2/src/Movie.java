public class Movie {

	private String title;
	private String screen;
	private String date;
	private String seat;
	
	public Movie(String title, String screen, String date, String seat) {
		super();
		this.title = title;
		this.screen = screen;
		this.date = date;
		this.seat = seat;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getScreen() {
		return screen;
	}
	public void setScreen(String screen) {
		this.screen = screen;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getSeat() {
		return seat;
	}
	public void setSeat(String seat) {
		this.seat = seat;
	}

	@Override
	public String toString() {
		return "Movie [title=" + title + ", screen=" + screen + ", date=" + date + ", seat=" + seat + "]";
	}
	
}
