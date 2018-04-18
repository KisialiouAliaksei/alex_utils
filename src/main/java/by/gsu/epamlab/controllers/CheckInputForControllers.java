package by.gsu.epamlab.controllers;

import by.gsu.epamlab.model.beans.Premiere;
import by.gsu.epamlab.model.exceptions.ValidateException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by alex on 12.07.2016.
 */
final class CheckInputForControllers {
    public static void checkPremieres(String data, String prem, List<Premiere> premiereList)
            throws  ValidateException
    {
        if (prem == null || data == null) {
            throw new ValidateException(Constants.ERROR_NULL_PREMIERE);
        }
        prem = prem.trim();
        data = data.trim();

        if (Constants.KEY_EMPTY.equals(prem) || Constants.KEY_EMPTY.equals(data)) {
            throw new ValidateException(Constants.ERROR_EMPTY_PREMIERE);
        }
        Premiere premiere = new Premiere(prem);
        if(!premiereList.contains(premiere)){
            throw new ValidateException(Constants.ERROR_WRONG_PREMIERE);
        }
        else{
            int index = premiereList.indexOf(premiere);
            premiere = premiereList.get(index);
            Date date = parseParameterDate(data);
            List<Date> dateList = premiere.getDates();
            if(!dateList.contains(date)){
                throw new ValidateException(Constants.ERROR_WRONG_DATE);

            }
        }
    }
    private static Date parseParameterDate(String date) throws ValidateException {
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("yyyy-MM-dd");
        long l;
        try {
            l = format.parse(date).getTime();
            return new Date(l);
        } catch (ParseException e) {
            throw new ValidateException(Constants.ERROR_WRONG_DATE);
        }


    }
}
