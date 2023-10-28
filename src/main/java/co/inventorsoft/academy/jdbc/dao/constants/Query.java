package co.inventorsoft.academy.jdbc.dao.constants;

public final class Query {
    private Query() {}

    //USERS
    public static final String SQL_USER_CREATE = "INSERT INTO users (firstname, lastname, phone, email, password, description) " +
            "VALUES (?, ?, ?, ?, ?, ?)";
    public static final String SQL_USER_GET_ALL = "SELECT * FROM users";
    public static final String SQL_USER_GET = "SELECT * FROM users WHERE id = ?";
    public static final String SQL_USER_GET_BY_EMAIL = "SELECT * FROM users WHERE name = ?";
    public static final String SQL_USER_UPDATE =
            "UPDATE users SET firstname = ?, lastname = ?, phone = ?, email = ?, password = ?, description = ? WHERE ID = ?";

    public static final String SQL_USER_DELETE = "DELETE FROM users WHERE id = ?";


    //COMPANIES
    public static final String SQL_COMPANY_CREATE = "INSERT INTO companies (company_name, phone, address, city, region, zip, country) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";
    public static final String SQL_COMPANY_GET_ALL = "SELECT * FROM companies";
    public static final String SQL_COMPANY_GET = "SELECT * FROM companies WHERE id = ?";
    public static final String SQL_COMPANY_UPDATE =
            "UPDATE companies SET company_name = ?, phone = ?, address = ?, city = ?, region = ?, zip = ?, country = ? WHERE ID = ?";
    public static final String SQL_COMPANY_DELETE = "DELETE FROM companies WHERE id = ?";

}
