/*
 * Copyright 2016 davidstrawn.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package strawn.evariant.rainsorter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.opengis.feature.simple.SimpleFeature;
import strawn.evariant.rainsorter.data.precipitation.PrecipitationRecord;

/**
 * This class represents a MetropolitanStatisticalArea for the purposes of calculating population wetness
 * 
 * It contains:
 *   a list of Weather Stations within the MSA
 *   a SimpleFeature representing the geographical area covered by the MSA
 * 
 * @author davidstrawn
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
