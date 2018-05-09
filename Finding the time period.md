Finding the time period.
========================

The task
--------

This project is a solution to a challenge task of finding a period of most visits.
The input data consists of lines of the form "hh:mm,hh:mm". Where "hh:mm" is the time (hour and minutes).
Each line is interpreted as a beginning and ending times of some visit, separated with comma.
The task is to find the period where the number of visits where maximal and print it in the form "hh:mm - hh:mm, N",
where N is the required number of visits.

The project structure
---------------------

### General layout

The project layout is a standard one, known from maven/gradle.
The solution sources are located in `src/main/java`, whereas the test sources are located `src/test/java`
along with test resources in `src/test/resources`.
Gradle was chosen as a build tool here.

### Sources

The main application class (the entry point) is `com.ey.challenge.gt.MainApp`.
It makes use of other classes located at various packages.
These packages are subpackages of `com.ey.challenge.gt`.
The subpackage `reader` contains classes to read raw data.
The next subpackage is `parser` that contains classes to convert raw data into a model,
thus using another subpackage - `model`.
The last subpackage used is `counter`, where we can find classes to manipulate the model data to obtain the required result.
Throughout the classes, the `util` package is also used.
It allows to manipulate Java 8 streams in a manner similar to that of Scala,
i.e. to manipulate streams as per the functional paradigms, distinguishing its head and tail parts.
A lot of routines convert input data into some outputs, so they are abstracted by `Processor` interface.

### Unit tests

Unit tests are prepared for jUnit 4 framework.
They are collected into a suite in `com.ey.challenge.gt.MainAppTest` class.
Appropriate entry can be found in `build.gradle` file (a Gradle's build file for this project).

Algorithm
---------

The algorithm starts with reading raw data from a given file and converting it into a stream of `VisitPeriod` vehicles.
Instead of file, class resources can also be used, but this needs a usage of different reader implementation (it is used, however, in unit tests).
Each `VisitPeriod` contains the beginning and ending times of a particular visit in its fields, respectively, `from` and `to`.

With `VisitsCounter`, the stream of `VisitPeriod`'s is then converted into a stream of `VisitsAtTime`'s.
Every `VisitsAtTime` represents a point in time that starts a new period of different number of simultaneous visitors.
It keeps the information of the time when it starts (`time` field) and the number of visitors (`noOfVisits` field).
Every such point in time corresponds to, either, a beginning of a visit or a minute after it ends.
If necessary, two or more consecutive points in time are reduced to one.
This takes place when such neighbouring points correspond to the same number of visits, as there is no need to keep them all
(see `compact` method in `VisitCounter`).

Next, with `VisitsToPeriodsProcessorImpl`, the stream of points in time is converted into a stream of periods of time paired with the number of visitors.
The information on each period is kept in `VisitsWithinPeriod` class.
It contains fields `from`, `to` - for time boundaries (both inclusive), and `noOfVisits` - for the number of concurrent visitors.

Finally, a special finder (see `MaxVisitFinder`) looks for the `VisitsWithinPeriod` in the stream that corresponds to the greatest number of concurrent visits.
When found, the info is flushed to standard output.
