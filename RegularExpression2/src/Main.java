
public class Main {

	public static void main(String[] args) {
		
		String inputUrl = "http://www.cgv.co.kr/reserve/show-times/?areacode=01&theaterCode=0056&date=20200827";
		DataManager dataManager = new DataManager();
		dataManager.movieDataCrawler(inputUrl);
		dataManager.printMovieData();
	}
}
