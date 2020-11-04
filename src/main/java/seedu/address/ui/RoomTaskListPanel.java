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

      //  populatePanel(roomTaskList);
        updateDetailsIfChanged(roomTaskList);
    }


    //@@author w-yeehong
    /**
     * Attaches a listener to {@code roomList}, repopulating the panel whenever
     * there are removals in the list of rooms.
     *
     * Fixes the issue of the panel not refreshing when a room is removed from the
     * list of rooms.
     *
     * @param taskList The room list to listen to for changes.
     */
    /*
    private void updateDetailsIfChanged(ObservableList<Task> taskList) {
        taskList.addListener(new ListChangeListener<Room>() {
            @Override
            public void onChanged(Change<? extends Room> change) {
                while (change.next()) {
                    int indexOfChange = change.getFrom();
                    Index index = Index.fromZeroBased(indexOfChange);
                    logger.info("Changes detected in Room " + index.getOneBased()
                            + ". Updating RoomTaskListPanel...");
                    resetPanel(taskList);
                }
            }
        });
    }
     */
    //@@author w-yeehong

    /**
     * Attaches a listener to {@code roomTaskList}. {@code roomTaskList} should be the original task list of
     * the room, i.e. not the filtered list.
     *
     * Fixes the issue of task index not refreshing when a new task is added and is not
     * in the filtered list.
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
                            + ". Updating RoomTaskDetailsPanel...");
                    taskListView.setCellFactory(listView -> new TaskListViewCell());
                }
            }
        });
    }


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
                int roomNumber = 1; //TODO: task class needs to have room number
                int taskNumber = 2; //TODO: No more index
                        //room.getFilteredTasks().getSourceIndex(getIndex()) + 1;
                int totalNumberOfTasksInRoom = 3;
                        //room.getReadOnlyTasks().size();
                setGraphic(new TaskCard(roomNumber, taskNumber, totalNumberOfTasksInRoom, task)
                        .getRoot());
            }
        }
    }

}
