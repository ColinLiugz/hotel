package com.example.hotel.repository;

import com.example.hotel.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders,Integer>{

    @Query("select a from Orders a where a.userId=?1 and a.isDeleted=0 order by a.keyId desc ")
    List<Orders> listMyReserved(Integer userKeyId);

    @Query(value = "select a.* from orders a JOIN housing b where a.housing_id = b.key_id and b.user_id=?1 order by a.key_id desc ",nativeQuery = true)
    List<Orders> listMyPublished(Integer userKeyId);

}
