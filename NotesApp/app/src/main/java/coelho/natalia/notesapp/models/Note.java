package coelho.natalia.notesapp.models;

import java.time.OffsetDateTime;
import java.util.UUID;

public class Note {
    private UUID Id;
    private String Text;
    private OffsetDateTime CreatedDateTime;
    private OffsetDateTime ModifiedDateTime;

    public Note(String text) {
        this.Id = UUID.randomUUID();
        this.Text = text;
        this.CreatedDateTime = OffsetDateTime.now();
        this.ModifiedDateTime = OffsetDateTime.now();
    }

    public Note(UUID id, String text, OffsetDateTime createdDateTime, OffsetDateTime modifiedDateTime) {
        this.Id = id;
        this.Text = text;
        this.CreatedDateTime = createdDateTime;
        this.ModifiedDateTime = modifiedDateTime;
    }

    @Override
    public String toString() {
        return "Note{" +
                "Id=" + Id +
                ", Text='" + Text + '\'' +
                ", CreatedDateTime=" + CreatedDateTime +
                ", ModifiedDateTime=" + ModifiedDateTime +
                '}';
    }

    public UUID getId() {
        return Id;
    }

    public String getText() {
        return Text;
    }

    public OffsetDateTime getCreatedDateTime() {
        return CreatedDateTime;
    }

    public OffsetDateTime getModifiedDateTime() {
        return ModifiedDateTime;
    }
}
