package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.model.task.Task;

/**
 * Panel containing the list of room with tasks.
 */
public class RoomTaskListPanel extends UiPart<Region> {

    private static final String FXML = "RoomTaskListPanel.fxml";

    private final Logger logger = LogsCenter.getLogger(RoomTaskListPanel.class);

    @FXML
    private ScrollPane roomScrollPane;
    @FXML
    private ListView<Task> taskListView;

    /**
     * Creates a {@code RoomTaskListPanel} with the given {@code ObservableList}.
     */
    public RoomTaskListPanel(ObservableList<Task> roomTaskList) {
        super(FXML);
        updateDetailsIfChanged(roomTaskList);
        taskListView.setItems(roomTaskList);
        taskListView.setCellFactory(listView -> new TaskListViewCell());

        updateDetailsIfChanged(roomTaskList);
    }


    //@@author w-yeehong
    /**
     * Attaches a listener to {@code roomTaskList}.
     *
     * @param roomTaskList The task list to listen to for changes.
     */
    private void updateDetailsIfChanged(ObservableList<Task> roomTaskList) {
        roomTaskList.addListener(new ListChangeListener<Task>() {
            @Override
            public void onChanged(Change<? extends Task> change) {
                while (change.next()) {
                    int indexOfChange = change.getFrom();
                    Index index = Index.fromZeroBased(indexOfChange);
                    logger.info("Changes detected in Task " + index.getOneBased()
                            + ". Updating RoomTaskListPanel...");
                    taskListView.setCellFactory(listView -> new TaskListViewCell());
                }
            }
        });
    }
    //@@author w-yeehong

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Task} using a {@code TaskCard}.
     */
    class TaskListViewCell extends ListCell<Task> {
        @Override
        protected void updateItem(Task task, boolean empty) {
            super.updateItem(task, empty);

            if (empty || task == null) {
                setGraphic(null);
                setText(null);
            } else {
                int roomNumber = task.getTaskRoomNumber();
                setGraphic(new TaskCard(roomNumber, task)
                        .getRoot());
            }
        }
    }
}
