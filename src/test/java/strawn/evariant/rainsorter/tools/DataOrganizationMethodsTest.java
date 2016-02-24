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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import strawn.evariant.rainsorter.data.msapop.MSAPopulationRecord;
import strawn.evariant.rainsorter.data.qclcdstations.QCWeatherStationRecord;
import strawn.evariant.rainsorter.calculation.MetropolitanStatisticalArea;
import strawn.evariant.rainsorter.calculation.WeatherStation;

/**
 *
 * @author davidstrawn
 */
public class DataOrganizationMethodsTest {
    
    QCWeatherStationRecord station1;
    QCWeatherStationRecord station2;
    QCWeatherStationRecord station3;
    ArrayList<QCWeatherStationRecord> stationList;
    Map<String, WeatherStation> testMap;
    
    MSAPopulationRecord msaRecord1;
    MSAPopulationRecord msaRecord2;
    MSAPopulationRecord msaRecord3;
    ArrayList<MSAPopulationRecord> msaRecordList;
    ArrayList<MetropolitanStatisticalArea> msaList;
    
    @Before
    public void setup() {
        
        //station setup
        station1 = new QCWeatherStationRecord("wban1", 0d, 0d);
        station2 = new QCWeatherStationRecord("wban2", 0d, 0d);
        station3 = new QCWeatherStationRecord("wban3", 0d, 0d);
        stationList = new ArrayList();
        stationList.add(station1);
        stationList.add(station2);
        stationList.add(station3);
        testMap = DataOrganizationMethods.createWBANToStationMap(stationList);
        
        //msa setup
        msaRecord1 = new MSAPopulationRecord("Austin", "CBSA1", 100);
        msaRecord2 = new MSAPopulationRecord("San Antonio", "CBSA2", 50);
        msaRecord3 = new MSAPopulationRecord("San Francisco", "CBSA3", 3);
        msaRecordList = new ArrayList();
        msaRecordList.add(msaRecord1);
        msaRecordList.add(msaRecord2);
        msaRecordList.add(msaRecord3);
        msaList = DataOrganizationMethods.createMSAList(msaRecordList);
    }
    
    @Test
    public void testCreateStationMapSize() {
        Assert.assertEquals(3, testMap.size());
    }
    
    @Test
    public void testCreateStationMapKeys() {
        for(int i = 1; i < 4; i++) {
            String testKey = "wban" + i;
            Assert.assertNotNull(testMap.get(testKey));
        }
    }
    
    @Test
    public void testMsaListSize() {
        Assert.assertEquals(3, msaList.size());
    }
    
    @Test
    public void testMsaListCodes() {
        HashSet<String> codes = new HashSet();
        for(MetropolitanStatisticalArea msa : msaList) {
            codes.add(msa.CBSACode);
        }
        Assert.assertTrue(codes.contains("CBSA1"));
        Assert.assertTrue(codes.contains("CBSA2"));
        Assert.assertTrue(codes.contains("CBSA3"));
    }
    
    @Test
    public void testMsaPopulation() {
        int populationSum = 0;
        for(MetropolitanStatisticalArea msa : msaList) {
            populationSum += msa.population;
        }
        Assert.assertEquals(153, populationSum);
    }
    
}
