package serie07;

import util.TPTester;

public final class TP07Test {
    private TP07Test() {
        // rien
    }
    public static void main(String[] args) {
        TPTester t = new TPTester(
                serie07.StdSlotModelTestCons.class
        );
        int exitValue = t.runAll();
        if (exitValue == 0) {
            t = new TPTester(
                    serie07.StdSlotModelTest.class
            );
            exitValue = t.runAll();
        }
        t = new TPTester(
                serie07.StdSwellingModelTest.class
        );
        exitValue = t.runAll();
        System.exit(exitValue);
    }
}
