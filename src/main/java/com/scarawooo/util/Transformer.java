package com.scarawooo.util;

import javafx.util.Pair;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Transformer {
    private static HashMap<Character, Character> simplifiedPhoneticClasses;
    private static HashMap<Character, ArrayList<Character>> phoneticClasses;
    private static ArrayList<Character> unknownCharacter;
    private static ArrayList<String> nonRequiredReplacements;
    private static ArrayList<Pair<String, String>> letterReplacements;

    static {
        phoneticClasses = new HashMap<>();
        phoneticClasses.put('а', new ArrayList<>());
        phoneticClasses.put('f', new ArrayList<>());
        phoneticClasses.get('а').add('A');
        phoneticClasses.get('а').add('O');
        phoneticClasses.get('f').add('A');
        phoneticClasses.get('f').add('O');
        phoneticClasses.put('о', new ArrayList<>());
        phoneticClasses.put('j', new ArrayList<>());
        phoneticClasses.get('о').add('O');
        phoneticClasses.get('j').add('O');
        phoneticClasses.put('я', new ArrayList<>());
        phoneticClasses.put('z', new ArrayList<>());
        phoneticClasses.get('я').add('A');
        phoneticClasses.get('я').add('E');
        phoneticClasses.get('z').add('A');
        phoneticClasses.get('z').add('E');
        phoneticClasses.put('е', new ArrayList<>());
        phoneticClasses.put('t', new ArrayList<>());
        phoneticClasses.get('е').add('E');
        phoneticClasses.get('t').add('E');
        phoneticClasses.put('и', new ArrayList<>());
        phoneticClasses.put('b', new ArrayList<>());
        phoneticClasses.get('и').add('E');
        phoneticClasses.get('и').add('I');
        phoneticClasses.get('b').add('E');
        phoneticClasses.get('b').add('I');
        phoneticClasses.put('ы', new ArrayList<>());
        phoneticClasses.put('s', new ArrayList<>());
        phoneticClasses.get('ы').add('I');
        phoneticClasses.get('s').add('I');
        phoneticClasses.put('э', new ArrayList<>());
        phoneticClasses.put('\'', new ArrayList<>());
        phoneticClasses.put('\"', new ArrayList<>());
        phoneticClasses.get('э').add('E');
        phoneticClasses.get('\'').add('E');
        phoneticClasses.get('\"').add('E');
        phoneticClasses.put('у', new ArrayList<>());
        phoneticClasses.put('e', new ArrayList<>());
        phoneticClasses.get('у').add('Y');
        phoneticClasses.get('e').add('Y');
        phoneticClasses.put('ю', new ArrayList<>());
        phoneticClasses.put('.', new ArrayList<>());
        phoneticClasses.put('>', new ArrayList<>());
        phoneticClasses.get('ю').add('Y');
        phoneticClasses.get('.').add('Y');
        phoneticClasses.get('>').add('Y');
        phoneticClasses.put('х', new ArrayList<>());
        phoneticClasses.put('[', new ArrayList<>());
        phoneticClasses.put('{', new ArrayList<>());
        phoneticClasses.get('х').add('G');
        phoneticClasses.get('[').add('G');
        phoneticClasses.get('{').add('G');
        phoneticClasses.put('к', new ArrayList<>());
        phoneticClasses.put('r', new ArrayList<>());
        phoneticClasses.get('к').add('G');
        phoneticClasses.get('r').add('G');
        phoneticClasses.put('г', new ArrayList<>());
        phoneticClasses.put('u', new ArrayList<>());
        phoneticClasses.get('г').add('G');
        phoneticClasses.get('u').add('G');
        phoneticClasses.put('ш', new ArrayList<>());
        phoneticClasses.put('i', new ArrayList<>());
        phoneticClasses.get('ш').add('W');
        phoneticClasses.get('ш').add('Z');
        phoneticClasses.get('i').add('W');
        phoneticClasses.get('i').add('Z');
        phoneticClasses.put('ж', new ArrayList<>());
        phoneticClasses.put(';', new ArrayList<>());
        phoneticClasses.put(':', new ArrayList<>());
        phoneticClasses.get('ж').add('Z');
        phoneticClasses.get(';').add('Z');
        phoneticClasses.get(':').add('Z');
        phoneticClasses.put('щ', new ArrayList<>());
        phoneticClasses.put('o', new ArrayList<>());
        phoneticClasses.get('щ').add('W');
        phoneticClasses.get('o').add('W');
        phoneticClasses.put('ч', new ArrayList<>());
        phoneticClasses.put('x', new ArrayList<>());
        phoneticClasses.get('ч').add('W');
        phoneticClasses.get('x').add('W');
        phoneticClasses.put('р', new ArrayList<>());
        phoneticClasses.put('h', new ArrayList<>());
        phoneticClasses.get('р').add('P');
        phoneticClasses.get('h').add('P');
        phoneticClasses.put('л', new ArrayList<>());
        phoneticClasses.put('k', new ArrayList<>());
        phoneticClasses.get('л').add('L');
        phoneticClasses.get('k').add('L');
        phoneticClasses.put('ц', new ArrayList<>());
        phoneticClasses.put('w', new ArrayList<>());
        phoneticClasses.get('ц').add('C');
        phoneticClasses.get('w').add('C');
        phoneticClasses.put('с', new ArrayList<>());
        phoneticClasses.put('c', new ArrayList<>());
        phoneticClasses.get('с').add('C');
        phoneticClasses.get('c').add('C');
        phoneticClasses.put('з', new ArrayList<>());
        phoneticClasses.put('p', new ArrayList<>());
        phoneticClasses.get('з').add('C');
        phoneticClasses.get('p').add('C');
        phoneticClasses.put('м', new ArrayList<>());
        phoneticClasses.put('v', new ArrayList<>());
        phoneticClasses.get('м').add('M');
        phoneticClasses.get('v').add('M');
        phoneticClasses.put('н', new ArrayList<>());
        phoneticClasses.put('y', new ArrayList<>());
        phoneticClasses.get('н').add('N');
        phoneticClasses.get('y').add('N');
        phoneticClasses.put('п', new ArrayList<>());
        phoneticClasses.put('g', new ArrayList<>());
        phoneticClasses.get('п').add('B');
        phoneticClasses.get('g').add('B');
        phoneticClasses.put('б', new ArrayList<>());
        phoneticClasses.put(',', new ArrayList<>());
        phoneticClasses.put('<', new ArrayList<>());
        phoneticClasses.get('б').add('B');
        phoneticClasses.get(',').add('B');
        phoneticClasses.get('<').add('B');
        phoneticClasses.put('ф', new ArrayList<>());
        phoneticClasses.put('a', new ArrayList<>());
        phoneticClasses.get('ф').add('V');
        phoneticClasses.get('a').add('V');
        phoneticClasses.put('в', new ArrayList<>());
        phoneticClasses.put('d', new ArrayList<>());
        phoneticClasses.get('в').add('V');
        phoneticClasses.get('d').add('V');
        phoneticClasses.put('т', new ArrayList<>());
        phoneticClasses.put('n', new ArrayList<>());
        phoneticClasses.get('т').add('D');
        phoneticClasses.get('n').add('D');
        phoneticClasses.put('д', new ArrayList<>());
        phoneticClasses.put('l', new ArrayList<>());
        phoneticClasses.get('д').add('D');
        phoneticClasses.get('l').add('D');
        phoneticClasses.put('й', new ArrayList<>());
        phoneticClasses.put('q', new ArrayList<>());
        phoneticClasses.get('й').add('I');
        phoneticClasses.get('q').add('I');
        phoneticClasses.put('ё', new ArrayList<>());
        phoneticClasses.put('`', new ArrayList<>());
        phoneticClasses.put('~', new ArrayList<>());
        phoneticClasses.get('ё').add('O');
        phoneticClasses.get('ё').add('E');
        phoneticClasses.get('`').add('O');
        phoneticClasses.get('`').add('E');
        phoneticClasses.get('~').add('O');
        phoneticClasses.get('~').add('E');
        //
        simplifiedPhoneticClasses = new HashMap<>();
        simplifiedPhoneticClasses.put('а', 'A');
        simplifiedPhoneticClasses.put('f', 'A');
        simplifiedPhoneticClasses.put('о', 'A');
        simplifiedPhoneticClasses.put('j', 'A');
        simplifiedPhoneticClasses.put('я', 'Q');
        simplifiedPhoneticClasses.put('z', 'Q');
        simplifiedPhoneticClasses.put('е', 'E');
        simplifiedPhoneticClasses.put('t', 'E');
        simplifiedPhoneticClasses.put('и', 'I');
        simplifiedPhoneticClasses.put('b', 'I');
        simplifiedPhoneticClasses.put('ы', 'I');
        simplifiedPhoneticClasses.put('s', 'I');
        simplifiedPhoneticClasses.put('э', 'E');
        simplifiedPhoneticClasses.put('\'', 'E');
        simplifiedPhoneticClasses.put('\"', 'E');
        simplifiedPhoneticClasses.put('у', 'Y');
        simplifiedPhoneticClasses.put('e', 'Y');
        simplifiedPhoneticClasses.put('ю', 'Y');
        simplifiedPhoneticClasses.put('.', 'Y');
        simplifiedPhoneticClasses.put('>', 'Y');
        simplifiedPhoneticClasses.put('х', 'G');
        simplifiedPhoneticClasses.put('[', 'G');
        simplifiedPhoneticClasses.put('{', 'G');
        simplifiedPhoneticClasses.put('к', 'G');
        simplifiedPhoneticClasses.put('r', 'G');
        simplifiedPhoneticClasses.put('г', 'G');
        simplifiedPhoneticClasses.put('u', 'G');
        simplifiedPhoneticClasses.put('ш', 'W');
        simplifiedPhoneticClasses.put('i', 'W');
        simplifiedPhoneticClasses.put('ж', 'Z');
        simplifiedPhoneticClasses.put(';', 'Z');
        simplifiedPhoneticClasses.put(':', 'Z');
        simplifiedPhoneticClasses.put('щ', 'R');
        simplifiedPhoneticClasses.put('o', 'R');
        simplifiedPhoneticClasses.put('ч', 'R');
        simplifiedPhoneticClasses.put('x', 'R');
        simplifiedPhoneticClasses.put('р', 'P');
        simplifiedPhoneticClasses.put('h', 'P');
        simplifiedPhoneticClasses.put('л', 'L');
        simplifiedPhoneticClasses.put('k', 'L');
        simplifiedPhoneticClasses.put('ц', 'C');
        simplifiedPhoneticClasses.put('w', 'C');
        simplifiedPhoneticClasses.put('с', 'C');
        simplifiedPhoneticClasses.put('c', 'C');
        simplifiedPhoneticClasses.put('з', 'C');
        simplifiedPhoneticClasses.put('p', 'C');
        simplifiedPhoneticClasses.put('м', 'M');
        simplifiedPhoneticClasses.put('v', 'M');
        simplifiedPhoneticClasses.put('н', 'N');
        simplifiedPhoneticClasses.put('y', 'N');
        simplifiedPhoneticClasses.put('п', 'B');
        simplifiedPhoneticClasses.put('g', 'B');
        simplifiedPhoneticClasses.put('б', 'B');
        simplifiedPhoneticClasses.put(',', 'B');
        simplifiedPhoneticClasses.put('<', 'B');
        simplifiedPhoneticClasses.put('ф', 'V');
        simplifiedPhoneticClasses.put('a', 'V');
        simplifiedPhoneticClasses.put('в', 'V');
        simplifiedPhoneticClasses.put('d', 'V');
        simplifiedPhoneticClasses.put('т', 'D');
        simplifiedPhoneticClasses.put('n', 'D');
        simplifiedPhoneticClasses.put('д', 'D');
        simplifiedPhoneticClasses.put('l', 'D');
        simplifiedPhoneticClasses.put('й', 'I');
        simplifiedPhoneticClasses.put('q', 'I');
        simplifiedPhoneticClasses.put('ё', 'X');
        simplifiedPhoneticClasses.put('`', 'X');
        simplifiedPhoneticClasses.put('~', 'X');
        //
        unknownCharacter = new ArrayList<>();
        unknownCharacter.add('U');
        // замены
        nonRequiredReplacements = new ArrayList<>();
        letterReplacements = new ArrayList<>();
        nonRequiredReplacements.add(".");
        nonRequiredReplacements.add(",");
        nonRequiredReplacements.add(">");
        nonRequiredReplacements.add("<");
        nonRequiredReplacements.add("[");
        nonRequiredReplacements.add("{");
        nonRequiredReplacements.add("]");
        nonRequiredReplacements.add("}");
        nonRequiredReplacements.add(":");
        nonRequiredReplacements.add(";");
        nonRequiredReplacements.add("\"");
        nonRequiredReplacements.add("'");
        nonRequiredReplacements.add("~");
        nonRequiredReplacements.add("`");
        letterReplacements.add(new Pair<>("ь", ""));
        letterReplacements.add(new Pair<>("ъ", ""));
        letterReplacements.add(new Pair<>("нтств", "нств"));
        letterReplacements.add(new Pair<>("yncnd", "ycnd"));
        letterReplacements.add(new Pair<>("стск", "ск"));
        letterReplacements.add(new Pair<>("cncr", "cr"));
        letterReplacements.add(new Pair<>("нтск", "нск"));
        letterReplacements.add(new Pair<>("yncr", "ycr"));
        letterReplacements.add(new Pair<>("вств", "ств"));
        letterReplacements.add(new Pair<>("dcnd", "cnd"));
        letterReplacements.add(new Pair<>("ндш", "нш"));
        letterReplacements.add(new Pair<>("yli", "yi"));
        letterReplacements.add(new Pair<>("ндц", "нц"));
        letterReplacements.add(new Pair<>("ylw", "yw"));
        letterReplacements.add(new Pair<>("нтг", "нг"));
        letterReplacements.add(new Pair<>("ynu", "yu"));
        letterReplacements.add(new Pair<>("здц", "зц"));
        letterReplacements.add(new Pair<>("plw", "pw"));
        letterReplacements.add(new Pair<>("здк", "зк"));
        letterReplacements.add(new Pair<>("plr", "pr"));
        letterReplacements.add(new Pair<>("здч", "зч"));
        letterReplacements.add(new Pair<>("plx", "px"));
        letterReplacements.add(new Pair<>("стл", "сл"));
        letterReplacements.add(new Pair<>("cnk", "ck"));
        letterReplacements.add(new Pair<>("стб", "сб"));
        letterReplacements.add(new Pair<>("cn,", "c,"));
        letterReplacements.add(new Pair<>("cn<", "c<"));
        letterReplacements.add(new Pair<>("стн", "сн"));
        letterReplacements.add(new Pair<>("cny", "cy"));
        letterReplacements.add(new Pair<>("стг", "сг"));
        letterReplacements.add(new Pair<>("cnu", "cu"));
        letterReplacements.add(new Pair<>("лнц", "нц"));
        letterReplacements.add(new Pair<>("kyw", "yw"));
        letterReplacements.add(new Pair<>("рдц", "рц"));
        letterReplacements.add(new Pair<>("hlw", "hw"));
        letterReplacements.add(new Pair<>("рдч", "рч"));
        letterReplacements.add(new Pair<>("hlx", "hx"));
        letterReplacements.add(new Pair<>("хг", "г"));
        letterReplacements.add(new Pair<>("[u", "u"));
        letterReplacements.add(new Pair<>("{u", "u"));
        letterReplacements.add(new Pair<>("тс", "ц"));
        letterReplacements.add(new Pair<>("tc", "w"));
        letterReplacements.add(new Pair<>("дц", "ц"));
        letterReplacements.add(new Pair<>("lw", "w"));
        letterReplacements.add(new Pair<>("сч", "щ"));
        letterReplacements.add(new Pair<>("cx", "o"));
        letterReplacements.add(new Pair<>("жч", "щ"));
        letterReplacements.add(new Pair<>(";x", "o"));
        letterReplacements.add(new Pair<>(":x", "o"));
    }

    public static String normalize(String word) {
        StringBuilder codeBuilder = new StringBuilder(word.toLowerCase());
        int indexOf;
        while ((indexOf = codeBuilder.indexOf("/")) != -1)
            codeBuilder.delete(indexOf, indexOf + 1);
        while ((indexOf = codeBuilder.indexOf("-")) != -1)
            codeBuilder.delete(indexOf, indexOf + 1);
        // упрощение последовательности одинаковых гласных и согласных
        for (int i = 0; i < codeBuilder.length() - 1; ++i)
            if (codeBuilder.charAt(i) == codeBuilder.charAt(i + 1))
                codeBuilder.deleteCharAt(i--);
        // замены
        for (Pair<String, String> i: letterReplacements)
            while (codeBuilder.indexOf(i.getKey()) != -1)
                codeBuilder.replace(codeBuilder.indexOf(i.getKey()), codeBuilder.indexOf(i.getKey()) + i.getKey().length(), i.getValue());
        return codeBuilder.toString();
    }

    // преобразование слова (по пересекающимся фонетическим классам)
    public static ArrayList<ArrayList<Character>> deepTransform(String word) {
        ArrayList<ArrayList<Character>> phoneCode = new ArrayList<>();
        String normalized = normalize(word);
        // фонетический код
        for (int i = 0; i < normalized.length(); ++i)
            phoneCode.add(phoneticClasses.getOrDefault(normalized.charAt(i), unknownCharacter));
        return phoneCode;
    }
}