/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.matrixCodeVerse.model;

import static java.lang.Math.abs;
import static java.lang.Math.exp;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 *
 * @author yogesh
 */
public class NormalDistribution {

    public static final double NORMAL_DISTRIBUTION(double x, double mean, double stdev) {
        if (Double.isNaN(stdev) || stdev == 0) {
            return Double.NaN;
        }
        return cdf(x, mean, stdev)*100;
    }

    // return pdf(x) = standard Gaussian pdf
    private static double pdf(double x) {
        return Math.exp(-x * x / 2) / Math.sqrt(2 * Math.PI);
    }

    // return pdf(x, mu, signma) = Gaussian pdf with mean mu and stddev sigma
    private static double pdf(double x, double mu, double sigma) {
        return pdf((x - mu) / sigma) / sigma;
    }

    // return cdf(z) = standard Gaussian cdf using Taylor approximation
    public static double cdf(double z) {
        if (z < -8.0) {
            return 0.0;
        }
        if (z > 8.0) {
            return 1.0;
        }
        double sum = 0.0, term = z;
        for (int i = 3; sum + term != sum; i += 2) {
            sum = sum + term;
            term = term * z * z / i;
        }
        return 0.5 + sum * pdf(z);
    }

    // return cdf(z, mu, sigma) = Gaussian cdf with mean mu and stddev sigma
    public static double cdf(double z, double mu, double sigma) {
        return cdf((z - mu) / sigma);
    }
    public static double stats_cdf_normal(double x, double mu, double sigma, int which) {
        double d = x - mu;
        return 0.5 * (1 + erf(d / (sqrt(sigma) * sqrt(2))));
    }

    public static double erf(double x) {
        double t = 1 / (1 + 0.5 * abs(x));
        double tau = t * exp(
                -x * x
                - 1.26551223
                + 1.00002368 * t
                + 0.37409196 * pow(t, 2)
                + 0.09678418 * pow(t, 3)
                - 0.18628806 * pow(t, 4)
                + 0.27886807 * pow(t, 5)
                - 1.13520398 * pow(t, 6)
                + 1.48851587 * pow(t, 7)
                - 0.82215223 * pow(t, 8)
                + 0.17087277 * pow(t, 9)
        );
        if (x >= 0) {
            return 1 - tau;
        } else {
            return tau - 1;
        }
    }
    
}
