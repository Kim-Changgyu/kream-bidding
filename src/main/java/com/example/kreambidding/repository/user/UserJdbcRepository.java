package com.example.kreambidding.repository.user;

import com.example.kreambidding.model.user.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserJdbcRepository implements UserRepository {
    private static final GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UserJdbcRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public User insert(User user) {
        var result = namedParameterJdbcTemplate.update(
                "INSERT INTO users(user_name, address) VALUES (:userName, :address)",
                new MapSqlParameterSource(toParamMap(user)),
                generatedKeyHolder
        );
        if (result != 1) {
            throw new RuntimeException("사용자가 정상적으로 생성되지 않았습니다.");
        }
        return User.builder()
                .userId(generatedKeyHolder.getKey().longValue())
                .userName(user.getUserName())
                .address(user.getAddress())
                .build();
    }

    @Override
    public List<User> findAll() {
        return namedParameterJdbcTemplate.query(
                "SELECT * FROM users",
                userRowMapper
        );
    }

    @Override
    public Optional<User> findById(long userId) {
        try {
            return Optional.of(
                    namedParameterJdbcTemplate.queryForObject(
                            "SELECT * FROM users WHERE user_id = :userId",
                            Collections.singletonMap("userId", userId),
                            userRowMapper)
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public User update(User user) {
        var result = namedParameterJdbcTemplate.update(
                "UPDATE users SET user_name = :userName, address = :address WHERE user_id = :userId",
                toParamMap(user)
        );
        if (result != 1) {
            throw new RuntimeException("사용자가 정상적으로 수정되지 않았습니다.");
        }
        return user;
    }

    @Override
    public void delete(long userId) {
        namedParameterJdbcTemplate.update(
                "DELETE FROM users WHERE user_id = :userId",
                Collections.singletonMap("userId", userId)
        );
    }

    @Override
    public void deleteAll() {
        namedParameterJdbcTemplate.update(
                "DELETE FROM users",
                Collections.emptyMap()
        );
    }

    private static final RowMapper<User> userRowMapper = (resultSet, i) -> {
        var userId = resultSet.getLong("user_id");
        var userName = resultSet.getString("user_name");
        var address = resultSet.getString("address");
        return User.builder()
                .userId(userId)
                .userName(userName)
                .address(address)
                .build();
    };

    private static Map<String, Object> toParamMap(User user) {
        var paramMap = new HashMap<String, Object>();
        paramMap.put("userId", user.getUserId());
        paramMap.put("userName", user.getUserName());
        paramMap.put("address", user.getAddress());
        return paramMap;
    }
}
