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
import strawn.evariant.rainsorter.data.precipitation.PrecipitationRecord;
import strawn.evariant.rainsorter.data.qclcdstations.QCWeatherStationRecord;

/**
 *
 * @author davidstrawn
 */
public class WeatherStation {
    
    public ArrayList<PrecipitationRecord> readings;
    public String wbanID;
    public double latitude;
    public double longitude;
    
    public WeatherStation(QCWeatherStationRecord record) {
        wbanID = record.wban;
        latitude = record.latitude;
        longitude = record.longitude;
        readings = new ArrayList();
    }
    
    public void addPrecipitationRecord(PrecipitationRecord record) {
        readings.add(record);
    }
    
}
