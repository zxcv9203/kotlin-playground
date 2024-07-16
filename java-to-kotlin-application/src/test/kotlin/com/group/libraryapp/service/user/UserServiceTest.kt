package com.group.libraryapp.service.user

import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.dto.user.request.UserCreateRequest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserServiceTest @Autowired constructor(
    private val userRepository: UserRepository,
    private val userService: UserService
) {
    @Nested
    inner class SaveUser {
        @Test
        fun successTest() {
            val request = UserCreateRequest(
                "test",
                null
            )

            userService.saveUser(request)

            val results = userRepository.findAll()
            assertThat(results).hasSize(1)
            assertThat(results[0].name).isEqualTo("test")
            assertThat(results[0].age).isNull()
        }
    }
}