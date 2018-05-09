package com.ey.challenge.gt;

import com.ey.challenge.gt.counter.MaxVisitFinder;
import com.ey.challenge.gt.model.VisitPeriod;
import com.ey.challenge.gt.model.VisitsWithinPeriod;
import com.ey.challenge.gt.reader.VisitsFileReader;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * The main class for the application. This is where it starts.
 * @author Paweł Ryszawa
 */
public class MainApp {

    private static void doFromVisitPeriods(Stream<VisitPeriod> vps) {
        Processor<Optional<VisitsWithinPeriod>, Stream<VisitPeriod>> finder = new MaxVisitFinder();
        Optional<VisitsWithinPeriod> vpMax = finder.process(vps);
        if (vpMax.isPresent()) {
            System.out.print(vpMax.get());
        }
    }

    /**
     * The entry point to the application.
     * @param args Command line arguments.
     */
    public static void main(String args[]) {
        if (args.length == 1) {
            String fileName = args[0];
            Processor<Stream<VisitPeriod>, String> reader = new VisitsFileReader();
            doFromVisitPeriods(reader.process(fileName));
        } else {
            System.err.println("Please provide the input file path and name as a single parameter.");
        }
    }

}
