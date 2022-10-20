package sample;

import static org.junit.Assert.*;

import java.util.regex.Pattern;

import org.junit.Test;

public class BMITest {

	@Test
	public void testCorrectOperation() {
		assertEquals(24.69, BMI.calculateBMI(180.0, 80.0) , 0.0);
		assertEquals(17.96, BMI.calculateBMI(175.0, 55.0) , 0.0);
		assertEquals(29.12, BMI.calculateBMI(155.7, 70.6) , 0.0);
		
		assertEquals("正常値です。", BMI.getMessage(24.69));
		assertEquals("やせ気味です。", BMI.getMessage(17.96));
		assertEquals("太り気味です。", BMI.getMessage(29.12));
		
		assertEquals(180.0, BMI.removeUnit("180.0", Pattern.compile("^[0-9]+(.[0-9]+)?(cm)$"), "cm"), 0.0);
		assertEquals(55.5, BMI.removeUnit("55.5", Pattern.compile("^[0-9]+(.[0-9]+)?(kg)$"), "kg"), 0.0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testExpect() {
		BMI.removeUnit("aaaa", Pattern.compile("^[0-9]+(.[0-9]+)?(cm)"), "cm");
		BMI.removeUnit("test", Pattern.compile("^[0-9]+(.[0-9]+)?(kg)"), "kg");
		BMI.removeUnit("1.1.1", Pattern.compile("^[0-9]+(.[0-9]+)?(cm)"), "cm");
		BMI.removeUnit(".", Pattern.compile("^[0-9]+(.[0-9]+)?(kg)"), "kg");
		BMI.removeUnit("11..", Pattern.compile("^[0-9]+(.[0-9]+)?(cm)"), "cm");
		BMI.removeUnit("11.", Pattern.compile("^[0-9]+(.[0-9]+)?(kg)"), "kg");
		BMI.removeUnit("", Pattern.compile("^[0-9]+(.[0-9]+)?(cm)"), "cm");
		BMI.removeUnit("180.0aaa", Pattern.compile("^[0-9]+(.[0-9]+)?(cm)"), "cm");
		BMI.removeUnit("66bbb", Pattern.compile("^[0-9]+(.[0-9]+)?(kg)"), "kg");
	}
	
	@Test
	public void testAddUnitOperation() {
		assertEquals(180.0, BMI.removeUnit("180.0cm", Pattern.compile("^[0-9]+(.[0-9]+)?(cm)"), "cm"), 0.0);
		assertEquals(180.0, BMI.removeUnit("180cm", Pattern.compile("^[0-9]+(.[0-9]+)?(cm)"), "cm"), 0.0);
		assertEquals(65.0, BMI.removeUnit("65kg", Pattern.compile("^[0-9]+(.[0-9]+)?(kg)"), "kg"), 0.0);
		assertEquals(65.5, BMI.removeUnit("65.5kg", Pattern.compile("^[0-9]+(.[0-9]+)?(kg)"), "kg"), 0.0);
		assertEquals(180.0, BMI.removeUnit("180.0cma", Pattern.compile("^[0-9]+(.[0-9]+)?(cm)"), "cm"), 0.0);
		assertEquals(66.0, BMI.removeUnit("66kga", Pattern.compile("^[0-9]+(.[0-9]+)?(kg)"), "kg"), 0.0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void errorOperation() {
		BMI.removeUnit("180.0cma", Pattern.compile("^[0-9]+(.[0-9]+)?(cm)"), "cm");
		BMI.removeUnit("66.0kgc", Pattern.compile("^[0-9]+(.[0-9]+)?(kg)"), "kg");
	}

}
