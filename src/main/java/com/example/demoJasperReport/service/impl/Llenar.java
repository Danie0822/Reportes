package com.example.demoJasperReport.service.impl;

import com.example.demoJasperReport.model.Conexion;
import com.example.demoJasperReport.model.Pet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Llenar {
    public List<Pet> findAll() {
        Conexion conexion = new Conexion();
        Connection con = conexion.openDB();

        List<Pet> pets = new ArrayList<>();

        if (con != null) {
            try {
                String query = "SELECT * FROM Pet";
                PreparedStatement statement = con.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    Pet pet = new Pet();
                    pet.setId(resultSet.getLong("id"));
                    pet.setName(resultSet.getString("name"));
                    pet.setBirthday(resultSet.getDate("birthday"));
                    pet.setBreed(resultSet.getString("breed"));
                    pets.add(pet);
                }

                resultSet.close();
                statement.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return pets;
    }
}
