package com.groupc.weather.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.groupc.weather.entity.HashtagEntity;
@Repository
public interface HashtagRepository extends JpaRepository<HashtagEntity, Integer>{

    public HashtagEntity findByHashtagNumber(Integer hashtagNumber);
    
}