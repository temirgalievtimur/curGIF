package mainPackage;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.cloud.openfeign.FeignClient(name = "giphyClient", url = "${giphy.url}")
public interface GiphyRequest{
    @GetMapping("/random")
    String getRandomGif(@RequestParam("api_key") String apiKey, @RequestParam("tag") String tag);
}