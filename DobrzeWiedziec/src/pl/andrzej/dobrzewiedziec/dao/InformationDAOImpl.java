package pl.andrzej.dobrzewiedziec.dao;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import pl.andrzej.dobrzewiedziec.model.Information;
import pl.andrzej.dobrzewiedziec.util.ConnectionProvider;

public class InformationDAOImpl implements InformationDAO {

    private static final String CREATE_INFORMATION =
            "INSERT INTO information(name, description, url, user_id, date, up_vote, down_vote) VALUES(:name, :description, :url, :user_id, :date, :up_vote, :down_vote);";

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
            resultInformation.setId((Long)holder.getKey());
        }
        return resultInformation;
    }

    @Override
    public Information read(Long primaryKey) {
        return null;
    }

    @Override
    public boolean update(Information updateObject) {
        return false;
    }

    @Override
    public boolean delete(Long key) {
        return false;
    }

    @Override
    public List<Information> getAll() {
        return null;
    }

}
