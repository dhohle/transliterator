package org.dhohle.rest.transliterator.code;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import com.ibm.icu.text.Transliterator;

public class Statics {
	
	private final static Wylie WYLIE = new Wylie();

	public void doNothing() {
		// done
	}
	
	public static String transliterateTibetanToLatin(final String text) {
		return WYLIE.toWylie(text);
	}
	
	public static String transliterateLatinToTibetan(final String text) {
		return WYLIE.fromWylie(text);
	}
	

	public static List<String> loadAllAvailableTransliterations() {
		final List<String> list = new ArrayList<>();
		final Enumeration<String> enumeration = Transliterator.getAvailableIDs();
		while (enumeration.hasMoreElements()) {
			final String string = enumeration.nextElement();
			if (string.startsWith("Any-"))
//				System.out.println(string);
				list.add(string.replace("Any-", ""));
//			System.out.println(string);
		}
		return list;
	}

	public static String transliterateToLatin(final String text) {
		return Transliterator.getInstance("Any-Latin").transliterate(text);
	}

	public static String transliterateTo(final String toLanguageCode, final String text) {
		return Transliterator.getInstance("Any-" + toLanguageCode).transliterate(text);
	}

	public static String transliterateFromTo(final String from, final String to, final String text) {
		return Transliterator.getInstance(from + "-" + to).transliterate(text);
	}

	public static String transliterateToChinese(final String text) {
		return Transliterator.getInstance("Any-zh").transliterate(text);
	}
}
