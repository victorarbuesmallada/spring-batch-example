package org.cascomio.springbatchexample.readers;

import org.jsoup.nodes.Document;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.cascomio.springbatchexample.parsers.HtmlParser;
import org.cascomio.springbatchexample.parsers.ParserPayload;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class FoxtonsReader implements ItemReader<Collection<Document>> {
    @Autowired
    private HtmlParser htmlParser;

    @Override
    public Collection<Document> read() throws IOException {
        ParserPayload payload = new ParserPayload();
        payload.setUrl("https://www.foxtons.co.uk/search");
        payload.setData(createParameters());
        Document response = htmlParser.parse(payload);
        String propertiesCountText = response.getElementById("search_results_total").text();
        int propertiesCount = Integer.valueOf(propertiesCountText.replaceAll(",|\\.",""));
        return Arrays.asList(response);
    }

    private Map<String, String> createParameters() {
        return new HashMap<String, String>(){
            {
                put("submit_type", "search");
                put("search_form", "keyword");
            }
        };
        //?submit_type=search&search_form=keyword&search_type=SS&price_to=470000&bedrooms_from=3&bedrooms_to=3&sold=1
    }
}
