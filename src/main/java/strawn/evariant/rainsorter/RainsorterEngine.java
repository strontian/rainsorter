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
import java.util.HashMap;
import java.util.List;
import org.opengis.feature.simple.SimpleFeature;
import strawn.evariant.rainsorter.data.msapop.MSAPopulationRecord;
import strawn.evariant.rainsorter.data.qclcdstations.QCWeatherStationRecord;

/**
 *
 * @author davidstrawn
 */
public class RainsorterEngine {
    
    private HashMap<String, WeatherStation> wbanToStationMap;
    private List<MetropolitanStatisticalArea> metroAreas;
    List<SimpleFeature> regions;
    
    public RainsorterEngine(List<MSAPopulationRecord> populations, List<QCWeatherStationRecord> stationRecords, List<SimpleFeature> regions) {
        //store the list of Features
        this.regions = regions;
        //create a map of weather stations, keyed by WBAN
        wbanToStationMap = DataOrganizationMethods.mapWBANToStation(stationRecords);
        //create the MetropolitanStatisticalAreas from the MSAPopulationRecord records
        metroAreas = DataOrganizationMethods.getMSAList(populations);
        //add geotools regions to MSAs
        //map stations to MSAs
        //add weather readings to stations
        //drop stations with no data
        //calculate population wetness of each MSA
        //sort regions by population wetness
    }
    
    
    
}
