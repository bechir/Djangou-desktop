import java.util.Arrays;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;

public class Translator {
    public enum Language {
        Francais, Anglais, Poulaar, Wolof, Soninke
    }

    Dictionary french = new Hashtable();
    Dictionary english = new Hashtable();

    Translator() {
        french.put("hi", "Salut");
        french.put("good", "Bien");

        english.put("good", "Good");
        english.put("hi", "Salut");
    }

    public static String translate(Language source, Language traget, String word)
    {
        switch (source) {
            case Francais:

                break;
            case Anglais:
                break;
            case Poulaar:
                break;
            case Wolof:
                break;
            case Soninke:
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + source);
        }

        return  "Bonjour";
    }

    public static List<String> getLanguages()
    {
        return Arrays.stream(Language.values()).map(Language::name).collect(Collectors.toList());
    }
}
