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
package strawn.evariant.rainsorter.data.precipitation;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author davidstrawn
 */
public class PrecipitationLoaderIT {
    
    static List<PrecipitationRecord> records;
    
    @BeforeClass
    public static void beforeClass() throws IOException {
        records = PrecipitationLoader.loadRecordsFromDisk();
    }
    
    @Test
    public void testRecordCount() {
        Assert.assertEquals(1216316, records.size());
    }
    /**
     * Test that all hours in the precipitation dataset are after 7am.
     */
    @Test
    public void testPrecipHours() {
        HashSet<Integer> observedHours = new HashSet(); 
        for(PrecipitationRecord record : records) {
            observedHours.add(record.hour);
        }
        for(Integer i : observedHours) {
            Assert.assertTrue(i > 7);
            Assert.assertTrue(i <= 24);
        }
    }
    
}
