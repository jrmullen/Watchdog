package com.watchdog.dao;

/**
 * Created by BBuck on 10/4/16.
 */

import com.watchdog.business.Permissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class PermissionsDaoImpl implements PermissionsDao {

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(this.dataSource);
    }

    @Override
    public void save (Permissions permissions) {

        Object[] args = new Object[]{permissions.getRole(), permissions.getDescription()};

        int out = jdbcTemplate.update(Constants.CREATE_PERMISSIONS_QUERY, args);

        if (out !=0) {
            System.out.println("Permissions " + permissions.getRole() + " " + permissions.getDescription() + " saved");
        } else System.out.println("Permissions " + permissions.getRole() + " " + permissions.getDescription() + " failed");

    }

    @Override
    public Permissions getById(int id) {

        //using RowMapper anonymous clas, we can create a separate RowMapper for reuse
        Permissions permissions = jdbcTemplate.queryForObject(Constants.GET_BY_PERMISSIONS_ID_QUERY, new Object[]{id}, new RowMapper<Permissions>() {

            @Override
            public Permissions mapRow(ResultSet rs, int rowNum)
                    throws SQLException {
                Permissions permissions = new Permissions();
                permissions.setId(rs.getInt("PERMISS_ID"));
                permissions.setRole(rs.getString("ROLE"));
                permissions.setDescription(rs.getString("PERMISS_DESCRIPTION"));

                return permissions;
            }
        });
        return permissions;
    }

    @Override
    public void update(Permissions permissions) {

        Object[] args = new Object[]{permissions.getRole(), permissions.getDescription()};

        int out = jdbcTemplate.update(Constants.UPDATE_BY_PERMISSIONS_ID_QUERY, args);
        if (out !=0) {
            System.out.println("Permissions updated with id= " + permissions.getId());
        } else System.out.println("No permissions found with id= " + permissions.getId());
    }


    @Override
    public void deleteById(int id) {

        int out = jdbcTemplate.update(Constants.DELETE_PERMISSIONS_BY_ID_QUERY, id);
        if (out !=0) {
            System.out.println("Permissions deleted with id= " + id);
        } else System.out.println("No permissions found with id= " + id);
    }

    @Override
    public List<Permissions> getAll() {

        List<Permissions> permissionsList = new ArrayList<Permissions>();
        List<Map<String, Object>> permissionsRows = jdbcTemplate.queryForList(Constants.GET_ALL_PERMISSIONS_QUERY);

        for (Map<String, Object> permissionsRow : permissionsRows) {
            Permissions permissions = new Permissions();
            permissions.setId(Integer.parseInt(String.valueOf(permissionsRow.get("PERMISS_ID"))));
            permissions.setRole(String.valueOf(permissionsRow.get("ROLE")));
            permissions.setDescription(String.valueOf(permissionsRow.get("PERMISS_DESCRIPTION")));
            permissionsList.add(permissions);
        }
        return permissionsList;
    }
}