package com.scarawooo.servlet;

import com.scarawooo.util.Correction;
import com.scarawooo.util.MongoHandler;
import com.scarawooo.util.Word;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CorrectorTester {
    public static void main(String[] args) {
        StringBuilder inputStr = new StringBuilder("");
        Pattern pattern = Pattern.compile("[^[a-zA-Zа-яА-Я/\\-]]++");
        JSONArray spellchecking = new JSONArray();
        long id = 0;
        for (Matcher matcher = pattern.matcher(inputStr);; matcher = pattern.matcher(inputStr)) {
            JSONObject spellcheckingWordDescription;
            if (matcher.find()) {
                if (matcher.start() != 0) {
                    spellcheckingWordDescription = new JSONObject();
                    String word = inputStr.substring(0, matcher.start());
                    if (MongoHandler.search(word) ||
                            MongoHandler.search(word.toLowerCase())) {
                        spellcheckingWordDescription.put("id", id++);
                        spellcheckingWordDescription.put("word", word);
                        spellcheckingWordDescription.put("incorrect", false);

                    } else if (inputStr.charAt(matcher.start()) == '.' && MongoHandler.search(word.toLowerCase().concat("."))) {
                        spellcheckingWordDescription.put("id", id++);
                        spellcheckingWordDescription.put("word", word);
                        spellcheckingWordDescription.put("incorrect", false);
                    } else {
                        Word wordDescription = Word.getWord(word);
                        wordDescription.findNearestCorrections();
                        spellcheckingWordDescription.put("id", id++);
                        spellcheckingWordDescription.put("word", wordDescription.getWord());
                        spellcheckingWordDescription.put("incorrect", !wordDescription.getCorrections().isEmpty());
                        spellcheckingWordDescription.put("corrections",
                                new JSONArray(wordDescription.getCorrections().stream().
                                        map(Correction::getWord).collect(Collectors.toList()))
                        );
                    }
                    spellchecking.put(spellcheckingWordDescription);
                }
                spellcheckingWordDescription = new JSONObject();
                spellcheckingWordDescription.put("id", id++);
                spellcheckingWordDescription.put("word", inputStr.substring(matcher.start(), matcher.end()));
                spellcheckingWordDescription.put("incorrect", false);
                spellchecking.put(spellcheckingWordDescription);
                inputStr.delete(0, matcher.end());
            } else {
                spellcheckingWordDescription = new JSONObject();
                String word = inputStr.toString();
                if (MongoHandler.search(word) || MongoHandler.search(word.toLowerCase())) {
                    spellcheckingWordDescription.put("id", id);
                    spellcheckingWordDescription.put("word", word);
                    spellcheckingWordDescription.put("incorrect", false);
                } else {
                    Word wordDescription = Word.getWord(word);
                    wordDescription.findNearestCorrections();
                    spellcheckingWordDescription.put("id", id);
                    spellcheckingWordDescription.put("word", wordDescription.getWord());
                    spellcheckingWordDescription.put("incorrect", !wordDescription.getCorrections().isEmpty());
                    spellcheckingWordDescription.put("corrections",
                            new JSONArray(wordDescription.getCorrections().stream().
                                    map(Correction::getWord).collect(Collectors.toList()))
                    );
                }
                spellchecking.put(spellcheckingWordDescription);
                break;
            }
        }
        System.out.print(spellchecking.toString());
    }
}