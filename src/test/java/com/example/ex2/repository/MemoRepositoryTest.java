package com.example.ex2.repository;

import com.example.ex2.entity.Memo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemoRepositoryTest {
    @Autowired
    MemoRepository memoRepository;

    @Test
    public void testClassValid(){
        System.out.println(memoRepository.getClass().getName());
    }

    @Test
    public void insertDummies(){
        IntStream.rangeClosed(1, 100).forEach(i->{
            Memo memo = Memo.builder().text("test " + i).build();
            memoRepository.save(memo);
        });
    }

    @Test
    public void selectFindById(){
        Long id = 100L;
        Optional<Memo> result = memoRepository.findById(id);
        System.out.println("================");

        if (result.isPresent()) {
            Memo memo = result.get();
            System.out.println("memo = " + memo);
        }
    }

    @Transactional
    @Test
    public void selectGetOne(){
        Long id = 100L;
        Memo memo = memoRepository.getReferenceById(id);
        System.out.println("================");


        System.out.println("memo = " + memo);

    }

    @Test
    public void update(){
        Memo memo = Memo.builder().id(100L).text("update text").build();
        System.out.println(memoRepository.save(memo));
    }

    @Test
    public void delete(){
        Long id = 3000L;
        memoRepository.deleteById(id);
    }

    @Test
    public void pageDefault(){
        Pageable pageable = PageRequest.of(0, 10);
        Page<Memo> result = memoRepository.findAll(pageable);
        System.out.println("result = " + result);
        System.out.println("result.getTotalPages() = " + result.getTotalPages());
        System.out.println("result = " + result.getTotalElements());
        System.out.println("result = " + result.getNumber());
        System.out.println("result = " + result.getSize());
        System.out.println("result = " + result.hasNext());
        System.out.println("result = " + result.isFirst());
    }

    @Test
    public void sortPage(){
        Sort sortByIdDesc = Sort.by("id").descending();
        Sort sortByText = Sort.by("text");

        Pageable pageable = PageRequest.of(0, 10, sortByIdDesc.and(sortByText));
        Page<Memo> result = memoRepository.findAll(pageable);

        result.get().forEach(memo ->{
            System.out.println("memo = " + memo);
        });

    }
    
    @Test
    public void queryMethod(){
        List<Memo> list = memoRepository.findByIdBetweenOrderByIdDesc(70L, 80L);
        for (Memo memo : list) {
            System.out.println("memo = " + memo);
        }
    }
    @Test
    public void queryMethodWithPageable(){
        Sort sortByIdDesc = Sort.by("id").descending();
        Pageable pageable = PageRequest.of(0, 10, sortByIdDesc);
        List<Memo> list = memoRepository.findByIdBetween(70L, 80L, pageable);
        for (Memo memo : list) {
            System.out.println("memo = " + memo);
        }
    }
}