package com.ey.challenge.gt.parser;

import com.ey.challenge.gt.Processor;
import com.ey.challenge.gt.model.VisitPeriod;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * A class converting an input data line into a model instance.
 * @author Paweł Ryszawa
 */
public class TextToVisitProcessorImpl implements Processor<VisitPeriod, String> {

    private static final String REGEX_TWO_DATES = "(\\d{2}:\\d{2}),(\\d{2}:\\d{2})";

    private final Pattern pCommaSep = Pattern.compile(",");

    /**
     * Converts a ine of text containing a pair of hours HH:MM separated by comma
     * into an instance of {@link VisitPeriod}.
     * @param line A given text line.
     * @return VisitPeriod instance.
     */
    @Override
    public VisitPeriod process(String line) {
        if (StringUtils.isEmpty(line)) {
            return null;
        }
        if (!line.matches(REGEX_TWO_DATES)) {
            throw new ParserException("Incorrect data found."); //TODO: explain more...
        }
        Stream<String> strTxtTime = pCommaSep.splitAsStream(line);
        Stream<LocalTime> strLocTime = strTxtTime.map(s -> LocalTime.parse(s, DateTimeFormatter.ISO_LOCAL_TIME));
        LocalTime[] locTime = strLocTime.toArray(LocalTime[]::new);
        VisitPeriod vp = new VisitPeriod(locTime[0], locTime[1]);
        return vp;
    }

}
