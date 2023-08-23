package com.example.demoJasperReport.service.impl;

import com.example.demoJasperReport.model.Conexion;
import com.example.demoJasperReport.model.Pet;
import com.example.demoJasperReport.repository.PetRepository;
import com.example.demoJasperReport.service.PetService;
import com.example.demoJasperReport.util.PetReportGenerator;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PetServiceImpl implements PetService {
    private final Llenar llenar = new Llenar();
    @Autowired
    private PetRepository petRepository;

    @Autowired
    private PetReportGenerator petReportGenerator;

    @Override
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


    @Override
    public Pet findById(Long id) {
        return petRepository.findById(id).get();
    }

    @Override
    public Pet save(Pet pet) {
        return petRepository.save(pet);
    }

    @Override
    public void deleteById(Long id) {
        petRepository.deleteById(id);
    }

    @Override
    public byte[] exportPdf() throws JRException, FileNotFoundException {
        List<Pet> pets = llenar.findAll();
        return petReportGenerator.exportToPdf(pets);
    }


    @Override
    public byte[] exportXls() throws JRException, FileNotFoundException {
        List<Pet> pets = llenar.findAll();
        return petReportGenerator.exportToXls(pets);
    }
}
