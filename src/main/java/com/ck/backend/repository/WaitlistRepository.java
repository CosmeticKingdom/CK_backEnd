package com.ck.backend.repository;

import com.ck.backend.entity.Waitlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaitlistRepository extends JpaRepository<Waitlist, Long> {
}
