package com.watchdog.dao.device;

/**
 * Created by BBuck on 10/4/16.
 */

import com.watchdog.business.Device;
import com.watchdog.dao.Constants;
import com.watchdog.dao.device.DeviceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class DeviceDaoImpl implements DeviceDao {

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(this.dataSource);
    }

    @Override
    public void save (Device device) {

        Object[] args = new Object[]{device.getDeviceName(), device.getDeviceMac(),
                                    device.getDeviceIp(), device.getDevicePort()};

        int out = jdbcTemplate.update(Constants.CREATE_DEVICE_QUERY, args);

        if (out !=0) {
            System.out.println("Device " + device.getDeviceName() + " " + device.getDeviceMac() +
                        " " + device.getDeviceIp() + " " + device.getDevicePort() + " saved");
        } else System.out.println("Device " + device.getDeviceName() + " " + device.getDeviceMac() +
                        " " + device.getDeviceIp() + " " + device.getDevicePort() + " failed to save");

    }

    @Override
    public Device getById(int id) {

        //using RowMapper anonymous clas, we can create a separate RowMapper for reuse
        Device device = jdbcTemplate.queryForObject(Constants.GET_BY_DEVICE_ID_QUERY, new Object[]{id}, new RowMapper<Device>() {

            @Override
            public Device mapRow(ResultSet rs, int rowNum)
                    throws SQLException {
                Device device = new Device();
                device.setId(rs.getInt("DEVICE_ID"));
                device.setUserId(rs.getInt("USER_ID"));
                device.setPermissId(rs.getInt("PERMISS_ID"));
                device.setDeviceName(rs.getString("DEVICE_NAME"));
                device.setDeviceMac(rs.getString ("DEVICE_MAC"));
                device.setDeviceIp(rs.getString("DEVICE_IP"));
                if (null != rs.getString("DEVICE_PORT")) {
                    device.setDevicePort(rs.getInt("DEVICE_PORT"));
                }

                return device;
            }
        });
        return device;
    }

    @Override
    public void update(Device device) {

        Object[] args = new Object[]{device.getDeviceName(),  device.getDeviceMac(),
                                    device.getDeviceIp(),  device.getDevicePort()};

        int out = jdbcTemplate.update(Constants.UPDATE_BY_DEVICE_ID_QUERY, args);
        if (out !=0) {
            System.out.println("Device updated with id= " + device.getId() +
                                            "  MAC= " + device.getDeviceMac());
        } else System.out.println("No device found with id= " + device.getId() +
                                            "  MAC= " + device.getDeviceMac());
    }


    @Override
    public void deleteById(int id) {

        int out = jdbcTemplate.update(Constants.DELETE_DEVICE_BY_ID_QUERY, id);
        if (out !=0) {
            System.out.println("Device deleted with id= " + id);
        } else System.out.println("No device found with id= " + id);
    }

    @Override
    public List<Device> getAll() {


        List<Device> deviceList = new ArrayList<Device>();

        List<Map<String, Object>> deviceRows = jdbcTemplate.queryForList(Constants.GET_ALL_DEVICES_QUERY);

        for (Map<String, Object> deviceRow : deviceRows) {
            Device device = new Device();
            device.setId(Integer.parseInt(String.valueOf(deviceRow.get("DEVICE_ID"))));
            device.setUserId(Integer.parseInt(String.valueOf(deviceRow.get("USER_ID"))));
            device.setPermissId(Integer.parseInt(String.valueOf(deviceRow.get("PERMISS_ID"))));
            device.setDeviceName(String.valueOf(deviceRow.get("DEVICE_NAME")));
            device.setDeviceMac(String.valueOf(deviceRow.get("DEVICE_MAC")));
            device.setDeviceIp(String.valueOf(deviceRow.get("DEVICE_IP")));
            if (null != deviceRow.get("DEVICE_PORT")) {
                device.setDevicePort(Integer.parseInt(String.valueOf(deviceRow.get("DEVICE_PORT"))));
            }
            device.buildDeviceUrl(device.getDeviceIp(), device.getDevicePort());

            deviceList.add(device);
        }
        return deviceList;
    }
}