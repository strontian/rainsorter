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
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import strawn.evariant.rainsorter.data.precipitation.PrecipitationLoader;
import strawn.evariant.rainsorter.data.precipitation.PrecipitationRecord;
import strawn.evariant.rainsorter.data.qclcdstations.QCWeatherStationLoader;
import strawn.evariant.rainsorter.data.qclcdstations.QCWeatherStationRecord;

/**
 *
 * @author davidstrawn
 * 
 * This class is for testing Station and Precipitation data
 * 
 */
public class StationDataAndPrecipitationIT {
    
    List<PrecipitationRecord> precipRecords;
    List<QCWeatherStationRecord> stations;
    HashMap<String, QCWeatherStationRecord> stationsByWBAN;
    
    @BeforeClass
    public void setup() throws IOException {
        precipRecords = PrecipitationLoader.loadRecordsFromDisk();
        stations = QCWeatherStationLoader.loadRecordsFromDisk();
        stationsByWBAN = new HashMap();
        for(QCWeatherStationRecord station : stations) {
            stationsByWBAN.put(station.wban, station);
        }
    }
    /**
     * Makes sure that for every record of rainfall, there is a station with a matching WBAN
     */
    @Test
    public void allHBANsPresent() {
        Set<String> keys = stationsByWBAN.keySet();
        for(PrecipitationRecord record : precipRecords) {
            if(!keys.contains(record.wbanId)) {
                Assert.fail();
            }
        }
    }
}
