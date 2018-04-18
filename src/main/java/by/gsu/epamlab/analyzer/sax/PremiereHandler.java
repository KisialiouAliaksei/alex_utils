package by.gsu.epamlab.analyzer.sax;


import by.gsu.epamlab.model.beans.Author;
import by.gsu.epamlab.model.beans.Premiere;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.Date;


/**
 * Created by alex on 14.06.2016.
 */
public class PremiereHandler extends DefaultHandler {
    private final static int ATTRIBUTE_NAME = 0;
    private final static int ATTRIBUTE_GENRE = 1;
    private final static int ATTRIBUTE_AUTHOR = 2;
    private final static int ATTRIBUTE_DESCRIPTION = 3;

    private static enum PremiereEnum {
        THEATER, PREMIERE, NAME, GENRE, AUTHOR,DESCRIPTION, DATE;
    }

    private PremiereEnum currentEnum;
    private Premiere premiere;
    private List<Premiere> premieres = new ArrayList<>();
    private List<Date> dates ;
    private Date date;

    public List<Premiere> getPremieres() {
        return premieres;
    }

    @Override
    public void startElement(String uri, String localName, String qName, org.xml.sax.Attributes attributes) throws SAXException {
        currentEnum = PremiereEnum.valueOf(localName.toUpperCase());
        switch (currentEnum) {
            case PREMIERE:
                dates = new ArrayList<>();
                premiere = new Premiere(new Author(attributes.getValue(ATTRIBUTE_AUTHOR)),
                        attributes.getValue(ATTRIBUTE_NAME),
                        attributes.getValue(ATTRIBUTE_GENRE),
                        dates,
                        attributes.getValue(ATTRIBUTE_DESCRIPTION));
                premieres.add(premiere);
        }
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        String s = new String(ch, start, length).trim();
        if (currentEnum != null && currentEnum == PremiereEnum.DATE) {
                SimpleDateFormat format = new SimpleDateFormat();
                format.applyPattern("yyyy-MM-dd");
            long l = 0;
            try {
                l = format.parse(s).getTime();
            } catch (ParseException e) {
                throw new SAXException();
            }
            Date d = new Date(l);
                date = d;
        }
    }
    @Override
    public void endElement(String s, String s1, String s2) throws SAXException {
        if(currentEnum != null){
            switch (currentEnum){
                case DATE:
                    dates.add(date);
                    break;
            }
        }
        currentEnum = null;
    }
}
