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
package strawn.evariant.rainsorter.data.qclcdstations;

import java.io.IOException;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author davidstrawn
 */
public class QCWeatherStationLoaderIT {
    
    static List<QCWeatherStationRecord> records;
    
    @BeforeClass
    public static void beforeTest() throws IOException {
        records = QCWeatherStationLoader.loadRecordsFromDisk();
    }
    /**
     * Stations file has 2554 lines, 7 stations don't have WBANs
     */
    @Test
    public void testStationRecordCount() {
        Assert.assertEquals(2546, records.size());
    }
    
}
