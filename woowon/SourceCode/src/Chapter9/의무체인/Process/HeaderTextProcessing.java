package Chapter9.의무체인.Process;

import Chapter9.의무체인.ProcessingObject;

public class HeaderTextProcessing extends ProcessingObject<String> {
    @Override
    protected String handleWork(String text) {
        return "From Raoul, Mario and Alan: " + text;
    }
}
