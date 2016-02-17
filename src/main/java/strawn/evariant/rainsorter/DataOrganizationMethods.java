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

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.opengis.feature.simple.SimpleFeature;
import strawn.evariant.rainsorter.data.msapop.MSAPopulationRecord;
import strawn.evariant.rainsorter.data.qclcdstations.QCWeatherStationRecord;

/**
 *
 * @author davidstrawn
 */
public class DataOrganizationMethods {
    
    /**
     * converts a list of WeatherStationRecords into a map of WeatherStations keyed by WBAN
     * 
     * @param stationRecords List of records to convert to map
     * @return Map of WeatherStations keyed by WBAN
     */
    public static HashMap<String, WeatherStation> mapWBANToStation(List<QCWeatherStationRecord> stationRecords) {
        HashMap<String, WeatherStation> stationsByWBAN = new HashMap();
        for(QCWeatherStationRecord station : stationRecords) {
            stationsByWBAN.put(station.wban, new WeatherStation(station));
        }
        return stationsByWBAN;
    }
    
    /**
     * converts a list of MSAPopulation Records into a list of MetropolitanStatisticalArea
     * @param populationRecords
     * @return list of MetropolitanStatisticalArea
     */
    public static List<MetropolitanStatisticalArea> getMSAList(List<MSAPopulationRecord> populationRecords) {
        ArrayList<MetropolitanStatisticalArea> toReturn = new ArrayList();
        for(MSAPopulationRecord record : populationRecords) {
            toReturn.add(new MetropolitanStatisticalArea(record.msaName, record.CBSACode, record.population));
        }
        return toReturn;
    }
    
    /**
     * Converts a list of Regions into a map of regions keyed by their CBSA code
     * 
     * @param featureList list of features to convert
     * @return map of regions keyed by their CBSA code
     */
    public static Map<String, SimpleFeature> getCBSAToFeaturesMap(ArrayList<SimpleFeature> featureList) throws IOException {
        HashMap<String, SimpleFeature> toReturn = new HashMap();
        for (SimpleFeature feature : featureList) {
            String cbsaId = feature.getProperty("CBSAFP").getValue().toString();
            toReturn.put(cbsaId, feature);
        }
        return toReturn;
    }
    
}
