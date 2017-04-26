package org.cascomio.springbatchexample.parsers;

import org.jsoup.nodes.Document;

import java.io.IOException;

public interface HtmlParser {
    Document parse(ParserPayload payload) throws IOException;
}

