package seedu.address;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.address.commons.core.Config;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Version;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.ConfigUtil;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.Logic;
import seedu.address.logic.LogicManager;
import seedu.address.model.CovigentApp;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyCovigentApp;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.room.ReadOnlyRoomList;
import seedu.address.model.room.RoomList;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.storage.CovigentAppStorage;
import seedu.address.storage.JsonCovigentAppStorage;
import seedu.address.storage.JsonRoomOccupancyStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.Storage;
import seedu.address.storage.StorageManager;
import seedu.address.storage.UserPrefsStorage;
import seedu.address.ui.Ui;
import seedu.address.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(0, 6, 0, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing CovigentApp ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        CovigentAppStorage covigentAppStorage = new JsonCovigentAppStorage(userPrefs.getCovigentAppFilePath());
        JsonRoomOccupancyStorage roomOccupancyStorage = new JsonRoomOccupancyStorage(
                userPrefs.getRoomsOccupiedFilePath());
        storage = new StorageManager(covigentAppStorage, userPrefsStorage, roomOccupancyStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s covigent app and RoomList
     * and {@code userPrefs}. <br>
     * The data from the sample covigent app and covigentApp will be used instead if {@code storage}'s covigent app is
     * not found,or an empty covigent app will be used instead if errors occur when reading {@code storage}'s
     * address book.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyCovigentApp> covigentAppOptional;
        ReadOnlyCovigentApp initialData;
        Optional<ReadOnlyRoomList> readOnlyRoomOccupancy;
        ReadOnlyRoomList initialRoomList;

        try {
            readOnlyRoomOccupancy = storage.readRoomOccupancyStorage();
            initialRoomList = readOnlyRoomOccupancy.orElseGet(SampleDataUtil::getSampleRoomList);
        } catch (DataConversionException e) {
            logger.warning(
                    "Room Data file not in the correct format. Will be starting with an empty CovigentApp");
            initialRoomList = new RoomList();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty CovigentApp");
            initialRoomList = new RoomList();
        }

        try {
            covigentAppOptional = storage.readCovigentApp();
            if (!covigentAppOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample CovigentApp");
            }
            initialData = covigentAppOptional.orElseGet(SampleDataUtil::getSampleCovigentApp);
        } catch (DataConversionException e) {
            logger.warning(
                    "Patient Data file not in the correct format. Will be starting with an empty CovigentApp");
            initialData = new CovigentApp();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty CovigentApp");
            initialData = new CovigentApp();
        }
        return new ModelManager(initialData, userPrefs, initialRoomList);
    }

    private void initLogging(Config config) {
        LogsCenter.init(config);
    }

    /**
     * Returns a {@code Config} using the file at {@code configFilePath}. <br>
     * The default file path {@code Config#DEFAULT_CONFIG_FILE} will be used instead
     * if {@code configFilePath} is null.
     */
    protected Config initConfig(Path configFilePath) {
        Config initializedConfig;
        Path configFilePathUsed;

        configFilePathUsed = Config.DEFAULT_CONFIG_FILE;

        if (configFilePath != null) {
            logger.info("Custom Config file specified " + configFilePath);
            configFilePathUsed = configFilePath;
        }

        logger.info("Using config file : " + configFilePathUsed);

        try {
            Optional<Config> configOptional = ConfigUtil.readConfig(configFilePathUsed);
            initializedConfig = configOptional.orElse(new Config());
        } catch (DataConversionException e) {
            logger.warning("Config file at " + configFilePathUsed + " is not in the correct format. "
                    + "Using default config properties");
            initializedConfig = new Config();
        }

        //Update config file in case it was missing to begin with or there are new/unused fields
        try {
            ConfigUtil.saveConfig(initializedConfig, configFilePathUsed);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }
        return initializedConfig;
    }

    /**
     * Returns a {@code UserPrefs} using the file at {@code storage}'s user prefs file path,
     * or a new {@code UserPrefs} with default configuration if errors occur when
     * reading from the file.
     */
    protected UserPrefs initPrefs(UserPrefsStorage storage) {
        Path prefsFilePath = storage.getUserPrefsFilePath();
        logger.info("Using prefs file : " + prefsFilePath);

        UserPrefs initializedPrefs;
        try {
            Optional<UserPrefs> prefsOptional = storage.readUserPrefs();
            initializedPrefs = prefsOptional.orElse(new UserPrefs());
        } catch (DataConversionException e) {
            logger.warning("UserPrefs file at " + prefsFilePath + " is not in the correct format. "
                    + "Using default user prefs");
            initializedPrefs = new UserPrefs();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty CovigentApp");
            initializedPrefs = new UserPrefs();
        }

        //Update prefs file in case it was missing to begin with or there are new/unused fields
        try {
            storage.saveUserPrefs(initializedPrefs);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }

        return initializedPrefs;
    }

    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting CovigentApp " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Address Book ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
