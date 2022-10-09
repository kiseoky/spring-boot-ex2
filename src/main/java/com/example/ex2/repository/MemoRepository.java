package com.example.ex2.repository;


import com.example.ex2.entity.Memo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {
    List<Memo> findByIdBetweenOrderByIdDesc(Long from, Long to);
    List<Memo> findByIdBetween(Long from, Long to, Pageable pageable);
    void deleteMemoByIdLessThan(Long id);
    @Query("select m from Memo m order by m.id desc")
    List<Memo> getListDesc();
    @Transactional
    @Modifying
    @Query("update Memo m set m.text = :text where m.id = :id")
    int updateText(@Param("id") Long id, @Param("text") String text);

    @Query(value = "select m from Memo m where m.id < :id")
    Page<Memo> getListWithQuery(Long id, Pageable pageable);

    @Query(value = "select m.id, m.text, CURRENT_DATE from Memo m where m.id > :id")
    Page<Memo> getListWithQueryObject(Long id, Pageable pageable);

    @Query(value = "select * from memo where id > 0", nativeQuery = true)
    List<Object[]> getNativeResult();
}
