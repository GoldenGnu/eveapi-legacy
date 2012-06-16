package com.beimin.eveapi.character.standings;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Collection;

import org.junit.Test;
import org.xml.sax.SAXException;

import com.beimin.eveapi.shared.standings.StandingsList;

public class StandingsParserTest {

	@Test
	public void standingsParser() throws IOException, SAXException, ParseException {
		StandingsParser parser = StandingsParser.getInstance();
		InputStream input = StandingsParserTest.class.getResourceAsStream("/character/Standings.xml");
		StandingsResponse response = parser.getResponse(input);
		assertNotNull(response);
		
		Collection<StandingsList> characterStandingsTo = response.getStandingsTo();
		assertNotNull(characterStandingsTo);
		assertEquals(2, characterStandingsTo.size());
		for (StandingsList standingsList : characterStandingsTo) {
			assertNotNull(standingsList);
			String name = standingsList.getName();
			if(name.equals("characters")) {
				assertEquals(0, standingsList.size());
			} else if(name.equals("corporations")) {
				assertEquals(2, standingsList.size());
			}
		}
		
		Collection<StandingsList> characterStandingsFrom = response.getStandingsFrom();
		assertNotNull(characterStandingsFrom);
		assertEquals(3, characterStandingsFrom.size());
		for (StandingsList standingsList : characterStandingsFrom) {
			assertNotNull(standingsList);
			String name = standingsList.getName();
			if(name.equals("agents")) {
				assertEquals(2, standingsList.size());
			} else if(name.equals("NPCCorporations")) {
				assertEquals(3, standingsList.size());
			} else if(name.equals("factions")) {
				assertEquals(16, standingsList.size());
			} 
		}
	}
}