package Additional;

import Model.CurrencyModel;
import mainPackage.GiphyRequest;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Additional {

    //метод получает два объекта-ответа по конкретной валюте и отвечает в
    // HTML с полученной гифкой фиксированного размера, основываясь на полях полученного объекта
    public static String toHTMLResult(CurrencyModel yesterdayOBJ, String yesterday, CurrencyModel todayOBJ, String today, String curr, GiphyRequest giphyRequest, String apiKeyGif){
        StringBuilder sb = new StringBuilder();
        String tag = "rich";
        String resume = "богаче!";

        if(todayOBJ.getRates().get(curr) < yesterdayOBJ.getRates().get(curr)){
            resume = "беднее((";
            tag = "broke";
        }
        if(todayOBJ.getRates().get(curr).compareTo(yesterdayOBJ.getRates().get(curr)) == 0){
            System.out.println("awdwdwddwd");
            resume = "...........таким же как и вчера, что удивительно конечно";
            tag = "what";
        }

        //здесь уже работа с JSONObject, а не с Jackson
        JSONObject json = new JSONObject(giphyRequest.getRandomGif(apiKeyGif, tag));

        sb.append("<HTML>\n" +
                "<HEAD> <TITLE>Результат вложений в " +curr +"</TITLE> </HEAD>\n" +
                "<BODY>\n" +
                "<h1>Вы вложились в " + curr + "</h1>"+
                "<p>Вчерашний(" + yesterday + ") курс = "+yesterdayOBJ.getRates().get(curr)+"</p>\n" +
                "<p>Сегодняшний(" + today + ") курс =  "+todayOBJ.getRates().get(curr)+"</p>\n" +
                "<p>Вы стали "+resume+"</p>\n" +
                "<iframe src=\""+ json.getJSONObject("data").get("embed_url").toString()+"\" width=\"480\" height=\"270\" frameBorder=\"0\" class=\"giphy-embed\" allowFullScreen></iframe>\n" +
                "</BODY>\n" +
                "</HTML>");

        return sb.toString();
    }


    //преобразование JSONObject в связанный список объектов
    public static Map<String, Object> JSONObjectToMap(JSONObject jsonobj)  throws JSONException {

        Map<String, Object> map = new TreeMap<>();
        Iterator<String> keys = jsonobj.keys();

        while(keys.hasNext()) {
            String key = keys.next();
            Object value = jsonobj.get(key);
            map.put(key, value);
        }   return map;
    }

    //метод формирует страницу со всеми спискаами валют и формирует ссылку для получения страницы с гифкой
    public static String toHTMLMain(Map<String, Object> map, String hostAppResult){
        StringBuilder sb = new StringBuilder();
        sb.append("<style>.table-box {max-width: 1024px;}.table-box p {" +
                        "font-size: larger;" +
                        "font-weight: bold;" +
                    "}" +
                    "table{" +
                        "width: 100%;" +
                        "border: 1px solid #ccc;" +
                        "border-collapse: collapse;" +
                    "}" +
                    "thead {" +
                        "border: 1px solid #ccc;" +
                    "}" +
                    "th, td " +
                    "{" +
                        "border-left: 1px solid #ccc;" +
                        "white-space: nowrap;" +
                        "padding: 5px;" +
                    "}" +
                    "td:nth-last-child(-n+2) {" +
                        "text-align: right;" +
                    "}" +
                    "@media screen and (max-width: 732px) " +
                    "{" +
                    ".table-box" +
                        "{" +
                            "overflow-x: scroll;" +
                        "}" +
                    "}" +
                "</style>" +
                "<div class=\"table-box\">" +
                "<p>Список доступных валют</p>" +
                "<table>" +
                    "<thead>" +
                        "<tr><th>Название валюты</th><th>Код</th><th>Ссылка</th></tr>" +
                    "</thead>" +
                "<tbody>");

        for (Map.Entry<String, Object> pair : map.entrySet()) {
            sb.append("<tr><td>"+ pair.getValue()+"</td><td>" + pair.getKey()+"</td><td><a href=\""+hostAppResult+"?curr="+pair.getKey()+"\">Узнать вердикт</a></td></tr>");
        }
        sb.append("</tr></tbody></table></div>");
        return sb.toString();
    }

    public static String toHTMLStartPage(String mainPage){
        return "<html>" +
                    "<head><title>Добро пожаловать</title></head>" +
                    "<body>" +
                        "<h1>Добро пожаловать на стартовую страницу</h1>" +
                        "<p1>Для перехода на основную нажмите по <a href=\""+mainPage+"\">ссылке</a></h1>" +
                    "</body"+
                "</html>";
    }
}
