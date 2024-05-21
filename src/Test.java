import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Test {
	public void admindb() throws Exception {
		String result = "";
		URL url = new URL(
				"https://api.odcloud.kr/api/15076560/v1/uddi:6273ff85-ea47-44ee-be2a-8de203e3eefd?page=1&perPage=10000&serviceKey=b2b4U%2FpF%2BKEef70xTdMhYhhu%2Bl6PEeveevNHZNjgsnRLjNdSRJy7SKMx1f%2FyE%2BRJ4nhrXKu1nTOp6sv7%2B3zf1Q%3D%3D&_type=json");
		BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
		result = bf.readLine();

		JSONParser parser = new JSONParser();
		JSONObject jsonObj = (JSONObject) parser.parse(result);

		JSONArray data = (JSONArray) jsonObj.get("data"); // 배열로 obj를 가져오기..

		// for문으로 결과뽑고 null의 결과가 있을때
		// try문으로 nullpointException 을 잡아달라는 걸 for 문 위에 올리면 null값은 null로 나온다..
		try {
			for (int i = 0; i < data.size(); i++) {

				JSONObject jobj = (JSONObject) data.get(i);
				String name = (String) jobj.get("국가");
				String countryid = (String) jobj.get("국가코드(ISO 2자리 코드)");
				String imgoods = (String) jobj.get("수입품");
				String exgoods = (String) jobj.get("수출품");
				String income = (String) jobj.get("우리나라와의 무역 수입액(US달러(호주는 AU달러)");
				String expense = (String) jobj.get("우리나라와의 무역 수출액(US달러(호주는 AU달러)");
				String year = (String) jobj.get("무역년도");

				int id = i;
				if (year != null || income != null) {
					System.out.println(i + 1 + ":" + name + "/ " + countryid + "/ " + year + "/ " + imgoods + "/ "
							+ exgoods + "/ " + income + "/ " + expense);
//					Tradedb tradedb = new Tradedb(id, countryid, name, year, imgoods, exgoods, income, expense);
				} else {

					System.out.println(i + 1 + ":" + name + "/ " + countryid + "/ " + 000 + "/ " + imgoods + "/ "
							+ exgoods + "/ " + 000 + "/ " + expense);

				}

			}

		} catch (NullPointerException e) {
		}

	}
}
