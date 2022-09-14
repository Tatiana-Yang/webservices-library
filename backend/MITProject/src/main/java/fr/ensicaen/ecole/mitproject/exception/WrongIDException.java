package fr.ensicaen.ecole.mitproject.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.Table;
import java.util.Locale;
import java.util.ResourceBundle;

@JsonIgnoreProperties(value = { "cause", "stackTrace", "suppressed" })
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class WrongIDException extends Throwable {
    private String id;
    private String entity;
    private String table;
    // inspired from https://stackoverflow.com/questions/24988491/difference-between-e-getmessage-and-e-getlocalizedmessage
    // and https://stackoverflow.com/questions/24988491/difference-between-e-getmessage-and-e-getlocalizedmessage
    //private static final ResourceBundle labels = ResourceBundle.getBundle("fr.ensicaen.intro_spring_2020.localization.ProjectResourcesTexts");
    private static final ResourceBundle labels = ResourceBundle.getBundle("locale.messages");
    // got it from https://stackoverflow.com/questions/24305512/how-to-get-the-default-resourcebundle-regardless-of-current-default-locale
    //private static final ResourceBundle root_labels = ResourceBundle.getBundle("fr.ensicaen.intro_spring_2020.localization.ProjectResourcesTexts", Locale.ROOT);
    private static final ResourceBundle root_labels = ResourceBundle.getBundle("locale.messages", Locale.ROOT);
    private final static String KEY_WRONG_ID = "wrong_id";

    protected WrongIDException(String id, Class entity) {
        super(root_labels.getString(KEY_WRONG_ID));
        this.id = id;
        if (entity != null) this.entity = entity.getSimpleName();
        Table table = (Table)entity.getAnnotation(Table.class);
        if (table != null) this.table = table.name();
    }

    @Override
    public String getLocalizedMessage() {
        return labels.getString(KEY_WRONG_ID);
    }

    public String getId() {
        return id;
    }

    public String getEntity() {
        return entity;
    }

    public String getTable() {
        return table;
    }

    public static WrongIDException builder(String id, Class clazz) {
        return new WrongIDException(id, clazz);
    }

}
