package com.ey.challenge.gt;

import com.ey.challenge.gt.counter.VisitsCounterTest;
import com.ey.challenge.gt.counter.VisitsToPeriodsConverterImplTest;
import com.ey.challenge.gt.parser.TextToVisitConverterImplTest;
import com.ey.challenge.gt.reader.VisitsResourceReaderTest;
import com.ey.challenge.gt.util.StreamUtilTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        StreamUtilTest.class,
        TextToVisitConverterImplTest.class,
        VisitsCounterTest.class,
        VisitsToPeriodsConverterImplTest.class,
        VisitsResourceReaderTest.class
})
public class MainAppTest {
}
