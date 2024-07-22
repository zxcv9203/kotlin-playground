package com.group.libraryapp.service.book

import com.group.libraryapp.domain.book.Book
import com.group.libraryapp.domain.book.BookRepository
import com.group.libraryapp.domain.book.BookType
import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistoryRepository
import com.group.libraryapp.domain.user.loanhistory.UserLoanStatus
import com.group.libraryapp.dto.book.request.BookLoanRequest
import com.group.libraryapp.dto.book.request.BookRequest
import com.group.libraryapp.dto.book.request.BookReturnRequest
import com.group.libraryapp.dto.book.response.BookStatResponse
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BookServiceTest @Autowired constructor(
    private val bookRepository: BookRepository,
    private val bookService: BookService,
    private val userRepository: UserRepository,
    private val userLoanHistoryRepository: UserLoanHistoryRepository,
) {

    @AfterEach
    fun tearDown() {
        bookRepository.deleteAll()
        userRepository.deleteAll()
    }

    @Nested
    inner class SaveBook {

        @Test
        @DisplayName("[성공] 책 생성에 성공한다.")
        fun successTest() {
            val request = BookRequest("테스트", BookType.COMPUTER)

            bookService.saveBook(request)

            val want = bookRepository.findAll()
            assertThat(want).hasSize(1)
            assertThat(want[0].name).isEqualTo("테스트")
            assertThat(want[0].type).isEqualTo(BookType.COMPUTER)
        }
    }

    @Nested
    inner class LoanBook {


        @Test
        @DisplayName("[성공] 책 대출에 성공한다.")
        fun successTest() {
            val savedBook = bookRepository.save(Book.fixture())
            val savedUser = userRepository.save(
                User(
                    "아무개",
                    null
                )
            )
            val request = BookLoanRequest("아무개", "테스트")

            bookService.loanBook(request)

            val want = userLoanHistoryRepository.findAll()
            assertThat(want).hasSize(1)
            assertThat(want[0].bookName).isEqualTo(savedBook.name)
            assertThat(want[0].user.id).isEqualTo(savedUser.id)
            assertThat(want[0].status).isEqualTo(UserLoanStatus.LOANED)
        }

        @Test
        @DisplayName("[실패] 책이 대출되어 있다면, 신규 대출이 실패합니다.")
        fun alreadyLoanTest() {
            val savedBook = bookRepository.save(Book.fixture())
            val savedUser = userRepository.save(
                User(
                    "아무개",
                    null
                )
            )
            userLoanHistoryRepository.save(
                UserLoanHistory.fixture(
                    savedUser,
                    savedBook.name,
                )
            )
            val request = BookLoanRequest("아무개", "테스트")

            assertThatThrownBy { bookService.loanBook(request) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("진작 대출되어 있는 책입니다")
        }

    }

    @Nested
    inner class ReturnBook {
        @Test
        @DisplayName("[성공] 책 반납이 성공한다.")
        fun returnBookTest() {
            val savedBook = bookRepository.save(Book.fixture())
            val savedUser = userRepository.save(
                User(
                    "아무개",
                    null
                )
            )
            userLoanHistoryRepository.save(
                UserLoanHistory.fixture(
                    savedUser,
                    savedBook.name,
                    UserLoanStatus.RETURNED
                )
            )
            val request = BookReturnRequest("아무개", "테스트")

            bookService.returnBook(request)

            val want = userLoanHistoryRepository.findAll()
            assertThat(want).hasSize(1)
            assertThat(want[0].status).isEqualTo(UserLoanStatus.RETURNED)
        }
    }

    @Nested
    inner class CountLoanedBook {
        @Test
        @DisplayName("[성공] 책 대여 권수를 정상 확인한다.")
        fun successTest() {
            val savedUser = userRepository.save(
                User(
                    "아무개",
                    null
                )
            )
            userLoanHistoryRepository.saveAll(
                listOf(
                    UserLoanHistory.fixture(savedUser, "A", UserLoanStatus.LOANED),
                    UserLoanHistory.fixture(savedUser, "B", UserLoanStatus.RETURNED),
                    UserLoanHistory.fixture(savedUser, "C", UserLoanStatus.RETURNED),
                )
            )

            val got = bookService.countLoanedBook()

            assertThat(got).isEqualTo(1)
        }
    }

    @Nested
    inner class BookStatistics {
        @Test
        @DisplayName("[성공] 책 통계를 정상 확인한다.")
        fun successTest() {
            bookRepository.saveAll(
                listOf(
                    Book("A", BookType.COMPUTER),
                    Book("B", BookType.COMPUTER),
                    Book("C", BookType.SCIENCE),
                )
            )

            val got = bookService.getBookStatistics()

            assertThat(got).hasSize(2)
            assertCount(got, BookType.COMPUTER, 2)
            assertCount(got, BookType.SCIENCE, 1)
        }

        private fun assertCount(results: List<BookStatResponse>, type: BookType, count: Int) {
            assertThat(results.first { it.type == type }.count).isEqualTo(count)

        }
    }
}