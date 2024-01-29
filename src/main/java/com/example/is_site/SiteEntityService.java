package com.example.is_site;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.sql.DataSource;

@Service
public class SiteEntityService {
    private final DataSource dataSource;
    
    @Autowired
    private SiteEntityRepository repository;

    @Autowired
    public SiteEntityService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<SiteEntity> getAllEntries() {
        return repository.findAll();
    }

       public String getDatabaseName() {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT DATABASE() AS dbName");

            if (resultSet.next()) {
                return resultSet.getString("dbName");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "Unknown"; 
    }
}