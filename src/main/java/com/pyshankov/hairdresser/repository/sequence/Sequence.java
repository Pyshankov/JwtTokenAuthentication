package com.pyshankov.hairdresser.repository.sequence;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by pyshankov on 1/18/17.
 */
@Document(collection = Sequence.COLLECTION_NAME)
public class Sequence {
    public static final String COLLECTION_NAME = "sequences";

    @Id
    private String id;
    private Long sequence;

    public Sequence() {
    }

    public Sequence(String id, Long sequence) {
        this.id = id;
        this.sequence = sequence;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getSequence() {
        return sequence;
    }

    public void setSequence(Long sequence) {
        this.sequence = sequence;
    }
}