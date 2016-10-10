package com.watchdog.dao;

/**
 * Created by BBuck on 10/4/16.
 */

import com.watchdog.business.Device;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;


public class DeviceDaoImpl implements DeviceDao {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(this.dataSource);
    }

    @Override
    public void save (Device device) {
        String query = "insert into device (DEVICE_NAME, DEVICE_TYPE) values (?,?)";

        Objects[] args = (Objects[]) new Object[]{device.getDeviceName(), device.getDeviceType()};

        int out = jdbcTemplate.update(query, args);
        if (out !=0) {
            System.out.println("Device saved with id= " + device.getId());
        } else System.out.println("Device save failed with id= " + device.getId());

    }

    @Override
    public Device getById(int id) {
        String query = "select DEVICE_NAME, DEVICE_TYPE from device where DEVICE_ID = ?";

        //using RowMapper anonymous clas, we can create a separate RowMapper for reuse
        Device device = jdbcTemplate.queryForObject(query, new Object[]{id}, new RowMapper<Device>() {

            @Override
            public Device mapRow(ResultSet rs, int rowNum)
                    throws SQLException {
                Device device = new Device();
                device.setDeviceName(rs.getString("DEVICE_NAME"));
                device.setDeviceType(rs.getString ("DEVICE_TYPE"));
                return device;
            }
        });
        return device;
    }

    @Override
    public void update(Device device) {
        String query = "update device set DEVICE_NAME = ?, DEVICE_TYPE = ? where DEVICE_ID = ?";

        Object[] args = new Object[]{device.getDeviceName(), device.getDeviceType()};

        int out = jdbcTemplate.update(query, args);
        if (out != 0) {
            System.out.println("Device updated with id= " + device.getId());
        } else System.out.println("No device found with id= " + device.getId());
    }


    @Override
    public void deleteById(int id) {
        String query = "delete from user where DEVICE_ID=?";

        int out = jdbcTemplate.update(query, id);
        if (out != 0) {
            System.out.println("Device deleted with id= " + id);
        } else System.out.println("No device found with id= " + id);
    }

    @Override
    public List<Device> getall() {
        String query = "select DEVICE_ID, DEVICE_NAME, DEVICE_TYPE from user";

        List<Device> deviceList = new ArrayList<Device>();

        List<Map<String, Object>> deviceRows = jdbcTemplate.queryForList(query);

        for (Map<String, Object> deviceRow : deviceRows) {
            Device device = new Device();
            device.setId(Integer.parseInt(String.valueOf(deviceRow.get("DEVICE_ID"))));
            device.setDeviceName(String.valueOf(deviceRow.get("DEVICE_NAME")));
            device.setDeviceType(String.valueOf(deviceRow.get("DEVICE_TYPE")));
            deviceList.add(device);
        }
        return deviceList;
    }
}
