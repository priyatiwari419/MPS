package mps.members.repository;

import mps.members.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity,Long> {

    @Transactional
    @Query("select entity from MemberEntity entity where entity.userName = :userName")
    Optional<MemberEntity> findByUserName(@Param("userName") String userName);
}
