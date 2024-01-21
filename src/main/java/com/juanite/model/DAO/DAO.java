package com.juanite.model.DAO;

import com.juanite.connection.ConnectionMySQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DAO {

    private final static String FINDALL = "SELECT * FROM products";
    private final static String INSERT = "INSERT INTO products (name) VALUES (?)";

    private Connection conn;

    public DAO(Connection conn){
        this.conn = conn;
    }
    public DAO() {
        this.conn = ConnectionMySQL.getConnect();
    }

    /**
     * Method that gets every product stored at the database as Strings.
     * @return an ObservableList of Strings. All the products in the DB.
     * @throws Exception
     */
    public synchronized ObservableList<String> findAll() throws Exception {
        ObservableList<String> result = FXCollections.observableArrayList();
        try(PreparedStatement pst = this.conn.prepareStatement(FINDALL)) {
            try (ResultSet res = pst.executeQuery()) {
                while (res.next()) {
                        String product = res.getString("name");
                        result.add(product);
                }
            }
        }
        return result;
    }

    /**
     * Method that stores a new product in the database.
     * @param name the product to store.
     * @throws Exception
     */
    public synchronized void save(String name) throws Exception {

            try(PreparedStatement pst = this.conn.prepareStatement(INSERT)) {
                pst.setString(1, name);
                pst.executeUpdate();
            }
    }
}
