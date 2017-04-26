package org.cascomio.springbatchexample.processors;

import org.cascomio.springbatchexample.models.Coordinates;
import org.cascomio.springbatchexample.models.Property;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.stream.Stream;

public class FoxtonsProcessor extends PropertyProcessor {
    protected final static String PROPERTY = "property_holder";
    private final static String LAT_ATTRIBUTE = "data-lat";
    private final static String LON_ATTRIBUTE = "data-lon";
    private final static String PROPERTY_SUMMARY = "property_summary";
    private final static String PROPERTY_PRICE = "formatPrice";
    private final static String PROPERTY_DESCRIPTION = "description";

    @Override
    public Stream<Property> parse(Document document) {
        Elements propertyElements = document.getElementsByClass(PROPERTY);
        return propertyElements.stream().map(p -> parseProperty(p));
    }

    private Property parseProperty(Element element) {
        Element propertySummary = element.getElementsByClass(PROPERTY_SUMMARY).first();

        Property property = new Property();
        try {
            property.setCoordinates(new Coordinates(element.attr(LAT_ATTRIBUTE), element.attr(LON_ATTRIBUTE)));
            property.setAddress(parseAddressFromSummary(propertySummary));
            property.setPrice(Long.valueOf(propertySummary.getElementsByClass(PROPERTY_PRICE).first().text().replaceAll("£|,|\\.", "")));
            property.setDescription(propertySummary.getElementsByClass(PROPERTY_DESCRIPTION).first().text());
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return property;
    }

    private String parseAddressFromSummary(Element summary) {
        Element addressElement = summary.getElementsByTag("h6").first();
        return addressElement.text();
    }
}
