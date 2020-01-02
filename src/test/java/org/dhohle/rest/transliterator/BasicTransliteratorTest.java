package org.dhohle.rest.transliterator;

import org.dhohle.rest.transliterator.code.Statics;
import org.dhohle.rest.transliterator.code.Wylie;
import org.junit.jupiter.api.Test;

public class BasicTransliteratorTest {

//	@Test
	public void basicTest() {
		// printAllAvailableTransliterations();
		final String text = "أَهْلًا";
		final String transliteration = Statics.transliterateToLatin(text);
		System.out.println(text + "->" + transliteration);
		System.out.println(transliteration + "->" + Statics.transliterateFromTo("Latin", "ar", transliteration));
		// final String backToChinese = transliterateToChinese(transliteration);
		// System.out.println(transliteration + "->" + backToChinese);
	}

//	@Test
	public void basicTibetanWylieTest() {
		final String tibetan = "ཁྱེད་རང་";
		final String wylie = new Wylie().toWylie(tibetan);
		System.out.println(tibetan + "->" + wylie);
	}

	
	@Test
	public void loadAllAvailableTransliterations() {
		Statics.loadAllAvailableTransliterations();
		
	}
}
