package Chapter9.의무체인.Process;

import Chapter9.의무체인.ProcessingObject;

public class SpellCheckerProcessing extends ProcessingObject<String> {
    @Override
    protected String handleWork(String text) {
        return text.replaceAll("labda", "lambda");
    }
}
