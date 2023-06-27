package Generator;

import Methods.*;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        FileUploadButton fileUploadButton = new FileUploadButton();

        //upload the User story file and parse the User story
        fileUploadButton.openFileUploadWindow(new UserStoryLoadedHandler());

    }


}
