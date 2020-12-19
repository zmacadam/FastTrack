package zmacadam.metrics.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import zmacadam.metrics.dao.RoleRepository;
import zmacadam.metrics.dao.UserRepository;
import zmacadam.metrics.model.User;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserServiceTest {

    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private RoleRepository mockRoleRepository;
    @Mock
    private BCryptPasswordEncoder mockBCryptPasswordEncoder;

    private UserService userServiceUnderTest;
    private User user;

    @Before
    public void setUp() {
        initMocks(this);
        userServiceUnderTest = new UserService(mockUserRepository,
                mockRoleRepository,
                mockBCryptPasswordEncoder);
        user = User.builder()
                .phoneNumber("1111111111")
                .userName("zmacadam")
                .first("Zach")
                .last("Macadam")
                .build();

        Mockito.when(mockUserRepository.save(any()))
                .thenReturn(user);
        Mockito.when(mockUserRepository.findByUsername(anyString()))
                .thenReturn(user);
        Mockito.when(mockUserRepository.findByPhonenumber(anyString()))
                .thenReturn(user);
    }

    @Test
    public void testFindUserByUsername() {
        // Setup
        final String username = "zmacadam";

        // Run the test
        final User result = userServiceUnderTest.findUserByUsername(username);

        // Verify the results
        assertEquals(username, result.getUserName());
    }

    @Test
    public void testFindUserByPhonenumber() {
        // Setup
        final String phonenumber = "1111111111";

        // Run the test
        final User result = userServiceUnderTest.findUserByPhonenumber(phonenumber);

        // Verify the results
        assertEquals(phonenumber, result.getPhoneNumber());
    }

    @Test
    public void testSaveUser() {
        // Setup
        final String username = "zmacadam";

        // Run the test
        User result = userServiceUnderTest.saveUser(User.builder().build());

        // Verify the results
        assertEquals(username, result.getUserName());
    }
}
