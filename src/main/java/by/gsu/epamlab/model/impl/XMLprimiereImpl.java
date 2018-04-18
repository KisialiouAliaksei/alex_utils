package by.gsu.epamlab.model.impl;


import by.gsu.epamlab.analyzer.sax.PremiereHandler;
import by.gsu.epamlab.controllers.Constants;
import by.gsu.epamlab.ifaces.IPrimiereDAO;
import by.gsu.epamlab.model.beans.Premiere;
import by.gsu.epamlab.model.exceptions.DaoException;

import org.xml.sax.SAXException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;


import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * Created by alex on 13.06.2016.
 */
public class XMLprimiereImpl implements IPrimiereDAO {

    @Override
    public List<Premiere> getPremieres(String path) throws DaoException {
        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            PremiereHandler contentHandler = new PremiereHandler();
            reader.setContentHandler(contentHandler);
            reader.parse(path);
            return contentHandler.getPremieres();

        } catch (SAXException e) {
           throw new DaoException(Constants.ERROR_RESOURCE_SAX_PARSING,e);
        } catch (IOException e) {
            throw  new DaoException(Constants.ERROR_FIND_RESOURSES_FILE,e);


        }
    }
}
