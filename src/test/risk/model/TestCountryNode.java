package test.risk.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import risk.model.map.CountryNode;

public class TestCountryNode {
	CountryNode in;
	ArrayList<CountryNode> list;
	/**
	 * Set up dummy data required for test
	 */
	@Before
	public void setUp(){
		in = new CountryNode("India", null, null, null);
		list = new ArrayList<CountryNode>();
		list.add(new CountryNode("Canada", null, null, null));
		list.add(in);
		list.add(new CountryNode("Pakistan", null, null, null));
		list.add(new CountryNode("Ireland", null, null, null));
		list.add(new CountryNode("Russia", null, null, null));
	}

	/**
	 * test the getCountries method for retrieving a CountryNode from a list of CountryNode by the name of country.
	 */
	@Test
	public void testGetCountries() {
		CountryNode actual = CountryNode.getCountry(list, "India");
		assertEquals(in.getCountryName(),actual.getCountryName());
	}

}
