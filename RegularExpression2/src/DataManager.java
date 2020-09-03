import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataManager implements DataReader{

	private List<Movie> movieList;
	private BufferedReader reader;
	private URL url;
	private HttpURLConnection conn;
	
	public void movieDataCrawler (String inputUrl) {
		
		movieList = new ArrayList<Movie>();
		
		String line;
		
		String iframeRegex ="<iframe id=\"ifrm_movie_time_table\"\s*src=[\"']?([^>\"']+)[\"']?[^>]*>";
		String scheduleUrl = "";
		
		String titleRegex = "(.+)</strong></a>";
		String movieInfoRegex = "<.*data-playstarttime=[\"']?([^>\"']+)[\"']?.*data-seatremaincnt=[\"']?([^>\"']+)[\"']?.*"
				+ "data-screenkorname=[\"']?([^>\"']+)[\"']?[^>]*>";
		
		String title = "";
		
		try {
			url = new URL(inputUrl);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			while((line = reader.readLine()) != null) {
				Pattern p = Pattern.compile(iframeRegex);
				Matcher m = p.matcher(line);
				
				if(m.find()) {
					scheduleUrl = inputUrl.replace("/reserve/show-times/?areacode=01&theaterCode=0056&date=20200827"
							, m.group(1));
					break;
				}
			}
			url = new URL(scheduleUrl);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			while((line = reader.readLine()) != null) {
				Pattern titleP = Pattern.compile(titleRegex);
				Matcher titleM = titleP.matcher(line);
				
				if(titleM.find()) {
					title = titleM.group(1).trim();
				}
				
				Pattern movieInfoP = Pattern.compile(movieInfoRegex);
				Matcher movieInfoM = movieInfoP.matcher(line);
				
				if(movieInfoM.find()) {
					Movie movie = new Movie(title, movieInfoM.group(3), movieInfoM.group(1), movieInfoM.group(2));
					movieList.add(movie);
				}
			}
			reader.close();
		} catch(IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void printMovieData() {
		for(Movie movie : movieList) {
			System.out.println("タイトル：" + movie.getTitle());
			System.out.println("上映館 ：" + movie.getScreen());
			System.out.println("時間：" + movie.getDate().substring(0,2) + ":" + movie.getDate().substring(2,4));
			System.out.println("座席数：" + movie.getSeat());
			System.out.println("-------------------------");
		}
	}
}
