package org.cascomio.springbatchexample.processors;

import org.cascomio.springbatchexample.models.Property;
import org.jsoup.nodes.Document;
import org.springframework.batch.item.ItemProcessor;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class PropertyProcessor implements ItemProcessor<Collection<Document>, Collection<Property>> {
    abstract Stream<Property> parse(Document document);

    @Override
    public Collection<Property> process(Collection<Document> documents) {
        return documents.stream().flatMap(d -> parse(d)).collect(Collectors.toList());
    }
}
