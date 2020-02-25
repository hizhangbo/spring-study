package io.github.hizhangbo.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Bob
 * @date 2020-02-25 1:25
 */
@Transactional
@Repository
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert() {
        String sql = "INSERT INTO `test`.`tbl_user`(`email`, `name`) VALUES (?, ?)";
        jdbcTemplate.update(sql, "Jessica@travel.com", "Jessica");
        int i = 1 / 0;
    }
}
