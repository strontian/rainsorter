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
package strawn.evariant.rainsorter.calculation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import strawn.evariant.rainsorter.data.precipitation.PrecipitationRecord;
import strawn.evariant.rainsorter.data.qclcdstations.QCWeatherStationRecord;

/**
 *
 * @author davidstrawn
 */
public class MetropolitanStatisticalAreaTest {
    
    WeatherStation station1;
    WeatherStation station2;
    WeatherStation station3;
    
    MetropolitanStatisticalArea msa;
    
    @Before
    public void before() {
        
        QCWeatherStationRecord stationRecord1 = new QCWeatherStationRecord("wban1", 0d, 0d);
        QCWeatherStationRecord stationRecord2 = new QCWeatherStationRecord("wban2", 0d, 0d);
        QCWeatherStationRecord stationRecord3 = new QCWeatherStationRecord("wban3", 0d, 0d);
        
        station1 = new WeatherStation(stationRecord1);
        station2 = new WeatherStation(stationRecord2);
        station3 = new WeatherStation(stationRecord3);
        
        msa = new MetropolitanStatisticalArea("Austin", "123", 300);
        
        PrecipitationRecord station1Record1 = new PrecipitationRecord("", 0, .5, false, false);
        PrecipitationRecord station1Record2 = new PrecipitationRecord("", 0, .5, false, false);
        station1.addPrecipitationRecord(station1Record1);
        station1.addPrecipitationRecord(station1Record2);
        PrecipitationRecord station2Record1 = new PrecipitationRecord("", 0, 1.5, false, false);
        PrecipitationRecord station2Record2 = new PrecipitationRecord("", 0, .5, false, false);
        station2.addPrecipitationRecord(station2Record1);
        station2.addPrecipitationRecord(station2Record2);
        PrecipitationRecord station3Record1 = new PrecipitationRecord("", 0, 1.0, false, false);
        PrecipitationRecord station3Record2 = new PrecipitationRecord("", 0, 10.0, false, true);
        station3.addPrecipitationRecord(station3Record1);
        station3.addPrecipitationRecord(station3Record2);
        
    }
    
    @Test
    public void testBasicWetnessCalculation() {
        msa.addStation(station1);
        Assert.assertEquals(300, msa.getPopulationWetness());
    }
    
    @Test
    public void testAveragedWetnessCalculation() {
        msa.addStation(station1);
        msa.addStation(station2);
        Assert.assertEquals(450, msa.getPopulationWetness());
    }
    
    @Test
    public void testSuspectReadingsExclusionInWetnessCalculation() {
        msa.addStation(station1);
        msa.addStation(station2);
        msa.addStation(station3);
        Assert.assertEquals(450, msa.getPopulationWetness());
    }
    
}
