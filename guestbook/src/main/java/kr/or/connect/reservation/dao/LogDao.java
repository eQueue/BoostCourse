package kr.or.connect.reservation.dao;

import javax.sql.*;

import org.springframework.jdbc.core.namedparam.*;
import org.springframework.jdbc.core.simple.*;
import org.springframework.stereotype.*;

import kr.or.connect.reservation.dto.*;

@Repository
public class LogDao {
	private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert insertAction;

    public LogDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource)  //자동으로 insert
                .withTableName("log")
                .usingGeneratedKeyColumns("id");
    }

	public Long insert(Log log) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(log);
		return insertAction.executeAndReturnKey(params).longValue(); //inser 된 id 리턴
	}
}
