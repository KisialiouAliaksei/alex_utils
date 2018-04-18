package by.gsu.epamlab.controllers;

/**
 * Created by alex on 09.06.2016.
 */
public final class Constants {

    //key
    public static final String KEY_USER = "user";
    public static final String KEY_PREMIERE = "premiere";
    public static final String KEY_PREMIERES = "premieres";
    public static final String KEY_SEAT = "seat";
    public static final String KEY_TICKETS = "tickets";
    public static final String KEY_TICKETS_REPT = "ticketsRept";
    public static final String KEY_DATE = "date";
    public static final String KEY_DATE_FOR_REPT = "dateRept";
    public static final String KEY_DATE_FOR_COURIER = "dateForCourier";
    public static final String KEY_PHONES = "phones";
    public static final String KEY_PRICE = "price";

    //jumpAndMessage
    public static final String JUMP_JSP_MAIN = "/main.jsp";
    public static final String JUMP_MAIN = "/main";
    public static final String JUMP_REGISTRATION= "/registration";
    public static final String JUMP_SEANCE = "/seance";

    //jumpAndMessage jsp
    public static final String JUMP_JSP_REGISTRATION = "/registration.jsp";
    public static final String JUMP_JSP_SEANCE = "/seance.jsp";
    public static final String JUMP_JSP_CART = "/cart.jsp";
    public static final String JUMP_JSP_REPORT = "/rept.jsp";
    public static final String JUMP_START = "/index.jsp";

    //errors
    public static final String KEY_MESSAGE = "errorMessage";
    public static final String KEY_EMPTY = "";
    public static final String ERROR_EMPTY_LOGIN = "Login is empty.";
    public static final String ERROR_EMPTY_PREMIERE = "Premiere is empty.";
    public static final String ERROR_EMPTY_TICKETS = "Tickets are not selected";
    public static final String ERROR_EMPTY_PASSWORD = "Password is empty.";
    public static final String ERROR_NULL = "Login or password are absent.";
    public static final String ERROR_NULL_PREMIERE = "Premiere are absent.";
    public static final String ERROR_PASS_WRONG = "Password is wrong.";
    public static final String ERROR_LOGIN_EXIST = "Such login already exists.";
    public static final String ERROR_PASS_REPEAT = "Passwords are different.";
    public static final String ERROR_LOGIN_ADD = "Error while creating login. Try again.";
    public static final String CONNECTION_ERROR = "Failed to connect to database";
    public static final String ERROR_WRONG_DATE= "not correct date";
    public static final String ERROR_WRONG_PREMIERE= "not correct date";
    public static final String ERROE_CLOSING_RESURS = "Resource closing problem : ";
    public static final String ERROR_RESOURCE_SAX_PARSING = "Failed parse";
    public static final String ERROR_FIND_RESOURSES_FILE  = "Resourses not found";
    public static final String ERROR_TICKET_PAID = "Ticket paid";
    public static final String ERROR_TICKETS_NOT_SELECT = "Ticket not select";

    //naming resurses
    public static final String RES_PACKAGE = "res\\";
    public static final String RES_PREMIERES = "premieres.xml";
    public static final String RES_SEAT_CLASS = "seatTheater.xml";

    //other
    public static final String ROOT_PROJECT = "/final";
    public static final String SLASH = "/";




}
