package com.example.hotel.repository;

import com.example.hotel.entity.Housing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface HousingRepository extends JpaRepository<Housing,Integer>{

    @Query("select a from Housing a where a.keyId=?1 and a.isDeleted=0")
    Housing getOneByKeyId(Integer keyId);

    @Query("select a from Housing a where a.isDeleted=0 order by a.keyId desc ")
    List<Housing> listAll();

    @Query("select a from Housing a where a.isDeleted = 0 and a.availableStartTime <= ?1 and a.availableEndTime >=?2 order by a.keyId desc")
    List<Housing> listByTime(Date liveInTime1,Date liveInTime2);

    @Query("select a from Housing a where a.city=?1 and a.isDeleted=0 order by a.keyId desc ")
    List<Housing> listByCity(String city);

    @Query("select a from Housing a where  a.isDeleted=0 and a.city=?1 and a.availableStartTime <= ?2 and a.availableEndTime >=?3 order by a.keyId desc ")
    List<Housing> listByCityAndTime(String city,Date liveInTime1,Date liveInTime2);

    @Query("select a from Housing a where a.userId=?1 and a.isDeleted=0 order by a.keyId desc ")
    List<Housing> listMyPublished(Integer userKeyId);
}
