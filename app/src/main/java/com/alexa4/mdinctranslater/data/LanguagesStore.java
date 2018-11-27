package com.alexa4.mdinctranslater.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Singleton store to collect Yandex languages pairs
 * @author alexa4
 */
public class LanguagesStore {
    //Pairs of languages
    private ArrayList<LanguageInfo> mPairs;

    //The language from which need translate
    private LanguageInfo mLanguageFrom;
    public LanguageInfo getLanguageFrom() {
        return mLanguageFrom;
    }
    public void setLanguageFrom(LanguageInfo languageFrom) {
        mLanguageFrom = languageFrom;
    }

    //The language to which need translate
    private LanguageInfo mLanguageTo;
    public LanguageInfo getLanguageTo() {
        return mLanguageTo;
    }
    public void setLanguageTo(LanguageInfo languageTo) {
        mLanguageTo = languageTo;
    }

    //Singleton instance of store
    private static LanguagesStore sStore;

    private LanguagesStore() {
        mPairs = new ArrayList<>();
        fillList();
        mLanguageFrom = getLangByName("Русский");
        mLanguageTo = getLangByName("Английский");
    }

    public static LanguagesStore getStore() {
        if (sStore == null)
            sStore = new LanguagesStore();

        return sStore;
    }

    public ArrayList<LanguageInfo> getPairs() {
        return mPairs;
    }


    private void fillList() {
        mPairs.addAll(
                Arrays.asList(
                        new LanguageInfo("Английский", "en"),
                        new LanguageInfo("Азербайджанский", "az"),
                        new LanguageInfo("Албанский", "sq"),
                        new LanguageInfo("Амхарский", "am"),
                        new LanguageInfo("Арабский", "ar"),
                        new LanguageInfo("Армянский", "hy"),
                        new LanguageInfo("Басксий", "eu"),
                        new LanguageInfo("Башкирский", "ba"),
                        new LanguageInfo("Белорусский", "be"),
                        new LanguageInfo("Болгарский", "bg"),
                        new LanguageInfo("Боснийский", "bs"),
                        new LanguageInfo("Венгерский", "hu"),
                        new LanguageInfo("Вьетнамский", "vi"),
                        new LanguageInfo("Голландский", "nl"),
                        new LanguageInfo("Греческий", "el"),
                        new LanguageInfo("Грузинский", "ka"),
                        new LanguageInfo("Датский", "da"),
                        new LanguageInfo("Испанский", "es"),
                        new LanguageInfo("Итальянский", "it"),
                        new LanguageInfo("Иврит", "he"),
                        new LanguageInfo("Индонезийский", "id"),
                        new LanguageInfo("Исландский", "is"),
                        new LanguageInfo("Корейский", "ko"),
                        new LanguageInfo("Китайский", "zh"),
                        new LanguageInfo("Казахский", "kk"),
                        new LanguageInfo("Киргизский", "ky"),
                        new LanguageInfo("Каталанский", "ca"),
                        new LanguageInfo("Лаосский", "lo"),
                        new LanguageInfo("Латынь", "la"),
                        new LanguageInfo("Латышский", "lv"),
                        new LanguageInfo("Люксембургский", "lb"),
                        new LanguageInfo("Малайсийский", "mg"),
                        new LanguageInfo("Малайский", "ms"),
                        new LanguageInfo("Мальтийский", "mt"),
                        new LanguageInfo("Македонский", "mk"),
                        new LanguageInfo("Немецкий", "de"),
                        new LanguageInfo("Непальский", "ne"),
                        new LanguageInfo("Норвежский", "no"),
                        new LanguageInfo("Персидский", "fa"),
                        new LanguageInfo("Польский", "pl"),
                        new LanguageInfo("Португалский", "pt"),
                        new LanguageInfo("Русский", "ru"),
                        new LanguageInfo("Румынский", "ro"),
                        new LanguageInfo("Сербский", "sr"),
                        new LanguageInfo("Словацкий", "sk"),
                        new LanguageInfo("Словенский", "sl"),
                        new LanguageInfo("Таджикский", "tg"),
                        new LanguageInfo("Тайский", "th"),
                        new LanguageInfo("Татарский", "tt"),
                        new LanguageInfo("Турецкий", "tr"),
                        new LanguageInfo("Удмуртский", "udm"),
                        new LanguageInfo("Украинский", "uk"),
                        new LanguageInfo("Узбекский", "uz"),
                        new LanguageInfo("Урду", "ur"),
                        new LanguageInfo("Финский", "fi"),
                        new LanguageInfo("Французский", "fr"),
                        new LanguageInfo("Хинди", "hi"),
                        new LanguageInfo("Хорватский", "hr"),
                        new LanguageInfo("Шведский", "sv"),
                        new LanguageInfo("Чешский", "cs"),
                        new LanguageInfo("Шотландский", "gd"),
                        new LanguageInfo("Эстонский", "et"),
                        new LanguageInfo("Эсперанто", "eo"),
                        new LanguageInfo("Яванский", "jv"),
                        new LanguageInfo("Японский", "ja")
                ));

        Collections.sort(mPairs, LanguageInfo.getNameComparator());
    }


    /**
     * Getting language by its name
     * @param name the name of language
     * @return the language or default lang (russian)
     */
    public LanguageInfo getLangByName(String name) {
        for (LanguageInfo info: mPairs)
            if (name.equals(info.getLanguageName()))
                return info;

        return mPairs.get(0);
    }


    /**
     * Swap the target languages
     */
    public void swapTargetLanguages() {
        LanguageInfo temp = mLanguageFrom;
        mLanguageFrom = mLanguageTo;
        mLanguageTo = temp;
    }

    /**
     * Create the string pair of translated languages
     * for example en-ru
     * @return the codes pair
     */
    public String createLangToTranslate() {
        return mLanguageFrom.getLanguageCode() + "-" + mLanguageTo.getLanguageCode();
    }
}
