package com.service.book.history;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BookTransactionHistoryRepository extends JpaRepository<BookTransactionHistory, Integer> {
    @Query("""
            SELECT
            (COUNT (*) > 0) AS isBorrowed
            FROM BookTransactionHistory bookTransactionHistory
            WHERE bookTransactionHistory.user.id = :userId
            AND bookTransactionHistory.book.id = :bookId
            AND bookTransactionHistory.returnApproved = false
            """)
    //  تتحقق مما إذا كان الكتاب المحدد قد تم استعارته بالفعل بواسطة المستخدم ولم تتم الموافقة على إرجاعه بعد.
    //  إذا كان الكتاب ما زال مستعارًا، سيتم إرجاع true، وإلا سيتم إرجاع false.
    boolean isAlreadyBorrowedByUser(@Param("bookId") Integer bookId, @Param("userId") Integer userId);

    @Query("""
            SELECT
            (COUNT (*) > 0) AS isBorrowed
            FROM BookTransactionHistory bookTransactionHistory
            WHERE bookTransactionHistory.book.id = :bookId
            AND bookTransactionHistory.returnApproved = false
            """)
    // هذا الاستعلام يتحقق من ما إذا كان الكتاب ما زال مستعارًا ولم تتم الموافقة على إرجاعه
    // ، وإذا كان كذلك يتم إرجاع true، وإلا سيتم إرجاع false.
    boolean isAlreadyBorrowed(@Param("bookId") Integer bookId);

    @Query("""
            SELECT transaction
            FROM BookTransactionHistory  transaction
            WHERE transaction.user.id = :userId
            AND transaction.book.id = :bookId
            AND transaction.returned = false
            AND transaction.returnApproved = false
            """)
    Optional<BookTransactionHistory> findByBookIdAndUserId(@Param("bookId") Integer bookId, @Param("userId") Integer userId);

    @Query("""
            SELECT transaction
            FROM BookTransactionHistory  transaction
            WHERE transaction.book.owner.id = :userId
            AND transaction.book.id = :bookId
            AND transaction.returned = true
            AND transaction.returnApproved = false
            """)
    Optional<BookTransactionHistory> findByBookIdAndOwnerId(@Param("bookId") Integer bookId, @Param("userId") Integer userId);

    @Query("""
            SELECT history
            FROM BookTransactionHistory history
            WHERE history.user.id = :userId
            """)
// هذا الاستعلام يبحث عن جميع المعاملات أو السجلات التي قام فيها المستخدم (userId)
// باستعارة كتب، أي أن المستخدم هو الشخص الذي قام بالاقتراض.
    Page<BookTransactionHistory> findAllBorrowedBooks(Pageable pageable, Integer userId);


@Query("""
            SELECT history
            FROM BookTransactionHistory history
            WHERE history.book.owner.id = :userId
            """)

// هذا الاستعلام يبحث عن جميع المعاملات أو السجلات التي يكون فيها المستخدم (userId) هو مالك الكتاب
// أي أن المستخدم هو الشخص الذي يملك الكتاب الذي تم إرجاعه.

// بشوف حاله الكتب بتعتي حد استعرها ولا لا
// اللي استعار كتاب رجعه ولا لا
    Page<BookTransactionHistory> findAllReturnedBooks(Pageable pageable, Integer userId);
}
