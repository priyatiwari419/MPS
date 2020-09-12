package mps.members.repository;

import mps.members.entity.ExpenditureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Optional;

public interface ExpenditureRepository extends JpaRepository<ExpenditureEntity,Long> {

    @Transactional
    @Modifying
    @Query("update ExpenditureEntity entity set entity.spendingAmount = :spendingLimit, entity.isSpendingAmountExceeds = :spendingExceedFlag where entity.memberEntity.id = :memberId")
    void updateSpendingAmountAndIsSpendingAmountExceedsByMemberId(@Param("spendingLimit") BigInteger spendingLimit,@Param("spendingExceedFlag") boolean spendingExceedFlag,@Param("memberId") Long memberId);

    @Transactional
    @Query("select expenditure from ExpenditureEntity expenditure where expenditure.memberEntity.id = :memberId and expenditure.memberRole = :memberRole")
    Optional<ExpenditureEntity> findByMemberIdAndMemberRole(@Param("memberId") Long memberId,@Param("memberRole") String memberRole);
}
