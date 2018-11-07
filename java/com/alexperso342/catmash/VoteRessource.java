package com.alexperso342.catmash;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class VoteRessource {

	@Autowired
	private VoteRepository VoteRepository;

	@GetMapping("/vote")
	public List<Vote> retrieveAllVotes() {
		return VoteRepository.findAll();
	}

	@PostMapping("/vote")
	public ResponseEntity<Object> createVote(@RequestBody Vote vote) {
		Vote savedVote = VoteRepository.save(vote);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedVote.getId()).toUri();

		return ResponseEntity.created(location).build();

	}
}
