package org.dhohle.rest.transliterator.controller;

import java.util.List;

import org.dhohle.rest.transliterator.code.Statics;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1")
@Api(value = "Transliterate")
public class TransliteratorController {

	@ApiOperation(value = "Get a list of accepted scripts", response = List.class)
	@GetMapping("/isonline")
	public ResponseEntity<String> getIsOnline() {
		return ResponseEntity.ok().body("online");
	}
	
	@ApiOperation(value = "Get a list of accepted scripts", response = List.class)
	@GetMapping("/scripts")
	public ResponseEntity<List<String>> getScriptsLanguages() {
		final List<String> rs = Statics.loadAllAvailableTransliterations();
		return ResponseEntity.ok().body(rs);
	}

	@ApiOperation(value = "Transliterate your text in the Latin script", response = String.class)
	@GetMapping("/transliterate/{text}")
	public ResponseEntity<String> transliterateToLatin(final @PathVariable(value = "text") String text) {
		final String lat = Statics.transliterateToLatin(text);
		return ResponseEntity.ok().body(lat);
	}
	
	@ApiOperation(value = "Transliterate your text in the required script", response = String.class)
	@GetMapping("/transliterate/{toScript}/{text}")
	public ResponseEntity<String> transliterateToLatin(final @PathVariable(value = "toScript") String toScript,
			final @PathVariable(value = "text") String text) {
		final String res = Statics.transliterateTo(toScript, text);
		return ResponseEntity.ok().body(res);
	}

	@ApiOperation(value = "Transliterate your Tibetan text in the Latin script", response = String.class)
	@GetMapping("/transliterate/from-tibetan/{text}")
	public ResponseEntity<String> transliterateTibetanToLatin(final @PathVariable(value = "text") String text) {
		final String res = Statics.transliterateTibetanToLatin(text);
		return ResponseEntity.ok().body(res);
	}

	@ApiOperation(value = "Transliterate your Latin scripted text in the Tibetan script", response = String.class)
	@GetMapping("/transliterate/to-tibetan/{text}")
	public ResponseEntity<String> transliterateLatinToTibetan(final @PathVariable(value = "text") String text) {
		final String res = Statics.transliterateLatinToTibetan(text);
		return ResponseEntity.ok().body(res);
	}

}
