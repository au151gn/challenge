package com.ey.challenge.gt;

import com.ey.challenge.gt.counter.VisitsCounterTest;
import com.ey.challenge.gt.counter.VisitsToPeriodsProcessorImplTest;
import com.ey.challenge.gt.parser.TextToVisitProcessorImplTest;
import com.ey.challenge.gt.reader.VisitsResourceReaderTest;
import com.ey.challenge.gt.util.StreamUtilTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        StreamUtilTest.class,
        TextToVisitProcessorImplTest.class,
        VisitsCounterTest.class,
        VisitsToPeriodsProcessorImplTest.class,
        VisitsResourceReaderTest.class
})
public class MainAppTest {
}
