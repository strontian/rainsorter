/**
 * Copyright (C) 2016  David Strawn
 * 
 * This file is part of rainsorter.
 *
 * rainsorter is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * rainsorter is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.

 * You should have received a copy of the GNU Lesser General Public License
 * along with rainsorter.  If not, see http://www.gnu.org/licenses.
 */
package strawn.evariant.rainsorter.engine;

import java.util.Comparator;

/**
 *
 * Comparator for sorting a Collection of type MetropolitanStatisticalArea by population wetness.
 * Using Collections.sort with this comparator returns a list in descending order
 * 
 * @author David Strawn
 */
public class MSAWetnessComparator implements Comparator<MetropolitanStatisticalArea> {
    /**
     * Positive when o2 is greater than o1
     * 
     * @param o1
     * @param o2
     * @return 
     */
    @Override
    public int compare(MetropolitanStatisticalArea o1, MetropolitanStatisticalArea o2) {
        double wetnessDifference = o2.getWetnessRating() - o1.getWetnessRating();
        if(wetnessDifference > 0) {
            return 1;
        }else if(wetnessDifference < 0) {
            return -1;
        }
        return 0;
    }
    
}
