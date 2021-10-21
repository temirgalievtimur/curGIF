package mainPackage;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(url = "${curr.url}", name = "currClient")
public interface CurrencyRequest {

    @RequestMapping(method = RequestMethod.GET, value = "/api/historical/{date}.json?app_id={appID}&base={base}&symbols={curr}")
    String forEachCurr(@PathVariable("date") String date, @PathVariable("appID") String appID, @PathVariable("base") String base, @PathVariable("curr") String curr);

    @RequestMapping(method = RequestMethod.GET, value = "/api/currencies.json")
    String listOfCurrencyes();

}
