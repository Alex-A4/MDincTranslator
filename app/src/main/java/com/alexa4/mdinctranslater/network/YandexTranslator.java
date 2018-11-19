package com.alexa4.mdinctranslater.network;

import android.os.AsyncTask;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

/**
 * Translate the text
 * @author alexa4
 */
public class YandexTranslator {
    private static final String API_KEY = "trnsl.1.1.20181119T161854Z.ebaf45211b68759c.1cfc251482d621c713f8095d4c9428e3a70a5248";

    public static void translateText(String text, String langs) {
        new AsyncTranslate(text, langs).execute();
    }

    private static class AsyncTranslate extends AsyncTask<Void, Void, String> {
        private String mText;
        private String mLangs;

        public AsyncTranslate(String text, String langs) {
            mText = text;
            mLangs = langs;
        }

        @Override
        protected String doInBackground(Void... voids) {
            String translatedText = "";

            //Translating text
            try {
                translatedText = getTranslatedText(mLangs, mText);
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
            System.out.println("translatedText = " + translatedText);
            return translatedText;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        /**
         * Getting the translated text
         * @param langs the languages pair like en-ru - translate from english to russian
         * @param text the text which need translate
         * @return the translated text
         * @throws IOException
         * @throws ParseException
         */
        private static String getTranslatedText(String langs, String text) throws IOException, ParseException {
            String requestUrl = "https://translate.yandex.net/api/v1.5/tr.json/translate?key="
                    + API_KEY + "&lang=" + langs + "&text=" + URLEncoder.encode(text, "UTF-8");

            URL url = new URL(requestUrl);
            HttpsURLConnection httpsConnection = (HttpsURLConnection) url.openConnection();
            httpsConnection.connect();
            int rc = httpsConnection.getResponseCode();

            if (rc == 200) {
                String line = null;
                BufferedReader buffReader = new BufferedReader(new InputStreamReader(httpsConnection.getInputStream()));
                StringBuilder strBuilder = new StringBuilder();
                while ((line = buffReader.readLine()) != null) {
                    strBuilder.append(line);
                    strBuilder.append("\n");
                }

                System.out.println("strBuilder.toString() = " + strBuilder.toString());

                return convertFromJSON(strBuilder.toString());
            }
            return "";
        }


        /**
         * Converting translated text from JSON response
         * @param json the json string which need to parse
         * @return the translated text
         * @throws ParseException
         */
        private static String convertFromJSON(String json) throws ParseException {
            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject) parser.parse(json);
            JSONArray array = (JSONArray) object.get("text");

            StringBuilder sb = new StringBuilder();
            if (array != null)
                for (Object s : array) {
                    sb.append(s.toString());
                    sb.append("\n");
                }

            return sb.toString();
        }
    }
}
