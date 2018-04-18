package by.gsu.epamlab.analyzer.sax;

import by.gsu.epamlab.model.beans.SeatClass;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


import java.util.ArrayList;
import java.util.List;


/**
 * Created by alex on 13.06.2016.
 */
public class SeatTheaterHandler extends DefaultHandler {
    private final static int ATTRIBUTE_CATEGORY = 0;
    private final static int ATTRIBUTE_PRICE = 1;
    private final static int ATTRIBUTE_AMOUNT = 2;

    private static enum SeatCategoryEnum {
        THEATER, SEATCLASS, CATEGORY, PRICE, AMOUNT;
    }

    private SeatCategoryEnum currentEnum;
    private List<SeatClass> seatClasses = new ArrayList<>();

    public List<SeatClass> getSeatClasses() {
        return seatClasses;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentEnum = SeatCategoryEnum.valueOf(localName.toUpperCase());
        switch (currentEnum) {
            case SEATCLASS:
                seatClasses.add(new SeatClass(attributes.getValue(ATTRIBUTE_CATEGORY),
                        attributes.getValue(ATTRIBUTE_PRICE), attributes.getValue(ATTRIBUTE_AMOUNT)));
                break;
        }
    }







}
