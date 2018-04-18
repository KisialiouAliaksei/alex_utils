package by.gsu.epamlab.ifaces;

import by.gsu.epamlab.model.beans.Premiere;
import by.gsu.epamlab.model.exceptions.DaoException;

import java.util.List;

/**
 * Created by alex on 13.06.2016.
 */
public interface IPrimiereDAO {
    public List<Premiere> getPremieres(String path) throws DaoException;

}
