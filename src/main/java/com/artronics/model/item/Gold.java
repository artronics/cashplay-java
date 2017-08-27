package com.artronics.model.item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("gold")
public class Gold extends Item {

    @Enumerated(EnumType.STRING)
    private GoldCaret caret;

    public GoldCaret getCaret() {
        return caret;
    }

    public void setCaret(GoldCaret caret) {
        this.caret = caret;
    }
}
