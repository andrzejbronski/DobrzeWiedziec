package pl.andrzej.dobrzewiedziec.dao;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import pl.andrzej.dobrzewiedziec.model.Information;
import pl.andrzej.dobrzewiedziec.util.ConnectionProvider;
import pl.andrzej.dobrzewiedziec.model.User;

public class InformationDAOImpl implements InformationDAO {

    private static final String CREATE_INFORMATION =
            "INSERT INTO information(name, description, url, user_id, date, up_vote, down_vote) VALUES(:name, :description, :url, :user_id, :date, :up_vote, :down_vote);";
    private static final String READ_ALL_INFORMATIONS =
            "SELECT user.user_id, username, email, is_active, password, information_id, name, description, url, date, up_vote, down_vote "
                    + "FROM information LEFT JOIN user ON information.user_id=user.user_id;";
    private static final String READ_INFORMATION =
            "SELECT user.user_id, username, email, is_active, password, information_id, name, description, url, date, up_vote, down_vote "
                    + "FROM information LEFT JOIN user ON information.user_id=user.user_id WHERE information_id=:information_id;";
    private static final String UPDATE_INFORMATION =
            "UPDATE information SET name=:name, description=:description, url=:url, user_id=:user_id, date=:date, up_vote=:up_vote, down_vote=:down_vote "
                    + "WHERE information_id=:information_id;";
    private NamedParameterJdbcTemplate template;

    public InformationDAOImpl() {
        template = new NamedParameterJdbcTemplate(ConnectionProvider.getDataSource());
    }
    @Override
    public Information create(Information information) {
        Information resultInformation = new Information(information);
        KeyHolder holder = new GeneratedKeyHolder();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("name", information.getName());
        paramMap.put("description", information.getDescription());
        paramMap.put("url", information.getUrl());
        paramMap.put("user_id", information.getUser().getId());
        paramMap.put("date", information.getTimestamp());
        paramMap.put("up_vote", information.getUpVote());
        paramMap.put("down_vote", information.getDownVote());
        SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
        int update = template.update(CREATE_INFORMATION, paramSource, holder);
        if(update > 0) {
           // resultInformation.setId((Long)holder.getKey());//generate exception!
            resultInformation.setId(holder.getKey().longValue());
        }
        return resultInformation;
    }

    @Override
    public Information read(Long primaryKey) {
        SqlParameterSource paramSource = new MapSqlParameterSource("information_id", primaryKey);
        Information information = template.queryForObject(READ_INFORMATION, paramSource, new InformationRowMapper());
        return information;
    }

    @Override
    public boolean update(Information information) {
        boolean result = false;
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("information_id", information.getId());
        paramMap.put("name", information.getName());
        paramMap.put("description", information.getDescription());
        paramMap.put("url", information.getUrl());
        paramMap.put("user_id", information.getUser().getId());
        paramMap.put("date", information.getTimestamp());
        paramMap.put("up_vote", information.getUpVote());
        paramMap.put("down_vote", information.getDownVote());
        SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
        int update = template.update(UPDATE_INFORMATION, paramSource);
        if(update > 0) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(Long key) {
        return false;
    }

    @Override
    public List<Information> getAll() {
        List<Information> informations = template.query(READ_ALL_INFORMATIONS, new InformationRowMapper());
        return informations;
    }
    private class InformationRowMapper implements RowMapper<Information> {
        @Override
        public Information mapRow(ResultSet resultSet, int row) throws SQLException {
            Information information = new Information();
            information.setId(resultSet.getLong("information_id"));
            information.setName(resultSet.getString("name"));
            information.setDescription(resultSet.getString("description"));
            information.setUrl(resultSet.getString("url"));
            information.setUpVote(resultSet.getInt("up_vote"));
            information.setDownVote(resultSet.getInt("down_vote"));
            information.setTimestamp(resultSet.getTimestamp("date"));
            User user = new User();
            user.setId(resultSet.getLong("user_id"));
            user.setUsername(resultSet.getString("username"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            information.setUser(user);
            return information;
        }
    }

}
