package accessor;

import laptop.Laptop;

import java.sql.*;
import java.util.ArrayList;

public class Accessor {

    private static Accessor singletonAccessor = null;
    private Connection con = null;
    Statement stat = null;
    private String urlDatabase = "master";
    private final String table = "laptop";

    private Accessor() throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    }

    private boolean connect(){
        try {
            String connectionString = "jdbc:sqlserver://localhost;databaseName=" + urlDatabase + ";User=sa;Password=root;";

            con = DriverManager.getConnection(connectionString);
            stat = con.createStatement();
            return true;
        }catch(Exception ex){
            return false;
        }
    }

    public Accessor create() throws Exception {
        return new Accessor();
    }

    public static Accessor getInstance()
            throws Exception {
        if (singletonAccessor == null)
            singletonAccessor = new Accessor();
        return singletonAccessor;
    }

    public void closeConnection()
            throws SQLException {
        if (con != null)
            con.close();
    }

    void propertyStatement() throws SQLException {
        boolean ro = con.getMetaData().supportsResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        System.out.println("TYPE_FORWARD_ONLY - " + ro);

        ro = con.getMetaData().supportsResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE);
        System.out.println("TYPE_SCROLL_INSENSITIVE - " + ro);

        ro = con.getMetaData().supportsResultSetType(ResultSet.TYPE_SCROLL_SENSITIVE);
        System.out.println("TYPE_SCROLL_SENSITIVE - " + ro);

        ro = con.getMetaData().supportsResultSetConcurrency(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        System.out.println("CONCUR_READ_ONLY - " + ro);

        ro = con.getMetaData().supportsResultSetConcurrency(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        System.out.println("CONCUR_UPDATABLE - " + ro);


    }

    public void selectDatabase(String name){
        connect();
        this.urlDatabase = name;
        if(!connect()){
            try {
                stat.execute("CREATE DATABASE " + this.urlDatabase);
                connect();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }

    public void selectTable(){
        try {
            ResultSet rs = stat.executeQuery("SELECT name FROM " + this.urlDatabase + ".sys.Tables");
            ArrayList<String> namesOfTables = new ArrayList<>();
            while (rs.next()) {
                namesOfTables.add(rs.getString(1));
            }
            if(!namesOfTables.contains(this.table)){
                stat.executeUpdate("CREATE TABLE laptop(\n" +
                                        "producer nvarchar(50),\n" +
                                        "model nvarchar(50),\n" +
                                        "year smallint,\n");
            }
            rs.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public boolean insertLaptop(Laptop laptop){
        try {
            int status = stat.executeUpdate("INSERT INTO laptop(producer, model, year)VALUES('" +
                    laptop.getProducer() +
                    "', '" + laptop.getModel() +
                    "', " + laptop.getYear() + "')");
            if(status == 1)
                return true;
            else
                return false;
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

}