package br.com.erudio.testcontainer;

import br.com.erudio.application.usecases.user.SaveUser;
import br.com.erudio.domain.entities.user.Permission;
import br.com.erudio.domain.entities.user.User;
import br.com.erudio.domain.repositories.JwtRepository;
import br.com.erudio.domain.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.lifecycle.Startables;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

@ContextConfiguration(initializers = AbstractIntegrationTest.Initializer.class)
public class AbstractIntegrationTest {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserDetailsService userDetailsService;

    @Autowired
    private SaveUser saveUser;

    @Autowired
    private JwtRepository jwtRepository;

    public static String token = "Bearer ";

    @BeforeEach
    public void setup() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        User user = User.builder()
                .userName("test")
                .password("test")
                .permissions(List.of(new Permission(1L, "test")))
                .build();

        when(userRepository.saveUser(any())).thenReturn(user);
        when(userDetailsService.loadUserByUsername(anyString())).thenReturn(user);

        saveUser.execute(user);

        token += jwtRepository.createAccessToken(user.getUserName(), user.getRoles())
                .getAccessToken();
    }

    @AfterEach
    public void restart() {
        token = "Bearer ";
    }

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:latest");


        private static Map<String, String> createConnectionConfiguration() {
            return Map.of(
                    "spring.datasource.url", mysql.getJdbcUrl(),
                    "spring.datasource.username", mysql.getUsername(),
                    "spring.datasource.password", mysql.getPassword()
            );
        }

        @SuppressWarnings({"unchecked", "rawtypes"})
        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            Startables.deepStart(Stream.of(mysql)).join();
            ConfigurableEnvironment environment = applicationContext.getEnvironment();
            MapPropertySource testContainer = new MapPropertySource(
                    "testcontainers",
                    (Map) createConnectionConfiguration()
            );
            environment.getPropertySources().addFirst(testContainer);
        }
    }

}
