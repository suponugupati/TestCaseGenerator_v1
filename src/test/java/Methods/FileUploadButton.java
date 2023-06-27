package Methods;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import static Methods.UserStoryParser.parseUserStory;

public class FileUploadButton {
    public void openFileUploadWindow(UserStoryLoadedCallback callback) {
        // Declare an array to store the selected file path

        JFrame frame = new JFrame("File Upload ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        // Create a heading label
        JLabel headingLabel = new JLabel("File Upload", SwingConstants.CENTER);
        headingLabel.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(headingLabel, BorderLayout.NORTH);

        // Create a label to display the selected file path
        JLabel filePathLabel = new JLabel("Select a User story file to Upload");
        frame.add(filePathLabel, BorderLayout.CENTER);

        // Create a panel for the button
        JPanel buttonPanel = new JPanel();
        frame.add(buttonPanel, BorderLayout.SOUTH);

        JButton uploadButton = new JButton("Choose File");
        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
                    // Perform file upload or further processing here
                    parseUserStory(selectedFile.getAbsolutePath());
                    // Call the callback method to indicate that the user story is loaded
                    callback.onUserStoryLoaded();
                    // Close the window after uploading the file
                    frame.dispose();

                }
            }
        });

        frame.add(uploadButton);
        frame.pack();
        frame.setVisible(true);
    }
}
