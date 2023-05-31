package com.sbnz.sbnz.facts;

import com.sbnz.sbnz.model.Rating;

import java.util.List;

public class RecommendationHelper {
    public static double piersonCoefficient(List<Rating> loggedInUserRatings, List<Rating> userRatings, double loggedInUserAvgRating, double userAvgRating) {
        double numerator = 0;
        double userDenominator = 0;
        double loggedInUserDenominator = 0;
        for (Rating loggedInUserRating : (List<Rating>)loggedInUserRatings) {
            for(Rating userRating : (List<Rating>) userRatings) {
                if(userRating.getBook().getId() == loggedInUserRating.getBook().getId()) {
                    numerator += ((userRating.getValue() - userAvgRating) * (loggedInUserRating.getValue() - loggedInUserAvgRating));
                    userDenominator += (Math.pow((userRating.getValue() - userAvgRating), 2));
                    loggedInUserDenominator += (Math.pow((loggedInUserRating.getValue() - loggedInUserAvgRating), 2));
                }
            }
        }
        double denominator = Math.sqrt(userDenominator) * Math.sqrt(loggedInUserDenominator);
        double score = numerator / denominator;
        return score;
    }

    public static double similarBooks(UserRating userRating, List<Rating> similarBookRatings) {
        int totalNumOfSameBookRatings = 0;
        int totalNumOfSameBookSimilarRatings = 0;
        for (Rating rating : similarBookRatings) {
            for (Rating potSimilarRating : rating.getAppUser().getRatings()) {
                if(potSimilarRating.getBook().getId() == userRating.getBook().getId()) {
                    totalNumOfSameBookRatings++;
                    if(Math.abs(potSimilarRating.getValue() - userRating.getValue()) <= 1) {
                        totalNumOfSameBookSimilarRatings++;
                    }
                }
            }
        }

        return totalNumOfSameBookRatings / (totalNumOfSameBookSimilarRatings + Math.pow(10, -6));
    }
}
