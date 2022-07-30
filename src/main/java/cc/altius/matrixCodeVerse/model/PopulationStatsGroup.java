/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.matrixCodeVerse.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author akil
 */
public class PopulationStatsGroup implements Serializable {

    private final Map<Integer, PopulationStats> questionStats;
    private final Map<Integer, PopulationStats> dimensionStats;
    private final Map<Integer, PopulationStats> traitStats;
    private final Map<Integer, PopulationStats> aspectStats;

    public static final int QUESTION_STATS = 1;
    public static final int DIMENSION_STATS = 2;
    public static final int TRAIT_STATS = 3;
    public static final int ASPECT_STATS = 4;
    
    public static final String[] typeName = new String[] {"", "Question", "Dimension", "Trait", "Aspect"};

    public PopulationStatsGroup() {
        this.questionStats = new HashMap<>();
        this.dimensionStats = new HashMap<>();
        this.traitStats = new HashMap<>();
        this.aspectStats = new HashMap<>();
    }

    public PopulationStats getStats(int type, int id) {
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

    public void setStats(int type, int id, PopulationStats stats) {
        switch (type) {
            case QUESTION_STATS:
                this.questionStats.put(id, stats);
                break;
            case DIMENSION_STATS:
                this.dimensionStats.put(id, stats);
                break;
            case TRAIT_STATS:
                this.traitStats.put(id, stats);
                break;
            case ASPECT_STATS:
                this.aspectStats.put(id, stats);
                break;
            default:
                break;
        }

    }

    public void setStats(int type, List<PopulationStatsWithId> statsList) {
        for (PopulationStatsWithId ps : statsList) {
            setStats(type, ps.getId(), (PopulationStats) ps);
        }
    }

    @Override
    public String toString() {
        return "Stats{" + "\n\tdimensionStats=\n\t" + dimensionStats + ", \n\ttraitStats=\n\t" + traitStats + ", \n\taspectStats=\n\t" + aspectStats + "\n}";
    }
}
