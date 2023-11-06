package com.players.server.app.PlayerServerApp.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.players.server.app.PlayerServerApp.entity.Player;
import com.players.server.app.PlayerServerApp.exception.PlayerNotFoundException;
import com.players.server.app.PlayerServerApp.helper.CSVHelper;
import com.players.server.app.PlayerServerApp.repositary.PlayerRepositary;

@Service
public class CSVService {
	@Autowired
	PlayerRepositary repository;

	public void save(MultipartFile file) {
		try {
			List<Player> players = com.players.server.app.PlayerServerApp.helper.CSVHelper
					.csvToTutorials(file.getInputStream());
			repository.saveAll(players);
		} catch (IOException e) {
			throw new RuntimeException("fail to store csv data: " + e.getMessage());
		}
	}

	public ByteArrayInputStream load() {
		List<Player> players = repository.findAll();

		ByteArrayInputStream in = CSVHelper.playersToCSV(players);
		return in;
	}

	public List<Player> getAllPlayers() {
		return repository.findAll();
	}

	public Player getPlayerById(int ranking) throws PlayerNotFoundException {
		Player player = repository.findByRanking(ranking);
		if(player != null) {
			return player;
		}else {
			throw new PlayerNotFoundException("Player not found with the id : " +ranking);
		}
	}

}
