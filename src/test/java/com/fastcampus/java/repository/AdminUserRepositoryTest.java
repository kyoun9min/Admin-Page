package com.fastcampus.java.repository;

import com.fastcampus.java.component.LoginUserAuditorAware;
import com.fastcampus.java.config.JpaConfig;
import com.fastcampus.java.model.entity.AdminUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("AdminUserRepository 테스트")
@Import({JpaConfig.class, LoginUserAuditorAware.class})
public class AdminUserRepositoryTest {

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Test
    @DisplayName("AdminUser Create 테스트")
    public void create() {
        AdminUser adminUser = new AdminUser();
        adminUser.setAccount("AdminUser01");
        adminUser.setPassword("AdminUser01");
        adminUser.setStatus("REGISTERED");
        adminUser.setRole("PARTNER");
//        adminUser.setCreatedAt(LocalDateTime.now());
//        adminUser.setCreatedBy("AdminServer");

        AdminUser newAdminUser = adminUserRepository.save(adminUser);
        Assertions.assertNotNull(newAdminUser);
    }
}
