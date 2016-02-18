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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.opengis.feature.simple.SimpleFeature;
import strawn.evariant.rainsorter.data.precipitation.PrecipitationRecord;

/**
 * This class represents a MetropolitanStatisticalArea for the purposes of calculating population wetness.
 * 
 * It contains:
 *   a list of Weather Stations within the MSA
 *   a SimpleFeature representing the geographical area covered by the MSA
 * 
 * @author David Strawn
 */
public class MetropolitanStatisticalArea {
    
    public String msaName;
    public String CBSACode;
    public int population;
    public List<WeatherStation> stations;
    private SimpleFeature geographicalRegionFeature;
    private double wetnessRating;
    private boolean isWetnessComputed;
    private int traceCount;
    
    public MetropolitanStatisticalArea(String msaName, String CBSAcode, int population) {
        this.msaName = msaName;
        this.CBSACode = CBSAcode;
        this.population = population;
        isWetnessComputed = false;
        traceCount = 0;
        wetnessRating = 0d;
        stations = new ArrayList();
    }
    
    public void addRegionFeature(SimpleFeature feature) {
        this.geographicalRegionFeature = feature;
    }
    
    public void addStation(WeatherStation station) {
        stations.add(station);
    }
    
    public void calculatePopulationWetness() {
        double totalInchHours = 0d;
        wetnessRating = 0d;
        traceCount = 0;
        
        if(!stations.isEmpty()) {
            for (int i = 0; i < stations.get(0).readings.size(); i++) {
                int participatingStationsCount = stations.size();
                double inchesThisHour = 0d;
                for (WeatherStation station : stations) {
                    PrecipitationRecord precip = station.readings.get(i);
                    if(precip.isSuspect) {
                        participatingStationsCount--;
                    }else if(precip.isTrace) {
                        traceCount++;
                    }else {
                        inchesThisHour += precip.inchesOfRain;
                    }
                }
                if(participatingStationsCount > 0) {
                    double averageInches = inchesThisHour/(double)participatingStationsCount;
                    totalInchHours += averageInches;
                }
            }
        }
        wetnessRating = totalInchHours * (double)population;
        isWetnessComputed = true;
    }
    
    public int getWetnessRating() {
        if(!isWetnessComputed) {
            calculatePopulationWetness();
        }
        return (int)wetnessRating;
    }
    
    public void dropEmptyStations() {
        Iterator<WeatherStation> iter = stations.iterator();
        while(iter.hasNext()) {
            WeatherStation station = iter.next();
            if(station.readings.isEmpty()) {
                iter.remove();
            }
        }
    }
    
    public boolean isWetnessComputed() {
        return isWetnessComputed;
    }
    
}
