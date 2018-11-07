package com.alexperso342.catmash;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alexperso342.catmash.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer> {

	List<Vote> findByCatId(String catId);
}