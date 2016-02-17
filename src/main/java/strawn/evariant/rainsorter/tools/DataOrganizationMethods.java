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
package strawn.evariant.rainsorter.tools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.opengis.feature.simple.SimpleFeature;
import strawn.evariant.rainsorter.data.msapop.MSAPopulationRecord;
import strawn.evariant.rainsorter.data.qclcdstations.QCWeatherStationRecord;
import strawn.evariant.rainsorter.engine.MetropolitanStatisticalArea;
import strawn.evariant.rainsorter.engine.WeatherStation;

/**
 * A collection of methods for converting from data structure to another. 
 * These could be generalized using generics and reflection at a later time.
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
    public static HashMap<String, WeatherStation> createWBANToStationMap(List<QCWeatherStationRecord> stationRecords) {
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
    public static List<MetropolitanStatisticalArea> createMSAList(List<MSAPopulationRecord> populationRecords) {
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
    public static Map<String, SimpleFeature> createCBSAToFeaturesMap(ArrayList<SimpleFeature> featureList) throws IOException {
        HashMap<String, SimpleFeature> toReturn = new HashMap();
        for (SimpleFeature feature : featureList) {
            String cbsaId = feature.getProperty("CBSAFP").getValue().toString();
            toReturn.put(cbsaId, feature);
        }
        return toReturn;
    }
    
    /**
     * Converts a list of MetropolitanStatisticalAreas into a Map with values of the same type, keyed by CBSA code
     * @param msas list of MSAs to convert
     * @return a map of MSAs keyed by CBSA code
     */
    public static HashMap<String, MetropolitanStatisticalArea> createCBSACodeToMSAMap(List<MetropolitanStatisticalArea> msas) {
        HashMap<String, MetropolitanStatisticalArea> msasByCBSPCode = new HashMap();
        for(MetropolitanStatisticalArea msa : msas) {
            msasByCBSPCode.put(msa.CBSACode, msa);
        }
        return msasByCBSPCode;
    }
    
}
