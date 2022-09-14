package fr.ensicaen.ecole.mitproject.exception;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Locale;
import java.util.ResourceBundle;

@JsonIgnoreProperties(value = { "cause", "stackTrace", "suppressed" })
@ResponseStatus(value = HttpStatus.CONFLICT)
public class AlreadyInDatabaseException extends Exception{

    private static final ResourceBundle labels = ResourceBundle.getBundle("fr.ensicaen.tuto_spring_2021.localization.ProjectResourcesTexts");
    private static final ResourceBundle root_labels = ResourceBundle.getBundle("fr.ensicaen.tuto_spring_2021.localization.ProjectResourcesTexts", Locale.ROOT);

    private String localizedMessage;
    private String additionalInformation = "";

    protected AlreadyInDatabaseException(String message_resource_key) {
        super(message_resource_key != null ? root_labels.getString(message_resource_key) : "");
        if (message_resource_key != null)
            this.localizedMessage = labels.getString(message_resource_key);
    }
    protected AlreadyInDatabaseException(String message_resource_key, String additionalInformation) {
        super(message_resource_key != null ? root_labels.getString(message_resource_key) : additionalInformation);
        if (message_resource_key != null) {
            this.localizedMessage = labels.getString(message_resource_key);
            this.additionalInformation = additionalInformation;
        }
    }

    public static AlreadyInDatabaseException builder(String message_resource_key) {
        return new AlreadyInDatabaseException(message_resource_key);
    }
    public static AlreadyInDatabaseException builder(String message_resource_key, String additionalInformation) {
        return new AlreadyInDatabaseException(message_resource_key, additionalInformation);
    }

    @JsonGetter("localizedMessage")
    @Override
    public String getLocalizedMessage() {
        return this.localizedMessage;
    }

    @JsonGetter("additionalInformation")
    public String getAdditionalInformation() {
        return additionalInformation;
    }
}
