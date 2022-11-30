package com.example.kreambidding.repository.user;

import com.example.kreambidding.model.user.User;
import org.junit.jupiter.api.*;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@Testcontainers
class UserJdbcRepositoryTest {
    @Container
    private static final MySQLContainer<?> MY_SQL_CONTAINER = new MySQLContainer<>("mysql:8.0.24")
            .withInitScript("schema.sql");

    public DataSource dataSource = DataSourceBuilder.create()
            .driverClassName(MY_SQL_CONTAINER.getDriverClassName())
            .url(MY_SQL_CONTAINER.getJdbcUrl())
            .username(MY_SQL_CONTAINER.getUsername())
            .password(MY_SQL_CONTAINER.getPassword())
            .build();

    public NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

    public UserRepository userRepository = new UserJdbcRepository(namedParameterJdbcTemplate);

    @BeforeEach
    void clear() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("사용자를 추가할 수 있다.")
    void insertTest() {
        User user = testUserBuilder();

        User insertedUser = userRepository.insert(user);

        assertThat(insertedUser, samePropertyValuesAs(user, "userId"));
    }

    @Test
    @DisplayName("저장된 사용자 전체를 조회할 수 있다.")
    void findAllTest() {
        userRepository.insert(testUserBuilder());

        var allUser = userRepository.findAll();

        assertThat(allUser.isEmpty(), is(false));
    }

    @Test
    @DisplayName("저장된 사용자를 아이디로 조회할 수 있다.")
    void findByIdTest() {
        User user = userRepository.insert(testUserBuilder());

        User foundUser = userRepository.findById(user.getUserId()).orElseThrow();

        assertThat(foundUser, samePropertyValuesAs(user));
    }

    @Test
    @DisplayName("저장된 사용자를 수정할 수 있다.")
    void updateTest() {
        User user = userRepository.insert(testUserBuilder());

        user.setUserName("수정된 테스트 이름");
        user.setUserName("수정된 테스트 주소");
        User updatedUser = userRepository.update(user);

        assertThat(updatedUser, samePropertyValuesAs(user));
    }

    @Test
    @DisplayName("저장된 사용자를 아이디로 삭제할 수 있다.")
    void deleteTest() {
        User user = userRepository.insert(testUserBuilder());
        userRepository.insert(testUserBuilder());

        userRepository.delete(user.getUserId());

        assertThat(userRepository.findById(user.getUserId()).isEmpty(), is(true));
    }

    @Test
    @DisplayName("전체 사용자를 삭제할 수 있다.")
    void deleteAllTest() {
        userRepository.insert(testUserBuilder());
        userRepository.insert(testUserBuilder());

        userRepository.deleteAll();

        assertThat(userRepository.findAll().isEmpty(), is(true));
    }

    // 객체 생성
    private static User testUserBuilder() {
        return User.builder()
                .userName("테스트 이름")
                .address("테스트 주소")
                .build();
    }
}