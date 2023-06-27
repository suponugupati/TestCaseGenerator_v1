package Methods;
import java.io.IOException;

public class UserStoryLoadedHandler implements UserStoryLoadedCallback {

    @Override
    public void onUserStoryLoaded() {
        // User story is loaded, continue with the process

        TestCaseTemplateWriter writer = new TestCaseTemplateWriter();
        try {
            writer.modifyTestCaseTemplate();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}