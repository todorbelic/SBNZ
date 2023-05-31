package com.sbnz.sbnz.facts;

import com.sbnz.sbnz.enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class GenreOfInterest {
    private Genre genre;

    public GenreOfInterest(Genre genre) {
        this.genre = genre;
    }
}
