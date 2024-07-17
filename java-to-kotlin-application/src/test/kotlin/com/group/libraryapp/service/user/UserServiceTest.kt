package com.group.libraryapp.service.user

import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.dto.user.request.UserCreateRequest
import com.group.libraryapp.dto.user.request.UserUpdateRequest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserServiceTest @Autowired constructor(
    private val userRepository: UserRepository,
    private val userService: UserService
) {
    @AfterEach
    fun tearDown() {
        userRepository.deleteAll()
    }

    @Nested
    inner class SaveJavaUser {
        @Test
        @DisplayName("[성공] 사용자 생성에 성공한다.")
        fun successTest() {
            val request = UserCreateRequest(
                "test",
                null
            )

            userService.saveUser(request)

            val want = userRepository.findAll()
            assertThat(want).hasSize(1)
            assertThat(want[0].name).isEqualTo("test")
            assertThat(want[0].age).isNull()
        }
    }

    @Nested
    inner class GetUsers {
        @Test
        @DisplayName("[성공] 사용자 목록을 조회한다.")
        fun successTest() {
            userRepository.saveAll(
                listOf(
                    User("A", 20),
                    User("B", null),
                )
            )

            val want = userService.getUsers()

            assertThat(want).hasSize(2)
            assertThat(want).extracting("name")
                .containsExactlyInAnyOrder("A", "B")
            assertThat(want).extracting("age")
                .containsExactlyInAnyOrder(20, null)
        }
    }

    @Nested
    inner class UpdateJavaUser {
        @Test
        @DisplayName("[성공] 사용자 이름을 수정한다.")
        fun successTest() {
            val savedUser = userRepository.save(User("A", null))
            val request = UserUpdateRequest(savedUser.id!!, "B")

            userService.updateUserName(request)

            val want = userRepository.findAll()[0]
            assertThat(want.name).isEqualTo("B")
        }
    }

    @Nested
    inner class DeleteJavaUser {
        @Test
        @DisplayName("[성공] 사용자를 삭제한다.")
        fun successTest() {
            val savedUser = userRepository.save(User("A", null))

            userService.deleteUser(savedUser.name)

            val want = userRepository.findAll()
            assertThat(want).isEmpty()
        }
    }
}