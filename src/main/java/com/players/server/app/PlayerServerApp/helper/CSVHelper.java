package com.players.server.app.PlayerServerApp.helper;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.springframework.web.multipart.MultipartFile;

import com.players.server.app.PlayerServerApp.entity.Player;

public class CSVHelper {
	public static String TYPE = "text/csv";
	static String[] HEADERs = { "ranking", "seasonId", "tournamentId", "tournamentRegionId", "tournamentRegionCode",
			"regionCode", "tournamentName", "tournamentShortName", "firstName", "lastName", "playerId", "isActive",
			"isOpta", "teamId", "teamName", "playedPositions", "age", "height", "weight", "positionText", "apps",
			"subOn", "minsPlayed", "rating", "goal", "assistTotal", "yellowCard", "redCard", "shotsPerGame",
			"aerialWonPerGame", "manOfTheMatch", "name", "isManOfTheMatch", "playedPositionsShort", "passSuccess" };

	public static boolean hasCSVFormat(MultipartFile file) {

		if (!TYPE.equals(file.getContentType())) {
			return false;
		}

		return true;
	}

	public static List<Player> csvToTutorials(InputStream is) {
		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT);) {

			List<Player> players = new ArrayList<Player>();

			Iterable<CSVRecord> csvRecords = csvParser.getRecords();

			for (CSVRecord csvRecord : csvRecords) {
				Player player = new Player();

				player.setRanking(Integer.parseInt(csvRecord.values()[0]));
				player.setSessionId(Integer.parseInt(csvRecord.values()[1]));
				player.setTournamentId(Integer.parseInt(csvRecord.values()[2]));
				player.setTournamentRegionId(Integer.parseInt(csvRecord.values()[3]));
				player.setTournamentRegionCode(csvRecord.values()[4]);
				player.setRegionCode(csvRecord.values()[5]);
				player.setTournamentName(csvRecord.values()[6]);
				player.setTournamentShortName(csvRecord.values()[7]);
				player.setFirstName(csvRecord.values()[8]);
				player.setLastName(csvRecord.values()[9]);
				player.setPlayerId(Integer.parseInt(csvRecord.values()[10]));
				player.setActive(Boolean.parseBoolean(csvRecord.values()[11]));
				player.setOpta(Boolean.parseBoolean(csvRecord.values()[12]));
				player.setTeamId(Integer.parseInt(csvRecord.values()[13]));
				player.setAge(Integer.parseInt(csvRecord.values()[16]));
				player.setTeamName(csvRecord.values()[14]);
				player.setPlayedPositions(csvRecord.values()[15]);
				player.setAge(Integer.parseInt(csvRecord.values()[16]));
				player.setHeight(Integer.parseInt(csvRecord.values()[17]));
				player.setWeight(Integer.parseInt(csvRecord.values()[18]));
				player.setPositionText(csvRecord.values()[19]);
				player.setApps(Integer.parseInt(csvRecord.values()[20]));
				player.setSubOn(Integer.parseInt(csvRecord.values()[21]));
				player.setMinsPlayed(Integer.parseInt(csvRecord.values()[22]));
				player.setRating(Float.parseFloat(csvRecord.values()[23]));
				player.setGoal(Integer.parseInt(csvRecord.values()[24]));
				player.setAssistTotal(Integer.parseInt(csvRecord.values()[25]));
				player.setYellowCard(Integer.parseInt(csvRecord.values()[26]));
				player.setRedCard(Integer.parseInt(csvRecord.values()[27]));
				player.setShotsPerGame(Float.parseFloat(csvRecord.values()[28]));
				player.setAerialWonPerGame(Float.parseFloat(csvRecord.values()[29]));
				player.setManOfTheMatch(Integer.parseInt(csvRecord.values()[30]));
				player.setName(csvRecord.values()[31]);
				player.setMmanOfTheMatch(Boolean.parseBoolean(csvRecord.values()[32]));
				player.setPlayedPositionsShort(csvRecord.values()[33]);
				player.setPassSuccess(Float.parseFloat(csvRecord.values()[34]));

				players.add(player);
			}

			return players;
		} catch (IOException e) {
			throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		}
	}

	public static ByteArrayInputStream playersToCSV(List<Player> players) {
		final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

		try (ByteArrayOutputStream out = new ByteArrayOutputStream();
				CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
			for (Player player : players) {
				List<String> data = Arrays.asList(String.valueOf(player.getRanking()),
						String.valueOf(player.getSessionId()), String.valueOf(player.getTournamentId()),
						String.valueOf(player.getTournamentRegionId()), player.getTournamentRegionCode(),
						player.getRegionCode(), player.getTournamentName(), player.getTournamentShortName(),
						player.getFirstName(), player.getLastName(), String.valueOf(player.getPlayerId()),
						String.valueOf(player.isActive()), String.valueOf(player.isOpta()),
						String.valueOf(player.getTeamId()), player.getTeamName(), player.getPlayedPositions(),
						String.valueOf(player.getAge()), String.valueOf(player.getHeight()),
						String.valueOf(player.getWeight()), player.getPositionText(), String.valueOf(player.getApps()),
						String.valueOf(player.getSubOn()), String.valueOf(player.getMinsPlayed()),
						String.valueOf(player.getRating()), String.valueOf(player.getGoal()),
						String.valueOf(player.getAssistTotal()), String.valueOf(player.getYellowCard()),
						String.valueOf(player.getRedCard()), String.valueOf(player.getShotsPerGame()),
						String.valueOf(player.getAerialWonPerGame()), String.valueOf(player.getManOfTheMatch()),
						player.getName(), String.valueOf(player.isMmanOfTheMatch()), player.getPlayedPositionsShort(),
						String.valueOf(player.getPassSuccess()));

				csvPrinter.printRecord(data);
			}

			csvPrinter.flush();
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
		}
	}

}
