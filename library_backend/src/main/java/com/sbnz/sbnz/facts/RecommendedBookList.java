package com.sbnz.sbnz.facts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class RecommendedBookList {
    private List<RecommendedBook> recommendedBooks;
    public RecommendedBookList() {
        recommendedBooks = new ArrayList<>();
    }

    public void addBookRecommendation(RecommendedBook book) {
        for(RecommendedBook b: recommendedBooks){
            if(b.getId() == book.getId()) {
                b.setRecommendationPoints(b.getRecommendationPoints() + 1);
                return;
            }
        }
        addRecommendedBook(book);
    }

    public void sortList(){
        Collections.sort(recommendedBooks, (obj1, obj2) -> {
            // Compare integer attribute in descending order
            int intComparison = Integer.compare(obj2.getRecommendationPoints(), obj1.getRecommendationPoints());
            if (intComparison != 0) {
                return intComparison;
            }
            // If integers are the same, compare date attribute in descending order
            return obj2.getPublishDate().compareTo(obj1.getPublishDate());
        });
    }

    public void addRecommendedBook(RecommendedBook book) {
        recommendedBooks.add(book);
    }
}
