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
package strawn.evariant.rainsorter;

import strawn.evariant.rainsorter.engine.WeatherStation;
import strawn.evariant.rainsorter.tools.DataOrganizationMethods;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import strawn.evariant.rainsorter.data.precipitation.PrecipitationLoader;
import strawn.evariant.rainsorter.data.precipitation.PrecipitationRecord;
import strawn.evariant.rainsorter.data.qclcdstations.QCWeatherStationLoader;
import strawn.evariant.rainsorter.data.qclcdstations.QCWeatherStationRecord;

/**
 * This class is for testing Station and Precipitation data
 */
public class StationDataAndPrecipitationIT {
    
    List<PrecipitationRecord> precipRecords;
    List<QCWeatherStationRecord> stations;
    HashMap<String, WeatherStation> stationsByWBAN;
    public static final int TOTAL_DAYTIME_HOURS = 527;
    
    @Before
    public void setup() throws IOException {
        precipRecords = PrecipitationLoader.loadRecordsFromDisk();
        stations = QCWeatherStationLoader.loadRecordsFromDisk();
        stationsByWBAN = DataOrganizationMethods.createWBANToStationMap(stations);
    }
    
    /**
     * Makes sure that for every record of rainfall, there is a station with a matching WBAN
     */
    @Test
    public void testAllPrecipitationCanBeAttributedToHBAN() {
        Set<String> keys = stationsByWBAN.keySet();
        for(PrecipitationRecord record : precipRecords) {
            if(!keys.contains(record.wbanCode)) {
                Assert.fail();
            }
        }
    }
    /**
     * Make sure we either have complete data or no data for every station
     */
    @Test
    public void testAllStationsHaveCompletePrecipitationData() {
        for(PrecipitationRecord record : precipRecords) {
            stationsByWBAN.get(record.wbanCode).addPrecipitationRecord(record);
        }
        for(WeatherStation station : stationsByWBAN.values()) {
            int readingsCount = station.readings.size();
            if(readingsCount != 0 && readingsCount != 527) {
                Assert.fail();
            }
        }
    }
    
}
