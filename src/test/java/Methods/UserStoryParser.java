package Methods;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

public class UserStoryParser {
    public static void parseUserStory(String filePath) {
        try {
            XWPFDocument document = new XWPFDocument(new FileInputStream(new File(filePath)));

            String acceptanceCriteria = "";

            for (XWPFParagraph paragraph : document.getParagraphs()) {
                String text = paragraph.getText();
                if (text.startsWith("User Story:")) {
                    GlobalData.userStory = text.replace("User Story:", "").trim();
                } else if (text.startsWith("Acceptance Criteria:")) {
                    acceptanceCriteria = text.replace("Acceptance Criteria:", "").trim();
                }
            }

            System.out.println("User Story: " + GlobalData.userStory );
            System.out.println("Acceptance Criteria: " + acceptanceCriteria);

            // Extract any other necessary details from the document

            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

       // return GlobalData.userStory;
    }
}

