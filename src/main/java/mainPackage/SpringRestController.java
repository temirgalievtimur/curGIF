package mainPackage;
import Additional.Additional;
import Model.CurrencyModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.time.DateUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.*;
import static Additional.Additional.*;


@RestController
public class SpringRestController {
    @Autowired
    CurrencyRequest currencyRequest;
    @Autowired
    GiphyRequest giphyRequest;
    @Value("${curr.apiKey}")
    private String apiKeyCurr;
    @Value("${giphy.apiKey}")
    private String apiKeyGif;
    @Value("${host.appResult}")
    private String hostAppResult;
    @Value("${host.appMainPage}")
    private String mainPage;
    @Value("${baseCurr}")
    private String baseCurr;

    private ObjectMapper mapper;
    private StringReader reader;
    private Date dateParam;
    private SimpleDateFormat sdf;
    private String yesterday;
    private String today;

    @RequestMapping("/start")
    public String startPage() throws IOException {
        return toHTMLStartPage(mainPage);
    }

    @RequestMapping("/main")
    public String mainPage() throws IOException {
        return toHTMLMain(JSONObjectToMap(new JSONObject(currencyRequest.listOfCurrencyes())), hostAppResult);
    }

    @RequestMapping("/result")
    public String resultForEachCurr(@RequestParam("curr") String curr ) throws IOException {

       //здесь я для примера преобразовываю JSON в объект, с помощбю Jackson
        mapper = new ObjectMapper();//jackson

        sdf = new SimpleDateFormat("yyyy-MM-dd");//форматирование даты
        dateParam = new Date();//форматирование даты
        today = sdf.format(dateParam);//форматирование даты
        dateParam = DateUtils.addDays(new Date(), -1);//форматирование даты, предыдущий день
        yesterday = sdf.format(dateParam);//форматирование даты, предыдущий день

        CurrencyModel currencyModelYesterday = mapper.readValue(new StringReader(currencyRequest.forEachCurr(yesterday, apiKeyCurr, baseCurr, curr)), CurrencyModel.class);
        CurrencyModel currencyModelToday = mapper.readValue(new StringReader(currencyRequest.forEachCurr(today, apiKeyCurr, baseCurr, curr)), CurrencyModel.class);

        return new Additional().toHTMLResult(currencyModelYesterday, yesterday, currencyModelToday, today, curr, giphyRequest, apiKeyGif);
    }



}