package com.players.server.app.PlayerServerApp.repositary;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.players.server.app.PlayerServerApp.entity.Player;



@Repository
public interface PlayerRepositary extends JpaRepository<Player, Integer> {

	Player findByRanking(int ranking);
    
	
}
