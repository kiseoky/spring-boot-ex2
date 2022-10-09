package com.example.ex2.repository;


import com.example.ex2.entity.Memo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {
    List<Memo> findByIdBetweenOrderByIdDesc(Long from, Long to);
    List<Memo> findByIdBetween(Long from, Long to, Pageable pageable);
}
