package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.GuestBookDaoSqls.DELETE_BY_ID;
import static kr.or.connect.reservation.dao.GuestBookDaoSqls.SELECT_COUNT;
import static kr.or.connect.reservation.dao.GuestBookDaoSqls.SELECT_PAGING;

import java.util.*;

import javax.sql.*;

import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.jdbc.core.simple.*;
import org.springframework.stereotype.*;

import kr.or.connect.reservation.dto.*;

@Repository
public class GuestBookDao {
	private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert insertAction;
    private RowMapper<GuestBook> rowMapper = BeanPropertyRowMapper.newInstance(GuestBook.class);

    public GuestBookDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("guestbook")
                .usingGeneratedKeyColumns("id");
    }
    
    public List<GuestBook> selectAll(Integer start, Integer limit) {
    		Map<String, Integer> params = new HashMap<>();
    		params.put("start", start);
    		params.put("limit", limit);
        return jdbc.query(SELECT_PAGING, params, rowMapper);
    }


	public Long insert(GuestBook guestbook) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(guestbook);
		return insertAction.executeAndReturnKey(params).longValue();
	}
	
	public int deleteById(Long id) {
		Map<String, ?> params = Collections.singletonMap("id", id);
		return jdbc.update(DELETE_BY_ID, params);
	}
	
	public int selectCount() {
		return jdbc.queryForObject(SELECT_COUNT, Collections.emptyMap(), Integer.class);
	}
}
