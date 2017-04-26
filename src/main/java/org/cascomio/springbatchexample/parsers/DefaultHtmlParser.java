package org.cascomio.springbatchexample.parsers;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class DefaultHtmlParser implements HtmlParser {
    @Override
    public Document parse(ParserPayload payload) throws IOException {
        Connection connection = Jsoup.connect(payload.getUrl())
                .data(payload.getData())
                .method(Connection.Method.valueOf(payload.getMethod()));
        return  connection.execute().parse();
    }
}
