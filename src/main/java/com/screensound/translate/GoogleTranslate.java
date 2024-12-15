package com.screensound.translate;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.translate.Translate;
import com.google.api.services.translate.model.TranslationsListResponse;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

public class GoogleTranslate {
    private static final String APPLICATION_NAME = "ScreenSound-Translator";
    private static final String API_KEY = System.getenv("API_KEY_GOOGLE");

    public static String translateText(String text, String targetLanguage) throws GeneralSecurityException, IOException {
        Translate translate = new Translate.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                GsonFactory.getDefaultInstance(),
                null)
                .setApplicationName(APPLICATION_NAME)
                .build();

        Translate.Translations.List request = translate.new Translations().list(
                Collections.singletonList(text),
                targetLanguage
        );

        request.setKey(API_KEY);

        TranslationsListResponse response = request.execute();
        if (response.getTranslations() != null && !response.getTranslations().isEmpty()) {
            return response.getTranslations().get(0).getTranslatedText();
        }
        return "Erro ao traduzir o texto.";
    }
}
