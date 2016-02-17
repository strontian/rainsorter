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

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import strawn.evariant.rainsorter.data.qclcdstations.QCWeatherStationRecord;
import strawn.evariant.rainsorter.unused.MSACountyRecord;

/**
 *
 * @author davidstrawn
 */
public class RainsorterEngine {
    
    private HashMap<String, WeatherStation> wbanToStationMap;
    private List<MetropolitanStatisticalArea> metroAreas;
    
    public RainsorterEngine(Collection<MSACountyRecord> areas, List<QCWeatherStationRecord> stationRecords) {
        wbanToStationMap = mapWBANToStation(stationRecords);
        //create MSA from Population records
        //add geotools regions to MSAs
        //map stations to MSAs
        //add weather readings to stations
    }
    
    public static HashMap<String, WeatherStation> mapWBANToStation(List<QCWeatherStationRecord> stationRecords) {
        HashMap<String, WeatherStation> stationsByWBAN = new HashMap();
        for(QCWeatherStationRecord station : stationRecords) {
            stationsByWBAN.put(station.wban, new WeatherStation(station));
        }
        return stationsByWBAN;
    }
    
}
