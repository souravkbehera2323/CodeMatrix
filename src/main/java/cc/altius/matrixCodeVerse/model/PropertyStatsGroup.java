/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.matrixCodeVerse.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author akil
 */
public class PropertyStatsGroup implements Serializable {

    private final Map<Integer, PropertyStats> questionStats;
    private final Map<Integer, PropertyStats> dimensionStats;
    private final Map<Integer, PropertyStats> traitStats;
    private final Map<Integer, PropertyStats> aspectStats;

    public static final int QUESTION_STATS = 1;
    public static final int DIMENSION_STATS = 2;
    public static final int TRAIT_STATS = 3;
    public static final int ASPECT_STATS = 4;

    public PropertyStatsGroup() {
        this.questionStats = new HashMap<>();
        this.dimensionStats = new HashMap<>();
        this.traitStats = new HashMap<>();
        this.aspectStats = new HashMap<>();
    }

    public PropertyStats getStats(int type, int id) {
        switch (type) {
            case QUESTION_STATS:
                return this.questionStats.get(id);
            case DIMENSION_STATS:
                return this.dimensionStats.get(id);
            case TRAIT_STATS:
                return this.traitStats.get(id);
            case ASPECT_STATS:
                return this.aspectStats.get(id);
            default:
                return null;
        }
    }

    public void addResponseForQuestion(QuestionResponseBasic questionResponse) {
        if (this.questionStats.get(questionResponse.getId()) != null) {
            this.questionStats.get(questionResponse.getId()).addScore(questionResponse);
        } else {
            PropertyStats propertyStats = new PropertyStats();
            propertyStats.addScore(questionResponse);
            this.questionStats.put(questionResponse.getId(), propertyStats);
        }
    }

    public void addResponseForDimension(QuestionResponseBasic questionResponse) {
        if (this.dimensionStats.get(questionResponse.getId()) != null) {
            this.dimensionStats.get(questionResponse.getId()).addScore(questionResponse);
        } else {
            PropertyStats propertyStats = new PropertyStats();
            propertyStats.addScore(questionResponse);
            this.dimensionStats.put(questionResponse.getId(), propertyStats);
        }
    }

    public void addResponseForTrait(QuestionResponseBasic questionResponse) {
        if (this.traitStats.get(questionResponse.getId()) != null) {
            this.traitStats.get(questionResponse.getId()).addScore(questionResponse);
        } else {
            PropertyStats propertyStats = new PropertyStats();
            propertyStats.addScore(questionResponse);
            this.traitStats.put(questionResponse.getId(), propertyStats);
        }
    }

    public void addResponseForAspect(QuestionResponseBasic questionResponse) {
        if (this.aspectStats.get(questionResponse.getId()) != null) {
            this.aspectStats.get(questionResponse.getId()).addScore(questionResponse);
        } else {
            PropertyStats propertyStats = new PropertyStats();
            propertyStats.addScore(questionResponse);
            this.aspectStats.put(questionResponse.getId(), propertyStats);
        }
    }

    public Double getScore(int type, int id) {
        switch (type) {
            case QUESTION_STATS:
                return this.questionStats.get(id).getScore(id);
            case DIMENSION_STATS:
                return this.dimensionStats.get(id).getScore(id);
            case TRAIT_STATS:
                return this.traitStats.get(id).getScore(id);
            case ASPECT_STATS:
                return this.aspectStats.get(id).getScore(id);
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return "Stats{" + "\n\tdimensionStats=\n\t" + dimensionStats + ", \n\ttraitStats=\n\t" + traitStats + ", \n\taspectStats=\n\t" + aspectStats + "\n}";
    }
}
