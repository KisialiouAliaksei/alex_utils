package by.gsu.epamlab.model.impl;

import by.gsu.epamlab.analyzer.sax.SeatTheaterHandler;
import by.gsu.epamlab.controllers.Constants;
import by.gsu.epamlab.ifaces.ISeatDAO;
import by.gsu.epamlab.model.beans.SeatClass;
import by.gsu.epamlab.model.exceptions.DaoException;
import org.xml.sax.SAXException;
import java.io.IOException;
import java.util.List;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;


/**
 * Created by alex on 13.06.2016.
 */
public class XMLseatClassesImpl implements ISeatDAO {

    @Override
    public List<SeatClass> getSeats(String path) throws DaoException {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();
            SeatTheaterHandler contentHandler = new SeatTheaterHandler();
            reader.setContentHandler(contentHandler);
            reader.parse(path);
            return contentHandler.getSeatClasses();

        }
        catch (SAXException e){
           throw new DaoException(Constants.ERROR_RESOURCE_SAX_PARSING,e);
        }
        catch (IOException e) {
            throw new DaoException(Constants.ERROR_FIND_RESOURSES_FILE,e);
        }

    }
}

