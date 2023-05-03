package com.example.courseWork.repository;

import com.example.courseWork.entity.Toy;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ToyRepository {
    private final JdbcTemplate jdbcTemplate;

    public ToyRepository(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/toyshop");
        dataSource.setUsername("root");
        dataSource.setPassword("zNvidq012V");

        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public List<Toy> getToy(Map<String,String> params) {
        String sql = "SELECT * FROM toy WHERE";
        for(Map.Entry<String, String> entry : params.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            try {
                Integer.parseInt(value);
                sql = sql + " " + key + "='" + value + "' AND";
            } catch (NumberFormatException ignored) {
                value = value.replace("'", "\'");
                value = value.replace('"', '\'');

                sql = sql + " " + key + " LIKE '%" + value + "%' AND";
            }
        }
        sql = sql.substring(0, sql.length() - 4);

        List<Toy> toys = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Toy.class));
        return toys;
    }

    public boolean recountGoods(List<Integer> orderToyList) {
        Map<Integer, Integer> mapToUpdate = new HashMap<>();

        for(int i = 0; i < orderToyList.size(); i++) {
            if (orderToyList.get(i) == 0) {
                continue;
            }

            int goodCount = Collections.frequency(orderToyList, orderToyList.get(i));

            String sql = "SELECT on_store FROM toy WHERE id = " + "'" + orderToyList.get(i) + "'";
            Toy toy = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Toy.class)).get(0);

            if (toy.getOn_store() < goodCount) {
                return false;
            }

            mapToUpdate.put(orderToyList.get(i), goodCount);

            int savedReplace = orderToyList.get(i);
            for(int j = 0; j < orderToyList.size(); j++) {
                if (orderToyList.get(j) == savedReplace) {
                    orderToyList.set(j, 0);
                }
            }
        }

        for(Map.Entry<Integer, Integer> entry : mapToUpdate.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();

            String sql = "SELECT on_store FROM toy WHERE id = " + "'" + key + "'";
            Toy toy = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Toy.class)).get(0);

            String updSql = "UPDATE toy SET on_store = " + "'" + (toy.getOn_store() - value) + "'" + " WHERE id = " + "'" + key + "'";
            jdbcTemplate.update(updSql);
        }

        return true;
    }

}
