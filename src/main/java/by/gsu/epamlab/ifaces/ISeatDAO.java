package by.gsu.epamlab.ifaces;

import by.gsu.epamlab.model.beans.SeatClass;
import by.gsu.epamlab.model.exceptions.DaoException;

import java.util.List;

/**
 * Created by alex on 13.06.2016.
 */
public interface ISeatDAO {
    public List<SeatClass> getSeats(String path) throws DaoException;
}
