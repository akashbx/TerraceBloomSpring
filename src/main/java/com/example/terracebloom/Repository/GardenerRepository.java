package com.example.terracebloom.Repository;

import com.example.terracebloom.Entity.Gardener;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GardenerRepository extends JpaRepository<Gardener, Integer> {
}
