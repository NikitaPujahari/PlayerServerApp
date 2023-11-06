package com.players.server.app.PlayerServerApp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Table(name="customer_info")
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int ranking;
	private long sessionId;
	private int tournamentId;
	private int tournamentRegionId;
	private String tournamentRegionCode;
	private String regionCode;
	private String tournamentName;
	private String tournamentShortName;
	private String firstName;
	private String lastName;
	private int playerId;
	private boolean isActive;
	private boolean isOpta;
	private int teamId;
	private String teamName;
	private String playedPositions;
	private int age;
	private int height;
	private int weight;
	private String positionText;
	private int apps;
	private int subOn;
	private int minsPlayed;
	private float rating;
	private int goal;
	private int assistTotal;
	private int yellowCard;
	private int redCard;
	private float shotsPerGame;
	private float aerialWonPerGame;
	private int manOfTheMatch;
	private String name;
	@Column(name = "isManOFTheMatch")
	private boolean mmanOfTheMatch;
	private String playedPositionsShort;
	private float passSuccess;
}
