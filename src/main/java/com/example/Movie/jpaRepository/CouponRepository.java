package com.example.Movie.jpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Movie.EntitaDB.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {

}
