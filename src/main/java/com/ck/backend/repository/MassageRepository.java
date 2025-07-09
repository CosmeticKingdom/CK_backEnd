package com.ck.backend.repository;

import com.ck.backend.entity.Massage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MassageRepository extends JpaRepository<Massage, Long> {
}
